package unb.poo.mwmobile.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by sousa on 19/09/2015.
 */
public class Materia implements Parcelable {
    protected Professor professor;
    protected ArrayList<Horario> horarios;
    protected int codigo;
    protected String turma;
    protected String nome;
    protected String sala;

    protected int creditos;

    public Materia() {

    }
    public Materia(Parcel in) {
        professor = new Professor(in.readString());
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
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (professor != null) {
            dest.writeString(professor.getNome());
        }
        else {
            dest.writeString("A DESIGNAR");
        }
        dest.writeInt(codigo);
        dest.writeString(turma);
        dest.writeString(nome);
        dest.writeString(sala);
        dest.writeInt(creditos);
        if (horarios == null) {
            horarios = new ArrayList<>();
        }
        int[] horarioHora = new int[horarios.size()];
        int[] horarioDia = new int[horarios.size()];
        dest.writeInt(horarios.size());
        int i = 0;
        for (Horario hora : horarios) {
            horarioHora[i] = hora.getHora();
            horarioDia[i] = hora.getDia();
            i++;
        }
        dest.writeIntArray(horarioHora);
        dest.writeIntArray(horarioDia);
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public ArrayList<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(ArrayList<Horario> horarios) {
        this.horarios = horarios;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public void addHorario(int hora,int dia) {
        this.horarios.add(new Horario(hora,dia));
    }


    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Materia> CREATOR = new Parcelable.Creator<Materia>() {
        public Materia createFromParcel(Parcel in) {
            return new Materia(in);
        }

        public Materia[] newArray(int size) {
            return new Materia[size];
        }
    };
}
