package unb.poo.mwmobile.models;

/**
 * Created by sousa on 19/09/2015.
 */
public class Materia {

    private Professor professor;
    private Horario[] horarios;

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
}
