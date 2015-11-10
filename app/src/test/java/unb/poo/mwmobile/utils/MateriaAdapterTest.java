package unb.poo.mwmobile.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import unb.poo.mwmobile.models.Horario;
import unb.poo.mwmobile.models.Materia;
import unb.poo.mwmobile.models.MateriaCursada;
import unb.poo.mwmobile.models.Professor;
import unb.poo.mwmobile.models.User;

import static org.junit.Assert.*;

/**
 * Created by sousa on 13/10/2015.
 */
public class MateriaAdapterTest {

    User u;
    int size;
    int position;

    ArrayList<Materia> materias;
    ArrayList<MateriaCursada> historico;
    ArrayList<Horario> horarios;

    Materia materia;
    MateriaCursada materiaCursada;

    int matricula;
    String senha;
    String nome;
    String curso;
    int periodo;

    @Before
    public void setUp() throws Exception {
        historico = new ArrayList<>();
        materias = new ArrayList<>();
        horarios = new ArrayList<>();

        horarios.add(new Horario(10, 1));

        materia = new Materia();
        materia.setNome("POO");
        materia.setProfessor(new Professor("Rodrigo Bonifacio"));
        materia.setHorarios(horarios);

        materiaCursada = new MateriaCursada();
        materiaCursada.setNome("ED");

        materias.add(materia);
        historico.add(materiaCursada);

        matricula = 123456789;
        senha = "1234";
        nome = "Emanuel B.";
        curso = "Engenharia Mecatronica";
        periodo = 9;

        u = new User(matricula);
        u.setSenha(senha);
        u.setNome(nome);
        u.setCurso(curso);
        u.setPeriodo(periodo);
        u.setMaterias(materias);
        u.setHistorico(historico);

        size = u.getMaterias().size();

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetCount() throws Exception {
        assertEquals(size, u.getMaterias().size());

    }

    @Test
    public void testGetItem() throws Exception {
        assertEquals(u.getMaterias().get(position), materias.get(position));
    }

    @Test
    public void testGetItemId() throws Exception {
        assertEquals(u.getMaterias().get(position).getCodigo(), materias.get(position).getCodigo());

    }

    @Test
    public void testGetView() throws Exception {


    }
}