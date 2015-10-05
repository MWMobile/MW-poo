package unb.poo.mwmobile.models;

/**
 * Created by sousa on 19/09/2015.
 */
public class Materia {
    private Professor professor;
    private Horario[] horarios;
    private int codigo;
    private String turma;
    private String nome;
    private String sala;
    private int Periodo_cursado;
    private boolean OBRIGATORIA,TRANCADA=false;
    private int creditos;
    private int Peso_mencao;
    
    public void setPeso_mencao(String MENCAO){
        int mencao;
        switch(MENCAO){
            case MENCAO=="SR":mencao=0;break;
			case MENCAO=="II":mencao=1;break;
			case MENCAO=="MI":mencao=2;break;
			case MENCAO=="MM":mencao=3;break;
			case MENCAO=="MS":mencao=4;break;
			case MENCAO=="SS":mencao=5;break;
        }
        this.Peso_mencao=mencao;
    }
    public int getPeso_mencao(){
        return Peso_mencao;
    }

    public void setObrigatoria(boolean OBR){
        this.OBRIGATORIA=OBR;
    }
    public boolean getObrigatoria(){return OBRIGATORIA;}
    public void setPeriodo(int Periodo){
        this.Periodo_cursado=Periodo;
    }
    public int getPeriodo_cursado(){return Periodo_cursado;}
    
    public void trancar(){
        this.TRANCADA=true;
    }
    public boolean getTrancada(){
        return TRANCADA;
    }
    
    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Horario[] getHorarios() {
        return horarios;
    }

    public void setHorarios(Horario[] horarios) {
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

}
