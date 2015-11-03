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
import org.robolectric.annotation.Config;

import java.io.File;
import java.util.ArrayList;

import unb.poo.mwmobile.acts.HomeActivity;
import unb.poo.mwmobile.acts.MainActivity;
import unb.poo.mwmobile.models.Materia;
import unb.poo.mwmobile.models.MateriaCursada;
import unb.poo.mwmobile.models.User;
import unb.poo.mwmobile.utils.Utils;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by sousa on 14/10/2015.
 */
@Config(manifest = Config.NONE)
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
        User user = new User(110115716);                        // Adiciona um User 11/0115716
        ArrayList<Materia> matList = new ArrayList<>();         //
        ArrayList<MateriaCursada> hisList = new ArrayList<>();  //
        matList.add(new Materia());                             //
        hisList.add(new MateriaCursada());                      // Cria ArrayLists de matérias com uma informação apenas
        db.addUser(user,matList,hisList);                       //
        assertNotNull(db.getUser(0));                           // Checa se existe o user recém-criado
        assertEquals(db.getUser(0).getMatricula(),110115716);   // Checa a matrícula
    }

    @Test
    public void testGetUser() throws Exception {
        User user = new User(110115716);                        // Adiciona um User 11/0115716
        ArrayList<Materia> matList = new ArrayList<>();         //
        ArrayList<MateriaCursada> hisList = new ArrayList<>();  //
        matList.add(new Materia());                             //
        hisList.add(new MateriaCursada());                      // Cria ArrayLists de matérias com uma informação apenas
        db.addUser(user,matList,hisList);

        User userNew = db.getUser(0);                              // Verifica se há um user no DB
        assertNotNull(userNew);                                    //
    }

    @Test
    public void testGetUser1() throws Exception {
        User user = new User(110115716);                        // Adiciona um User 11/0115716
        ArrayList<Materia> matList = new ArrayList<>();         //
        ArrayList<MateriaCursada> hisList = new ArrayList<>();  //
        matList.add(new Materia());                             //
        hisList.add(new MateriaCursada());                      // Cria ArrayLists de matérias com uma informação apenas
        db.addUser(user,matList,hisList);

        User userNew = db.getUser("110115716");                    // Adicionei um "mock" com 11/0115716
        assertNotNull(user);
    }

    @Test
    public void testUpdUser() throws Exception {
        User user = new User(110115716);                        // Adiciona um User 11/0115716
        ArrayList<Materia> matList = new ArrayList<>();         //
        ArrayList<MateriaCursada> hisList = new ArrayList<>();  //
        matList.add(new Materia());                             //
        hisList.add(new MateriaCursada());                      // Cria ArrayLists de matérias com uma informação apenas
        db.addUser(user,matList,hisList);

        db.updUser(db.getUser(0));                              // TODO dar um jeito de verificar as informações além do status do objeto
        assertNotNull(db.getUser(0));                           //
    }

    @Test
    public void testDelUser() throws Exception {
        User user = new User(110115716);                        // Adiciona um User 11/0115716
        ArrayList<Materia> matList = new ArrayList<>();         //
        ArrayList<MateriaCursada> hisList = new ArrayList<>();  //
        matList.add(new Materia());                             //
        hisList.add(new MateriaCursada());                      // Cria ArrayLists de matérias com uma informação apenas
        db.addUser(user,matList,hisList);

        db.delUser(db.getUser(0));
        assertNull(db.getUser(0));                              // Verifica se a exclusão foi efetuada.
    }

}