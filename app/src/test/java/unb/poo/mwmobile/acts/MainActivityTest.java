package unb.poo.mwmobile.acts;

import android.app.Activity;
import android.test.ActivityUnitTestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.util.ActivityController;

/**
 * Created by sousa on 14/10/2015.
 */
@RunWith(RobolectricTestRunner.class)
//@Config(constants = BuildConfig.class, manifest = "src/main/AndroidManifest.xml", sdk = 16)
public class MainActivityTest extends ActivityUnitTestCase<GradeActivity> {

    private Activity mainActivity;
    private ActivityController controller;

    public MainActivityTest() {
        super(GradeActivity.class);
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