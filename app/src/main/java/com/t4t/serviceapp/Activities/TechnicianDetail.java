package com.t4t.serviceapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.t4t.serviceapp.GeneralHelpers;
import com.t4t.serviceapp.Pojo.DataPojo;
import com.t4t.serviceapp.R;

import java.util.ArrayList;

/**
 * Created by VIKAS SAHU on 09/01/17.
 */

public class TechnicianDetail extends BaseActivity {

    private TextView mTechNameTextView, mSiteNameTextView, mCustomerNameTextView;
    private ArrayList<DataPojo> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarIcon(R.mipmap.icon);
        setActionBarTitle("" + getResources().getString(R.string.app_name));
        init();
        dataList = getIntent().getParcelableArrayListExtra("DataList");


    }

    //Next Click
    public void onNextClickTechnicianDetail(View view) {
        if (validateTextFields()) {

            GeneralHelpers.setTechnicianName(TechnicianDetail.this, "" + mTechNameTextView.getText().toString().trim());
            GeneralHelpers.setSiteName(TechnicianDetail.this, "" + mSiteNameTextView.getText().toString().trim());
            GeneralHelpers.setCustomerName(TechnicianDetail.this, "" + mCustomerNameTextView.getText().toString().trim());

            Intent intent = new Intent(TechnicianDetail.this, MainActivity.class);
            intent.putParcelableArrayListExtra("DataList", dataList);
            startActivity(intent);
            finish();

        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_tech_detail;
    }

    private void init() {

        mTechNameTextView = (TextView) findViewById(R.id.editTextTechName);
        mSiteNameTextView = (TextView) findViewById(R.id.editTextSiteName);
        mCustomerNameTextView = (TextView) findViewById(R.id.editTextCustomerName);
    }


    private boolean validateTextFields() {
        boolean valid = false;
        String techName = mTechNameTextView.getText().toString().trim();
        String siteName = mSiteNameTextView.getText().toString().trim();
        String customerName = mCustomerNameTextView.getText().toString().trim();

        if (TextUtils.isEmpty(techName) && TextUtils.isEmpty(siteName) && TextUtils.isEmpty(customerName)) {
            mTechNameTextView.setError("Required.");
            mSiteNameTextView.setError("Required.");
            mCustomerNameTextView.setError("Required.");
            valid = false;
        } else if (TextUtils.isEmpty(techName) && TextUtils.isEmpty(siteName)) {
            mTechNameTextView.setError("Required.");
            mSiteNameTextView.setError("Required.");
            mCustomerNameTextView.setError(null);
        } else if (TextUtils.isEmpty(techName) && TextUtils.isEmpty(customerName)) {
            mTechNameTextView.setError("Required.");
            mCustomerNameTextView.setError("Required.");
            mSiteNameTextView.setError(null);
            valid = false;
        } else if (TextUtils.isEmpty(customerName) && TextUtils.isEmpty(siteName)) {
            mSiteNameTextView.setError("Required.");
            mCustomerNameTextView.setError("Required.");
            mTechNameTextView.setError(null);
            valid = false;
        } else {
            valid = true;
        }
        return valid;
    }


}
