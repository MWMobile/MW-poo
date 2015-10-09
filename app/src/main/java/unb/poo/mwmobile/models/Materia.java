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
        switch(MENCAO){
            default:
            case "SR":
                this.Peso_mencao  = 0;
                break;
			case "II":
                this.Peso_mencao  = 1;
                break;
			case "MI":
                this.Peso_mencao  = 2;
                break;
			case "MM":
                this.Peso_mencao  = 3;
                break;
			case "MS":
                this.Peso_mencao  = 4;
                break;
			case "SS":
                this.Peso_mencao  = 5;
                break;
        }
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

    public boolean obrigatoriaTrancada() {
        return (OBRIGATORIA && TRANCADA);
    }

    public boolean optativaTrancada() {
        return (!OBRIGATORIA && TRANCADA);
    }

}
