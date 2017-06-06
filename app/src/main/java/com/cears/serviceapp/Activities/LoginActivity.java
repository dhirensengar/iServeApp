package com.cears.serviceapp.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.cears.serviceapp.AppGlobal.AppConstant;
import com.cears.serviceapp.AppGlobal.AppGlobal;
import com.cears.serviceapp.GeneralHelpers;
import com.cears.serviceapp.NewDatabase.NewGeneralDatabase;
import com.cears.serviceapp.R;
import com.cears.serviceapp.models.LoginResponse;
import com.cears.serviceapp.webservice.RestClient;

import java.util.HashMap;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by VIKAS SAHU on 21/12/16.
 */

public class LoginActivity extends BaseActivity {
    //  private ArrayList<DataPojo> dataList;
    private EditText mEmailEditText, mPasswordEditText;
    private ProgressDialog mProgressDialog;
    NewGeneralDatabase db = new NewGeneralDatabase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarIcon(R.mipmap.icon);
        setActionBarTitle("" + getResources().getString(R.string.app_name));
  /*      GeneralHelpers.setCurrentFragment(LoginActivity.this, 0);
        dataList = getIntent().getParcelableArrayListExtra("DataList");*/
        init();
        //    db.deletePreQuestion();

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_login;
    }

    public void onLoginClick(View view) {

        validation();

      /*  if (validateForm()) {

            if (GeneralHelpers.isOnline(this)) {
                findViewById(R.id.textViewError).setVisibility(View.GONE);
                mProgressDialog = new ProgressDialog(this);
                mProgressDialog.setMessage("Signing...");
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                mProgressDialog.setCancelable(false);
                mProgressDialog.show();

                if (GeneralHelpers.isOnline(this)) {
                    CallWebServiceInBackground serviceInBackground = new CallWebServiceInBackground(LoginActivity.this, this);

                    String urlParameters = null;

                    try {
                        urlParameters = "userid=" + mEmailEditText.getText().toString().trim() +
                                "&password=" + mPasswordEditText.getText().toString().trim();

                    } catch (Exception e) {

                        e.printStackTrace();

                    }

                    serviceInBackground.execute(getString(R.string.API_LOGIN), urlParameters);
                }
            }

        }*/

    }

    public void onPasswordVisibleClick(View view) {
        if (mPasswordEditText.getInputType() == 129) {
            mPasswordEditText.setInputType(InputType.TYPE_CLASS_TEXT);
        } else if (mPasswordEditText.getInputType() == InputType.TYPE_CLASS_TEXT) {
            mPasswordEditText.setInputType(129);
        }
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = mEmailEditText.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            mEmailEditText.setError("Required.");
            valid = false;
        } else {
            mEmailEditText.setError(null);
        }

        String password = mPasswordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(password)) {
            mPasswordEditText.setError("Required.");
            valid = false;
        } else {
            mPasswordEditText.setError(null);
        }

        if (!GeneralHelpers.isValidEmail(mEmailEditText.getText().toString().trim())) {
            mEmailEditText.setError("Invalid email id.");
            valid = false;
        } else {
            mEmailEditText.setError(null);
        }

        return valid;
    }

    private void init() {
        mEmailEditText = (EditText) findViewById(R.id.editTextLogin);
        mPasswordEditText = (EditText) findViewById(R.id.editTextPassword);
//        mEmailEditText.setText("tech@gmail.com");
//        mPasswordEditText.setText("tech");
        //  findViewById(R.id.textViewError).setVisibility(View.GONE);

    }

