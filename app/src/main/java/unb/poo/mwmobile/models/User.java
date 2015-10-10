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
    private int curso;
    private double IRA;

  /* em vez de setar uma array list e tal, o certo seria ter um addMateria na arraylist
  * nessa estrutura ele deve poder procurar materias na lista, adicionar e remover
  * TODO criar um addMateria ou addMateriaCursada (revisar os métodos criados)
  * TODO criar um delMateria ou delMateriaCursada
  * TODO criar um getMateria ou getMateriaCursada (revisar os métodos criados)
  * */

    private ArrayList<Materia> materias = new ArrayList<Materia>();
    private ArrayList<MateriaCursada> historico = new ArrayList<MateriaCursada>();



    public User(int matricula) {
        this.matricula = matricula;
    }

    // Por enquanto, só transmite o nome, o IRA e a matrícula do usuário.
    public User(Parcel in) {
        this.matricula = in.readInt();
        this.nome = in.readString();
        this.IRA = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeInt(this.getMatricula());
        dest.writeString(this.getNome());
        dest.writeDouble(this.getIRA());
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

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
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
        * CRi:créditos de uma disciplina
        */
        double constante;
        double disc;
        double variavel = 0;

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
            Peso_mencao = materia.getPeso_mencao();
            Periodo_disciplina = materia.getPeriodoCursado();
            Credito_disciplina = materia.getCreditos();

            disc = (Periodo_disciplina * Peso_mencao * Credito_disciplina);
            disc /= (Credito_disciplina * Periodo_disciplina);

            variavel += disc;
        }

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
        for (int i = 0; i < materias.size(); i++) {
            if (materias.get(i).getNome().equals(nomeMateria)) {
                ref = materias.get(i);
            }
        }
        return ref;
    }

    public MateriaCursada getMateriaCursada(String nomeMateria) {
        MateriaCursada ref = null;
        for (int i = 0; i < historico.size(); i++) {
            if (historico.get(i).getNome().equals(nomeMateria)) {
                ref = historico.get(i);
            }
        }
        return ref;
    }
}
