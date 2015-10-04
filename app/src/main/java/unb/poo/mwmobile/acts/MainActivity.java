package unb.poo.mwmobile.acts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import unb.poo.mwmobile.R;
import unb.poo.mwmobile.db.DBCore;
import unb.poo.mwmobile.models.User;
import unb.poo.mwmobile.models.Horario;
import unb.poo.mwmobile.models.Materia;
import unb.poo.mwmobile.models.Professor;

public class MainActivity extends AppCompatActivity {

    DBCore db = new DBCore(this);
    Intent loginAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        Activity que checa se ele esta logado ou nao e redireciona para login ou home conforme
        home > logado
        login > nao logado

        Nesta ordem:
            primeiro ele procura no DB o usuario
            sendo nulo ele vai pra tela de login (nao esta logado)
            retornando um usuario valido ele vai pra tela de "home" e passa o usuario para a home
            depois esta activity se mata
        */

        User user = db.getUser(0);

        if(user == null)
            loginAct = new Intent(this, LoginActivity.class);
        else {
            loginAct = new Intent(this, HomeActivity.class);
            loginAct.putExtra("user", user);
        }

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
