package unb.poo.mwmobile.models;

import android.util.Log;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by sousa on 21/09/2015.
 */
public class UserTest extends TestCase {

    User u;
    int mat = 123456789;
    String senha = "1234";

    @Override
    protected void setUp() throws Exception {
        u = new User(123456789);
        u.setSenha(senha);
    }

    @Test
    public void testGetMatricula() throws Exception {
        assertEquals(mat, u.getMatricula());
    }

    @Test
    public void testGetSenha() throws Exception {
        assertEquals(senha, u.getSenha());
    }

    @Test
    public void testSetSenha() throws Exception {

    }

    public void testGetNome() throws Exception {

    }

    public void testSetNome() throws Exception {

    }

    public void testGetMaterias() throws Exception {

    }

    public void testSetMaterias() throws Exception {

    }

    public void testGetHistorico() throws Exception {

    }

    public void testSetHistorico() throws Exception {

    }
}