package unb.poo.mwmobile.acts;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.StringTokenizer;

import unb.poo.mwmobile.R;
import unb.poo.mwmobile.singleton.SingletonUser;
import unb.poo.mwmobile.ui.animations.WelcomeActAnimation;

public class WelcomeActivity extends Activity {

    AnimatorSet animateText;
    Activity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        context = this;

        SingletonUser singletonUser = SingletonUser.getInstance();

        StringTokenizer st = new StringTokenizer(singletonUser.getUser().getNome());

        TextView name = (TextView) findViewById(R.id.name);
        name.setText(st.nextToken());

        animateText = WelcomeActAnimation.setScreen(this);

        animateText.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {}

            @Override
            public void onAnimationEnd(Animator animation) {
                Intent homeAct = new Intent(context, MenuHomeActivity.class);
                startActivity(homeAct);
            }

            @Override
            public void onAnimationCancel(Animator animation) {}

            @Override
            public void onAnimationRepeat(Animator animation) {}
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        animateText.start();
    }

}
