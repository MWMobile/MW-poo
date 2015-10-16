package unb.poo.mwmobile.models;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.SyncStateContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

import unb.poo.mwmobile.db.DBCore;

/**
 * Created by sousa on 19/09/2015.
 */

public class User implements Parcelable{

    private final int matricula;
    private String senha;
    private String nome;
    private String curso;
    private int periodo;
    private double IRA;

  /* em vez de setar uma array list e tal, o certo seria ter um addMateria na arraylist
  * nessa estrutura ele deve poder procurar materias na lista, adicionar e remover
  * TODO criar um addMateria ou addMateriaCursada (revisar os mÃ©todos criados)
  * TODO criar um delMateria ou delMateriaCursada
  * TODO criar um getMateria ou getMateriaCursada (revisar os mÃ©todos criados)
  * */

    private ArrayList<Materia> materias = new ArrayList<Materia>();
    private ArrayList<MateriaCursada> historico = new ArrayList<MateriaCursada>();



    public User(int matricula) {
        this.matricula = matricula;
        this.materias = new ArrayList<>();
        this.historico = new ArrayList<>();
    }

    // Por enquanto, sÃ³ transmite o nome, o IRA e a matrÃ­cula do usuÃ¡rio.
    public User(Parcel in) {
        nome = in.readString();
        matricula = in.readInt();
        senha = in.readString();
        IRA = in.readDouble();
        curso = in.readString();
        periodo = in.readInt();
        materias = new ArrayList<>();
        historico = new ArrayList<>();

        in.readTypedList(materias,Materia.CREATOR);
        in.readTypedList(historico, MateriaCursada.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(nome);
        dest.writeInt(matricula);
        dest.writeString(senha);
        dest.writeDouble(IRA);
        dest.writeString(curso);
        dest.writeInt(periodo);
        dest.writeTypedList(materias);
        dest.writeTypedList(historico);
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
        setIRA();
        this.historico = historico;
    }

    public double getIRA() {
        return IRA;
    }

    private void setIRA() {
        /**
         * DTb:disciplinas OBRIGATORIAS trancadas
         * DTp:disciplinas OPTATIVAS trancadas
         * DC: disciplinas matriculadas
         * Pi:peso da mencao
         * Pei:Periodo em que uma disciplina foi cursada
         * CRi:crÃ©ditos de uma disciplina
         */
        double constante;
        double disc = 0,disc2 = 0;
        double variavel;

        int Peso_mencao;
        int Periodo_disciplina;
        int Credito_disciplina;

        //--------conta alguns parametros das disciplinas
        int DTb = 0;
        int DTp = 0;
        int DC;

        for (MateriaCursada materia : historico) {
            if (materia.obrigatoriaTrancada()) {
                DTp++;
            }
            if (materia.optativaTrancada()) {
                DTb++;
            }
        }

        DC = historico.size();
        constante = 1 - ((0.6 * DTb + 0.4D * DTp)/DC);
        //-----------------------------------------

        for(MateriaCursada materia : historico){
            Peso_mencao = materia.getPesoMencao();
            Periodo_disciplina = materia.getPeriodoCursado();
            Credito_disciplina = materia.getCreditos();

            disc = disc+(Periodo_disciplina * Peso_mencao * Credito_disciplina);
            disc2 = disc2+(Credito_disciplina * Periodo_disciplina);
        }

        variavel = disc/disc2;

        this.IRA = constante * variavel;
    }

    public boolean login(Context context){

        // TODO autenticacao com o MW
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

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public void addMateria(Materia materia) {
        materias.add(materia);
    }

    public void addMateriaCursada(MateriaCursada cursada) {
        historico.add(cursada);
    }

    public Materia getMateria(String nomeMateria) {
        Materia ref = null;
        for (Materia iterator : materias) {
            if (iterator.getNome().equals(nomeMateria)) {
                ref = iterator;
            }
        }
        return ref;
    }

    public MateriaCursada getMateriaCursada(String nomeMateria) {
        MateriaCursada ref = null;
        for (MateriaCursada auxiliar : historico) {
            if (auxiliar.getNome().equals(nomeMateria)) {
                ref = auxiliar;
            }
        }
        return ref;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }
}