package unb.poo.mwmobileTest.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unb.poo.mwmobile.models.User;
import unb.poo.mwmobile.utils.Utils;

import static org.junit.Assert.*;

/**
 * Created by sousa on 14/10/2015.
 */
public class UtilsTest {

    User u;
    Utils utils;

    int matricula;
    String password;

    @Before
    public void setUp() throws Exception {
        utils = new Utils();
//        u = utils.mockUser(123456789, "1234");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGradePopulate() throws Exception {

    }

    @Test
    public void testMockUser() throws Exception {
        u = utils.mockUser(123456789, "1234");
        assertNotNull(u);
        assertNotNull(u.getCurso());
        assertNotNull(u.getHistorico());
        assertNotNull(u.getIRA());
        assertNotNull(u.getMateria("Robotica Industrial"));
        assertNotNull(u.getMateriaCursada("Fisica 1"));
        assertNotNull(u.getMaterias());
        assertNotNull(u.getMatricula());
        assertNotNull(u.getNome());
        assertNotNull(u.getPeriodo());
        assertNotNull(u.getSenha());
    }
}