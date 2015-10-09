package unb.poo.mwmobile.utils;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import unb.poo.mwmobile.R;
import unb.poo.mwmobile.models.Horario;
import unb.poo.mwmobile.models.Materia;
import unb.poo.mwmobile.models.MateriaCursada;
import unb.poo.mwmobile.models.User;

/**
 * Created by sousa on 05/10/2015.
 */
public class Utils {

    public void gradePopulate(GridView gridView, final Context context, User user) {
        Materia[] materias = new Materia[15];
        for(int i=0; i< 15; i++) {
            materias[i] = new Materia();
            materias[i].setNome("materia " + String.valueOf(i));
        }


//        TODO Usar um ArrayList<Materia> no adapter
//        TODO user user.getMaterias().get(i).getNome() para pegar o nome da materia do usuario

        String[] nomeMaterias = new String[user.getMaterias().size()];
        for (int i = 0; i < user.getMaterias().size(); i++) {
            nomeMaterias[i] = materias[i].getNome();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, nomeMaterias);

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(context, ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
            }
        });

    };

    public User mockUser(int matricula, String password){

        // TODO atualizar assim que as modificacoes nas arraylist de materias forem feitas
        ArrayList<Materia> materias = new ArrayList<Materia>();
        materias.add(createMockMateria("Programacao Orientada a Objetos", 116795, 4));

        ArrayList<MateriaCursada> materiasCursadas = new ArrayList<MateriaCursada>();
        materiasCursadas.add(createMockMateriaCursada("Estrutura de Dados", 116034, 4));

        User user = new User(matricula);

        user.setNome("John Doe");
        user.setSenha(password);
        user.setCurso(19);
        user.setMaterias(materias);
        user.setHistorico(materiasCursadas);

        return user;
    };

    private Materia createMockMateria(String nome, int codigo, int creditos){
        ArrayList<Horario> horarios = new ArrayList<Horario>();
        horarios.add(new Horario(2, 1000));
        horarios.add(new Horario(4, 1000));

        Materia materia = new Materia();

        materia.setNome(nome);
        materia.setCodigo(codigo);
        materia.setCreditos(creditos);
        materia.setHorarios(horarios);

        return materia;
    }

    private MateriaCursada createMockMateriaCursada(String nome, int codigo, int creditos){

        ArrayList<Horario> horarios = new ArrayList<Horario>();
        horarios.add(new Horario(2, 1000));
        horarios.add(new Horario(4, 1000));

        MateriaCursada materia = new MateriaCursada();

        materia.setNome(nome);
        materia.setCodigo(codigo);
        materia.setCreditos(creditos);
        materia.setHorarios(horarios);

        ArrayList<String> mencao = new ArrayList<String>();
        mencao.add("SS");
        materia.setMencao(mencao);

        materia.setObrigatoria(true);
        materia.setPeriodoCursado(1);
        materia.setReprovacoes(0);

        return materia;
    }

}
