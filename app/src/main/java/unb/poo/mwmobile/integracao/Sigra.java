package unb.poo.mwmobile.integracao;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

/**
 *      Classe que faz a interface com o get no MiddleServer
 *  toda vez que user um metodo dessa classe fica na reposabilidade
 *  de quem usa implementar o listener do EventBus pois por
 *  uma questao de thread é por ele que sera enviado o dado pedido,
 *  sera eviado atravez da classe MessageServerEB que é um POJO
 *
 *  Para o uso do eventBus segue a referencia:
 *      https://github.com/greenrobot/EventBus
 *
 * Created by Eduardo Scartezini on 30/10/2015.
 */
public class Sigra implements ISigra {

    private MiddleServer server;
    private String header;
    private Map<String, String> params;

    public Sigra(Context context) {
        server = new MiddleServer(context);
        header = new  String();
        params  = new  HashMap<String, String>();
    }

    @Override
    public void autentica(String matricula, String senha) {


        header = "login";

        params.put("matricula",matricula);
        params.put("senha",senha);

        server.get(header,params);
    }

    @Override
    public void requestIRA(String token) {

        header = "getIra";
        params.put("token", token);

        server.get(header, params);
    }

    @Override
    public void requestCurso(String token) {
        header = "getCurso";
        params.put("token", token);

        server.get(header,params);
    }

    @Override
    public void requestMaterias(String token) {
        header = "getMaterias";
        params.put("token", token);

        server.get(header,params);
    }

    @Override
    public void requestPeriodo(String token) {
        header = "getPeriodo";
        params.put("token", token);

        server.get(header,params);
    }

    @Override
    public void requestHistorico(String token) {
        header = "getHistorico";
        params.put("token", token);

        server.get(header,params);
    }

    @Override
    public void requestNome(String token) {
        header = "getNome";
        params.put("token", token);

        server.get(header,params);
    }

}
