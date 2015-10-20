package unb.poo.mwmobile.services;

import android.content.Intent;

/**
 * Created by sousa on 20/10/2015.
 */
public class InstanceIDListenerService extends com.google.android.gms.iid.InstanceIDListenerService{

    private static final String TAG = "InstanceIDLS";

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. This call is initiated by the
     * InstanceID provider.
     */
    // [START refresh_token]
    @Override
    public void onTokenRefresh() {
        // Fetch updated Instance ID token and notify our app's server of any changes (if applicable).
        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
    }
    // [END refresh_token]

}
