package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import aplicacao.FichaTreino;

public class FichaTreinoDAO {
    private static Conexao con;

    public static void setConexao(Conexao conexao) {
        con = conexao;
    }

    public static void inserirFichaTreino(FichaTreino ft1) {
        try {

            con.conectar();

            PreparedStatement instrucao = con.getCon().prepareStatement("INSERT INTO fichadetreino (id_aluno, id_instrutor, datainicio, datafim, descricao) VALUES (?, ?, ?, ?, ?)");
            
            instrucao.setInt(1, ft1.getId_aluno());
            instrucao.setInt(2, ft1.getId_instrutor());
            instrucao.setString(3, ft1.getDataInicio());
            instrucao.setString(4, ft1.getDataFim());
            instrucao.setString(5, ft1.getDescricao());

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
            FichaTreinoDAO.addAlunoInstrutor(ft1);
        }
    }

    public static void addAlunoInstrutor(FichaTreino ft1){
        try {
            con.conectar();
            String sql = "SELECT id_fichadetreino FROM fichadetreino WHERE (id_aluno = ?) AND (id_instrutor = ?)";
            PreparedStatement instrucao = con.getCon().prepareStatement(sql);
            instrucao.setInt(1, ft1.getId_aluno());
            instrucao.setInt(2, ft1.getId_instrutor());

            ResultSet rs = instrucao.executeQuery();

            if (rs.next()) {
                int id_fichadetreino = rs.getInt("id_fichadetreino");
                
                AlunoInstrutorDAO.inserirAlunoInstrutor(id_fichadetreino, ft1.getId_aluno(), ft1.getId_instrutor());
            }

        } catch (SQLException e) {
            System.out.println("ERRO NO METODO: " + e.getMessage());
        } finally{
            con.desconectar();
        }
    }

    public static void alterarFichaTreino(int id, FichaTreino ft1) {
        try {
            con.conectar();

            String sql = "UPDATE fichadetreino SET id_aluno = ?, id_instrutor = ?, datainicio = ?, datafim = ?, descricao = ?  WHERE id_fichadetreino = ?";
            PreparedStatement instrucao = con.getCon().prepareStatement(sql);

            instrucao.setInt(1, ft1.getId_aluno());
            instrucao.setInt(2, ft1.getId_instrutor());
            instrucao.setString(3, ft1.getDataInicio());
            instrucao.setString(4, ft1.getDataFim());
            instrucao.setString(5, ft1.getDescricao());
            instrucao.setInt(6, id);

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
            FichaTreinoDAO.modAlunoInstrutor(ft1);
        }
    }

    public static void modAlunoInstrutor(FichaTreino ft1){
        try {
            con.conectar();
            String sql = "SELECT id_fichadetreino FROM fichadetreino WHERE (id_aluno = ?) AND (id_instrutor = ?)";
            PreparedStatement instrucao = con.getCon().prepareStatement(sql);
            instrucao.setInt(1, ft1.getId_aluno());
            instrucao.setInt(2, ft1.getId_instrutor());

            ResultSet rs = instrucao.executeQuery();

            if (rs.next()) {
                int id_fichadetreino = rs.getInt("id_fichadetreino");
                
                AlunoInstrutorDAO.alterarAlunoInstrutor(id_fichadetreino, ft1.getId_aluno(), ft1.getId_instrutor());
            }
        } catch (SQLException e) {
            System.out.println("ERRO NO METODO: " + e.getMessage());
        } finally{
            con.desconectar();
        }
    }

