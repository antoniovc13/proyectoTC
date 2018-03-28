package com.tivit.talmatc.feature.traslado_carga3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tivit.talmatc.R;
import com.tivit.talmatc.base.ui.BaseFragment;
import com.tivit.talmatc.feature.traslado_carga3.paso3.FlightListFragment;

import butterknife.BindView;
import timber.log.Timber;

/**
 * Created by Antonio.Valdivieso on 26/03/2018.
 */

public class OrderFragment extends BaseFragment  {


    public static int TAB_PASO1 = 0;
    public static int TAB_PASO2 = 1;
    public static int TAB_PASO3 = 2;

    //private OrderPresenter mPresenter;
    OrderPagerAdapter mPagerAdapter;

    @BindView(R.id.order_view_pager)    ViewPager mViewPager;
    @BindView(R.id.tab_layout)          TabLayout mTabLayout;
    //@BindView(R.id.simpleFrameLayout)   FrameLayout simpleFrameLayout;

    public static OrderFragment newInstance(){
        Bundle args = new Bundle();
        OrderFragment fragment = new OrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_order, container, false);
        //mPresenter = new OrderPresenter(this);

        return view;
    }

    @Override
    protected void setUp(View view) {

        mPagerAdapter = new OrderPagerAdapter(getChildFragmentManager());
        mPagerAdapter.setCount(3);

        mViewPager.setAdapter(mPagerAdapter);

        // get the reference of FrameLayout and TabLayout
        //simpleFrameLayout = (FrameLayout) view.findViewById(R.id.simpleFrameLayout);


        //mTabLayout.addTab(mTabLayout.newTab().setText("PASO 01"));
        TabLayout.Tab firstTab = mTabLayout.newTab();
        //firstTab.setText("PASO 01");
        firstTab.setIcon(R.mipmap.ic_number_round_gray_one); // set an icon for the first tab
        mTabLayout.addTab(firstTab); // add  the tab at in the TabLayout

        //mTabLayout.addTab(mTabLayout.newTab().setText("PASO 02"));
        TabLayout.Tab secondTab = mTabLayout.newTab();
        //secondTab.setText("PASO 02");
        secondTab.setIcon(R.mipmap.ic_number_round_gray_two); // set an icon for the first tab
        mTabLayout.addTab(secondTab); // add  the tab at in the TabLayout

        TabLayout.Tab thirdTab = mTabLayout.newTab();
        //thirdTab.setText("PASO 03");
        thirdTab.setIcon(R.mipmap.ic_number_round_gray_three); // set an icon for the first tab
        mTabLayout.addTab(thirdTab); // add  the tab at in the TabLayout

/*
        mViewPager.setOffscreenPageLimit(mTabLayout.getTabCount());

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });*/
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    public void goToFlight() {
        Timber.d("goToFlight");
        mViewPager.setCurrentItem(TAB_PASO2);
        (mTabLayout.getTabAt(TAB_PASO1)).setIcon(R.mipmap.ic_number_round_red_one);
        (mTabLayout.getTabAt(TAB_PASO2)).select();
    }

    public void backFlight() {
        mViewPager.setCurrentItem(TAB_PASO1);
    }

    public void goToFlightList(String codeFlight) {
        ((FlightListFragment) mPagerAdapter.getItem(TAB_PASO3)).setCodeFlight(codeFlight);
        mViewPager.setCurrentItem(TAB_PASO3);
        (mTabLayout.getTabAt(TAB_PASO2)).setIcon(R.mipmap.ic_number_round_red_two);
        (mTabLayout.getTabAt(TAB_PASO3)).select();
    }

    public void startTravel() {
        (mTabLayout.getTabAt(TAB_PASO3)).setIcon(R.mipmap.ic_number_round_red_three);

    }


    public void onCloseDialog(){
        ((FlightListFragment) mPagerAdapter.getItem(TAB_PASO3)).onCloseDialog();
    }
}
