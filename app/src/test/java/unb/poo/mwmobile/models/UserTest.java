package unb.poo.mwmobile.models;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcel;
import android.test.AndroidTestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

/**
 * Created by sousa on 13/10/2015.
 */
@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class UserTest extends AndroidTestCase {

    User u;
    //Parcel in;

    ArrayList<Materia> materias;
    ArrayList<MateriaCursada> historico;

    Materia materia;
    MateriaCursada materiaCursada;

    int matricula;
    String senha;
    String nome;
    String curso;
    int periodo;

    //Parcel parcel;

    Activity main;

    @Before
    public void setUp() throws Exception {
        main = Robolectric.setupActivity(Activity.class);

        historico = new ArrayList<>();
        materias = new ArrayList<>();

        materia = new Materia();
        materia.setNome("POO");
        materia.setProfessor(new Professor("Rodrigo Bonifacio"));
        materia.setHorarios(new ArrayList<Horario>());
        materia.addHorario(800, 2);
        materia.addHorario(800, 4);

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
        assertEquals(curso, u.getCurso());
    }

    @Test
    public void testSetCurso() throws Exception {
        String newCurso = "Design";
        u.setCurso(newCurso);
        assertEquals(newCurso, u.getCurso());
    }

    @Test
    public void testGetPeriodo() throws Exception {
        assertEquals(periodo, u.getPeriodo());
    }

    @Test
    public void testSetPeriodo() throws Exception {
        int newPeriodo = 10;
        u.setPeriodo(newPeriodo);
        assertEquals(newPeriodo, u.getPeriodo());
    }

    @Test
    public void testGetMaterias() throws Exception {
        assertEquals(materias, u.getMaterias());
    }

    @Test
    public void testSetMaterias() throws Exception {
        ArrayList<Materia> newMaterias = new ArrayList<>();
        newMaterias.add(new Materia());
        u.setMaterias(newMaterias);
        assertEquals(newMaterias, u.getMaterias());
    }

    @Test
    public void testGetHistorico() throws Exception {
        assertEquals(historico, u.getHistorico());
    }

    @Test
    public void testSetHistorico() throws Exception {
        ArrayList<MateriaCursada> newHistorico = new ArrayList<>();
        newHistorico.add(new MateriaCursada());
        u.setHistorico(newHistorico);
        assertEquals(newHistorico, u.getHistorico());
    }

    @Test
    public void testGetIRA() throws Exception {
        assertNotNull(u.getIRA());
    }

    @Test
    public void testGetMateria() throws Exception {
        assertEquals(materia, u.getMateria(materia.getNome()));
    }

    @Test
    public void testGetMateriaCursada() throws Exception {
        assertEquals(materiaCursada, u.getMateriaCursada(materiaCursada.getNome()));
    }

    @Test
    public void testLogin() throws Exception {
//        TODO FIX DBTest para fazer login
        assertTrue(u.login(main));
        u.setSenha("123");
        assertFalse(u.login(main));

        u = new User(123456788);
        u.setSenha("1234");
        assertFalse(u.login(main));
    }

    @Test
    public void testDescribeContents() throws Exception {
        assertEquals(u.describeContents(), 0);
    }

    @Test
    public void testAddMateria() throws Exception{
        Materia newMateria = new Materia();
        newMateria.setNome("Materia");
        u.addMateria(newMateria);

        assertNotNull(u.getMateria("Materia"));
        assertEquals(newMateria, u.getMateria("Materia"));
    }

    @Test
    public void testAddMateriaCursada() throws Exception{
        MateriaCursada newMateria = new MateriaCursada();
        newMateria.setNome("Materia");
        u.addMateriaCursada(newMateria);

        assertNotNull(u.getMateriaCursada("Materia"));
        assertEquals(newMateria, u.getMateriaCursada("Materia"));
    }


    @Test
    public void testWriteToParcel() throws Exception {
        //Bundle bTest = new Bundle();
        //bTest.putParcelable("JUnitUser",u);
        //User newUser = bTest.getParcelable("JUnitUser");

        Parcel in = Parcel.obtain();
        assertNotNull(in);
        u.writeToParcel(in,0);
        in.setDataPosition(0);
        User newUser = User.CREATOR.createFromParcel(in);

        assertNotNull(newUser);
        assertEquals(u.getNome(),newUser.getNome());
        assertEquals(u.getCurso(),newUser.getCurso());
        assertEquals(u.getIRA(),newUser.getIRA());
        assertEquals(u.getMatricula(),newUser.getMatricula());
        assertEquals(u.getPeriodo(),newUser.getPeriodo());
        assertEquals(u.getSenha(), newUser.getSenha());
        assertNotNull(newUser.getMateria("POO"));
        assertNotNull(newUser.getMateriaCursada("ED"));
        assertEquals(newUser.getMateria("POO").getProfessor().getNome(), "Rodrigo Bonifacio");
        in.recycle();
    }


}