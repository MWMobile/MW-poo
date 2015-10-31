package unb.poo.mwmobile.integracao;

/**
 * Created by sousa on 16/10/2015.
 */
public interface ISigra {

    public void autentica(String matricula, String senha);
    public void requestIRA(String matricula);
    public void requestCurso(String matricula);
    public void requestMaterias(String matricula);
    public void requestPeriodo(String matricula);


}
