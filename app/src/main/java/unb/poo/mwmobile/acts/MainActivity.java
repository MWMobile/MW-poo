package unb.poo.mwmobile.acts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import unb.poo.mwmobile.R;
import unb.poo.mwmobile.config.GCMConfig;
import unb.poo.mwmobile.db.DBCore;
import unb.poo.mwmobile.integracao.MiddleServer;
import unb.poo.mwmobile.models.User;
import unb.poo.mwmobile.services.RegistrationIntentService;

public class MainActivity extends AppCompatActivity {

    DBCore db = new DBCore(this);
    Intent loginAct;

    private static final String TAG = "MainActivity";

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                SharedPreferences sharedPreferences =
                        PreferenceManager.getDefaultSharedPreferences(context);
                boolean sentToken = sharedPreferences
                        .getBoolean(GCMConfig.SENT_TOKEN_TO_SERVER, false);
                if (sentToken) {
                    Log.d(TAG, "R.string.gcm_send_message)");
                } else {
                    Log.d(TAG, "R.string.token_error_message");
                }
            }
        };

        if (checkPlayServices()) {
            // Start IntentService to register this application with GCM.
            Intent intent = new Intent(this, RegistrationIntentService.class);
            startService(intent);
        }

        MiddleServer m = new MiddleServer(this);
        m.get();
        m.getIRA(123456);


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

        DBCore db = new DBCore(this);
        db.printDB();

        if(user == null)
            loginAct = new Intent(this, LoginActivity.class);
        else {
            loginAct = new Intent(this, HomeActivity.class);
            loginAct.putExtra("user", user);
        }

        startActivity(loginAct);
        finish();
    }

    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }

    @Override
    public void onPause(){
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }

    @Override
    public void onResume(){
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(GCMConfig.REGISTRATION_COMPLETE));
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
