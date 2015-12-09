package unb.poo.mwmobile.acts;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toolbar;

import java.util.ArrayList;

import unb.poo.mwmobile.R;
import unb.poo.mwmobile.models.MateriaCursada;
import unb.poo.mwmobile.models.User;
import unb.poo.mwmobile.singleton.SingletonUser;
import unb.poo.mwmobile.ui.adapters.HistoricoCardAdapter;
import unb.poo.mwmobile.ui.adapters.HistoricoPagerAdapter;
import unb.poo.mwmobile.ui.fragments.HistoricoFragments;

/**
 * Created by Raphael on 24/11/2015.
 */
public class HistoricoActivity extends AppCompatActivity {

    Activity context;
    User user;
    ArrayList<MateriaCursada> hist;
    SingletonUser singletonUser;
    android.support.v7.widget.Toolbar toolbar;

    private HistoricoPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hist);

        context = this;

        singletonUser = SingletonUser.getInstance();

        this.user = singletonUser.getUser();
        this.hist = user.getHistorico();

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Periodo 1");

        mSectionsPagerAdapter = new HistoricoPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                getSupportActionBar().setTitle("Periodo " + (position + 1));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }



}

// TODO: adicionar professor nas materias cursadas
