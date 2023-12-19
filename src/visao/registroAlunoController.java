package visao;

import java.io.IOException;

import aplicacao.Aluno;
import aplicacao.UsuarioLogado;
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

public class registroAlunoController {

    @FXML
    private Button btnRegisterNow;

    @FXML
    private Button btnVoltar;

    @FXML
    private TextField textFieldEmail;

    @FXML
    private PasswordField textFieldSenha;

    @FXML
    private PasswordField textFieldRepSenha;


    @FXML
    private TextField textFiieldNome;

    @FXML
    void registerNow(ActionEvent event) {
        String nome = textFiieldNome.getText().trim();
        String email = textFieldEmail.getText().trim();
        String senha = textFieldSenha.getText().trim();
        String senhaRep = textFieldRepSenha.getText().trim();

        if (!nome.isEmpty() && !email.isEmpty() && !senha.isEmpty()) {
            if (senha.equals(senhaRep)) {
                Aluno a1 = new Aluno(nome, email, senha);
                AlunoDAO.registrarAluno(a1);
                Aluno addLogado = AlunoDAO.buscarALogin(email);
                UsuarioLogado.setLogado(addLogado.getId(), nome, email, senha);
                if (AlunoDAO.isVerif()) {
                    Stage stage = (Stage) btnRegisterNow.getScene().getWindow();
                    try {
                        // Carrega o arquivo FXML da nova janela
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("registroAlunopt2.fxml"));
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
            } else {
                System.out.println("AS SENHAS NÃO COINCIDEM!");
            }
        } else {
            System.out.println("PREENCHA TODOS OS CAMPOS!");
        }
    }

    @FXML
    void voltarNow(ActionEvent event) {
        Stage stage = (Stage) btnVoltar.getScene().getWindow();
        try {
            // Carrega o arquivo FXML da nova janela
            FXMLLoader loader = new FXMLLoader(getClass().getResource("tipoReg.fxml"));
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