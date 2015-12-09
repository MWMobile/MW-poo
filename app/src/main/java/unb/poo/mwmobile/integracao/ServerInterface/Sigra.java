package unb.poo.mwmobile.integracao.ServerInterface;

import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;

import unb.poo.mwmobile.integracao.ServerRequest.MiddleServer;
import unb.poo.mwmobile.integracao.ServerRequest.Transaction;
import unb.poo.mwmobile.integracao.ServerRequest.WrapObjToNetwork;
import unb.poo.mwmobile.models.Materia;

/**
 * Classe que faz a interface entre quem pede
 * o requeste é a classe que se comunica com o servidor
 *
 * Created by Eduardo Scartezini on 30/10/2015.
 */
public class Sigra implements ISigra {

    //Constantes
    private static final String AUTENTICA = "login";
    private static final String IRA = "getIra";
    private static final String USER = "getUser";
    private static final String HISTORICO = "getHistorico";
    private static final String PERIODO = "getPreriodo";
    private static final String NOME = "getNome";
    private static final String CURSO = "getCurso";
    private static final String MATERIAS = "getMaterias";
    private static final String DBMATERIAS = "getDBMaterias";
    private static final String MATRICULA = "requestMatricula";
    private static final String CANCELARMATRICULA = "cancelMatricula";


    private MiddleServer server;
    private String header;
    private String tag;
    private Transaction transaction;
    private WrapObjToNetwork obj;
    private HashMap<String,String> params;
    private Gson gson;

    /**
     * Construtor padrao do Sigra
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
    public Sigra(Context context, Transaction transaction, String tag) {
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

        server.execute(transaction,tag, Sigra.AUTENTICA,params);
    }

    @Override
    public void requestIRA(String token) {
        params = new HashMap<>();
        params.put("token",token);

        server.execute(transaction,tag, Sigra.IRA,params);
    }

    @Override
    public void requestCurso(String token) {
        params = new HashMap<>();
        params.put("token", token);

        server.execute(transaction,tag, Sigra.CURSO,params);

    }

    @Override
    public void requestMaterias(String token) {
        params = new HashMap<>();
        params.put("token", token);

        server.execute(transaction,tag, Sigra.MATERIAS,params);
    }

    @Override
    public void requestPeriodo(String token) {
        params = new HashMap<>();
        params.put("token", token);

        server.execute(transaction,tag, Sigra.PERIODO,params);

    }

    @Override
    public void requestHistorico(String token) {
        params = new HashMap<>();
        params.put("token", token);

        server.execute(transaction,tag, Sigra.HISTORICO,params);

    }

    @Override
    public void requestNome(String token) {
        params = new HashMap<>();
        params.put("token", token);

        server.execute(transaction,tag, Sigra.NOME,params);

    }

    @Override
    public void mockUser(String token) {
        params = new HashMap<>();
        params.put("token", token);

        server.execute(transaction,tag, Sigra.USER,params);
    }

    @Override
    public void requestDBMaterias(String token) {
        params = new HashMap<>();
        params.put("token", token);

        server.execute(transaction,tag, Sigra.DBMATERIAS,params);
    }

    @Override
    public void requestMatricula(String token, Materia materia) {
        params = new HashMap<>();
        gson = new Gson();

        params.put("token", token);
        params.put("Materia",gson.toJson(materia));

        server.execute(transaction,tag,Sigra.MATRICULA,params);
    }

    @Override
    public void cancelarMatricula(String token, Materia materia) {
        params = new HashMap<>();
        gson = new Gson();

        params.put("token", token);
        params.put("Materia",gson.toJson(materia));

        server.execute(transaction,tag,Sigra.CANCELARMATRICULA,params);
    }


}
