package visao;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import aplicacao.UsuarioLogado;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TelaInicialAluno implements Initializable{

    @FXML
    private Button btnFichaTreino;

    @FXML
    private Button btnInfoPessoais;

    @FXML
    private Button btnInstrutores;

    @FXML
    private Button btnSair;

    @FXML
    private Label labelUser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelUser.setText("BEM VINDO, " + UsuarioLogado.getNome());

        AnchorPane.setLeftAnchor(labelUser, 0.0);
        AnchorPane.setRightAnchor(labelUser, 0.0);
        labelUser.setAlignment(Pos.CENTER);
    }

    @FXML
    void sairSistema(ActionEvent event) {
        Stage stage = (Stage) btnInstrutores.getScene().getWindow();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("telaInicial.fxml"));
            Parent root = loader.load();

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
    void verifFicha(ActionEvent event) {
        Stage stage = (Stage) btnFichaTreino.getScene().getWindow();

        try {
            // Carrega o arquivo FXML da nova janela
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FichaAluno.fxml"));
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
    void verifInfoPessoais(ActionEvent event) {
        Stage stage = (Stage) btnInstrutores.getScene().getWindow();

        try {
            // Carrega o arquivo FXML da nova janela
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InformacoesAluno.fxml"));
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
    void verifInstrutores(ActionEvent event) {
        Stage stage = (Stage) btnInstrutores.getScene().getWindow();

        try {
            // Carrega o arquivo FXML da nova janela
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AcessoDeInstrutores.fxml"));
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