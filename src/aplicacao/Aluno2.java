package aplicacao;

import javafx.beans.property.SimpleIntegerProperty;

public class Aluno2 extends Usuario2 {
    private int id;
    private int idade;
    private Float peso;
    private int idFichaTreino;

    private SimpleIntegerProperty idProperty = new SimpleIntegerProperty();


    public Aluno2() {

    }

    /*public Aluno(AlunoInstrutor alunoInstrutor) {
        super(alunoInstrutor.getNome(), alunoInstrutor.getEmail(), alunoInstrutor.getSenha());
        this.id = alunoInstrutor.getId_aluno();
        this.idade = alunoInstrutor.getIdade();
        this.peso = alunoInstrutor.getPeso();
    }*/


    public Aluno2(int id, String nome, String email, String senha) {
        super(nome, email, senha);
        this.id = id;
        this.idProperty = new SimpleIntegerProperty(id);
    }



    public Aluno2(String nome, String email, String senha) {
        super(nome, email, senha);
    }

    public Aluno2(Integer id, String nome, int idade, float peso, String email, String senha) {
        super(nome, email, senha);
        this.idade = idade;
        this.peso = peso;
    }

    public Aluno2(int id, String nome, int idade, float peso, String email) {
        super(nome, email);
        this.idade = idade;
        this.peso = peso;
    }

    public int getIdFichaTreino() {
        return idFichaTreino;
    }

    public void setIdFichaTreino(int idFichaTreino) {
        this.idFichaTreino = idFichaTreino;
    }

    public int getId() {
        return idProperty != null ? idProperty.get() : 0;
    }    

    public void setId(int id) {
        this.id = id;
        this.idProperty.set(id);
    }

    public SimpleIntegerProperty idProperty() {
        return idProperty;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

}