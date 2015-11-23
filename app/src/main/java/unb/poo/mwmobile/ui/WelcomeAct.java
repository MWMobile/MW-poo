package unb.poo.mwmobile.ui;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import org.w3c.dom.Text;

import unb.poo.mwmobile.R;
import unb.poo.mwmobile.acts.MenuHomeActivity;

/**
 * Created by sousa on 23/11/2015.
 */
public class WelcomeAct {

    private static AnimatorSet animatorSet = new AnimatorSet();

    public static AnimatorSet setScreen(final Activity activity) {

        ViewGroup view = (ViewGroup) activity.getWindow().getDecorView();

        TextView welcome = (TextView) view.findViewById(R.id.welcome);
        TextView name = (TextView) view.findViewById(R.id.name);

        ObjectAnimator welcomeOA = ObjectAnimator.ofFloat(welcome, "alpha", 0, 1);
        welcomeOA.setStartDelay(500);
        welcomeOA.setDuration(2000);

        ObjectAnimator nameOA = ObjectAnimator.ofFloat(name, "alpha", 0, 1);
        nameOA.setStartDelay(1500);
        nameOA.setDuration(2000);

        animatorSet.setInterpolator(new LinearInterpolator());
        animatorSet.play(welcomeOA).with(nameOA);

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                try {
                    Thread.sleep(500);
                    Log.d("animation", "done");
                    Intent homeAct = new Intent(activity, MenuHomeActivity.class);
                    activity.startActivity(homeAct);
                    activity.finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        return animatorSet;
    }
}
