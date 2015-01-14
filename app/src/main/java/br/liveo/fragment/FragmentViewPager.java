package br.liveo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

import br.liveo.adapter.TabPagerItem;
import br.liveo.adapter.ViewPagerAdapter;
import br.liveo.navigationviewpagerliveo.R;

public class FragmentViewPager extends Fragment{
	private List<TabPagerItem> mTabs = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTabs.add(new TabPagerItem(0, getString(R.string.starred)));
        mTabs.add(new TabPagerItem(1, getString(R.string.important)));
        mTabs.add(new TabPagerItem(2, getString(R.string.documents)));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_viewpager, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
    	ViewPager mViewPager = (ViewPager) view.findViewById(R.id.pager);
    	
    	mViewPager.setOffscreenPageLimit(3); 
        mViewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(), mTabs));

        PagerSlidingTabStrip mSlidingTabLayout = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
        mSlidingTabLayout.setTextColorResource(R.color.nliveo_white);
        mSlidingTabLayout.setViewPager(mViewPager);
    }
}