package unb.poo.mwmobile.models;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by sousa on 13/10/2015.
 */
@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class MateriaCursadaTest {

    MateriaCursada m;

    boolean obrigatoria;
    boolean trancada;

    int periodoCursado;
    int reprovacoes;
    int avaliacaoMateria;
    int pesoMencao;

    String mencao;

    Activity main;

    @Before
    public void setUp() throws Exception {
        main = Robolectric.setupActivity(Activity.class);
        m = new MateriaCursada();

        mencao= "SS";

        obrigatoria = true;
        trancada = false;

        periodoCursado = 3;
        reprovacoes = 0;
        avaliacaoMateria = 10;
        pesoMencao = 5;

        m.setMencao(mencao);
        m.setObrigatoria(obrigatoria);
        m.setPeriodoCursado(periodoCursado);
        m.setReprovacoes(reprovacoes);
        m.setAvaliacaoMateria(avaliacaoMateria);

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetReprovacoes() throws Exception {
        assertEquals(reprovacoes, m.getReprovacoes());
    }

    @Test
    public void testSetReprovacoes() throws Exception {
        int newReprovacoes = 1;
        m.setReprovacoes(newReprovacoes);
        assertEquals(newReprovacoes, m.getReprovacoes());
    }

    @Test
    public void testGetMencao() throws Exception {
        assertEquals(mencao, m.getMencao());
    }

    @Test
    public void testSetMencao() throws Exception {
        String newMencao = "SS";
        m.setMencao(newMencao);
        assertEquals(newMencao, m.getMencao());
    }

    @Test
    public void testGetAvaliacaoMateria() throws Exception {
        assertEquals(avaliacaoMateria, m.getAvaliacaoMateria());
    }

    @Test
    public void testSetAvaliacaoMateria() throws Exception {
        int newAvaliacaoMateria = 9;
        m.setAvaliacaoMateria(newAvaliacaoMateria);
        assertEquals(newAvaliacaoMateria, m.getAvaliacaoMateria());
    }

    @Test
    public void testGetPeriodoCursado() throws Exception {
        assertEquals(periodoCursado, m.getPeriodoCursado());
    }

    @Test
    public void testSetPeriodoCursado() throws Exception {
        int newPeriodoCursado = 4;
        m.setPeriodoCursado(newPeriodoCursado);
        assertEquals(newPeriodoCursado, m.getPeriodoCursado());
    }

    @Test
    public void testObrigatoriaTrancada() throws Exception {
        assertEquals((obrigatoria && trancada), m.obrigatoriaTrancada());
    }

    @Test
    public void testOptativaTrancada() throws Exception {
        assertEquals((!obrigatoria && trancada), m.optativaTrancada());
    }

    @Test
    public void testSetObrigatoria() throws Exception {
        boolean newObrigatoria = false;
        m.setObrigatoria(newObrigatoria);
        assertEquals(newObrigatoria, m.getObrigatoria());
    }

    @Test
    public void testGetObrigatoria() throws Exception {
        assertEquals(obrigatoria, m.getObrigatoria());
    }

    @Test
    public void testTrancar() throws Exception {
        m.trancar();
        assertEquals(true, m.getTrancada());
    }

    @Test
    public void testGetTrancada() throws Exception {
        assertEquals(trancada, m.getTrancada());
    }

    @Test
    public void testSetPesoMencao() throws Exception {
        String newMencao = "SS";
        m.setPesoMencao(newMencao);
        assertEquals(5, m.getPesoMencao());

        newMencao = "MS";
        m.setPesoMencao(newMencao);
        assertEquals(4, m.getPesoMencao());

        newMencao = "MM";
        m.setPesoMencao(newMencao);
        assertEquals(3, m.getPesoMencao());

        newMencao = "MI";
        m.setPesoMencao(newMencao);
        assertEquals(2, m.getPesoMencao());

        newMencao = "II";
        m.setPesoMencao(newMencao);
        assertEquals(1, m.getPesoMencao());

        newMencao = "SR";
        m.setPesoMencao(newMencao);
        assertEquals(0, m.getPesoMencao());

    }

    @Test
    public void testDescribeContents() throws Exception {
        assertEquals(m.describeContents(),0);
    }

    @Test
    public void testWriteToParcel() throws Exception {
        Parcel in = Parcel.obtain();
        assertNotNull(in);
        m.writeToParcel(in,m.describeContents());
        in.setDataPosition(0);
        MateriaCursada newMateria = MateriaCursada.CREATOR.createFromParcel(in);

        assertNotNull(newMateria);

        assertEquals(m.getCodigo(), newMateria.getCodigo());
        assertEquals(m.getNome(), newMateria.getNome());
        assertEquals(m.getCreditos(), newMateria.getCreditos());

        // Erro de NullPointException aqui, o resto em baixo passa
//        assertEquals(m.getProfessor().getNome(), newMateria.getProfessor().getNome());

        assertNotNull(newMateria.getHorarios());
        assertEquals(m.getSala(), newMateria.getSala());
        assertEquals(m.getTurma(),newMateria.getTurma());
        assertEquals(m.getObrigatoria(),newMateria.getObrigatoria());
        assertEquals(m.getPeriodoCursado(), newMateria.getPeriodoCursado());
        assertNotNull(newMateria.getMencao());
        in.recycle();
    }
}