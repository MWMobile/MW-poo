package unb.poo.mwmobile.db;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.test.InstrumentationTestCase;
import android.test.RenamingDelegatingContext;
import android.test.mock.MockApplication;
import android.test.mock.MockContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.io.File;

import unb.poo.mwmobile.acts.HomeActivity;
import unb.poo.mwmobile.acts.MainActivity;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by sousa on 14/10/2015.
 */

@RunWith(RobolectricTestRunner.class)
public class DBCoreTest extends AndroidTestCase {

    DBCore db;

    Context context;
    Activity main;

    @Before
    public void setUp() throws Exception {
        super.setUp();

        main = Robolectric.setupActivity(Activity.class);

        db = new DBCore(main);
    }


    @After
    public void tearDown() throws Exception {
        db.close();
        super.tearDown();
    }

    @Test
    public void testOnCreate() throws Exception {
        assertNotNull(db.getWritableDatabase());
        assertNotNull(db.getReadableDatabase());
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