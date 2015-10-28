package unb.poo.mwmobile.acts;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import unb.poo.mwmobile.R;
import unb.poo.mwmobile.integracao.MiddleServer;
import unb.poo.mwmobile.models.User;
import unb.poo.mwmobile.utils.Utils;

public class LoginActivity extends AppCompatActivity {

    boolean doubleBackToExitPressedOnce = false;
    User user;
    Utils utils = new Utils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /**
        * primeiro ele pega o botao do xml da view do app e atrela ele a uma variavel
        * depois ele adiciona um listener para esse botao (evento de click)
        */



        Button loginBtn = (Button) findViewById(R.id.loginButton);
        loginBtn.setOnClickListener(login);
    }


    /**
    * no listener ele pega os valores dos campos da tela de login
    * depois ele verifica se foi preenchido
    *    caso nao seja ele mostra uma mensagem por campo respectivo vazio
    *    caso esteja preenchido errado ele tambem mostra uma mensagem avisanso
    * se estiver tudo certo ele cria um objeto de usuario e chama a funcao de autenticacao
    * se ela retornar valida ele vai pra "home", se nao ele avisa que os dados estao errados
    */
    View.OnClickListener login = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent homeAct = new Intent(getBaseContext(), HomeActivity.class);

            EditText matriculaField = (EditText) findViewById(R.id.matriculaField);
            EditText passwordField = (EditText) findViewById(R.id.passwordField);

            if(!matriculaField.getText().toString().trim().equals("")){
                if(!passwordField.getText().toString().trim().equals("")){

                    try {
                        int matricula = Integer.parseInt(String.valueOf(matriculaField.getText()));

                        String password = String.valueOf(passwordField.getText());

                        user = utils.mockUser(matricula, password);

                        if (user.login(getApplicationContext())) {
                            homeAct.putExtra("user", user);
                            startActivity(homeAct);
                            finish();
                        } else
                            Toast.makeText(getApplicationContext(), "Informacoes Incorretas", Toast.LENGTH_SHORT).show();

                    } catch (NumberFormatException e) {
                        Toast.makeText(getApplicationContext(), "O campo matricula so aceita numeros", Toast.LENGTH_LONG).show();
                    }

                } else
                    Toast.makeText(getApplicationContext(), "Preencha o campo de password", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(getApplicationContext(), "Preencha o campo de usuario", Toast.LENGTH_SHORT).show();

        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
    funcao basica de clique duplo no botao voltar para sair
    verifica se tem menos de 2 segundos que o usuario clicou o botao de voltar
    se sim ele fecha o app, se nao ele reseta o contador
    */

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Aperte VOLTAR novamente para sair", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
