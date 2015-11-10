package unb.poo.mwmobile.integracao.ServerRequest;

/**
 * Classe anemica para mandar dados
 * como parametro ao servidor
 *
 * Created by Scartezini on 07/11/2015.
 */
public class WrapObjToNetwork {

    private String matricula;
    private String senha;
    private String token;


    public WrapObjToNetwork(){
        matricula = null;
        senha = null;
        token = null;
    }


    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
