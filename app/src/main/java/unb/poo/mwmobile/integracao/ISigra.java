package unb.poo.mwmobile.integracao;

import unb.poo.mwmobile.models.User;

/**
 * Created by sousa on 16/10/2015.
 */
public interface ISigra {

    public User autentica(String matricula, String senha);
    public void requestIRA();
    public void requestCurso();
    public void requestMaterias();
    public void requestPeriodo();


}
