package unb.poo.mwmobile.integracao.ServerInterface;

import unb.poo.mwmobile.models.Materia;

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


    /**
     * Prede para o servidor o banco de dados
     * completos das materias disponivel no
     * sistema
     *
     * @param token
     */
    public void requestDBMaterias(String token);


    /**
     * Faz a requicao do pedido de matricula
     * de uma materia
     *
     * @param materia
     * @param token
     */
    public void requestMatricula(String token, Materia materia);


    /**
     * Faz a requicao para cancelar a matricula de uma
     * materia
     *
     * @param tonken
     * @param materia
     */
    public void cancelarMatricula(String token, Materia materia );
}