/*    @Override
    public void onRemoteCallComplete(String jsonFromWSCall) {


        try {
            mProgressDialog.cancel();
            JSONObject jsonObject = new JSONObject(jsonFromWSCall);
            String response = jsonObject.get("response").toString();
            if (response.equals("success")) {

                Toast.makeText(getBaseContext(), "Successfully logged-in", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(LoginActivity.this, LandingpageActivity.class);
                intent.putParcelableArrayListExtra("DataList", dataList);
                startActivity(intent);
                finish();
            } else {
                GeneralHelpers.showPopUp(this, "" + response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/


    public void loginApi() {

        if (AppGlobal.isNetwork(getApplicationContext())) {

//            LoginJson model = new LoginJson("login", new LoginRequestModel(mEmailEditText.getText().toString(), mPasswordEditText.getText().toString()));

            AppGlobal.showProgressDialog(LoginActivity.this, "Loading");

            HashMap<String, String> requestHashMap = new HashMap<String, String>();
            requestHashMap.put("email_id", mEmailEditText.getText().toString());
            requestHashMap.put("password", mPasswordEditText.getText().toString());

            HashMap<String, Object> loginRequest = new HashMap<String, Object>();
            loginRequest.put("service", "login");
            loginRequest.put("request", requestHashMap);

            Log.e("TAG", "iServ Login " + new Gson().toJson(loginRequest));

            new RestClient(LoginActivity.this).getInstance().get().login(loginRequest).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                    try {
                        if (response.body() != null) {
                            if (response.body().getSuccess().equals("1")) {

                                AppGlobal.hideProgressDialog(LoginActivity.this);

                                AppGlobal.setStringPreference(LoginActivity.this, response.body().getData().getUserInfo().getAdminId(), AppConstant.PREF_ADMINID);

                                Log.e("TAg", "ID  " + AppGlobal.getStringPreference(LoginActivity.this, AppConstant.PREF_ADMINID));
                                Log.e("TAG", "Token  " + AppGlobal.getStringPreference(LoginActivity.this, AppConstant.PREF_TOKEN));

                                AppGlobal.setStringPreference(LoginActivity.this, response.body().getToken(), AppConstant.PREF_TOKEN);
                                AppGlobal.setStringPreference(LoginActivity.this, response.body().getSecretLogId(), AppConstant.PREF_SECRET_LOG_ID);
                                AppGlobal.setStringPreference(LoginActivity.this, response.body().getData().getUserInfo().getUsername(), AppConstant.PREF_USERNAME);
                                AppGlobal.setStringPreference(LoginActivity.this, response.body().getData().getUserInfo().getMobile(), AppConstant.PREF_MOBILE);
                                AppGlobal.setStringPreference(LoginActivity.this, response.body().getData().getUserInfo().getStatus(), AppConstant.PREF_STATUS);
                                AppGlobal.setStringPreference(LoginActivity.this, response.body().getData().getUserInfo().getRoleGroup(), AppConstant.PREF_ROLEGROUP);
                                AppGlobal.setStringPreference(LoginActivity.this, response.body().getData().getUserInfo().getEmailId(), AppConstant.PREF_USEREMAIL);
                                AppGlobal.setStringPreference(LoginActivity.this, response.body().getData().getUserInfo().getPassword(), AppConstant.PREF_PASSWORD);

                                AppGlobal.setBooleanPreference(LoginActivity.this, true, AppConstant.PREF_ISLOGIN);

                                Intent i = new Intent(LoginActivity.this, LandingpageActivity.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(i);
                                finish();

                            } else {
                                AppGlobal.hideProgressDialog(LoginActivity.this);
                                Toasty.error(getApplicationContext(), " " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            AppGlobal.hideProgressDialog(LoginActivity.this);
                            Toasty.error(getApplicationContext(), "Null Body", Toast.LENGTH_SHORT).show();
                            Log.e("TAG", "iServ response: Null Body ");
                        }
                    } catch (Exception e) {
                        AppGlobal.hideProgressDialog(LoginActivity.this);
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Log.e("lookinside", "failed" + t.toString());
                }
            });

        } else {
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
            Log.e("TAG", "Connection : No Internet Connection ");
        }
    }

    public void validation() {
        if (mEmailEditText.getText().toString().length() == 0) {
            mEmailEditText.setError("Required.");
        } else if (mPasswordEditText.getText().toString().length() == 0) {
            mPasswordEditText.setError("Required.");
        } else {
            loginApi();
        }

    }
}
