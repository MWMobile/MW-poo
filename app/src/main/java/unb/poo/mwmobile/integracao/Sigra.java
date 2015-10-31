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
    private Map<String, String> header;
    private Map<String, String> params;

    public Sigra(Context context) {
        server = new MiddleServer(context);
        header = new  HashMap<String, String>();
        params  = new  HashMap<String, String>();
    }

    @Override
    public void autentica(String matricula, String senha) {


        header.put("header","login");
        params.put("matricula",matricula);
        params.put("senha",senha);

        server.get(header,params);
    }

    @Override
    public void requestIRA(String matricula) {
        header.put("header","IRA");
        params.put("matricula",matricula);

        server.get(header, params);
    }

    @Override
    public void requestCurso(String matricula) {
        header.put("header","curso");
        params.put("matricula",matricula);

        server.get(header,params);
    }

    @Override
    public void requestMaterias(String matricula) {
        header.put("header","materias");
        params.put("matricula",matricula);

        server.get(header,params);
    }

    @Override
    public void requestPeriodo(String matricula) {
        header.put("header","periodo");
        params.put("matricula",matricula);

        server.get(header,params);
    }

}
