package aplicacao;

public class UsuarioLogado {
    private static int id;
    private static String nome;
    private static String email;
    private static String senha;

    public static void setLogado(int id, String nome, String email, String senha) {
        UsuarioLogado.id = id;
        UsuarioLogado.nome = nome;
        UsuarioLogado.email = email;
        UsuarioLogado.senha = senha;
    }

    public static int getId() {
        return id;
    }
    public static void setId(int id) {
        UsuarioLogado.id = id;
    }
    public static String getNome() {
        return nome;
    }
    public static void setNome(String nome) {
        UsuarioLogado.nome = nome;
    }
    public static String getEmail() {
        return email;
    }
    public static void setEmail(String email) {
        UsuarioLogado.email = email;
    }
    public static String getSenha() {
        return senha;
    }
    public static void setSenha(String senha) {
        UsuarioLogado.senha = senha;
    }
}