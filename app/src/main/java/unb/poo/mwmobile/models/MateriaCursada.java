package unb.poo.mwmobile.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by vinic on 04-Oct-15.
 *
 *
 * Classe que herda todas as características de uma matéria comum,
 * mas como já foi cursada é caracterizada por possuir menção,
 * avaliação do aluno sobre  a matéria e número de reprovações.
 * Mais tarde as notas serão utilizadas para o cálculo de IRA.
 */
public class MateriaCursada extends Materia implements Parcelable {

    private ArrayList<String> mencao = new ArrayList<>();                                           //Lista de Menções (uma menção única caso o aluno não tenha reprovado)
    private ArrayList<Integer> periodosCursados = new ArrayList<>();                                //Períodos em que a matéria foi cursada (em caso de reprovação)

    private boolean obrigatoria;                                                                    // Boolean que checa a obrigatoriedade da matéria
    private boolean trancada = false;                                                               // Verifica se a matéria foi trancada.
    private int periodoTerminado;                                                                   //Período em que a matéria foi concluída
    private int reprovacoes;                                                                        //Número de vezes que o aluno reprovou
    private int avaliacaoMateria;                                                                   //Avaliação numérica de 0 a 100% da qualidade da matéria(envolve desde
    private int pesoMencao;

    public MateriaCursada() {
        this.mencao = new ArrayList<>();
        this.periodosCursados = new ArrayList<>();
    }

    public MateriaCursada(Parcel in) {
        super(in);
        this.obrigatoria = Boolean.parseBoolean(in.readString());
        this.trancada = Boolean.parseBoolean(in.readString());
        this.periodoTerminado = in.readInt();
        this.reprovacoes = in.readInt();
        this.avaliacaoMateria = in.readInt();
        this.pesoMencao = in.readInt();
        this.mencao = new ArrayList<String>();
        in.readStringList(this.mencao);
        int nPer = in.readInt();
        int[] pArray = new int[nPer];
        in.readIntArray(pArray);
        this.periodosCursados = new ArrayList<>();
        for (int i = 0; i < nPer; i++) {
            this.periodosCursados.add(new Integer(pArray[i]));
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MateriaCursada> CREATOR = new Creator<MateriaCursada>() {
        @Override
        public MateriaCursada createFromParcel(Parcel in) {
            return new MateriaCursada(in);
        }

        @Override
        public MateriaCursada[] newArray(int size) {
            return new MateriaCursada[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest,int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(Boolean.toString(this.obrigatoria));
        dest.writeString(Boolean.toString(this.trancada));
        dest.writeInt(this.periodoTerminado);
        dest.writeInt(this.reprovacoes);
        dest.writeInt(this.avaliacaoMateria);
        dest.writeInt(this.pesoMencao);
        dest.writeStringList(this.mencao);
        int nPer = 0;
        if (this.periodosCursados != null) {
            nPer = this.periodosCursados.size();
        }
        int[] perArray = new int[nPer];
        dest.writeInt(nPer);
        dest.writeIntArray(perArray);
    }

    public void setPesoMencao(String MENCAO){
        switch(MENCAO){
            default:
            case "SR":
                this.pesoMencao = 0;
                break;
            case "II":
                this.pesoMencao = 1;
                break;
            case "MI":
                this.pesoMencao = 2;
                break;
            case "MM":
                this.pesoMencao = 3;
                break;
            case "MS":
                this.pesoMencao = 4;
                break;
            case "SS":
                this.pesoMencao = 5;
                break;
        }
    }

    public int getPesoMencao(){
        return pesoMencao;
    }

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

    public ArrayList<Integer> getPeriodosCursados() {
        return periodosCursados;
    }

    public void setPeriodosCursados(ArrayList<Integer> periodosCursados) {
        this.periodosCursados = periodosCursados;
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
