package unb.poo.mwmobile.acts;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import mehdi.sakout.fancybuttons.FancyButton;
import unb.poo.mwmobile.R;

public class MenuGradeActivity extends Activity {

    Activity context;
    FancyButton materiaBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_grade);

        context = this;

        materiaBtn = (FancyButton) findViewById(R.id.historicoBtn);

        materiaBtn.setOnClickListener(new View.OnClickListener() {
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

    }

}
