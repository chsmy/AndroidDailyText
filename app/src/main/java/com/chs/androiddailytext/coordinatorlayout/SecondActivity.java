package com.chs.androiddailytext.coordinatorlayout;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.chs.androiddailytext.R;
import com.chs.androiddailytext.widget.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chs
 */
public class SecondActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager viewpager;
    private String[] chinnals = new String[]{"first","second","third"};
    private List<SecondFragment> mFragments = new ArrayList<>();
    private MyPagerAdapter mMyPagerAdapter;
    int mCurrentPosition = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.slidingTabLayout);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        initFragment();

        mMyPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(mMyPagerAdapter);

        mSlidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this,R.color.green));
        mSlidingTabLayout.setDistributeEvenly(isDistributeEvenly());
        mSlidingTabLayout.setViewPager(viewpager);
        mSlidingTabLayout.setOnPageChangeListener(this);
        mSlidingTabLayout.setCurrent(mCurrentPosition);
    }

    private void initFragment() {
        for(String str:chinnals){
            mFragments.add(SecondFragment.newInstance(str));
        }
    }
    protected boolean isDistributeEvenly() {
        return chinnals.length <= 5;
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
            return mFragments.get(i);
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
