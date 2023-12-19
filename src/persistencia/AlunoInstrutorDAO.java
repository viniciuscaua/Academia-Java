package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import aplicacao.AlunoInstrutor;

public class AlunoInstrutorDAO {
    private static Conexao con;

    public static void setConexao(Conexao conexao) {
        con = conexao;
    }

    public static void inserirAlunoInstrutor(int id_fichadetreino, int id_aluno, int id_instrutor) {
        try {
            con.conectar();

            PreparedStatement instrucao = con.getCon().prepareStatement("INSERT INTO alunoinstrutor (id_fichadetreino, id_aluno, id_instrutor) VALUES (?, ?, ?)");
            
            instrucao.setInt(1, id_fichadetreino);
            instrucao.setInt(2, id_aluno);
            instrucao.setInt(3, id_instrutor);

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

    public static void alterarAlunoInstrutor(int id_fichadetreino, int id_aluno, int id_instrutor) {
        try {
            con.conectar();
    
            String sql = "UPDATE alunoinstrutor SET id_fichadetreino = ?, id_aluno = ?, id_instrutor = ?  WHERE id_fichadetreino = ?";
            PreparedStatement instrucao = con.getCon().prepareStatement(sql);
            
            instrucao.setInt(1, id_fichadetreino);
            instrucao.setInt(2, id_aluno);
            instrucao.setInt(3, id_instrutor);
            instrucao.setInt(4, id_fichadetreino);
    
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

    public static void deletarAlunoInstrutorPFT(int id) {
        try {
            con.conectar();
            
            String sql = "DELETE FROM alunoinstrutor WHERE id_fichadetreino = ?";
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

    /////////////////////////////////////////////////////////////////////////////////////////////////////
                                    // FUNÇÕES JAVAFX LOGIN REGISTRO //
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    public static AlunoInstrutor buscarRelacao(int id) {
        AlunoInstrutor ai = null;
        try {
            con.conectar();

            String sql = "SELECT * FROM alunoinstrutor WHERE id_aluno = ?";
            PreparedStatement instrucao = con.getCon().prepareStatement(sql);
            instrucao.setInt(1, id);

            ResultSet rs = instrucao.executeQuery();

            if (rs.next()) {
                int id_aluno = rs.getInt("id_aluno");
                int id_instrutor = rs.getInt("id_instrutor");
            
                ai = new AlunoInstrutor(id_aluno, id_instrutor);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar o aluno: " + e.getMessage());
        } finally {
            con.desconectar();
        }
        return ai;
    }

}