package visao;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControllerRegistro {

    @FXML
    private Button btnVoltar;

    @FXML
    private Button regisAluno;

    @FXML
    private Button regisInstrutor;

    @FXML
    void goToRegAluno(ActionEvent event) {
        Stage stage = (Stage) regisAluno.getScene().getWindow();

        try {
            // Carrega o arquivo FXML da nova janela
            FXMLLoader loader = new FXMLLoader(getClass().getResource("registroAluno.fxml"));
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

    @FXML
    void goToRegInstrutor(ActionEvent event) {
        Stage stage = (Stage) regisInstrutor.getScene().getWindow();

        try {
            // Carrega o arquivo FXML da nova janela
            FXMLLoader loader = new FXMLLoader(getClass().getResource("registroInstrutor.fxml"));
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
