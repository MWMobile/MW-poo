package unb.poo.mwmobile.db;

import android.app.Activity;
import android.test.InstrumentationTestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import unb.poo.mwmobile.models.Horario;
import unb.poo.mwmobile.models.Materia;
import unb.poo.mwmobile.models.MateriaCursada;
import unb.poo.mwmobile.models.Professor;
import unb.poo.mwmobile.models.User;

/**
 * Created by Raphael on 26/10/2015.
 */
@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class DBMateriaTest extends InstrumentationTestCase {
    DBCore db;

    Activity main;

    @Before
    public void setUp() throws Exception{
        super.setUp();

        main = Robolectric.setupActivity(Activity.class);

        db = new DBCore(main);
    }

    @After
    public void tearDown() throws Exception{

    }

    @Test
    public void testDropDB() throws Exception{
        ArrayList<Horario> horario = new ArrayList<>();
        horario.add(new Horario(10, 1));

        Materia materia = new Materia();
        materia.setNome("ADL");
        materia.setCodigo(1122);
        materia.setHorarios(horario);
        materia.setProfessor(new Professor("ProfessorTeste"));
        materia.setTurma("A");
        materia.setSala("Sala");

        db.dropDB();

        assertNull(db.getMateria("ADL"));
    }

    @Test
    public void testPrintDB() throws Exception{
        ArrayList<Horario> horario = new ArrayList<>();
        horario.add(new Horario(10, 1));

        Materia materia = new Materia();
        materia.setNome("ADL");
        materia.setCodigo(1122);
        materia.setHorarios(horario);
        materia.setProfessor(new Professor("ProfessorTeste"));
        materia.setTurma("A");
        materia.setSala("Sala");

        ArrayList<Materia> matList = new ArrayList<>();
        matList.add(materia);

        db.addMaterias(matList);
        assertNotNull(db.getMateria("ADL"));
        db.printDB();
    }

    @Test
    public void testAddMat() throws Exception{
        ArrayList<Materia> matList = new ArrayList<>();
        ArrayList<Horario> horario = new ArrayList<>();
        Materia materia = new Materia();

        horario.add(new Horario(10, 1));
        materia.setNome("ADL");
        materia.setCodigo(1122);
        materia.setHorarios(horario);
        materia.setProfessor(new Professor("ProfessorTeste"));
        materia.setTurma("A");
        materia.setSala("Sala");
        matList.add(materia);

        db.addMaterias(matList);

        assertNotNull(db.getMateria("ADL"));
        assertEquals(materia.getCodigo(), db.getMateria("ADL").getCodigo());
    }

    @Test
    public void testGetMat() throws Exception{
        ArrayList<Materia> matList = new ArrayList<>();
        ArrayList<Horario> horario = new ArrayList<>();

        Materia materia = new Materia();

        horario.add(new Horario(10, 1));

        materia.setNome("ADL");
        materia.setCodigo(1122);
        materia.setHorarios(horario);
        materia.setProfessor(new Professor("ProfessorTeste"));
        materia.setTurma("A");
        materia.setSala("Sala");

        matList.add(materia);

        assertNull(db.getMateria("ADL"));

        db.addMaterias(matList);

        assertNotNull(db.getMateria("ADL"));
        assertNotNull(db.getMateria(1122));
    }

    @Test
    public void testUpdMat() throws Exception{
        ArrayList<Horario> horario = new ArrayList<>();
        horario.add(new Horario(10, 1));

        Materia materia = new Materia();
        materia.setNome("ADL");
        materia.setCodigo(1122);
        materia.setHorarios(horario);
        materia.setProfessor(new Professor("ProfessorTeste"));
        materia.setTurma("A");
        materia.setSala("Sala");

        ArrayList<Materia> matList = new ArrayList<>();
        matList.add(materia);

        db.addMaterias(matList);
        db.updMateria(db.getMateria("ADL"));
        assertNotNull(db.getMateria("ADL"));
    }

    @Test
    public void testDelMat() throws Exception{
        ArrayList<Horario> horario = new ArrayList<>();         //
        horario.add(new Horario(10, 1));                        // Cria uma array de informação de horario

        Materia materia = new Materia();                        // Adiciona uma materia
        materia.setNome("ADL");                                 //
        materia.setCodigo(1122);                                 //
        materia.setHorarios(horario);                           //
        materia.setProfessor(new Professor("ProfessorTeste"));
        materia.setTurma("A");
        materia.setSala("Sala");

        ArrayList<Materia> matList = new ArrayList<>();         //
        matList.add(materia);

        db.addMaterias(matList);                            //
        db.delMateria(db.getMateria("ADL"));

        assertNull(db.getMateria("ADL"));                        // Verifica se a exclusão foi efetuada.
   }

}
