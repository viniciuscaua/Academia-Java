package aplicacao;

public class Usuario2 {
    private int id;
    private String nome;
    private String email;
    private String senha;

    public Usuario2(){
        
    }

    public Usuario2(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Usuario2(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public Usuario2(String nome){
        this.nome = nome;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
}