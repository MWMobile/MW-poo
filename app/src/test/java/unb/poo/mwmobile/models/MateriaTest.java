package unb.poo.mwmobile.models;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcel;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.lang.Exception;
import java.util.ArrayList;

import dalvik.annotation.TestTargetClass;

import static org.junit.Assert.*;

/**
 * Created by sousa on 13/10/2015.
 */
@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class MateriaTest {

    Materia m;
    Professor professor;

    String professorNome;

    ArrayList<Horario> horarios;
    Horario horario1;
    Horario horario2;

    int codigo;
    int creditos;
    String nome;
    String turma;
    String sala;
    Activity main;

    @Before
    public void setUp() throws Exception {
        main = Robolectric.setupActivity(Activity.class);
        professorNome = "Rodrigo Bonifacio";

        professor = new Professor(professorNome);

        horario1 = new Horario(1000, 1);
        horario2 = new Horario(1000, 3);

        codigo = 116795;
        creditos = 4;
        nome = "Programacao Orientada a Objetos";
        turma = "A";
        sala = "PAT AT029";

        horarios = new ArrayList<Horario>();

        horarios.add(horario1);
        horarios.add(horario2);

        m = new Materia();

        m.setNome(nome);
        m.setCodigo(codigo);
        m.setTurma(turma);
        m.setSala(sala);
        m.setHorarios(horarios);
        m.setProfessor(professor);
        m.setCreditos(creditos);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetProfessor() throws Exception {
        assertEquals(professor, m.getProfessor());
    }

    @Test
    public void testSetProfessor() throws Exception {
        Professor newProfessor = new Professor("Bodrigo Ronifacio");
        m.setProfessor(newProfessor);
        assertEquals(newProfessor, m.getProfessor());
    }

    @Test
    public void testGetHorarios() throws Exception {
        assertEquals(horarios, m.getHorarios());
    }

    @Test
    public void testSetHorarios() throws Exception {
        Horario newHorario1 = new Horario(800, 1);
        Horario newHorario2 = new Horario(800, 3);

        ArrayList<Horario> newHorarios = new ArrayList<Horario>();
        newHorarios.add(newHorario1);
        newHorarios.add(newHorario2);

        m.setHorarios(newHorarios);
        assertEquals(newHorarios, m.getHorarios());
    }

    @Test
    public void testGetCodigo() throws Exception {
        assertEquals(codigo, m.getCodigo());
    }

    @Test
    public void testSetCodigo() throws Exception {
        int newCodigo = 116034;
        m.setCodigo(newCodigo);
        assertEquals(newCodigo, m.getCodigo());
    }

    @Test
    public void testGetTurma() throws Exception {
        assertEquals(turma, m.getTurma());
    }

    @Test
    public void testSetTurma() throws Exception {
        String newTurma = "B";
        m.setTurma(newTurma);
        assertEquals(newTurma, m.getTurma());
    }

    @Test
    public void testGetNome() throws Exception {
        assertEquals(nome, m.getNome());
    }

    @Test
    public void testSetNome() throws Exception {
        String newNome = "Programacao Orientada a Testes";
        m.setNome(newNome);
        assertEquals(newNome, m.getNome());
    }

    @Test
    public void testGetSala() throws Exception {
        assertEquals(sala, m.getSala());
    }

    @Test
    public void testSetSala() throws Exception {
        String newSala = "PJC BT/133";
        m.setSala(newSala);
        assertEquals(newSala, m.getSala());
    }

    @Test
    public void testGetCreditos() throws Exception {
        assertEquals(creditos, m.getCreditos());
    }

    @Test
    public void testSetCreditos() throws Exception {
        int newCreditos = 6;
        m.setCreditos(newCreditos);
        assertEquals(newCreditos, m.getCreditos());
    }

    @Test
    public void testDescribeContents() throws Exception {
        assertEquals(m.describeContents(),0);
    }
}