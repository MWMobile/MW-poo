package unb.poo.mwmobileTest.db;

import android.app.Activity;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.AndroidTestCase;
import android.test.InstrumentationTestCase;
import android.test.InstrumentationTestRunner;
import android.test.RenamingDelegatingContext;
import android.test.mock.MockContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import unb.poo.mwmobile.db.DBCore;

import static org.junit.Assert.*;

/**
 * Created by sousa on 14/10/2015.
 */
public class DBCoreTest extends InstrumentationTestCase{

    DBCore db;

    Context context;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        context = new MockContext();
        db = new DBCore(context);
    }


    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testOnCreate() throws Exception {
        DBCore newDB = new DBCore(context);
        newDB.onCreate(db.getWritableDatabase());
        assertNotNull(newDB);
    }

    @Test
    public void testOnUpgrade() throws Exception {
        DBCore newDB = new DBCore(context);
        newDB.onUpgrade(db.getWritableDatabase(), 0, 1);
        assertNotNull(newDB);
    }

    @Test
    public void testDropDB() throws Exception {
        db.dropDB();
        assertNull(db.getUser(0));
    }

    @Test
    public void testPrintDB() throws Exception {
        db.printDB();
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

}