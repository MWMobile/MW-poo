package unb.poo.mwmobileTest.db;

import android.content.Context;
import android.test.InstrumentationTestCase;
import android.test.mock.MockContext;

import junit.framework.TestResult;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import unb.poo.mwmobile.db.DBMat;

/**
 * Created by Raphael on 26/10/2015.
 */
public class DBMatTest extends InstrumentationTestCase {
    DBMat db;

    Context context;

    @Before
    public void setUp() throws Exception{
        super.setUp();
        context = new MockContext();
        db = new DBMat(context);
    }

    @After
    public void tearDown() throws Exception{

    }

    @Test
    public void testeOnCreate() throws Exception{
        DBMat newDB = new DBMat(context);
        newDB.onCreate(db.getWritableDatabase());
        assertNotNull(newDB);
    }

    @Test
    public void testOnUpgrade() throws Exception{
        DBMat newDB = new DBMat(context);
        newDB.onUpgrade(db.getWritableDatabase(), 0, 2);
        assertNotNull(newDB );
    }

    @Test
    public void testDropDB() throws Exception{
        db.dropDB();
        assertNull(db);
    }

    @Test
    public void testPrinDB() throws Exception{
        db.printDbM();
    }

    @Test
    public void testAddMat() throws Exception{

    }
}
