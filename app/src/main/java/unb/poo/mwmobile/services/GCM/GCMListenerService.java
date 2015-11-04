package unb.poo.mwmobile.services.GCM;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;


import unb.poo.mwmobile.R;
import unb.poo.mwmobile.acts.MainActivity;
import unb.poo.mwmobile.services.NotificationService;

/**
 * Created by sousa on 20/10/2015.
 */
public class GCMListenerService extends GcmListenerService {

    private static final String TAG = "GcmListenerService";

    /**
     * Chamado quando o app recebe uma mensagem PUSH
     *
     * @param from ID do remetente
     * @param data Bundle contento o conteudo em pared de key:value
     */
    @Override
    public void onMessageReceived(String from, Bundle data) {
        String message = data.getString("message");

        Log.d(TAG, "From: " + from);
        Log.d(TAG, "Message: " + message);

        if (from.startsWith("/topics/")) {
            // recebe mensagem no topico /topics/?
        } else {
            // qualquer outra mensagem
        }

        /**
         * Processara a mensagem aqui
         * Por agora ele envia uma notificacao
         */
        NotificationService.sendNotification(message, this);
    }
}
