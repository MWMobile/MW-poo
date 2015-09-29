package unb.poo.mwmobile.models;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.SyncStateContract;
import android.util.Log;

/**
 * Created by sousa on 19/09/2015.
 */

public class User implements Parcelable{

    private final int matricula;
    private String senha;
    private String nome;
    private Materia[] materias;
    private Materia[] historico;
    private double IRA;

    public User(int matricula) {
        this.matricula = matricula;
    }

    public User(Parcel in) {
        this.matricula = in.readInt();
        this.nome = in.readString();        // Por enquanto, só transmite o nome e a matrícula do usuário.
        Log.d("Construtor zoado pra c*",this.nome);
    }

    public int getMatricula() {
        return matricula;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Materia[] getMaterias() {
        return materias;
    }

    public void setMaterias(Materia[] materias) {
        this.materias = materias;
    }

    public Materia[] getHistorico() {
        return historico;
    }

    public void setHistorico(Materia[] historico) {
        this.historico = historico;
    }

    public double getIRA() {
        return IRA;
    }

    public void setIRA(double IRA) {
        this.IRA = IRA;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.getMatricula());
        dest.writeString(this.getNome());
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
