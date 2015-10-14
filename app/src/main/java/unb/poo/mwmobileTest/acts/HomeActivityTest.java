package unb.poo.mwmobileTest.acts;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ActivityUnitTestCase;
import android.widget.TextView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unb.poo.mwmobile.acts.HomeActivity;

import static org.junit.Assert.*;

/**
 * Created by sousa on 14/10/2015.
 */
public class HomeActivityTest extends ActivityUnitTestCase<HomeActivity> {

    private HomeActivity mHomeActivity;

    public HomeActivityTest(Class<HomeActivity> activityClass) {
        super(activityClass);
    }

    public HomeActivityTest() {
        super(HomeActivity.class);
    }

    public void testPreconditions() {
        assertNotNull(mHomeActivity);
    }

    @Before
    public void setUp() throws Exception {
        mHomeActivity = new HomeActivity();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testOnCreate() throws Exception {

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