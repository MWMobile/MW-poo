package unb.poo.mwmobile.ui.adapters;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.widget.Toolbar;

import java.util.ArrayList;

import unb.poo.mwmobile.models.MateriaCursada;
import unb.poo.mwmobile.singleton.SingletonUser;
import unb.poo.mwmobile.ui.fragments.HistoricoFragments;

/**
 * Created by sousa on 07/12/2015.
 */
public class HistoricoPagerAdapter extends FragmentPagerAdapter {

    private static String TAG = "HistoricoPagerAdapter";

    SingletonUser singletonUser;
    ArrayList<MateriaCursada> hist;

    public HistoricoPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return HistoricoFragments.PeriodoFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        singletonUser = SingletonUser.getInstance();
        hist = singletonUser.getUser().getHistorico();

        int latPer = 0;
        for(MateriaCursada a: hist){
            if(a.getPeriodoTerminado() > latPer)
                latPer = a.getPeriodoTerminado();
        }

        return latPer;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }


}
