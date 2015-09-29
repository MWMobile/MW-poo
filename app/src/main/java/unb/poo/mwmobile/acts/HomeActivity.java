package unb.poo.mwmobile.acts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import unb.poo.mwmobile.R;
import unb.poo.mwmobile.models.User;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent thisIntent = getIntent();
        User user = (User) thisIntent.getParcelableExtra("sample_user_home");

        /*TextView nome = (TextView) findViewById(R.id.nomeField);
        TextView matricula = (TextView) findViewById(R.id.matriculaField);

        nome.setText(user.getNome());
        matricula.setText(user.getMatricula());*/

        Log.d("Tag zica de Exemplo", user.getNome());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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
