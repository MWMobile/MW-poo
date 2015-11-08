package unb.poo.mwmobile.integracao;

/**
 * Interface com os metodos que pode
 * ser pedido ao servidor
 *
 * Created by scartezini on 16/10/2015.
 */
public interface ISigra {

    /**
     * Autentica o loguin de um usuario com o servidor
     *
     * @param matricula
     * @param senha
     */
    public void autentica(String matricula, String senha);

    /**
     * Pede o ira de um usuario a partir
     * da token do mesmo como parametro
     *
     * @param token
     */
    public void requestIRA(String token);

    /**
     * Pede o curso de um usuario a partir
     * da token do mesmo como parametro
     *
     * @param token
     */
    public void requestCurso(String token);

    /**
     * Pede as materia de um usuario a partir
     * da token do mesmo como parametro
     *
     * @param token
     */
    public void requestMaterias(String token);

    /**
     * Pede o periodo/semestre de um usuario a partir
     * da token do mesmo como parametro
     *
     * @param token
     */
    public void requestPeriodo(String token);

    /**
     *  Pede o Historico de um usuario a partir
     * da token do mesmo como parametro
     *
     * @param token
     */
    public void requestHistorico(String token);


    /**
     * Pede O nome de um usuario a partir
     * da token do mesmo como parametro
     *
     * @param token
     */
    public void requestNome(String token);

    /**
     * pede para o servidor dodo o
     * o usuario a partir do token
     * como parametro
     *
     * @param token
     */
    public void mockUser(String token);
}
