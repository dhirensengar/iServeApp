package com.t4t.serviceapp.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.t4t.serviceapp.GeneralHelpers;
import com.t4t.serviceapp.JsonHelper.CallWebServiceInBackground;
import com.t4t.serviceapp.JsonHelper.GetJSONListener;
import com.t4t.serviceapp.Pojo.DataPojo;
import com.t4t.serviceapp.R;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by VIKAS SAHU on 21/12/16.
 */

public class LoginActivity extends BaseActivity implements GetJSONListener {
    private ArrayList<DataPojo> dataList;
    private static final String TAG = "EmailPassword";
    private EditText mEmailEditText, mPasswordEditText;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarIcon(R.mipmap.icon);
        setActionBarTitle("" + getResources().getString(R.string.app_name));
        GeneralHelpers.setCurrentFragment(LoginActivity.this, 0);
        dataList = getIntent().getParcelableArrayListExtra("DataList");
        init();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_login;
    }

    public void onLoginClick(View view) {

        if (validateForm()) {
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
        }

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
        findViewById(R.id.textViewError).setVisibility(View.GONE);

    }

    @Override
    public void onRemoteCallComplete(String jsonFromWSCall) {


        try {
            mProgressDialog.cancel();
            JSONObject jsonObject = new JSONObject(jsonFromWSCall);
            String response = jsonObject.get("response").toString();
            if (response.equals("success")) {

                Toast.makeText(getBaseContext(), "Successfully logged-in", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putParcelableArrayListExtra("DataList", dataList);
                startActivity(intent);
                finish();
            } else {
                GeneralHelpers.showPopUp(this, "" + response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
