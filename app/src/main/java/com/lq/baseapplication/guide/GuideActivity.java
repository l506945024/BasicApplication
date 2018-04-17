package com.lq.baseapplication.guide;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.lq.baseapplication.R;
import com.lq.baseapplication.basic.BasicActivity;
import com.lq.baseapplication.utils.ScreenUtil;

import java.util.ArrayList;

public class GuideActivity extends BasicActivity {
    ViewPager vp_image;
    LinearLayout layout_indicator;

    ArrayList<Fragment> mFragments = new ArrayList<>();


    @Override
    public void initActivityConfig() {
        super.initActivityConfig();

        isUseBasicLayout = false;
        isUseFlow = true;
    }

    @Override
    public int getContentView() {
        return R.layout.activity_guide;
    }

    @Override
    public void initView() {
        vp_image = findViewById(R.id.vp_image);
        layout_indicator = findViewById(R.id.layout_indicator);
    }

    @Override
    public void initData() {
        mFragments.add(GuideFragment.newInstance(R.mipmap.ic_launcher, false));
        mFragments.add(GuideFragment.newInstance(R.mipmap.ic_launcher, false));
        mFragments.add(GuideFragment.newInstance(R.mipmap.ic_launcher, true));
        for (int i = 0; i < mFragments.size(); i++) {
            View view = new View(this);
            view.setBackgroundResource(R.drawable.guide_indicator_point);
            if (i == 0) {
                view.setSelected(true);
            } else {
                view.setSelected(false);
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.rightMargin = ScreenUtil.dp2px(this, 10);
            layout_indicator.addView(view, layoutParams);

        }

        vp_image.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        });

        vp_image.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                notifyIndicator(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    void notifyIndicator(int position) {
        if (position < layout_indicator.getChildCount()) {
            for (int i = 0; i < layout_indicator.getChildCount(); i++) {
                View view = layout_indicator.getChildAt(i);
                view.setSelected(false);
            }
            layout_indicator.getChildAt(position).setSelected(true);
        }

    }
}
