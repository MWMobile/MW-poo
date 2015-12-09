package unb.poo.mwmobile.ui.animations;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import unb.poo.mwmobile.R;
import unb.poo.mwmobile.acts.MenuHomeActivity;

/**
 * Created by sousa on 05/12/2015.
 */
public class LoginActAnimation {

    public static void setScreen(final Activity activity) {

        ViewGroup view = (ViewGroup) activity.getWindow().getDecorView();

        EditText matriculaField = (EditText) view.findViewById(R.id.matriculaField);
        EditText passwordField = (EditText) view.findViewById(R.id.passwordField);
        Button loginBtn = (Button) view.findViewById(R.id.loginButton);


        Animation animation = AnimationUtils.loadAnimation(activity, android.R.anim.fade_in);
        animation.setStartOffset(300);

        matriculaField.startAnimation(animation);
        passwordField.startAnimation(animation);
        loginBtn.startAnimation(animation);

    }
}
