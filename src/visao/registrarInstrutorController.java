package visao;

import java.io.IOException;

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
import persistencia.InstrutorDAO;

public class registrarInstrutorController {

    @FXML
    private Button btnRegistrar;

    @FXML
    private Button btnVoltar;

    @FXML
    private TextField textFieldEmail;

    @FXML
    private TextField textFieldNome;

    @FXML
    private PasswordField textFieldSenha;

    @FXML
    private TextField textFieldToken;

    @FXML
    private PasswordField texxtFieldRepSenha;

    @FXML
    void registrarNow(ActionEvent event) {
        String nome = textFieldNome.getText().trim();
        String email = textFieldEmail.getText().trim();
        String senha = textFieldSenha.getText().trim();
        String senhaRep = texxtFieldRepSenha.getText().trim();
        String accessToken = textFieldToken.getText().trim();

        if (!nome.isEmpty() && !email.isEmpty() && !senha.isEmpty() && !accessToken.isEmpty()) {
            if (senha.equals(senhaRep)) {
                if (accessToken.equals(InstrutorDAO.getTokenaccess())) {
                    Instrutor i1 = new Instrutor(nome, email, senha);
                    InstrutorDAO.registrarInstrutor(i1);
                    if (InstrutorDAO.isVerif()) {
                        Stage stage = (Stage) btnRegistrar.getScene().getWindow();
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
                } else {
                    System.out.println("ACCESS TOKEN INCORRETO!");
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