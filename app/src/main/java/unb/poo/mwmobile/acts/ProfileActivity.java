package unb.poo.mwmobile.acts;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import unb.poo.mwmobile.R;
import unb.poo.mwmobile.models.User;
import unb.poo.mwmobile.singleton.SingletonUser;

/**
 * Created by Raphael on 08/12/2015.
 */
public class ProfileActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SingletonUser singletonUser = SingletonUser.getInstance();

        User user = singletonUser.getUser();

        TextView matView = (TextView) findViewById(R.id.matricProfile);
        TextView nomeView = (TextView) findViewById(R.id.nomeProfile);
        TextView emailView = (TextView) findViewById(R.id.emailProfile);

        matView.setText(String.format("Matrícula :  " + user.getMatricula()));
        nomeView.setText("Nome :    " + user.getNome());
        emailView.setText(String.format("Email Aluno :  " + user.getMatricula() + "@aluno.unb.br"));
    }
}
