package unb.poo.mwmobile.singleton;

import java.util.ArrayList;

/**
 * Created by sousa on 06/12/2015.
 */
public class SingletonSemana {

    private static final SingletonSemana INSTANCE = new SingletonSemana();

    ArrayList<String> dias = new ArrayList<>();

    private SingletonSemana(){
        this.dias.add("Segunda");
        this.dias.add("Terca");
        this.dias.add("Quarta");
        this.dias.add("Quinta");
        this.dias.add("Sexta");
        this.dias.add("Sabado");
    }

    public static SingletonSemana getInstance() {
        return INSTANCE;
    }

    public ArrayList<String> getDias() {
        return this.dias;
    }

}
