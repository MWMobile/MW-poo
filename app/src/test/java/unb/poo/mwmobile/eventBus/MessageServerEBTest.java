package unb.poo.mwmobile.eventBus;


import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
/**
 * Created by Eduardo Scartezini on 02/11/2015.
 */
public class MessageServerEBTest {

    MessageServerEB messageServerEB = new MessageServerEB();

    @Test
    public void responseTest(){
        messageServerEB.setResponse("teste");

        assertEquals("teste", messageServerEB.getResponse());

        messageServerEB.setResponse("primeiro");
        messageServerEB.setResponse("Segundo");

        assertEquals("Segundo",messageServerEB.getResponse());
    }

    @Test
    public void headerTest(){

        String header = new String();
        header = "teste";

        messageServerEB.setHeader(header);
        assertEquals(header,messageServerEB.getHeader());
    }
}
