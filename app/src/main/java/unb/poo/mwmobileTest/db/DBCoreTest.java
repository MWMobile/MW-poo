package unb.poo.mwmobileTest.db;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.test.ActivityTestCase;
import android.test.AndroidTestCase;
import android.test.InstrumentationTestCase;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import unb.poo.mwmobile.acts.MainActivity;
import unb.poo.mwmobile.db.DBCore;

/**
 * Created by sousa on 13/10/2015.
 */
public class DBCoreTest extends AndroidTestCase {

    DBCore db;

    @Before
    public void setUp() throws Exception {
        db = new DBCore(getContext());
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testOnCreate() throws Exception {

    }

    @Test
    public void testOnUpgrade() throws Exception {

    }

    @Test
    public void testDropDB() throws Exception {

    }

    @Test
    public void testPrintDB() throws Exception {
//        db.printDB();
//        TODO arrumar DB/TestesDB para encaixar nos testes
    }

    @Test
    public void testAddUser() throws Exception {

    }

    @Test
    public void testGetUser() throws Exception {

    }

    @Test
    public void testGetUser1() throws Exception {

    }

    @Test
    public void testUpdUser() throws Exception {

    }

    @Test
    public void testDelUser() throws Exception {

    }

    @Test
    public void testGetDatabaseName() throws Exception {

    }

    @Test
    public void testSetWriteAheadLoggingEnabled() throws Exception {

    }

    @Test
    public void testGetWritableDatabase() throws Exception {

    }

    @Test
    public void testGetReadableDatabase() throws Exception {

    }

    @Test
    public void testClose() throws Exception {

    }

    @Test
    public void testOnConfigure() throws Exception {

    }

    @Test
    public void testOnCreate1() throws Exception {

    }

    @Test
    public void testOnUpgrade1() throws Exception {

    }

    @Test
    public void testOnDowngrade() throws Exception {

    }

    @Test
    public void testOnOpen() throws Exception {

    }
}