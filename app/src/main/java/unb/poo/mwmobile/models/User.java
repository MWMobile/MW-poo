package unb.poo.mwmobile.models;

/**
 * Importa as classes embutidas nesse IDE
 * para implementar certos metodos da classe User.
 */

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
 * @author Andrei Sousa
 * @since 19/09/2015
 */

/**
 * A classe User representa um aluno da universidade pelo
 * numero da matricula e nome. Alem disso, informa qual curso
 * faz, em que periodo atua e o seu IRA. Como eh um usuario do
 * aplicativo, ele tem uma senha de acesso.
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

    /**
     * Instancia o vetor das classes de Materias e de MateriasCursadas.
     */

    private ArrayList<Materia> materias = new ArrayList<Materia>();
    private ArrayList<MateriaCursada> historico = new ArrayList<MateriaCursada>();

    /**
     * Metodo de declaracao de matricula de um usuario,
     * das materias que esta fazendo e o historico
     * que contem todas as materias cursadas.
     * @param matricula tipo: int; privado e constante
     */

    public User(int matricula) {
        this.matricula = matricula;
        this.materias = new ArrayList<>();
        this.historico = new ArrayList<>();
    }

    /**
     * Transmite todas as informacoes do usuario pelo Parcel.
     * @param in
     */
    public User(Parcel in) {
        setIRA();

        nome = in.readString();
        matricula = in.readInt();
        senha = in.readString();
        IRA = in.readDouble();
        curso = in.readString();
        periodo = in.readInt();
        materias = new ArrayList<>();
        historico = new ArrayList<>();

        in.readTypedList(materias, Materia.CREATOR);
        in.readTypedList(historico, MateriaCursada.CREATOR);
    }

    /**
     * Metodo de serializacao das informacoes
     * do usuario da matricula pelo Parcel.
     * @param dest
     * @param flags
     */

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

    /**
     * Metodo de retorno do valor da matricula do usuario.
     * @return matricula tipo: int
     */

    public int getMatricula() {
        return matricula;
    }

    /**
     * Metodo de retorno do valor da senha do usuario.
     * @return senha classe: String
     */

    public String getSenha() {
        return senha;
    }

    /**
     * Metodo de declaracao do valor da senha do usuario.
     * @param senha classe: String
     */

    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * Metodo de retorno do nome do usuario.
     * @return nome classe: String
     */

    public String getNome() {
        return nome;
    }

    /**
     * Metodo de declaracao do nome do usuario.
     * @param nome classe: String
     */

    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Metodo de retorno do curso do usuario.
     * @return curso classe: String
     */

    public String getCurso() {
        return curso;
    }

    /**
     * Metodo de declaracao do curso do usuario.
     * @param curso classe: String
     */

    public void setCurso(String curso) {
        this.curso = curso;
    }

    /**
     * Metodo de retorno do vetor de materias que o usuario esta fazendo.
     * @return materias classe: Materias
     */

    public ArrayList<Materia> getMaterias() {
        return materias;
    }

    /**
     * Metodo de declaracao do vetor de materias que o usuario esta fazendo.
     * @param materias classe: Materias
     */

    public void setMaterias(ArrayList<Materia> materias) {
        this.materias = materias;
    }

    /**
     * Metodo de retorno do vetor de materias que o usuario ja cursou.
     * @return historico classe: MateriaCursada
     */

    public ArrayList<MateriaCursada> getHistorico() {
        return historico;
    }

    /**
     * Metodo de declaracao do vetor de materias que o usuario ja cursou.
     * @param historico classe: MateriaCursada
     */

    public void setHistorico(ArrayList<MateriaCursada> historico) {
        setIRA();
        this.historico = historico;
    }

    /**
     * Metodo de retorno do valor do IRA do usuario
     * @return IRA tipo: double
     */

    public double getIRA() {
        setIRA();
        return IRA;
    }

    /**
     * Metodo do calculo do IRA
     */

    private void setIRA() {
        /**
         * Parametros da formula do IRA:
         * DTb: disciplinas OBRIGATORIAS trancadas
         * DTp: disciplinas OPTATIVAS trancadas
         * DC: disciplinas matriculadas
         * Pi: peso da mencao
         * Pei: Periodo em que uma disciplina foi cursada
         * CRi: creditos de uma disciplina
         *
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
        constante = 1 - (((0.6 * DTb) + (0.4 * DTp))/DC);
        //-----------------------------------------

        for(MateriaCursada materia : historico){
            Peso_mencao = materia.getPesoMencao();
            Periodo_disciplina = materia.getPeriodoCursado();
            Credito_disciplina = materia.getCreditos();

            disc = disc + (Periodo_disciplina * Peso_mencao * Credito_disciplina);
            disc2 = disc2 + (Credito_disciplina * Periodo_disciplina);
        }

        variavel = disc/disc2;

        this.IRA = constante * variavel;
    }

    /**
     * Metodo de login de um usuario que pede o
     * numero de matricula e a senha como acesso.
     * @param context
     * @return valor booleano TRUE: entra; FALSE: bloqueia o acesso
     */

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

    /**
     * Adiciona as materias no banco de dados do usuario.
     * @param user
     * @param context
     */

    private void saveOnDb(User user, Context context){
        DBCore db = new DBCore(context);
        db.addUser(user, materias, historico);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Instancia a classe User pela classe Parcel.
     */

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    /**
     * Metodo de adicao de materias que o usuario esta cursando.
     * @param materia
     */

    public void addMateria(Materia materia) {
        materias.add(materia);
    }

    /**
     * Metodo de adicao de materias que foram feitas pelo usuario.
      * @param cursada
     */

    public void addMateriaCursada(MateriaCursada cursada) {
        historico.add(cursada);
    }

    /**
     * Metodo de referencia a uma materia que o usuario esta cursando.
     * @param nomeMateria classe: String
     * @return ref classe: Materia
     */

    public Materia getMateria(String nomeMateria) {
        Materia ref = null;
        for (Materia iterator : materias) {
            if (iterator.getNome().equals(nomeMateria)) {
                ref = iterator;
            }
        }
        return ref;
    }

    /**
     * Metodo de referencia a uma materia que o usuario ja cursou.
     * @param nomeMateria classe: String
     * @return ref classe: Materia
     */

    public MateriaCursada getMateriaCursada(String nomeMateria) {
        MateriaCursada ref = null;
        for (MateriaCursada auxiliar : historico) {
            if (auxiliar.getNome().equals(nomeMateria)) {
                ref = auxiliar;
            }
        }
        return ref;
    }

    /**
     * Metodo de retorno do periodo em que o usuario esta cursando.
     * @return periodo tipo: int
     */

    public int getPeriodo() {
        return periodo;
    }

    /**
     * Metodo de declaracao do periodo em que o usuario esta cursando.
     * @param periodo tipo: int
     */

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }
}