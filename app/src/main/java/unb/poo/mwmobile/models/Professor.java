package unb.poo.mwmobile.models;

/**
 * @author Andrei Sousa
 * @since 19/09/2015
 */

/**
 * A classe Professor contem o nome do docente
 * que ministra as aulas de uma materia.
 */
public class Professor {

    /**
     * O parametro nome eh privado e constante.
     */
    private final String nome;

    /**
     * Metodo de definicao do nome do professor de uma materia.
     * @param nome classe: String
     */

    public Professor(String nome) {
        this.nome = nome;
    }

    /**
     * Metodo de retorno do nome do docente de uma materia.
     * @return nome classe: String
     */

    public String getNome() {
        return nome;
    }

}
