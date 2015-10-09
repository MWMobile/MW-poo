package unb.poo.mwmobile.models;

import java.util.ArrayList;

/**
 * Created by vinic on 04-Oct-15.
 *
 *
 * Classe que herda todas as características de uma matéria comum,
 * mas como já foi cursada é caracterizada por possuir menção,
 * avaliação do aluno sobre  a matéria e número de reprovações.
 * Mais tarde as notas serão utilizadas para o cálculo de IRA.
 */
public class MateriaCursada extends Materia{
    private int periodoCursado;                                                                     //Período em que a matéra foi cursada
    private int reprovacoes;                                                                        //Número de vezes que o aluno reprovou
    private ArrayList<String> mencao = new ArrayList<>();                                           //Lista de Menções (uma menção única caso o aluno não tenha reprovado)
    private int avaliacaoMateria;                                                                   //Avaliação numérica de 0 a 100% da qualidade da matéria(envolve desde
                                                                                                    // ementa e professor até horário)

    public int getReprovacoes() {
        return reprovacoes;
    }

    public void setReprovacoes(int reprovacoes) {
        this.reprovacoes = reprovacoes;
    }

    public ArrayList<String> getMencao() {
        return mencao;
    }

    public void setMencao(ArrayList<String> mencao) {
        this.mencao = mencao;
    }

    public int getAvaliacaoMateria() {
        return avaliacaoMateria;
    }

    public void setAvaliacaoMateria(int avaliacaoMateria) {
        this.avaliacaoMateria = avaliacaoMateria;
    }


    public int getPeriodoCursado() {
        return periodoCursado;
    }

    public void setPeriodoCursado(int periodoCursado) {
        this.periodoCursado = periodoCursado;
    }
}
