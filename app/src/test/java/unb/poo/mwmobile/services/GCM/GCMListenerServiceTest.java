package unb.poo.mwmobile.services.GCM;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.test.AndroidTestCase;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;
import org.robolectric.shadows.ShadowContext;
import org.robolectric.shadows.ShadowNotification;
import org.robolectric.shadows.ShadowNotificationManager;
import org.robolectric.util.ServiceController;

import java.util.List;

import unb.poo.mwmobile.acts.MainActivity;
import unb.poo.mwmobile.services.NotificationService;

import static org.junit.Assert.*;

/**
 * Created by sousa on 03/11/2015.
 */
@RunWith(RobolectricTestRunner.class)
@Config(sdk = 16, manifest = Config.NONE, shadows = {ShadowNotificationManager.class,
        ShadowNotification.class, ShadowContext.class})
public class GCMListenerServiceTest extends AndroidTestCase {

    Activity main;

    @Test
    public void setUp() throws Exception {
        super.setUp();

        main = Robolectric.setupActivity(Activity.class);
    }

    @Test
    public void testOnMessageReceived() throws Exception {
        String from = "0000";
        Bundle data = new Bundle();
        data.putString("mensagem", "teste");

        //TODO Andrei: conseguir mocar o Notification Service no getSystemService
//        GCMListenerService gcmListenerService = new GCMListenerService();
//        gcmListenerService.onMessageReceived(from, data);

    }
}