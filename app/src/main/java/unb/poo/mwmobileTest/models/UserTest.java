package unb.poo.mwmobileTest.models;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unb.poo.mwmobile.models.User;

/**
 * Created by sousa on 13/10/2015.
 */
public class UserTest extends TestCase {

    User u;

    int matricula = 123456789;
    String senha = "1234";
    String nome = "John Doe";

    @Before
    public void setUp() throws Exception {
        u = new User(matricula);
        u.setSenha(senha);
        u.setNome(nome);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testWriteToParcel() throws Exception {

    }

    @Test
    public void testGetMatricula() throws Exception {
        assertEquals(matricula, u.getMatricula());
    }

    @Test
    public void testGetSenha() throws Exception {
        assertEquals(senha, u.getSenha());
    }

    @Test
    public void testSetSenha() throws Exception {
        String newSenha = "12345";
        u.setSenha(newSenha);
        assertEquals(newSenha, u.getSenha());
    }

    @Test
    public void testGetNome() throws Exception {
        assertEquals(nome, u.getNome());
    }

    @Test
    public void testSetNome() throws Exception {
        String newNome = "Johnnathan Doe";
        u.setNome(newNome);
        assertEquals(newNome, u.getNome());
    }

    @Test
    public void testGetCurso() throws Exception {

    }

    @Test
    public void testSetCurso() throws Exception {

    }

    @Test
    public void testGetMaterias() throws Exception {

    }

    @Test
    public void testSetMaterias() throws Exception {

    }

    @Test
    public void testGetHistorico() throws Exception {

    }

    @Test
    public void testSetHistorico() throws Exception {

    }

    @Test
    public void testGetIRA() throws Exception {

    }

    @Test
    public void testLogin() throws Exception {

    }

    @Test
    public void testDescribeContents() throws Exception {

    }
}