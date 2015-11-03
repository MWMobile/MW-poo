package unb.poo.mwmobile.config;

/**
 * Created by sousa on 20/10/2015.
 */
public class GCMConfig {

    private static final String SENT_TOKEN_TO_SERVER = "sentTokenToServer";
    private static final String REGISTRATION_COMPLETE = "registrationComplete";
    private static final String[] TOPICS = {"global"};

    public static String getSentTokenToServer() {
        return SENT_TOKEN_TO_SERVER;
    }

    public static String getRegistrationComplete(){
        return REGISTRATION_COMPLETE;
    }

    public static String[] getTopics(){
        return TOPICS;
    }

}
