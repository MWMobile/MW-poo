package unb.poo.mwmobile.models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by sousa on 13/10/2015.
 */
public class HorarioTest {

    Horario h;
    int hora;
    int dia;

    @Before
    public void setUp() throws Exception {
        hora = 1000;
        dia = 1;

        h = new Horario(hora, dia);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetHora() throws Exception {
        assertEquals(hora, h.getHora());
    }

    @Test
    public void testGetDia() throws Exception {
        assertEquals(dia, h.getDia());
    }
}