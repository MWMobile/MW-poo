package unb.poo.mwmobile.acts;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import mehdi.sakout.fancybuttons.FancyButton;
import unb.poo.mwmobile.R;

public class MenuMateriasActivity extends Activity {

    Activity context;

    FancyButton gradeBtn;
    FancyButton historicoBtn;
    FancyButton quadroBtn;

    FancyButton matriculaMenuBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_materias);

        context = this;

        gradeBtn = (FancyButton) findViewById(R.id.gradeBtn);
        historicoBtn = (FancyButton) findViewById(R.id.historicoBtn);
        quadroBtn = (FancyButton) findViewById(R.id.quadroBtn);

        matriculaMenuBtn = (FancyButton) findViewById(R.id.matriculaMenu);

        gradeBtn.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GradeActivity.class);
                // create the transition animation - the images in the layouts
                // of both activities are defined with android:transitionName="robot"
                //ActivityOptions options = ActivityOptions
                //       .makeSceneTransitionAnimation(context, materiaBtn, "menuIcon");
                // start the new activity
                startActivity(intent);
            }
        });

        historicoBtn.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HistoricoActivity.class);
                // create the transition animation - the images in the layouts
                // of both activities are defined with android:transitionName="robot"
                //ActivityOptions options = ActivityOptions
                //       .makeSceneTransitionAnimation(context, materiaBtn, "menuIcon");
                // start the new activity
                startActivity(intent);
            }
        });

        quadroBtn.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, QuadroResumoActivity.class);
                // create the transition animation - the images in the layouts
                // of both activities are defined with android:transitionName="robot"
                //ActivityOptions options = ActivityOptions
                //       .makeSceneTransitionAnimation(context, materiaBtn, "menuIcon");
                // start the new activity
                startActivity(intent);
            }
        });

        matriculaMenuBtn.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                context.onBackPressed();
            }
        });

    }



}
