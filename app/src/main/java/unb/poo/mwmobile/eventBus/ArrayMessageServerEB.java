package unb.poo.mwmobile.eventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Scartezini on 03/11/2015.
 */
public class ArrayMessageServerEB {
    private List<String> response;
    private String header;

    public List<String> getResponse() {
        return response;
    }

    public void setResponse(List<String> response) {
        this.response = response;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
