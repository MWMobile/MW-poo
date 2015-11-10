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
    private String mencaoTag;

    public String getMencaoTag() {
        return mencaoTag;
    }

    public void setMencaoTag(String mencaoTag) {
        this.mencaoTag = mencaoTag;
    }

    public MateriaCursada() {
        super();
        this.mencao = new ArrayList<>();
        this.periodosCursados = new ArrayList<>();
    }

    public MateriaCursada(Parcel in) {
        super(in);

        /*professor = new Professor(in.readString());
        codigo = in.readInt();
        turma = in.readString();
        nome = in.readString();
        sala = in.readString();
        creditos = in.readInt();
        int tam = in.readInt();
        int[] horarioHora = new int[tam];
        int[] horarioDia = new int[tam];
        in.readIntArray(horarioHora);
        in.readIntArray(horarioDia);
        horarios = new ArrayList<>();
        for (int i = 0; i < tam; i++) {
            horarios.add(new Horario(horarioHora[i],horarioDia[i]));
        }*/

        obrigatoria = Boolean.parseBoolean(in.readString());
        trancada = Boolean.parseBoolean(in.readString());
        periodoTerminado = in.readInt();
        reprovacoes = in.readInt();
        avaliacaoMateria = in.readInt();
        pesoMencao = in.readInt();
        mencao = new ArrayList<String>();
        in.readStringList(mencao);
        int nPer = in.readInt();
        int[] pArray = new int[nPer];
        in.readIntArray(pArray);
        periodosCursados = new ArrayList<>();
        for (int i = 0; i < nPer; i++) {
            periodosCursados.add(new Integer(pArray[i]));
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

        /*if (this.professor != null) {
            dest.writeString(this.professor.getNome());
        }
        else {
            dest.writeString("A DESIGNAR");
        }
        dest.writeInt(this.codigo);
        dest.writeString(this.turma);
        dest.writeString(this.nome);
        dest.writeString(this.sala);
        dest.writeInt(this.creditos);
        if (this.horarios == null) {
            this.horarios = new ArrayList<>();
        }
        int[] horarioHora = new int[this.horarios.size()];
        int[] horarioDia = new int[this.horarios.size()];
        dest.writeInt(this.horarios.size());
        int i = 0;
        for (Horario hora : horarios) {
            horarioHora[i] = hora.getHora();
            horarioDia[i] = hora.getDia();
            i++;
        }
        dest.writeIntArray(horarioHora);
        dest.writeIntArray(horarioDia);*/

        dest.writeString(Boolean.toString(obrigatoria));
        dest.writeString(Boolean.toString(trancada));
        dest.writeInt(periodoTerminado);
        dest.writeInt(reprovacoes);
        dest.writeInt(avaliacaoMateria);
        dest.writeInt(pesoMencao);
        dest.writeStringList(mencao);
        int nPer = 0;
        if (periodosCursados != null) {
            nPer = periodosCursados.size();
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
