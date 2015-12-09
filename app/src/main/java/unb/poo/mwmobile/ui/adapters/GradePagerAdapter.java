package unb.poo.mwmobile.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import unb.poo.mwmobile.models.Materia;
import unb.poo.mwmobile.singleton.SingletonUser;
import unb.poo.mwmobile.ui.fragments.GradeFragments;

/**
 * Created by Raphael on 07/12/2015.
 */
public class GradePagerAdapter extends FragmentPagerAdapter {
    private static String TAG = "GradePagerAdapter";

    public GradePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return GradeFragments.PeriodoFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return 6;
    }
}
