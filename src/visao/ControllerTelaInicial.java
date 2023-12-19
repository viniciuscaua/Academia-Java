package visao;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControllerTelaInicial {

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnRegistro;

    @FXML
    void goToAreaLogin(ActionEvent event) {
        Stage stage = (Stage) btnLogin.getScene().getWindow();

        try {
            // Carrega o arquivo FXML da nova janela
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
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
    void goToRegistro(ActionEvent event) {
        Stage stage = (Stage) btnRegistro.getScene().getWindow();

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