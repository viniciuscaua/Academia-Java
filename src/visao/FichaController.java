package visao;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import aplicacao.FichaTreino;
import aplicacao.UsuarioLogado;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import persistencia.FichaTreinoDAO;
import javafx.fxml.Initializable;

public class FichaController implements Initializable{

    @FXML
    private Button btnConcluir;

    @FXML
    private Button btnVoltar;

    @FXML
    private TextField fieldDataFim;

    @FXML
    private TextField fieldDataInicio;

    @FXML
    private TextArea fieldDescFicha;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        FichaTreino ftd = FichaTreinoDAO.buscarFichaTreinoPorAlu(UsuarioLogado.getId());

        if (ftd != null) {
            fieldDataFim.setText(ftd.getDataFim());
            fieldDataInicio.setText(ftd.getDataInicio());
            fieldDescFicha.setText(ftd.getDescricao());
        }else{
            fieldDataFim.setText("SEM TREINO");
            fieldDataInicio.setText("SEM TREINO");
            fieldDescFicha.setText("SEM TREINO");
            btnConcluir.setDisable(true);
        }
    }

    @FXML
    void concluirTreino(ActionEvent event) {
        System.out.println(UsuarioLogado.getId());
        FichaTreinoDAO.deletarFichaTreinosPA(UsuarioLogado.getId());
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