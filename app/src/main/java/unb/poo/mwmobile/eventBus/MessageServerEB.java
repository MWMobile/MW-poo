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

    private String header;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
