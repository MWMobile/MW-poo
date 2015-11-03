package unb.poo.mwmobile.acts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.Button;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import java_cup.Main;
import unb.poo.mwmobile.BuildConfig;
import unb.poo.mwmobile.R;

/**
 * Created by sousa on 14/10/2015.
 */
@RunWith(RobolectricTestRunner.class)
//@Config(constants = BuildConfig.class, manifest = "src/main/AndroidManifest.xml", sdk = 16)
public class MainActivityTest extends ActivityUnitTestCase<HomeActivity> {

    private Activity mainActivity;
    private ActivityController controller;

    public MainActivityTest() {
        super(HomeActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
//        mainActivity = Robolectric.buildActivity(MainActivity.class).create().get();
    }

    @Test
    public void testOnCreate() throws Exception {
//        ActivityController controller = Robolectric.buildActivity(MainActivity.class).create().start();
//        Activity activity = (Activity) controller.get();
//        assertEquals(activity, mainActivity);
    }

    @Test
    public void testOnCreateOptionsMenu() throws Exception {

    }

    @Test
    public void testOnOptionsItemSelected() throws Exception {

    }

    @Test
    public void testOnBackPressed() throws Exception {

    }
}