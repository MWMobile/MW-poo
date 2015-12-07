package unb.poo.mwmobile.singleton;

import unb.poo.mwmobile.models.User;

/**
 * Classe singleton  para passar usuario entre
 * activitys, é usada como uma alternativa
 * melhor do que parce.
 *
 * http://www.devmedia.com.br/trabalhando-com-singleton-java/23632
 *
 * Created by Scartezini on 10/11/2015.
 */
public class SingletonUser {

    private static final SingletonUser INSTANCE = new SingletonUser();
    private SingletonUser(){}

    public static SingletonUser getInstance() {
        return INSTANCE;
    }

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
