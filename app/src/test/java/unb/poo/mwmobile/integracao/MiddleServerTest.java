package unb.poo.mwmobile.integracao;

import android.app.Activity;
import android.content.Context;
import android.test.AndroidTestCase;
import android.test.mock.MockContext;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.HashMap;
import java.util.Map;

import de.greenrobot.event.EventBus;
import unb.poo.mwmobile.acts.MainActivity;
import unb.poo.mwmobile.eventBus.MessageServerEB;

/**
 * Created by Eduardo Scartezini on 02/11/2015.
 */

@RunWith(RobolectricTestRunner.class)
public class MiddleServerTest extends AndroidTestCase {
    private static final String TAG = "MiddleServerTest";


    private MiddleServer middleServer;
    private Activity activity;

    private Map<String, String> header;
    private Map<String, String> params;



    @Before
    public void setUp() throws Exception {
        super.setUp();

        activity = Robolectric.setupActivity(Activity.class);

        middleServer = new MiddleServer(activity);

        header = new HashMap<String ,String>();
        params = new HashMap<String ,String>();

        params.put("arroz","arroz");
        header.put("teste", "testando");


        EventBus.getDefault().register(MiddleServerTest.this);
    }

    @After
    public void tearDown() throws Exception {

        EventBus.getDefault().unregister(MiddleServerTest.this);
        super.tearDown();
    }

    @Test
    public void testGet(){

        middleServer.get(header, params);


    }


    public void onEvent(MessageServerEB message){
        assertEquals(message.getHeader(),header);
        Log.d(TAG,message.getResponse());
        assertEquals(message.getResponse(), "{arroz:arroz}");
    }

}