    public static void deletarFichaTreinos(int id) {
        try {
            AlunoInstrutorDAO.deletarAlunoInstrutorPFT(id);
            con.conectar();

            String sql = "DELETE FROM fichadetreino WHERE id_fichadetreino = ?";
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

    public static void deletarFichaTreinosPA(int id) {
        try {
            FichaTreinoDAO.deletarAlunoInstrutorPA(id);
            con.conectar();
            String sql = "DELETE FROM fichadetreino WHERE id_aluno = ?";
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

    public static void deletarAlunoInstrutorPA(int id){
        try {
            con.conectar();
            String sql = "SELECT id_fichadetreino FROM fichadetreino WHERE id_aluno = ?";
            PreparedStatement instrucao = con.getCon().prepareStatement(sql);
            instrucao.setInt(1, id);

            ResultSet rs = instrucao.executeQuery();

            if (rs.next()) {
                int id_fichadetreino = rs.getInt("id_fichadetreino");
                
                AlunoInstrutorDAO.deletarAlunoInstrutorPFT(id_fichadetreino);
            }
        } catch (SQLException e) {
            System.out.println("ERRO NO METODO: " + e.getMessage());
        } finally{
            con.desconectar();
        }
    }

    public static void deletarFichaTreinosPI(int id) {
        try {
            FichaTreinoDAO.deletarAlunoInstrutorPI(id);
            con.conectar();
            String sql = "DELETE FROM fichadetreino WHERE id_instrutor = ?";
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

    public static void deletarAlunoInstrutorPI(int id){
        try {
            con.conectar();
            String sql = "SELECT id_fichadetreino FROM fichadetreino WHERE id_instrutor = ?";
            PreparedStatement instrucao = con.getCon().prepareStatement(sql);
            instrucao.setInt(1, id);

            ResultSet rs = instrucao.executeQuery();

            if (rs.next()) {
                int id_fichadetreino = rs.getInt("id_fichadetreino");
                
                AlunoInstrutorDAO.deletarAlunoInstrutorPFT(id_fichadetreino);
            }
        } catch (SQLException e) {
            System.out.println("ERRO NO METODO: " + e.getMessage());
        } finally{
            con.desconectar();
        }
    }

    public static FichaTreino buscarFichaTreino(int id) {
        FichaTreino ft1 = null;
        try {
            con.conectar();

            String sql = "SELECT * FROM fichadetreino WHERE id_fichadetreino = ?";
            PreparedStatement instrucao = con.getCon().prepareStatement(sql);
            instrucao.setInt(1, id);

            ResultSet rs = instrucao.executeQuery();

            if (rs.next()) {
                int id_aluno = rs.getInt("id_aluno");
                int id_instrutor = rs.getInt("id_instrutor");
                String datainicio = rs.getString("datainicio");
                String datafim = rs.getString("datafim");
                String descricao = rs.getString("descricao");

                ft1 = new FichaTreino(id_aluno, id_instrutor, datainicio, datafim, descricao);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar o FichaTreino: " + e.getMessage());
        } finally {
            con.desconectar();
        }
        return ft1;
    }
    
    //BUSCAR FICHA TREINO SOBRECARGA

    public static FichaTreino buscarFichaTreino(int idAluno, int idInstrutor) {
        FichaTreino ft1 = null;
        try {
            con.conectar();

            String sql = "SELECT * FROM fichadetreino WHERE id_aluno = ? AND id_instrutor = ?";
            PreparedStatement instrucao = con.getCon().prepareStatement(sql);
            instrucao.setInt(1, idAluno);
            instrucao.setInt(2, idInstrutor);

            System.out.println("ID do aluno: " + idAluno);
            System.out.println("ID do instrutor: " + idInstrutor);

            ResultSet rs = instrucao.executeQuery();

            if (rs.next()) {
                int idFichaTreino = rs.getInt("id_fichadetreino");
                String dataInicio = rs.getString("datainicio");
                String dataFim = rs.getString("datafim");
                String descricao = rs.getString("descricao");

                ft1 = new FichaTreino(idFichaTreino, idAluno, idInstrutor, dataInicio, dataFim, descricao);

                System.out.println("ID da ficha de treino encontrada: " + idFichaTreino);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar a FichaTreino: " + e.getMessage());
        } finally {
            con.desconectar();
        }
        return ft1;
    }


    public static ArrayList<FichaTreino> retornarListaCompleta() {
        ArrayList<FichaTreino> listaFichaTreinos = new ArrayList<FichaTreino>();

        try {
            con.conectar();
            String sql = "SELECT * FROM fichadetreino";
            PreparedStatement instrucao = con.getCon().prepareStatement(sql);
            ResultSet rs = instrucao.executeQuery();

            while (rs.next()) {
                int id_aluno = rs.getInt("id_aluno");
                int id_instrutor = rs.getInt("id_instrutor");
                String datainicio = rs.getString("datainicio");
                String datafim = rs.getString("datafim");
                String descricao = rs.getString("descricao");

                FichaTreino ft1 = new FichaTreino(id_aluno, id_instrutor, datainicio, datafim, descricao);

                listaFichaTreinos.add(ft1);
            }
            con.desconectar();
        } catch (SQLException e) {
            System.out.println("Erro ao buscar o FichaTreino: " + e.getMessage());
        }
        return listaFichaTreinos;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////
                                    // FUNÇÕES JAVAFX LOGIN REGISTRO //
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    public static FichaTreino buscarFichaTreinoPorAlu(int id) {
        FichaTreino ft1 = null;
        try {
            con.conectar();

            String sql = "SELECT * FROM fichadetreino WHERE id_aluno = ?";
            PreparedStatement instrucao = con.getCon().prepareStatement(sql);
            instrucao.setInt(1, id);

            ResultSet rs = instrucao.executeQuery();

            if (rs.next()) {
                int id_aluno = rs.getInt("id_aluno");
                int id_instrutor = rs.getInt("id_instrutor");
                String datainicio = rs.getString("datainicio");
                String datafim = rs.getString("datafim");
                String descricao = rs.getString("descricao");

                ft1 = new FichaTreino(id_aluno, id_instrutor, datainicio, datafim, descricao);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar o FichaTreino: " + e.getMessage());
        } finally {
            con.desconectar();
        }
        return ft1;
    }

}