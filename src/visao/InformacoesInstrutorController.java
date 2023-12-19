package visao;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import aplicacao.Instrutor;
import aplicacao.UsuarioLogado;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import persistencia.InstrutorDAO;

public class InformacoesInstrutorController implements Initializable{

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnVoltar;

    @FXML
    private TextField fieldEmail;

    @FXML
    private TextField fieldMatricula;

    @FXML
    private TextField fieldNome;

    @FXML
    private TextField fieldSenha;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Instrutor i1 = InstrutorDAO.buscarALogin(UsuarioLogado.getEmail());
        
        String valorId = Integer.toString(i1.getId());
        fieldMatricula.setText(valorId);
        fieldNome.setText(i1.getNome());
        fieldEmail.setText(i1.getEmail());
        fieldSenha.setText(i1.getSenha());
    }

    @FXML
    void atualizarInfo(ActionEvent event) {
        Instrutor i1 = InstrutorDAO.buscarALogin(UsuarioLogado.getEmail());
        String nome = fieldNome.getText();
        String email = fieldEmail.getText();
        String senha = fieldSenha.getText();

        UsuarioLogado.setLogado(i1.getId(), nome, email, senha);

        Instrutor i2 = new Instrutor(nome, email, senha);

        InstrutorDAO.alterarInstrutor(i1.getId(), i2);
    }

    @FXML
    void voltarTela(ActionEvent event) {
        Stage stage = (Stage) btnVoltar.getScene().getWindow();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceInstrutor.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}