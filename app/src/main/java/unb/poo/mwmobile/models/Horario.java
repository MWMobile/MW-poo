package unb.poo.mwmobile.models;

/**
 * Created by sousa on 19/09/2015.
 */
public class Horario {

    private final int hora;
    private final int dia;

    public Horario(int hora, int dia) {
        this.hora = hora;
        this.dia = dia;
    }

    public int getHora() {
        return hora;
    }

    public int getDia() {
        return dia;
    }

}
