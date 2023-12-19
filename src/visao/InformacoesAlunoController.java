package visao;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import aplicacao.Aluno;
import aplicacao.PagamentoMensalidade;
import aplicacao.UsuarioLogado;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import persistencia.AlunoDAO;
import persistencia.AlunoInstrutorDAO;
import persistencia.InstrutorDAO;
import persistencia.PagamentoMensalidadeDAO;
import aplicacao.AlunoInstrutor;
import aplicacao.Instrutor;
import javafx.fxml.Initializable;

public class InformacoesAlunoController implements Initializable{

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnVoltar;

    @FXML
    private TextField fieldEmail;

    @FXML
    private TextField fieldIdade;

    @FXML
    private TextField fieldInstrutor;

    @FXML
    private TextField fieldMatricula;

    @FXML
    private TextField fieldNome;

    @FXML
    private TextField fieldPagamento;

    @FXML
    private TextField fieldPeso;

    @FXML
    private TextField fieldPlano;

    @FXML
    private TextField fieldSenha;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Aluno a1 = AlunoDAO.buscarAluno(UsuarioLogado.getId());
        
        String valorId = Integer.toString(UsuarioLogado.getId());
        fieldMatricula.setText(valorId);
        fieldNome.setText(a1.getNome());
        String idadeString = Integer.toString(a1.getIdade());
        fieldIdade.setText(idadeString);
        fieldEmail.setText(a1.getEmail());
        String pesoString = Float.toString(a1.getPeso());
        fieldPeso.setText(pesoString);
        fieldSenha.setText(a1.getSenha());
        PagamentoMensalidade pgm = PagamentoMensalidadeDAO.buscarPag(UsuarioLogado.getId());
        String dataMetade = pgm.getDatapagamento().substring(0, 5);
        fieldPagamento.setText(dataMetade);
        int valor = 0;
        String plano = "NADA";
        if (valor == 60) {
            plano = "INDIVIDUAL";
        } else {
            plano = "FAMILIA";
        }
        fieldPlano.setText(plano);
        
        AlunoInstrutor ait = AlunoInstrutorDAO.buscarRelacao(UsuarioLogado.getId());

        if (ait != null) {
            Instrutor i1 = InstrutorDAO.buscarNomeInstrutor(ait.getId_instrutor());
            fieldInstrutor.setText(i1.getNome());
        } else {
            fieldInstrutor.setText("SEM INSTRUTOR");
        }

    }

    @FXML
    void atualizarInfo(ActionEvent event) {
        try {
            int idadeInt = Integer.parseInt(fieldIdade.getText());
            float pesoFloat = Float.parseFloat(fieldPeso.getText());
            Aluno a1 = new Aluno(fieldNome.getText(), idadeInt, pesoFloat, fieldEmail.getText(),fieldSenha.getText());
            AlunoDAO.alterarAluno(UsuarioLogado.getId(), a1);
            UsuarioLogado.setEmail(a1.getEmail());
            UsuarioLogado.setNome(a1.getNome());
            UsuarioLogado.setSenha(a1.getSenha());
        } catch (Exception e) {
            System.out.println("Erro ao buscar o aluno: " + e.getMessage());
        }
    }

    @FXML
    void voltarTela(ActionEvent event) {
        Stage stage = (Stage) btnVoltar.getScene().getWindow();

        try {
            // Carrega o arquivo FXML da nova janela
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaInicialAluno.fxml"));
            Parent root = loader.load();

            // Cria uma nova cena
            Scene scene = new Scene(root);

            // Define a cena na janela atual
            stage.setScene(scene);

            // Exibe a nova janela
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}