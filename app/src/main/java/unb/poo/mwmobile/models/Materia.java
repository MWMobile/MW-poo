package unb.poo.mwmobile.models;

/**
 * Importa as classes embutidas nesse IDE para
 * executar algumas funcionalidades da classe Materia.
 */

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * @author Andrei Sousa
 * @since 19/09/2015
 */

/**
 * A classe Materia define qual eh o código e o nome de uma materia,
 * quais professores vao ministrar a aula, quais sao os horarios,
 * turmas e salas disponiveis, quantos creditos valem. Ela utiliza
 * a interface Parcelable.
 */
public class Materia extends Horario implements Parcelable {

    /**
     * Os parametros sao definidos como protected
     * por ser uma superclasse da classe MateriaCursada.
     */
    protected Professor professor;
    protected ArrayList<Horario> horarios;
    protected int codigo;
    protected String turma;
    protected String nome;
    protected String sala;
    protected int creditos;

    /**
     *Metodo de leitura das informacoes da materia pelo Parcel.
     * @param in classe: Parcel
     */

    public Materia(Parcel in) {
        professor = new Professor(in.readString());
        codigo = in.readInt();
        turma = in.readString();
        nome = in.readString();
        sala = in.readString();
        creditos = in.readInt();
    }

    public Materia() {

    }

    /**
     * Metodo de serializacao de todas as
     * informacoes da materia com o uso de Parcel.
     * @param dest classe: Parcel
     * @param flags tipo: int
     */

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        /**
         * Quando nao ha professores numa materia, sera escrita
         * uma frase de aviso determinada pela condiçao else.
         */
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
    }

    /**
     * Metodo de retorno da classe Professor que contem o nome do docente.
     * @return professor classe: Professor
     */
    public Professor getProfessor() {
        return professor;
    }

    /**
     * Metodo de definicao de qual professor ministrara a materia.
     * @param professor classe: Professor
     */

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    /**
     * Metodo de retorno de um vetor de horarios da materia.
     * @return horarios classe: ArrayList<Horario>
     */

    public ArrayList<Horario> getHorarios() {
        return horarios;
    }

    /**
     * Metodo de definicao dos horarios que a materia sera ministrada.
     * @param horarios classe: ArrayList<Horario>
     */

    public void setHorarios(ArrayList<Horario> horarios) {
        this.horarios = horarios;
    }

    /**
     * Metodo de retorno do codigo da materia.
     * @return codigo tipo: int
     */

    public int getCodigo() {
        return codigo;
    }

    /**
     * Metodo de definicao do codigo da materia.
     * @param codigo tipo: int
     */

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Metodo de retorno de uma turma da materia.
     * @return turma classe: String
     */

    public String getTurma() {
        return turma;
    }

    /**
     * Metodo de definicao de uma turma da materia.
     * @param turma classe: String
     */

    public void setTurma(String turma) {
        this.turma = turma;
    }

    /**
     * Metodo de retorno do nome da materia.
     * @return nome classe: String
     */

    public String getNome() {
        return nome;
    }

    /**
     * Metodo de definicao do nome da materia.
     * @param nome classe: String
     */

    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Metodo de retorno de uma sala de aula da materia.
     * @return sala classe: String
     */

    public String getSala() {
        return sala;
    }

    /**
     * Metodo de definicao de uma sala de aula da materia.
     * @param sala classe: String
     */

    public void setSala(String sala) {
        this.sala = sala;
    }

    /**
     * Metodo de retorno de um valor de creditos que a materia concede.
     * @return creditos tipo: int
     */

    public int getCreditos() {
        return creditos;
    }

    /**
     * Metodo de definicao de um valor de creditos que a materia concede.
     * @param creditos tipo: int
     */

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }


    /*public void saveOnDbM(Materia materia, Context context){
        DBMateria db = new DBMateria(context);
        db.onCreate(materia, horarios);
    }*/

    /**
     * Retorna o bitmask como zero, ou seja, nenhum bit vai indicar
     * a um tipo de objeto especial ordenado pela interface Parcelable.
     * @return 0
     */

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Metodo que instancia uma classe Materia pela classe Parcel.
     */

    public static final Parcelable.Creator<Materia> CREATOR = new Parcelable.Creator<Materia>() {
        public Materia createFromParcel(Parcel in) {
            return new Materia(in);
        }

        public Materia[] newArray(int size) {
            return new Materia[size];
        }
    };
}
