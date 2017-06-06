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
import com.cears.serviceapp.models.GetAllJobSiteSubResponse;
import com.cears.serviceapp.models.GetAllJob_Jobs;
import com.cears.serviceapp.models.QuestionSubResponse;
import com.cears.serviceapp.utils.SlidingTabLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends BaseActivity {
    private ViewPager mViewPager;
    private SlidingTabLayout mSlidingTabLayout;
    private NewJobFragment mNewJobFragment;
    private SavedJobFragment mSavedJobFragment;
    private SubmittedFragment mSubmittedFragment;
    public final static int REQUEST_CODE_NEW_FRAGMENT = 1025;
    public final static int REQUEST_CODE_SAVED_FRAGMENT = 1026;
    public final static int REQUEST_CODE_SUBMITTED_FRAGMENT = 1027;
    public final static String PRE_ANSWER_CONSTANT = "PRE_ANSWER_CONSTANT";

    private List<GetAllJobSiteSubResponse> listMaintenance;
    private List<QuestionSubResponse> filteredQuestionList = new ArrayList<QuestionSubResponse>();
    private List<GetAllJob_Jobs> questionAnslist = new ArrayList<>();
    String maintainance = "", fromSaveJob = "";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarIcon(R.mipmap.icon);
        setActionBarTitle("" + getResources().getString(R.string.app_name));

        listMaintenance = new ArrayList<>();

        setView();

        Intent i = this.getIntent();
        if (i != null) {
            filteredQuestionList = (List<QuestionSubResponse>) i.getSerializableExtra("sectionlist");
            listMaintenance = (List<GetAllJobSiteSubResponse>) i.getSerializableExtra("listMaintenance");
            maintainance = i.getStringExtra("maintainance");
        }

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
                Bundle b = new Bundle();
                mSavedJobFragment = new SavedJobFragment();
                b.putSerializable("listMaintenance", (Serializable) listMaintenance);
                b.putSerializable("sectionlist", (Serializable) filteredQuestionList);
                if (filteredQuestionList != null)
                    Log.e("Main2 ", "filteredQuestionList size " + filteredQuestionList.size());

                b.putString("maintainance", maintainance);
                b.putInt("type", pos);
                mSavedJobFragment.setArguments(b);
                return mSavedJobFragment;

            } else {
                if (pos == 1) {
                    Bundle b = new Bundle();
                    mSavedJobFragment = new SavedJobFragment();
                    b.putInt("type", pos);
                    b.putString("maintainance", maintainance);
                    b.putSerializable("listMaintenance", (Serializable) listMaintenance);
                    b.putSerializable("sectionlist", (Serializable) filteredQuestionList);
                    if (filteredQuestionList != null)
                        Log.e("Main2 ", "filteredQuestionList size " + filteredQuestionList.size());

                    mSavedJobFragment.setArguments(b);
                    return mSavedJobFragment;

                } else if (pos == 2) {
                    Bundle b = new Bundle();
                    mSavedJobFragment = new SavedJobFragment();
                    b.putInt("type", pos);
                    return mSavedJobFragment;
                }
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
