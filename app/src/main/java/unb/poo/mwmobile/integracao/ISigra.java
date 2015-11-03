package unb.poo.mwmobile.integracao;

/**
 * Quando fizer o pedido para o Sigra
 * para receber a resposta a activity
 * tem que esta registrada como ouvinte do
 * EventBus por uma questão de multi Thread
 * para isso a activity deve estar registrada como
 * ouvinte do EventBus e implementar o metodo
 * onEvent ou o onEventMainThread
 * a mensagem vai ser passada pela classe
 * MessageServerEB.
 * Por padrao do EB sempre que for lançado um
 * evendo pela MessageServerEB todos os metodos que
 * ouvem o evento com essa assinatura vai ser chamados
 * entao usar o Header que tbm é passado pelo evento
 * para verifica se realmente é aquele metodo que deve ser
 * chamado ou não.
 * 
 * Para o uso do eventBus segue a referencia:
 *      https://github.com/greenrobot/EventBus
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
     * da matricula do mesmo como parametro
     *
     * @param matricula
     */
    public void requestIRA(String matricula);

    /**
     * Pede o curso de um usuario a partir
     * da matricula do mesmo como parametro
     *
     * @param matricula
     */
    public void requestCurso(String matricula);

    /**
     * Pede as materia de um usuario a partir
     * da matricula do mesmo como parametro
     *
     * @param matricula
     */
    public void requestMaterias(String matricula);

    /**
     * Pede o periodo/semestre de um usuario a partir
     * da matricula do mesmo como parametro
     *
     * @param matricula
     */
    public void requestPeriodo(String matricula);


}
