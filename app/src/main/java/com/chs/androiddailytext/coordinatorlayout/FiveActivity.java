package com.chs.androiddailytext.coordinatorlayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.chs.androiddailytext.R;
import com.chs.androiddailytext.widget.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

public class FiveActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager viewpager;
    private String[] chinnals = new String[]{"first","second"};
    private MyPagerAdapter mMyPagerAdapter;
    int mCurrentPosition = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);
        mSlidingTabLayout =  findViewById(R.id.slidingTabLayout);
        viewpager =  findViewById(R.id.viewpager);

        mMyPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(mMyPagerAdapter);

        mSlidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this,R.color.green));
        mSlidingTabLayout.setDistributeEvenly(true);
        mSlidingTabLayout.setViewPager(viewpager);
        mSlidingTabLayout.setOnPageChangeListener(this);
        mSlidingTabLayout.setCurrent(mCurrentPosition);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class MyPagerAdapter extends FragmentStatePagerAdapter {
        MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            if(i == 0){
                return FiveFragment1.newInstance(chinnals[i]);
            }else {
                return FiveFragment2.newInstance(chinnals[i]);
            }
        }

        @Override
        public int getCount() {
            return chinnals.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return chinnals[position];
        }
    }
}
