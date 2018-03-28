package com.tivit.talmatc.feature.traslado_carga3;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tivit.talmatc.feature.traslado_carga3.paso1.ChargeMoveFragment;
import com.tivit.talmatc.feature.traslado_carga3.paso2.FlightFragment;
import com.tivit.talmatc.feature.traslado_carga3.paso3.FlightListFragment;

/**
 * Created by Antonio.Valdivieso on 26/03/2018.
 */

public class OrderPagerAdapter extends FragmentStatePagerAdapter {

    private int mTabCount;
    ChargeMoveFragment fragment1;
    FlightFragment fragment2;
    FlightListFragment fragment3;

    public OrderPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.mTabCount = 0;
        fragment1 = new ChargeMoveFragment();
        fragment1 = fragment1.newInstance(0);

        fragment2 = new FlightFragment();
        fragment2 = fragment2.newInstance(1);

        fragment3 = new FlightListFragment();
        fragment3 = fragment3.newInstance(2);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                //ChargeMoveFragment fragment1 = new ChargeMoveFragment();
                //return fragment1.newInstance(position);
                return fragment1;
            case 1:
                //FlightFragment fragment2 = new FlightFragment();
                //return fragment2.newInstance(position);
                return fragment2;
            case 2:
                //FlightListFragment fragment3 = new FlightListFragment();
                //return fragment3.newInstance(position);
                return fragment3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mTabCount;
    }

    public void setCount(int count) {
        mTabCount = count;
    }

}
