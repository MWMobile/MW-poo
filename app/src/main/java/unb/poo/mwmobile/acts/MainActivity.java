package unb.poo.mwmobile.acts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import unb.poo.mwmobile.R;
import unb.poo.mwmobile.models.User;
import unb.poo.mwmobile.models.Horario;
import unb.poo.mwmobile.models.Materia;
import unb.poo.mwmobile.models.Professor;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Activity que checa se ele esta logado ou nao e redireciona para login ou home conforme
        logado ou nao

        Por agora ele so joga na loginActivity
        */

        User exampleUser = new User(110115716);
        exampleUser.setNome("Emanuel B.");
        exampleUser.setSenha("coelhosverdes");
        exampleUser.setIRA(3.9502);

        Intent loginAct = new Intent(this, LoginActivity.class);
        loginAct.putExtra("sample_user", exampleUser);
        startActivity(loginAct);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
