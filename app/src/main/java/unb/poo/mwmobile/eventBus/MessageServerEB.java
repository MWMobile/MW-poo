package unb.poo.mwmobile.eventBus;

import java.util.Map;

/**
 * Classe anemica para enviar mensagem pelo
 * padrao do EventBus
 *
 *      https://github.com/greenrobot/EventBus
 *
 * Created by Eduardo Scartezini on 30/10/2015.
 */
public class MessageServerEB {
    private String response;

    //TODO mudar apenas para string
    //TODO problema em usar header.getValue()
    //Server para definir quando cada evento deve ser ouvido
    //Na classe que ouvir o evento sera melhor explicado
    private Map<String,String> header;



    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }
}
