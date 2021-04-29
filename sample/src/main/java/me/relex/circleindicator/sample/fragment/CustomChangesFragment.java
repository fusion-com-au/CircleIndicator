package me.relex.circleindicator.sample.fragment;


import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import me.relex.circleindicator.CircleIndicator;
import me.relex.circleindicator.sample.CustomFancyPagerAdapter;
import me.relex.circleindicator.sample.R;

public class CustomChangesFragment extends Fragment implements ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private CustomFancyPagerAdapter pagerAdapter;
    private ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    private Integer[] backgroundColors;
    private Integer[] indicatorColors;
    private CircleIndicator indicator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        Integer color1 = getResources().getColor(R.color.red);
        Integer color2 = getResources().getColor(R.color.green);
        Integer color3 = getResources().getColor(R.color.blue);
        Integer color4 = getResources().getColor(R.color.orange);
        Integer color5 = getResources().getColor(R.color.purple);

        indicatorColors = new Integer[] {color2,color3,color4,color5,color1};
        backgroundColors = new Integer[] {color1, color2, color3,color4,color5};


        return inflater.inflate(R.layout.fragment_sample_default, container, false);

    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        indicator = (CircleIndicator) view.findViewById(R.id.indicator);

        pagerAdapter = new CustomFancyPagerAdapter();
        viewPager.setAdapter(pagerAdapter);
        indicator.setViewPager(viewPager);
        viewPager.setCurrentItem(2);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        // if not at the end use the next color
        if(position < (pagerAdapter.getCount() -1) && position < (backgroundColors.length - 1)) {
            viewPager.setBackgroundColor((Integer) argbEvaluator.evaluate(positionOffset, backgroundColors[position], backgroundColors[position + 1]));
            indicator.setIndicatorColor((Integer) argbEvaluator.evaluate(positionOffset, indicatorColors[position], indicatorColors[position + 1]));
        } else {
            // Use the last color
            viewPager.setBackgroundColor(backgroundColors[backgroundColors.length - 1]);
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}