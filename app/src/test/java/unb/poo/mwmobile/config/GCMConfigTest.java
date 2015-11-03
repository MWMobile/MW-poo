package unb.poo.mwmobile.config;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by sousa on 03/11/2015.
 */
public class GCMConfigTest {

    @Test
    public void testGCMConfig(){
        GCMConfig gcmConfig = new GCMConfig();
        assertNotNull(gcmConfig);
    }

    @Test
    public void testGetSentTokenToServer(){
        assertNotNull(GCMConfig.getSentTokenToServer());
    }

    @Test
    public void testGetRegistrationComplete() {
        assertNotNull(GCMConfig.getRegistrationComplete());
    }

    @Test
    public void testGetTopics(){
        assertNotNull(GCMConfig.getTopics());
    }
}