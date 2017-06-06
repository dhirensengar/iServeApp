package com.cears.serviceapp.Service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.cears.serviceapp.AppGlobal.AppConstant;
import com.cears.serviceapp.AppGlobal.AppGlobal;
import com.cears.serviceapp.Database.GeneralDatabase;
import com.cears.serviceapp.models.QuestionResponse;
import com.cears.serviceapp.webservice.ApiManager;
import com.cears.serviceapp.webservice.RestClient;

import java.util.HashMap;

import es.dmoral.toasty.Toasty;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Radhe on 8/11/2016.
 */
public class MyService extends Service {
    private boolean isRunning;
    private Context context;
    private Thread backgroundThread;
    private ApiManager apiManager;

    GeneralDatabase db1 = new GeneralDatabase(MyService.this);

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public ApiManager getApiManager() {
        if (apiManager == null) {
            apiManager = new ApiManager(MyService.this);
        }
        return apiManager;
    }

    @Override
    public void onCreate() {

        this.context = this;
        this.isRunning = false;
        this.backgroundThread = new Thread(myTask);
    }


    private Runnable myTask = new Runnable() {
        public void run() {

            Log.e("iServ", "RunServiceStart");

            getquestionAPI();
            //   isRunning = false;
        }
    };

    @Override
    public void onDestroy() {
        this.isRunning = false;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!this.isRunning) {
            this.isRunning = true;
            this.backgroundThread.start();
        }
        Log.e("iServ", " ServiceStart ");
        return START_STICKY;
    }

    public void getquestionAPI() {

        if (AppGlobal.isNetwork(getApplicationContext())) {

            HashMap<String, Object> auth = new HashMap<String, Object>();
            auth.put("id", AppGlobal.getStringPreference(MyService.this, AppConstant.PREF_ADMINID));
            auth.put("token", AppGlobal.getStringPreference(MyService.this, AppConstant.PREF_TOKEN));

            final HashMap<String, Object> hashMap = new HashMap<String, Object>();
            hashMap.put("service", "get_question");
            hashMap.put("request", "");
            hashMap.put("auth", auth);

            Log.e("SurveyAct  ", "Question Reqst  " + hashMap + "");


            new RestClient(MyService.this).getInstance().get().getquestions(hashMap).enqueue(new Callback<QuestionResponse>() {
                @Override
                public void onResponse(Call<QuestionResponse> call, Response<QuestionResponse> response) {

                    try {
                        if (response.body() != null) {
                            if (response.body().getSuccess().equals("1")) {

                                db1.deleteQuestions();

                                Toasty.success(MyService.this, " " + response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                for (int i = 0; i < response.body().getData().size(); i++) {
                                    db1.insertQuestions(response.body().getData().get(i));
                                }
                                db1.close();

                            } else {
                                Toasty.error(getApplicationContext(), " " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toasty.error(getApplicationContext(), "Null Body", Toast.LENGTH_SHORT).show();
                            Log.e("TAG", "iServ response: Null Body ");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<QuestionResponse> call, Throwable t) {
                    Log.e("lookinside", "failed" + t.toString());
                }
            });

        } else {
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
            Log.e("TAG", "Connection : No Internet Connection ");
        }
    }


    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                okhttp3.MultipartBody.FORM, descriptionString);
    }


}
