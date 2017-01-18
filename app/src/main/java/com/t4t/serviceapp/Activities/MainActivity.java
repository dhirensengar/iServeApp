package com.t4t.serviceapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.t4t.serviceapp.Fragments.NewFragment;
import com.t4t.serviceapp.Fragments.SavedFragment;
import com.t4t.serviceapp.Fragments.SubmittedFragment;
import com.t4t.serviceapp.GeneralHelpers;
import com.t4t.serviceapp.R;
import com.t4t.serviceapp.utils.SlidingTabLayout;

/**
 * Created by VIKAS SAHU on 29/12/16.
 */

public class MainActivity extends BaseActivity {
    private ViewPager mViewPager;
    private SlidingTabLayout mSlidingTabLayout;
    private NewFragment mNewFragment;
    private SavedFragment mSavedFragment;
    private SubmittedFragment mSubmittedFragment;
    public final static int REQUEST_CODE_NEW_FRAGMENT = 1025;
    public final static int REQUEST_CODE_SAVED_FRAGMENT = 1026;
    public final static int REQUEST_CODE_SUBMITTED_FRAGMENT = 1027;
    public final static String PRE_ANSWER_CONSTANT = "PRE_ANSWER_CONSTANT";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarIcon(R.mipmap.icon);
        setActionBarTitle("" + getResources().getString(R.string.app_name));

        setView();
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
                mNewFragment = new NewFragment();
                return mNewFragment;

            } else if (pos == 1) {
                mSavedFragment = new SavedFragment();
                return mSavedFragment;

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
        mViewPager.setCurrentItem(GeneralHelpers.getCurrentFragment(this));
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
