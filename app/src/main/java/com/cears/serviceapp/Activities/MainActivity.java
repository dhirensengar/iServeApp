package com.cears.serviceapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.cears.serviceapp.Fragments.NewJobFragment;
import com.cears.serviceapp.Fragments.SavedJobFragment;
import com.cears.serviceapp.Fragments.SubmittedFragment;
import com.cears.serviceapp.GeneralHelpers;
import com.cears.serviceapp.R;
import com.cears.serviceapp.models.PreQuestionModel;
import com.cears.serviceapp.utils.SlidingTabLayout;

import java.io.Serializable;
import java.util.List;

import static com.cears.serviceapp.Fragments.NewJobFragment.ALL_PRE_QUESTIONS;
import static com.cears.serviceapp.Fragments.NewJobFragment.EQUIPMENT_NUMBER_CONSTANT;
import static com.cears.serviceapp.Fragments.NewJobFragment.JOB_TICKET_CONSTANT;
import static com.cears.serviceapp.Fragments.NewJobFragment.SELECTED_EQUIPMENT_TYPE;
import static com.cears.serviceapp.Fragments.NewJobFragment.SELECTED_SERVICE_TYPE;

public class MainActivity extends BaseActivity {
    private ViewPager mViewPager;
    private SlidingTabLayout mSlidingTabLayout;
    private NewJobFragment mNewJobFragment;
    private SavedJobFragment mSavedJobFragment;
    private SubmittedFragment mSubmittedFragment;
    public final static int REQUEST_CODE_NEW_FRAGMENT = 1025;
    public final static int REQUEST_CODE_SAVED_FRAGMENT = 1026;
    public final static int REQUEST_CODE_SUBMITTED_FRAGMENT = 1027;
    public final static String PRE_ANSWER_CONSTANT = "PRE_ANSWER_CONSTANT";
    private String save = "";
    private int servicePosition, equipmentTypePosition;
    private String jobTicketNumber, EquipmentNumber;
    public List<PreQuestionModel> allQuestion;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarIcon(R.mipmap.icon);
        setActionBarTitle("" + getResources().getString(R.string.app_name));

        setView();

        Intent i = this.getIntent();
        if (i != null) {
            save = getIntent().getStringExtra("save");
            jobTicketNumber = i.getStringExtra(JOB_TICKET_CONSTANT);
            allQuestion = (List<PreQuestionModel>) i.getSerializableExtra(ALL_PRE_QUESTIONS);
            EquipmentNumber = i.getStringExtra(EQUIPMENT_NUMBER_CONSTANT);
            equipmentTypePosition = i.getIntExtra(SELECTED_EQUIPMENT_TYPE, 1);
            servicePosition = i.getIntExtra(SELECTED_SERVICE_TYPE, 1);

            if (save != null && save.equalsIgnoreCase("save")) {
                mViewPager.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        mViewPager.setCurrentItem(1);
                    }
                }, 100);
            }
        }

        Bundle bundle = new Bundle();
        SavedJobFragment fragobj = new SavedJobFragment();
        bundle.putString("save", "save");
        bundle.putString(JOB_TICKET_CONSTANT, "" + jobTicketNumber);
        bundle.putSerializable(ALL_PRE_QUESTIONS, (Serializable) allQuestion);
        bundle.putString(EQUIPMENT_NUMBER_CONSTANT, "" + EquipmentNumber);
        bundle.putInt(SELECTED_EQUIPMENT_TYPE, equipmentTypePosition);
        bundle.putInt(SELECTED_SERVICE_TYPE, servicePosition);
        fragobj.setArguments(bundle);

    }


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main_new;
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            // TODO Auto-generated constructor stub
        }

        @Override
        public Fragment getItem(int pos) {

            if (pos == 0) {
                mNewJobFragment = new NewJobFragment();
                return mNewJobFragment;

            } else if (pos == 1) {
                mSavedJobFragment = new SavedJobFragment();
                return mSavedJobFragment;

            } else if (pos == 2) {
                mSubmittedFragment = new SubmittedFragment();
                return mSubmittedFragment;
            }

            return null;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return 3;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mViewPager.setCurrentItem(GeneralHelpers.getCurrentFragment(this));
        if (requestCode == REQUEST_CODE_NEW_FRAGMENT) {

        } else if (requestCode == REQUEST_CODE_SAVED_FRAGMENT) {

        } else if (requestCode == REQUEST_CODE_SUBMITTED_FRAGMENT) {

        }
        setView();
    }

    public void setView() {
        mViewPager = (ViewPager) findViewById(R.id.pager1);
        mViewPager.setOffscreenPageLimit(3);
        Log.e("MainActivity", "current save fragment pos :  " + GeneralHelpers.getCurrentFragment(this));
//        mViewPager.setCurrentItem(GeneralHelpers.getCurrentFragment(this));
        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setDividerColors(getResources().getColor(R.color.colorWhite));
        mSlidingTabLayout.setCustomTabView(R.layout.custom_tab, 0);
        mSlidingTabLayout.setViewPager(mViewPager);
        mViewPager.setCurrentItem(2);

    }


    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mViewPager.setCurrentItem(GeneralHelpers.getCurrentFragment(this));
        GeneralHelpers.setCurrentFragment(this, 0);
    }

    @Override
    protected void onDestroy() {
        GeneralHelpers.setIsSurveyInCompleted(this, false);
        super.onDestroy();
    }
}
