package unb.poo.mwmobile.models;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.SyncStateContract;
import android.util.Log;

import java.util.ArrayList;

import unb.poo.mwmobile.db.DBCore;

/**
 * Created by sousa on 19/09/2015.
 */

public class User implements Parcelable{

    private final int matricula;
    private String senha;
    private String nome;
    private String curso;
    private ArrayList<Materia> materias = new ArrayList<Materia>();
    private ArrayList<MateriaCursada> historico = new ArrayList<MateriaCursada>();
    private double IRA;



    public User(int matricula) {
        this.matricula = matricula;
    }

    public User(Parcel in) {
        this.matricula = in.readInt();
        this.nome = in.readString();        // Por enquanto, só transmite o nome e a matrícula do usuário.
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

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public ArrayList<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(ArrayList<Materia> materias) {
        this.materias = materias;
    }

    public ArrayList<MateriaCursada> getHistorico() {
        return historico;
    }

    public void setHistorico(ArrayList<MateriaCursada> historico) {
        this.historico = historico;
    }

    public double getIRA() {
        return IRA;
    }

    public void setIRA(double IRA) {
        this.IRA = IRA;
    }

    public boolean login(Context context){

//        Aqui viria a autenticacao com o MW
        User fakeUser = new User(123456789);
        fakeUser.setSenha("1234");

        if(this.getMatricula() == fakeUser.getMatricula())
            if (this.getSenha().equals(fakeUser.getSenha())) {
                saveOnDb(this, context);
                return true;
            } else
                return false;
        else
            return false;

    }

    private void saveOnDb(User user, Context context){
        DBCore db = new DBCore(context);
        db.addUser(user);
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
