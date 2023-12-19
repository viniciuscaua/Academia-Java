package visao;

import java.io.IOException;

import aplicacao.UsuarioLogado;
import aplicacao.Aluno;
import aplicacao.Instrutor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import persistencia.AlunoDAO;
import persistencia.InstrutorDAO;

public class LoginController {

    @FXML
    private Button btnLoginAluno;

    @FXML
    private Button btnLoginInstrutor;

    @FXML
    private Button btnVoltar;

    @FXML
    private TextField labelEmail;

    @FXML
    private PasswordField labelSenha;

    @FXML
    void goToLoginAluno(ActionEvent event) {
        String email = labelEmail.getText();
        String senha = labelSenha.getText();

        Aluno a1 = AlunoDAO.buscarALogin(email);

        if (a1 != null && email.equals(a1.getEmail()) && senha.equals(a1.getSenha())) {
            //System.out.println("PARABÉNS, VOCÊ LOGOU COMO ALUNO!");
            UsuarioLogado.setLogado(a1.getId(), a1.getNome(), email, senha);
            Stage stage = (Stage) btnLoginInstrutor.getScene().getWindow();
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
        } else {
            System.out.println("LAMENTO, VOCÊ ERROU SENHA OU EMAIL! =(");
        }
    }

    @FXML
    void goToLoginInstrutor(ActionEvent event) {
        String email = labelEmail.getText();
        String senha = labelSenha.getText();

        Instrutor i1 = InstrutorDAO.buscarALogin(email);

        if (i1 != null && email.equals(i1.getEmail()) && senha.equals(i1.getSenha())) {
            UsuarioLogado.setLogado(i1.getId(), i1.getNome(), email, senha);
            Stage stage = (Stage) btnLoginInstrutor.getScene().getWindow();
            try {
                // Carrega o arquivo FXML da nova janela
                FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceInstrutor.fxml"));
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
        } else {
            System.out.println("LAMENTO, VOCÊ ERROU SENHA OU EMAIL! =()");
        }
    }

    @FXML
    void voltarNow(ActionEvent event) {
        Stage stage = (Stage) btnVoltar.getScene().getWindow();

        try {
            // Carrega o arquivo FXML da nova janela
            FXMLLoader loader = new FXMLLoader(getClass().getResource("telaInicial.fxml"));
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