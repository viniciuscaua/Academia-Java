package persistencia;

import aplicacao.Instrutor;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class InstrutorDAO{
    private static Conexao con;

    private static final String tokenAccess = "FAsd#895SFD243242"; 

    private static boolean verif = false;

   public static String getTokenaccess() {
        return tokenAccess;
    }

 public static boolean isVerif() {
        return verif;
    }

    public static void setVerif(boolean verif) {
        InstrutorDAO.verif = verif;
    }

    public static void setConexao(Conexao conexao) {
        con = conexao;
    }

    public static void inserirInstrutor(Instrutor i1) {
        try {
            con.conectar();

            PreparedStatement instrucao = con.getCon().prepareStatement("INSERT INTO instrutores (nome, email, senha) VALUES (?, ?, ?)");
            instrucao.setString(1, i1.getNome());
            instrucao.setString(2, i1.getEmail());
            instrucao.setString(3, i1.getSenha());

            int linhasAfetadas = instrucao.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Inserção realizada com sucesso.");
            } else {
                System.out.println("Nenhuma linha afetada durante a inserção.");
            }

        } catch (SQLException e) {
            System.out.println("ERRO NO METODO: " + e.getMessage());
        } finally {
            con.desconectar();
        }
    }

    public static void alterarInstrutor(int id, Instrutor i1) {
        try {
            con.conectar();
    
            if (!isEmailExistenteParaOutroInstrutor(id, i1.getEmail())) {
                String sql = "UPDATE instrutores SET nome = ?, email = ?, senha = ?  WHERE id_instrutor = ?";
                PreparedStatement instrucao = con.getCon().prepareStatement(sql);
    
                instrucao.setString(1, i1.getNome());
                instrucao.setString(2, i1.getEmail());
                instrucao.setString(3, i1.getSenha());
                instrucao.setInt(4, id);
    
                int linhasAfetadas = instrucao.executeUpdate();
    
                if (linhasAfetadas > 0) {
                    System.out.println("Alteração realizada com sucesso.");
                } else {
                    System.out.println("Nenhuma linha afetada durante a alteração.");
                }
            } else {
                System.out.println("Erro: O email já está cadastrado para outro instrutor.");
            }
        } catch (SQLException e) {
            System.out.println("ERRO NO METODO: " + e.getMessage());
        } finally {
            con.desconectar();
        }
    }
    
    private static boolean isEmailExistenteParaOutroInstrutor(int idInstrutorAtual, String novoEmail) throws SQLException {
        String sql = "SELECT COUNT(*) AS total FROM instrutores WHERE email = ? AND id_instrutor != ?";
        PreparedStatement instrucao = con.getCon().prepareStatement(sql);
        instrucao.setString(1, novoEmail);
        instrucao.setInt(2, idInstrutorAtual);
    
        ResultSet rs = instrucao.executeQuery();
        rs.next();
        
        int total = rs.getInt("total");
    
        return total > 0;
    }
    

    public static void deletarInstrutors(int id) {
        try {

            FichaTreinoDAO.deletarFichaTreinosPI(id);

            con.conectar();

            String sql = "DELETE FROM instrutores WHERE id_instrutor = ?";
            PreparedStatement instrucao = con.getCon().prepareStatement(sql);

            instrucao.setInt(1, id);

            int linhasAfetadas = instrucao.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Alteração realizada com sucesso.");
            } else {
                System.out.println("Nenhuma linha afetada durante a alteração.");
            }

        } catch (SQLException e) {
            System.out.println("ERRO NO METODO: " + e.getMessage());
        } finally {
            con.desconectar();
        }
    }

    public static Instrutor buscarInstrutor(int id) {
        Instrutor i1 = null;
        try {
            con.conectar();

            String sql = "SELECT * FROM instrutores WHERE id_instrutor = ?";
            PreparedStatement instrucao = con.getCon().prepareStatement(sql);
            instrucao.setInt(1, id);

            ResultSet rs = instrucao.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String senha = rs.getString("senha");

                i1 = new Instrutor(nome, email, senha);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar o Instrutor: " + e.getMessage());
        } finally {
            con.desconectar();
        }
        return i1;
    }

    public static ArrayList<Instrutor> retornarLista(String parteNome) {
        ArrayList<Instrutor> listaInstrutors = new ArrayList<Instrutor>();

        try {
            con.conectar();
            String sql = "SELECT * FROM instrutores WHERE nome ILIKE ?";
            PreparedStatement instrucao = con.getCon().prepareStatement(sql);
            instrucao.setString(1, "%" + parteNome + "%");
            ResultSet rs = instrucao.executeQuery();

            while (rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String senha = rs.getString("senha");

                Instrutor i1 = new Instrutor(nome, email, senha);

                listaInstrutors.add(i1);
            }
            con.desconectar();
        } catch (SQLException e) {
            System.out.println("Erro ao buscar o Instrutor: " + e.getMessage());
        }

        return listaInstrutors;
    }

    public static ArrayList<Instrutor> retornarListaCompleta() {
        ArrayList<Instrutor> listaInstrutors = new ArrayList<Instrutor>();

        try {
            con.conectar();
            String sql = "SELECT * FROM instrutores";
            PreparedStatement instrucao = con.getCon().prepareStatement(sql);
            ResultSet rs = instrucao.executeQuery();

            while (rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String senha = rs.getString("senha");

                Instrutor i1 = new Instrutor(nome, email, senha);

                listaInstrutors.add(i1);

            }
            con.desconectar();
        } catch (SQLException e) {
            System.out.println("Erro ao buscar o Instrutor: " + e.getMessage());
        }
        return listaInstrutors;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////
                                    // FUNÇÕES JAVAFX LOGIN REGISTRO //
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    public static Instrutor buscarALogin(String email) {
        Instrutor i1 = null;
        try {
            con.conectar();

            String sql = "SELECT * FROM instrutores WHERE email = ?";
            PreparedStatement instrucao = con.getCon().prepareStatement(sql);
            instrucao.setString(1, email);

            ResultSet rs = instrucao.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id_instrutor");
                String nome = rs.getString("nome");
                String senha = rs.getString("senha");
            
                i1 = new Instrutor(nome, email, senha, id);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar o aluno: " + e.getMessage());
        } finally {
            con.desconectar();
        }
        return i1;
    }

    public static void registrarInstrutor(Instrutor i1) { 
        try {
            con.conectar();
            setVerif(false);
    
            // Verificar se o email já está cadastrado
            PreparedStatement verificarEmailStmt = con.getCon().prepareStatement("SELECT COUNT(*) FROM instrutores WHERE email = ?");
            verificarEmailStmt.setString(1, i1.getEmail());
            ResultSet resultSet = verificarEmailStmt.executeQuery();
    
            resultSet.next();
            int count = resultSet.getInt(1);
    
            if (count > 0) {
                System.out.println("Já existe um instrutor cadastrado com o email fornecido. Inserção não realizada.");
            } else {
                // Realizar a inserção do aluno
                PreparedStatement insercaoStmt = con.getCon().prepareStatement("INSERT INTO instrutores (nome, email, senha) VALUES (?, ?, ?)");
                insercaoStmt.setString(1, i1.getNome());
                insercaoStmt.setString(2, i1.getEmail());
                insercaoStmt.setString(3, i1.getSenha());
    
                int linhasAfetadas = insercaoStmt.executeUpdate();
    
                if (linhasAfetadas > 0) {
                    System.out.println("Inserção realizada com sucesso.");
                    setVerif(true);
                } else {
                    System.out.println("Nenhuma linha afetada durante a inserção.");
                }
            }
    
        } catch (SQLException e) {
            System.out.println("ERRO NO METODO: " + e.getMessage());
        } finally {
            con.desconectar();
        }
    }

    public static Instrutor buscarNomeInstrutor(int id) {
        Instrutor instrutor = null;
        try {
            con.conectar();
    
            String sql = "SELECT * FROM instrutores WHERE id_instrutor = ?";
            PreparedStatement instrucao = con.getCon().prepareStatement(sql);
            instrucao.setInt(1, id);
    
            ResultSet rs = instrucao.executeQuery();
    
            if (rs.next()) {
                String nome = rs.getString("nome");
                instrutor = new Instrutor(nome);
            }
    
        } catch (SQLException e) {
            // Melhore a mensagem de erro ou faça um log adequado.
            System.out.println("Erro ao buscar o instrutor: " + e.getMessage());
        } finally {
            con.desconectar();
        }
        return instrutor;
    }
}