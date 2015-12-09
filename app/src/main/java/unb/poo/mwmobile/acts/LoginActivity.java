package unb.poo.mwmobile.acts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;


import org.json.JSONArray;

import unb.poo.mwmobile.R;
import unb.poo.mwmobile.integracao.ServerInterface.Sigra;
import unb.poo.mwmobile.integracao.ServerRequest.MiddleServer;
import unb.poo.mwmobile.integracao.ServerRequest.Transaction;
import unb.poo.mwmobile.models.User;
import unb.poo.mwmobile.singleton.SingletonUser;
import unb.poo.mwmobile.ui.animations.LoginActAnimation;

public class LoginActivity extends Activity implements Transaction{
    
    User user;

    Activity context;

    ProgressBar progressBar;

    EditText matriculaField;
    EditText passwordField;

    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = this;
        /**
        * primeiro ele pega o botao do xml da view do app e atrela ele a uma variavel
        * depois ele adiciona um listener para esse botao (evento de click)
        */

        matriculaField = (EditText) findViewById(R.id.matriculaField);
        passwordField = (EditText) findViewById(R.id.passwordField);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        progressBar.setVisibility(View.GONE);

        loginBtn = (Button) findViewById(R.id.loginButton);
        loginBtn.setOnClickListener(login);

        matriculaField.setText("123456789");
        passwordField.setText("1234");

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

            if(!matriculaField.getText().toString().trim().equals("")){
                if(!passwordField.getText().toString().trim().equals("")){

                    try {
                        toggleLoading();

                        String matricula = String.valueOf(matriculaField.getText());
                        String password = String.valueOf(passwordField.getText());

                        Sigra sigra = new Sigra(LoginActivity.this.getApplicationContext(),
                                LoginActivity.this,
                                LoginActivity.class+"");

                        sigra.autentica(matricula,password);

                    } catch (NumberFormatException e) {
                        Toast.makeText(getApplicationContext(), "O campo matricula so aceita numeros", Toast.LENGTH_LONG).show();
                    }

                } else
                    Toast.makeText(getApplicationContext(), "Preencha o campo de password", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(getApplicationContext(), "Preencha o campo de usuario", Toast.LENGTH_SHORT).show();

        }
    };


    private void toggleLoading() {
        if(progressBar.getVisibility() == View.VISIBLE) {
            progressBar.setVisibility(View.GONE);

            passwordField.setVisibility(View.VISIBLE);
            matriculaField.setVisibility(View.VISIBLE);
            loginBtn.setVisibility(View.VISIBLE);

            LoginActAnimation.setScreen(this);
        } else {
            matriculaField.setVisibility(View.GONE);
            passwordField.setVisibility(View.GONE);
            loginBtn.setVisibility(View.GONE);

            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        LoginActAnimation.setScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MiddleServer.getInstance(LoginActivity.this.getApplicationContext())
                .getRequestQueue()
                .cancelAll(LoginActivity.class + "");
    }


    @Override
    public void doAfter(JSONArray jsonArray) {
        if( jsonArray != null){

            Gson gson = new Gson();

            Log.d("JSON", String.valueOf(jsonArray));
            user = gson.fromJson(String.valueOf(jsonArray), User.class);
            user.saveOnDb(context);


            SingletonUser singletonUser = SingletonUser.getInstance();
            singletonUser.setUser(user);

            Intent welcomeAct = new Intent(getBaseContext(), WelcomeActivity.class);
            startActivity(welcomeAct);


        } else {

            Toast.makeText(getApplicationContext(), "Falha no Login", Toast.LENGTH_SHORT).show();
        }
        toggleLoading();
    }
}
