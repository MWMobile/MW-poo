package unb.poo.mwmobile.config;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by sousa on 03/11/2015.
 */
public class GCMTest {

    @Test
    public void testGCMConfig(){
        GCM gcm = new GCM();
        assertNotNull(gcm);
    }

    @Test
    public void testGetSentTokenToServer(){
        assertNotNull(GCM.getSentTokenToServer());
    }

    @Test
    public void testGetRegistrationComplete() {
        assertNotNull(GCM.getRegistrationComplete());
    }

    @Test
    public void testGetTopics(){
        assertNotNull(GCM.getTopics());
    }
}