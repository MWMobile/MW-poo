package unb.poo.mwmobile.integracao.ServerInterface;

import android.content.Context;

import java.util.HashMap;

import unb.poo.mwmobile.integracao.ServerRequest.MiddleServer;
import unb.poo.mwmobile.integracao.ServerRequest.Transaction;
import unb.poo.mwmobile.integracao.ServerRequest.WrapObjToNetwork;

/**
 * Classe que faz a interface entre quem pede
 * o requeste é a classe que se comunica com o servidor
 *
 * Created by Eduardo Scartezini on 30/10/2015.
 */
public class SigraUser implements ISigraUser {

    //Constantes
    private static final String AUTENTICA = "login";
    private static final String IRA = "getIra";
    private static final String USER = "getUser";
    private static final String HISTORICO = "getHistorico";
    private static final String PERIODO = "getPreriodo";
    private static final String NOME = "getNome";
    private static final String CURSO = "getCurso";
    private static final String MATERIAS = "getMaterias";



    private MiddleServer server;
    private String header;
    private String tag;
    private Transaction transaction;
    private WrapObjToNetwork obj;
    private HashMap<String,String> params;

    /**
     * Construtor padrao do SigraUser
     *
     * @param context
     *      contexto da aplicacao
     * @param transaction
     *      Quem implementa Transaction que vai ouvir o evento
     *      de chegada de dados do servidor
     * @param tag
     *      Tag para parrar o request caso o app entre no
     *      onStop
     */
    public SigraUser(Context context, Transaction transaction, String tag) {
        server = MiddleServer.getInstance(context);
        this.tag = tag;
        this.transaction = transaction;
        header = new  String();
    }


    @Override
    public void autentica(String matricula, String senha) {
        params = new HashMap<>();
        params.put("matricula",matricula);
        params.put("senha",senha);

        server.execute(transaction,tag, SigraUser.AUTENTICA,params);
    }

    @Override
    public void requestIRA(String token) {
        params = new HashMap<>();
        params.put("token",token);

        server.execute(transaction,tag, SigraUser.IRA,params);
    }

    @Override
    public void requestCurso(String token) {
        params = new HashMap<>();
        params.put("token", token);

        server.execute(transaction,tag, SigraUser.CURSO,params);

    }

    @Override
    public void requestMaterias(String token) {
        params = new HashMap<>();
        params.put("token", token);

        server.execute(transaction,tag, SigraUser.MATERIAS,params);
    }

    @Override
    public void requestPeriodo(String token) {
        params = new HashMap<>();
        params.put("token", token);

        server.execute(transaction,tag, SigraUser.PERIODO,params);

    }

    @Override
    public void requestHistorico(String token) {
        params = new HashMap<>();
        params.put("token", token);

        server.execute(transaction,tag, SigraUser.HISTORICO,params);

    }

    @Override
    public void requestNome(String token) {
        params = new HashMap<>();
        params.put("token", token);

        server.execute(transaction,tag, SigraUser.NOME,params);

    }

    @Override
    public void mockUser(String token) {
        params = new HashMap<>();
        params.put("token", token);

        server.execute(transaction,tag, SigraUser.USER,params);
    }
}
