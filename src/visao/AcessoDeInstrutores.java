package visao;

import aplicacao.Instrutor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import persistencia.AlunoDAO;
import persistencia.InstrutorDAO;

import java.io.IOException;
import java.util.*;

public class AcessoDeInstrutores {

    @FXML
    private Button btnVoltar;

    @FXML
    private TableView<Instrutor> tableView;

    @FXML
    private TextField tfPesquisa;

    private ObservableList<Instrutor> observableList = FXCollections.observableArrayList();
    private ArrayList<Instrutor> lista;
    private Instrutor instrutorSelecionado = new Instrutor();

    @FXML
    void initialize() {
        criarColunasTabela();
        atualizarTabela();
    }

    @FXML
    void addAlunoAoInstrutor(ActionEvent event) {
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

    void criarColunasTabela() {
        TableColumn<Instrutor, String> colunaNome = new TableColumn<>("NOME");
        TableColumn<Instrutor, String> colunaEmail = new TableColumn<>("EMAIL");

        tableView.setColumnResizePolicy(tableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        tableView.getColumns().addAll(colunaNome, colunaEmail);

        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    void atualizarTabela() {
        observableList.clear();
        String termoPesquisa = tfPesquisa.getText();

        if (termoPesquisa.length() == 0) {
            lista = InstrutorDAO.retornarListaCompleta();
        } else {
            lista = InstrutorDAO.retornarLista(tfPesquisa.getText());
        }

        for (Instrutor a : lista) {
            observableList.add(a);
        }

        tableView.getItems().setAll(observableList);
        tableView.getSelectionModel().selectFirst();

    }

    @FXML
    void filtrarRegistros(KeyEvent event) {
        atualizarTabela();
    }

}
