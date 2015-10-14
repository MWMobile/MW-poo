package unb.poo.mwmobileTest.models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unb.poo.mwmobile.models.Materia;

import static org.junit.Assert.*;

/**
 * Created by sousa on 13/10/2015.
 */
public class MateriaTest {

    Materia m;

    String nome = "Programacao Orientada a Objetos";

    @Before
    public void setUp() throws Exception {
        m = new Materia();
        m.setNome(nome);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testSetPeso_mencao() throws Exception {

    }

    @Test
    public void testGetPeso_mencao() throws Exception {

    }

    @Test
    public void testGetProfessor() throws Exception {

    }

    @Test
    public void testSetProfessor() throws Exception {

    }

    @Test
    public void testGetHorarios() throws Exception {

    }

    @Test
    public void testSetHorarios() throws Exception {

    }

    @Test
    public void testGetCodigo() throws Exception {

    }

    @Test
    public void testSetCodigo() throws Exception {

    }

    @Test
    public void testGetTurma() throws Exception {

    }

    @Test
    public void testSetTurma() throws Exception {

    }

    @Test
    public void testGetNome() throws Exception {
        assertEquals(nome, m.getNome());
    }

    @Test
    public void testSetNome() throws Exception {

    }

    @Test
    public void testGetSala() throws Exception {

    }

    @Test
    public void testSetSala() throws Exception {

    }

    @Test
    public void testGetCreditos() throws Exception {

    }

    @Test
    public void testSetCreditos() throws Exception {

    }
}