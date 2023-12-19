package aplicacao;

public class Instrutor extends Usuario{

    private int id;

    public Instrutor(){
        
    }

    public Instrutor(String nome){
        super(nome);
    }

    public Instrutor(String nome, String email, String senha, int id) {
        super(nome, email, senha);
        this.id = id;
    }

    public Instrutor(String nome, String email, String senha) {
        super(nome, email, senha);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Instrutor []";
    }
    
}