package unb.poo.mwmobile.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

import unb.poo.mwmobile.config.GCMConfig;
import unb.poo.mwmobile.R;

/**
 * Created by sousa on 20/10/2015.
 */
public class RegistrationIntentService extends IntentService {

    /**
     * Nome da classe.
     * Para ser usada em Logs, ou quando precisar de nome desta classe
     */
    private static final String TAG = "RegIntentService";

    public RegistrationIntentService() {
        super(TAG);
    }

    /**
     * Lida com a ligacao do applicativo com o GCM (Google Cloud Messaging).
     * Ela pega o token do google-services.json e deriva um SenderID disso e se registra no GCM para
     * receber pushs pelo Google Play Services
     *
     * @author Andrei Sousa
     * @param intent a intent do Registro no GCM
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        try {

            InstanceID instanceID = InstanceID.getInstance(this);

            // R.string.gcm_defaultSenderId | ID deste app (sender) derivada de google-services.json.
            String token = instanceID.getToken(getString(R.string.gcm_defaultSenderId),
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            // Log.i(TAG, "GCM Registration Token: " + token);

            // TODO: Implement this method to send any registration to your app's servers.
            sendRegistrationToServer(token);

            // Se inscreve nos topicos
            subscribeTopics(token);

            // Guarda uma boolean que indica se a token foi gerada/enviada pro servidor ou nao
            // Se for falsa, envia o token pro server, se nao o server ja deveria ter recebido o token
            sharedPreferences.edit().putBoolean(GCMConfig.SENT_TOKEN_TO_SERVER, true).apply();

        } catch (Exception e) {
            Log.d(TAG, "Falhou em renovar a Token", e);
            //Nao consegue atualizar a token e zera a variavel de estado da token
            sharedPreferences.edit().putBoolean(GCMConfig.SENT_TOKEN_TO_SERVER, false).apply();
        }
        //Notifica a UI que foi registrado com sucesso
        Intent registrationComplete = new Intent(GCMConfig.REGISTRATION_COMPLETE);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }

    /**
     * Envia o registro para nosso servidor proprio
     *
     * Esse metodo assegura que o token no nosso server e no GCM so o mesmo, mantendo
     * a unicidade de autenticacao
     *
     * @param token GCM token
     */
    private void sendRegistrationToServer(String token) {
        //TODO fazer autenticacao com nosso servidor
    }

    /**
     * Se inscreve nos topicos desejados.
     * Funcao pega os topicos da classe de configuracao do app e se inscreve para receber pushs
     * de tais topicos
     *
     * @param token GCM token
     * @throws IOException se nao conseguir conecao com o GCM
     */
    // [START subscribe_topics]
    private void subscribeTopics(String token) throws IOException {
        GcmPubSub pubSub = GcmPubSub.getInstance(this);
        for (String topic : GCMConfig.TOPICS) {
            pubSub.subscribe(token, "/topics/" + topic, null);
        }
    }
}
