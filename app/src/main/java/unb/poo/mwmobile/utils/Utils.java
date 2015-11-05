package unb.poo.mwmobile.utils;

import android.content.Context;
import android.os.Parcel;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import unb.poo.mwmobile.R;
import unb.poo.mwmobile.models.Horario;
import unb.poo.mwmobile.models.Materia;
import unb.poo.mwmobile.models.MateriaCursada;
import unb.poo.mwmobile.models.Professor;
import unb.poo.mwmobile.models.User;

/**
 * Created by sousa on 05/10/2015.
 */
public class Utils {

    public User mockUser(int matricula, String password){

        // TODO Scartezini: acabar com o UTILS
        ArrayList<Materia> materias = new ArrayList<Materia>();
        materias.add(createMockMateria("Programacao Orientada a Objetos", 116795, 4, "Rodrigo Bonifacio", "A", "PAT-AT 029", 10, 2));
        materias.add(createMockMateria("Elementos de Automacao",170798,4, "Guilherme Caribe de Carvalho", "A", "GRACO Lab. Rockwell", 10, 2));
        materias.add(createMockMateria("Robotica Industrial",169641,4, "Walter de Britto Vidal Filho", "A", "GRACO 1", 16, 2));
        materias.add(createMockMateria("Higiene e Seguranca do Trabalho", 168921, 2, "Luciana Morais de Freitas", "B", "ENM DT 34/15", 14, 2));
        materias.add(createMockMateria("Sistemas Digitais Integrados", 117391, 4, "Carlos Humberto Llanos Quintero/Ricardo Pezzuol Jacobi", "A", "LINF 2", 8, 3));
        materias.add(createMockMateria("Trabalho de Graduacao 1", 167681, 2, "Eugenio Liborio Feitosa Fortaleza/Eduardo Stockler Togrett", "G", "Lab. Auto.", 12, 5));

        ArrayList<MateriaCursada> materiasCursadas = new ArrayList<MateriaCursada>();
        materiasCursadas.add(createMockMateriaCursada("Calculo 1",113034,6,"SS",true,1));
        materiasCursadas.add(createMockMateriaCursada("Fisica 1",118001,4,"MM",true,1));
        materiasCursadas.add(createMockMateriaCursada("Fisica 1 Experimental",118010,2,"MS",true,1));
        materiasCursadas.add(createMockMateriaCursada("Computacao Basica",116301,6,"MS",true,1));
        materiasCursadas.add(createMockMateriaCursada("Quimica Geral Teorica",114626,4,"MS",true,1));
        materiasCursadas.add(createMockMateriaCursada("Quimica Experimental",114634,2,"MS",true,1));
        materiasCursadas.add(createMockMateriaCursada("Introducao a Engenharia Mecatronica",168891,2,"MS",false,1));

        materiasCursadas.add(createMockMateriaCursada("Calculo 2",113042,6,"SS",true,2));
        materiasCursadas.add(createMockMateriaCursada("Fisica 2",118028,4,"MS",true,2));
        materiasCursadas.add(createMockMateriaCursada("Fisica 2 Experimental",118036,4,"MS",true,2));
        materiasCursadas.add(createMockMateriaCursada("Desenho Mecanico Assistido por Computador 1",168874,6,"MS",true,2));
        materiasCursadas.add(createMockMateriaCursada("Probabilidade e Estatistica",115045,6,"MS",true,2));
        materiasCursadas.add(createMockMateriaCursada("Introducao a Algebra Linear",113093,4,"MM",true,2));

        materiasCursadas.add(createMockMateriaCursada("Calculo 3",113051,6,"SS",true,3));
        materiasCursadas.add(createMockMateriaCursada("Fisica 3",118044,4,"MM",true,3));
        materiasCursadas.add(createMockMateriaCursada("Fisica 3 Experimental",118052,4,"MM",true,3));
        materiasCursadas.add(createMockMateriaCursada("Estruturas de Dados", 116034, 4,"MS",true,3));
        materiasCursadas.add(createMockMateriaCursada("Mecanica 1",168769,4,"SS",true,3));

        materiasCursadas.add(createMockMateriaCursada("Variavel Complexa 1",113069,6,"SS",true,4));
        materiasCursadas.add(createMockMateriaCursada("Metodos Matematicos da Fisica 1",113522,6,"SS",false,4));
        materiasCursadas.add(createMockMateriaCursada("Mecanica dos Materiais 1",169510,4,"MM",true,4));
        materiasCursadas.add(createMockMateriaCursada("Mecanica 2",168777,4,"MM",true,4));
        materiasCursadas.add(createMockMateriaCursada("Circuitos Eletricos 1",167011,6,"MS",true,4));
        materiasCursadas.add(createMockMateriaCursada("Circuitos Digitais",116351,6,"MS",true,4));

        materiasCursadas.add(createMockMateriaCursada("Tecnologia de Fabricacao 1",168831,3,"SS",true,5));
        materiasCursadas.add(createMockMateriaCursada("Calculo Numerico",113417,4,"SS",false,5));
        materiasCursadas.add(createMockMateriaCursada("Introducao a Ciencia dos Materiais",0,3,"MM",true,5));
        materiasCursadas.add(createMockMateriaCursada("Circuitos Eletricos 2",167029,6,"MS",true,5));
        materiasCursadas.add(createMockMateriaCursada("Transporte de Calor e Massa",168840,4,"MM",true,5));
        materiasCursadas.add(createMockMateriaCursada("Conversao Eletromecanica de Energia",163627,6,"MM",true,5));

        materiasCursadas.add(createMockMateriaCursada("Analise Dinamica Linear",160041,6,"MS",true,6));
        materiasCursadas.add(createMockMateriaCursada("Organizacao e Arquitetura de Computadores",116394,4,"SS",true,6));
        materiasCursadas.add(createMockMateriaCursada("Ciencias do Ambiente",122408,2,"MM",true,6));
        materiasCursadas.add(createMockMateriaCursada("Tecnologias de Comando Numerico",164399,4,"MS",true,6));
        materiasCursadas.add(createMockMateriaCursada("Sistemas de Medicao",168742,3,"MS",true,6));
        materiasCursadas.add(createMockMateriaCursada("Dispositivos e Circuitos Eletronicos",170321,6,"MS",true,6));

        materiasCursadas.add(createMockMateriaCursada("Transmissao de Dados",116424,4,"MM",true,7));
        materiasCursadas.add(createMockMateriaCursada("Software Basico",116432,4,"SS",true,7));
        materiasCursadas.add(createMockMateriaCursada("Sistemas Integrados de Manufatura",168912,4,"MS",true,7));
        materiasCursadas.add(createMockMateriaCursada("Sistemas Hidraulicos e Pneumaticos",168238,4,"MS",true,7));
        materiasCursadas.add(createMockMateriaCursada("Eletronica de Potencia",169421,6,"MS",false,7));
        materiasCursadas.add(createMockMateriaCursada("Controle Dinamico",160032,6,"MS",true,7));

        materiasCursadas.add(createMockMateriaCursada("Processamento em Tempo Real",116599,4,"MS",true,8));
        materiasCursadas.add(createMockMateriaCursada("Organizacao Industrial",181315,4,"MM",true,8));
        materiasCursadas.add(createMockMateriaCursada("Controle Digital",164887,4,"MS",true,8));
        materiasCursadas.add(createMockMateriaCursada("Instrumentacao de Controle",167347,4,"MS",true,8));
        materiasCursadas.add(createMockMateriaCursada("Controle para Automacao",167657,4,"MS",true,8));

        User user = new User(matricula);
        user.setNome("Emanuel B.");
        user.setSenha(password);
        user.setCurso("Engenharia Mecatronica");
        user.setMaterias(materias);
        user.setHistorico(materiasCursadas);
        user.setPeriodo(9);

        return user;
    };

    private Materia createMockMateria(String nome, int codigo, int creditos, String professor, String turma, String sala, int hora, int dia){
        Materia materia = new Materia();

        materia.setNome(nome);
        materia.setCodigo(codigo);
        materia.setCreditos(creditos);
        materia.setHora(hora);
        materia.setDia(dia);
        materia.setProfessor(new Professor(professor));
        materia.setSala(sala);
        materia.setTurma(turma);

        return materia;
    }

    private MateriaCursada createMockMateriaCursada(String nome, int codigo, int creditos,String nota,boolean obr,int periodo){
        MateriaCursada materia = new MateriaCursada();

        materia.setNome(nome);
        materia.setCodigo(codigo);
        materia.setCreditos(creditos);

        ArrayList<String> mencao = new ArrayList<String>();
        mencao.add(nota);
        materia.setMencao(mencao);
        materia.setPesoMencao(nota);

        materia.setObrigatoria(obr);
        materia.setPeriodoCursado(periodo);
        materia.setReprovacoes(0);

        return materia;
    }

}
