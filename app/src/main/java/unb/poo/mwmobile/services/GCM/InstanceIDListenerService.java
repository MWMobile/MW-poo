package unb.poo.mwmobile.services.GCM;

import android.content.Intent;

/**
 * Created by sousa on 20/10/2015.
 */
public class InstanceIDListenerService extends com.google.android.gms.iid.InstanceIDListenerService{

    private static final String TAG = "InstanceIDLS";

    /**
     * Chamado se o token da InstanceID for alterado.
     */
    @Override
    public void onTokenRefresh() {
        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
    }

}
