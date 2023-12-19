package aplicacao;

public class Aluno extends Usuario{
    private int id;
    private int idade;
    private Float peso;

    public Aluno(){
        
    }

    public Aluno(int id, String nome, String email, String senha) {
        super(nome, email, senha);
        this.id = id;
    }

    public Aluno(String nome, String email, String senha) {
        super(nome, email, senha);
    }

    public Aluno(String nome, int idade, float peso, String email, String senha) {
        super(nome, email, senha);
        this.idade = idade;
        this.peso = peso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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