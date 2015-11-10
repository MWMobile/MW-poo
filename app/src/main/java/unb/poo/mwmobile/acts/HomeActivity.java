package unb.poo.mwmobile.acts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import unb.poo.mwmobile.R;
import unb.poo.mwmobile.db.DBCore;
import unb.poo.mwmobile.db.DBMateria;
import unb.poo.mwmobile.models.User;
import unb.poo.mwmobile.singleton.SingletonUser;
import unb.poo.mwmobile.utils.MateriaAdapter;

public class HomeActivity extends AppCompatActivity {

    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        /*
        o codigo aqui pega sua propria intent e extrai dela o objeto de usuario que lhe foi passado
        logo em seguida ele seta campos para o texto em tela de nome e matricula
        apos isso ele popula estes campos com os valores do usuario que lhe foi passado
        */

        SingletonUser singletonUser = SingletonUser.getINSTANCE();

        User user = singletonUser.getUser();

        TextView nome = (TextView) findViewById(R.id.nomeField);
        TextView matricula = (TextView) findViewById(R.id.matriculaField);
        TextView iraFormat = (TextView) findViewById(R.id.iraField);

        nome.setText(user.getNome());
        matricula.setText(String.valueOf(user.getMatricula()));
        iraFormat.setText(String.format("IRA: %.4f", user.getIRA()));

        GridView gridView = (GridView) findViewById(R.id.gradeHoraria);

        gradePopulate(gridView, getApplicationContext(), user);
    }

    public void gradePopulate(GridView gridView, final Context context, User user) {

        MateriaAdapter adapter = new MateriaAdapter(context,user);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int pos, long id) {
                LinearLayout parent = (LinearLayout) v;
                TextView t = (TextView) parent.findViewById(R.id.grid_materia);
                Toast.makeText(context, t.getText(), Toast.LENGTH_LONG).show();
            }
        });

    };

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

    /*
    funcao para saida do app, mesma da tela de login com contador de 2 segundos
    porem esta faz o logout do usuario assim que ele aperta a segunda vez
    */

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            DBCore db = new DBCore(this);

            db.dropDB();

            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Aperte Voltar mais uma vez para fazer LogOut", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }



}
