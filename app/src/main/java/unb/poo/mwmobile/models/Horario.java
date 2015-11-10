package unb.poo.mwmobile.models;

/**
 * @author Andrei Sousa
 * @since 19/09/2015
 */

/**
 * A classe Horario define qual dia e hora
 * em que as aulas de uma materia sao ministradas.
 */

public class Horario {

    /**
     * Os parametros dessa classe sao privados e constantes.
     */

    private int hora;
    private int dia;

    public Horario(int h, int d) {
        this.hora = h;
        this.dia = d;
    }

    public Horario(){}

    /**
     * Metodo de definicao de valor dos parametros privados da classe Horario.
     * @param hora tipo: int
     */

    public void setHora (int hora){
        this.hora = hora;
    }

    public void setDia (int dia){
        this.dia = dia;
    }


    /**
     * Metodo de retorno das horas das aulas de uma materia.
     * @return hora tipo: int
     */

    public int getHora() {
        return hora;
    }

    /**
     * Metodo de retorno dos dias das aulas de uma materia.
     * @return dia tipo: int
     */

    public int getDia() {
        return dia;
    }

}
