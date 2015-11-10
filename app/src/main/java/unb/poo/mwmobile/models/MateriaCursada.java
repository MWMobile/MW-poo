package unb.poo.mwmobile.models;

import android.os.Parcel;
import android.os.Parcelable;

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


    private boolean obrigatoria;                                                                    // Boolean que checa a obrigatoriedade da matéria
    private boolean trancada = false;                                                               // Verifica se a matéria foi trancada.
    private int periodoTerminado;                                                                   //Período em que a matéria foi concluída
    private int reprovacoes;                                                                        //Número de vezes que o aluno reprovou
    private int avaliacaoMateria;
    private String mencao;

    public String getMencao() {
        return mencao;
    }

    public void setMencao(String mencao) {
        this.mencao = mencao;
    }


    public int getReprovacoes() {
        return reprovacoes;
    }

    public void setReprovacoes(int reprovacoes) {
        this.reprovacoes = reprovacoes;
    }



    public int getAvaliacaoMateria() {
        return avaliacaoMateria;
    }

    public void setAvaliacaoMateria(int avaliacaoMateria) {
        this.avaliacaoMateria = avaliacaoMateria;
    }


    public int getPeriodoCursado() {
        return periodoTerminado;
    }

    public void setPeriodoCursado(int periodoCursado) {
        this.periodoTerminado = periodoCursado;
    }

    public boolean obrigatoriaTrancada() {
        return (obrigatoria && trancada);
    }

    public boolean optativaTrancada() {
        return (!obrigatoria && trancada);
    }


    public void setObrigatoria(boolean OBR){
        this.obrigatoria=OBR;
    }
    public boolean getObrigatoria(){return obrigatoria;}

    public void trancar(){
        this.trancada = true;
    }
    public boolean getTrancada(){
        return trancada;
    }

}
