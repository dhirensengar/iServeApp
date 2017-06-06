package com.cears.serviceapp.Activities;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.cears.serviceapp.AppGlobal.AppConstant;
import com.cears.serviceapp.AppGlobal.AppGlobal;
import com.cears.serviceapp.GeneralHelpers;
import com.cears.serviceapp.Pojo.DataPojo;
import com.cears.serviceapp.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class SplashActivity extends BaseActivity {
    private String json;
    int hasPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarIcon(R.mipmap.icon);
        setActionBarTitle("" + getResources().getString(R.string.app_name));


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //  if (AppGlobal.getBooleanPreference(SplashActivity.this, AppConstant.PREF_ISLOGIN)) {
                Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.e("Login Status ", AppGlobal.getBooleanPreference(SplashActivity.this, AppConstant.PREF_ISLOGIN) + "");
                startActivity(i);
                finish();
               /* } else {
                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Log.e("Login Status", AppGlobal.getBooleanPreference(SplashActivity.this, AppConstant.PREF_ISLOGIN) + "");
                    startActivity(i);
                    finish();
                }*/

            }
        }, 1 * 1000);


        //   GeneralHelpers.setIsSurveyInCompleted(this, false);

        //  json = GeneralHelpers.loadJSONFromAsset(SplashActivity.this, "question");


     /*   if (checkDeviceAPI()) {

            if (isPermisssionsGranted()) {

                setData();
            } else {

                requestPermission();
            }
        } else {

            setData();

        }
*/
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_splash;
    }

    private void setData() {
        try {
            JSONArray jsonArray = new JSONArray(json);
            ArrayList<DataPojo> dataList = new ArrayList<DataPojo>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                DataPojo pojo = new DataPojo();
                pojo.setId(jsonObject.getString("id"));
                pojo.setQuestionType(jsonObject.getString("type"));
                pojo.setIsPhoto(jsonObject.getString("photo"));
                pojo.setCode("" + jsonObject.getString("code"));
                pojo.setGroup("" + jsonObject.getString("group_name"));
                pojo.setQuestion("" + jsonObject.getString("question"));
                pojo.setIsMajor("" + jsonObject.getString("major"));
                pojo.setIsMinor("" + jsonObject.getString("minor"));
                pojo.setIsCleaning("" + jsonObject.getString("cleaning"));
                pojo.setIsCentrifugal("" + jsonObject.getString("Chiller_Centrifugal"));
                pojo.setIsRecipAirCooled("" + jsonObject.getString("Chiller_Recip_Air_Cooled"));
                pojo.setIsRecipWaterCooled("" + jsonObject.getString("Chiller_Recip_Water_Cooled"));
                pojo.setIsScrewAirCooled("" + jsonObject.getString("Chiller_Screw_Air_Cooled"));
                pojo.setIsScrewWaterCooled("" + jsonObject.getString("Chiller_Screw_Water_Cooled"));
                pojo.setIsRTU("" + jsonObject.getString("RTU"));
                pojo.setIsSplits("" + jsonObject.getString("Splits"));
                pojo.setIsMiniSplits("" + jsonObject.getString("Mini_Splits"));
                pojo.setIsAHU("" + jsonObject.getString("AHU"));
                pojo.setIsFAHU("" + jsonObject.getString("FAHU"));
                pojo.setIsFCU("" + jsonObject.getString("FCU"));
                pojo.setIsPump("" + jsonObject.getString("Pump"));
                pojo.setResponse1("" + jsonObject.getString("R1"));
                pojo.setResponse2("" + jsonObject.getString("R2"));
                pojo.setResponse3("" + jsonObject.getString("R3"));
                pojo.setResponse4("" + jsonObject.getString("R4"));
                pojo.setAnswered(false);
                pojo.setResponse5("" + jsonObject.getString("R5"));
                pojo.setUnit("" + jsonObject.getString("unit"));
                Log.e("", "" + jsonObject.getString("unit"));
                pojo.setUnitFixed("" + jsonObject.getString("unit"));
                dataList.add(pojo);

            }

            GeneralHelpers.setIsSurveyInCompleted(this, false);
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putParcelableArrayListExtra("DataList", dataList);
            startActivity(intent);
            finish();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @SuppressLint("NewApi")
    private boolean isPermisssionsGranted() {
        if (
                checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return true;
        else
            return false;
    }

    //Requesting permission
    @SuppressLint("NewApi")
    private void requestPermission() {

        hasPermission = checkSelfPermission(Manifest.permission.CAMERA);

        if (hasPermission != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.CAMERA}, 111);
            return;
        }

        hasPermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasPermission != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 222);
            return;
        }


    }


    //Checking SDK Version
    private boolean checkDeviceAPI() {
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= android.os.Build.VERSION_CODES.M) {
            return true;


        } else {
            return false;
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        switch (requestCode) {

            case 111: {

                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 222);
                else
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, 111);
                break;

            }

            case 222: {

                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //   setData();
                } else {

                    requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, 222);

                }

                return;

            }

        }


    }


}

