package unb.poo.mwmobile.models;

/**
 * Created by sousa on 19/09/2015.
 */

public class User {

    private final int matricula;
    private String senha;
    private String nome;
    private Materia[] materias;
    private Materia[] historico;

    public User(int matricula) {
        this.matricula = matricula;
    }

    public int getMatricula() {
        return matricula;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Materia[] getMaterias() {
        return materias;
    }

    public void setMaterias(Materia[] materias) {
        this.materias = materias;
    }

    public Materia[] getHistorico() {
        return historico;
    }

    public void setHistorico(Materia[] historico) {
        this.historico = historico;
    }

}
