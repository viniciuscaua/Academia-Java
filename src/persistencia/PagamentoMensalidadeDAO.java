package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import aplicacao.PagamentoMensalidade;

public class PagamentoMensalidadeDAO {

    private static Conexao con;

    public static void setCon(Conexao con) {
        PagamentoMensalidadeDAO.con = con;
    }
    
    public static void inserirPagamentoMensalidade(PagamentoMensalidade pam) {
        try {
            con.conectar();

            PreparedStatement instrucao = con.getCon().prepareStatement("INSERT INTO pagamentomensalidade (id_aluno, datapagamento, valor) VALUES (?, ?, ?)");
            
            instrucao.setInt(1, pam.getId_aluno());
            instrucao.setString(2, pam.getDatapagamento());
            instrucao.setInt(3, pam.getValor());

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

    public static void atualizarPagamentoMensalidade(PagamentoMensalidade pam) {
        try {
            con.conectar();
    
            PreparedStatement instrucao = con.getCon().prepareStatement("UPDATE pagamentomensalidade SET datapagamento = ?, valor = ? WHERE id_aluno");
    
            instrucao.setString(1, pam.getDatapagamento());
            instrucao.setInt(2, pam.getValor());
            instrucao.setInt(3, pam.getId_aluno()); 
    
            int linhasAfetadas = instrucao.executeUpdate();
    
            if (linhasAfetadas > 0) {
                System.out.println("Atualização realizada com sucesso.");
            } else {
                System.out.println("Nenhuma linha afetada durante a atualização.");
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

    public static PagamentoMensalidade buscarPag(int id) {
        PagamentoMensalidade pgm = null;
        try {
            con.conectar();

            String sql = "SELECT * FROM pagamentomensalidade WHERE id_aluno = ?";
            PreparedStatement instrucao = con.getCon().prepareStatement(sql);
            instrucao.setInt(1, id);

            ResultSet rs = instrucao.executeQuery();

            if (rs.next()) {
                String data = rs.getString("datapagamento");
                int valor = rs.getInt("valor");
            
                pgm = new PagamentoMensalidade(data, valor);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar o aluno: " + e.getMessage());
        } finally {
            con.desconectar();
        }
        return pgm;
    }

}