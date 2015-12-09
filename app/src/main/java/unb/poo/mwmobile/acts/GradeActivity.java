package unb.poo.mwmobile.acts;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import unb.poo.mwmobile.R;
import unb.poo.mwmobile.models.User;
import unb.poo.mwmobile.singleton.SingletonSemana;
import unb.poo.mwmobile.singleton.SingletonUser;
import unb.poo.mwmobile.ui.adapters.GradePagerAdapter;
import unb.poo.mwmobile.ui.adapters.HistoricoPagerAdapter;

public class GradeActivity extends AppCompatActivity {

    private static String TAG = "GradeActivity";

    User user;
    android.support.v7.widget.Toolbar toolbar;

    private GradePagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);

        SingletonUser singletonUser = SingletonUser.getInstance();

        this.user = singletonUser.getUser();

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.gradeToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Segunda");

        mSectionsPagerAdapter = new GradePagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.gradeContainer);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                SingletonSemana singletonSemana = SingletonSemana.getInstance();
                getSupportActionBar().setTitle(singletonSemana.getDias().get(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
// TODO fazer sliding para as materias