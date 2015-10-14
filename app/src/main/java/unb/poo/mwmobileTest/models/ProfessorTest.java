package unb.poo.mwmobileTest.models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unb.poo.mwmobile.models.Professor;

import static org.junit.Assert.*;

/**
 * Created by sousa on 13/10/2015.
 */
public class ProfessorTest {

    Professor p;

    String nome;

    @Before
    public void setUp() throws Exception {
        nome = "Rodrigo Bonifacio";

        p = new Professor(nome);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetNome() throws Exception {
        assertEquals(nome, p.getNome());
    }
}