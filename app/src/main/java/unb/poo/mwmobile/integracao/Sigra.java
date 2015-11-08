package unb.poo.mwmobile.integracao;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

/**

 * Created by Eduardo Scartezini on 30/10/2015.
 */
public class Sigra implements ISigra {

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

    public Sigra(Context context, Transaction transaction, String tag) {
        server = MiddleServer.getInstance(context);
        this.tag = tag;
        this.transaction = transaction;
        header = new  String();
    }


    @Override
    public void autentica(String matricula, String senha) {
        obj = new WrapObjToNetwork();
        obj.setMatricula(matricula);
        obj.setSenha(senha);

        server.execute(transaction,tag,Sigra.AUTENTICA,obj);
    }

    @Override
    public void requestIRA(String token) {
        obj = new WrapObjToNetwork();
        obj.setToken(token);

        server.execute(transaction,tag,Sigra.IRA,obj);
    }

    @Override
    public void requestCurso(String token) {
        obj = new WrapObjToNetwork();
        obj.setToken(token);

        server.execute(transaction,tag,Sigra.CURSO,obj);

    }

    @Override
    public void requestMaterias(String token) {
        obj = new WrapObjToNetwork();
        obj.setToken(token);

        server.execute(transaction,tag,Sigra.MATERIAS,obj);
    }

    @Override
    public void requestPeriodo(String token) {
        obj = new WrapObjToNetwork();
        obj.setToken(token);

        server.execute(transaction,tag,Sigra.PERIODO,obj);

    }

    @Override
    public void requestHistorico(String token) {

        obj = new WrapObjToNetwork();
        obj.setToken(token);

        server.execute(transaction,tag,Sigra.HISTORICO,obj);

    }

    @Override
    public void requestNome(String token) {
        obj = new WrapObjToNetwork();
        obj.setToken(token);

        server.execute(transaction,tag,Sigra.NOME,obj);

    }

    @Override
    public void mockUser(String token) {
        obj = new WrapObjToNetwork();
        obj.setToken(token);

        server.execute(transaction,tag,Sigra.USER,obj);
    }
}
