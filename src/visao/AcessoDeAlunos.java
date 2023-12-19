package visao;

import aplicacao.Aluno;
import aplicacao.Aluno2;
import aplicacao.FichaTreino;
import aplicacao.UsuarioLogado;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import persistencia.AlunoDAO;
import persistencia.FichaTreinoDAO;

import java.io.IOException;
import java.util.ArrayList;

public class AcessoDeAlunos {

    @FXML
    private Button btnAtualizarTreino;

    @FXML
    private Button btnEnviarTreino;

    @FXML
    private Button btnSair;

    @FXML
    private TableView<Aluno2> tableView;

    @FXML
    private TextField tfDataFinal;

    @FXML
    private TextField tfDataInicio;

    @FXML
    private TextField tfDescricao;

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfIdade;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfPesquisa;

    @FXML
    private ObservableList<Aluno2> observableList = FXCollections.observableArrayList();

    private Aluno2 alunoSelecionado = new Aluno2(null, null, 0, 0, null, null);

    private ArrayList<Aluno2> lista;

    @FXML
    void initialize() {
        criarColunasTabela();
        atualizarTabela();
        getCamposTabela();
    }

    @FXML
    void acaoDoBotaoSair(ActionEvent event) {
        Stage stage = (Stage) btnSair.getScene().getWindow();

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
    }

    @FXML
    void atualizaTreino(ActionEvent event) {
        if (alunoSelecionado != null) {
            try {
                String descricao = tfDescricao.getText();
                String dataInicio = tfDataInicio.getText();
                String dataFinal = tfDataFinal.getText();

                Aluno a2 = AlunoDAO.buscarALogin(alunoSelecionado.getEmail());

                FichaTreino fichaTreinoExistente = FichaTreinoDAO.buscarFichaTreino(a2.getId(), UsuarioLogado.getId());

                if (fichaTreinoExistente != null) {
                    fichaTreinoExistente.setDescricao(descricao);
                    fichaTreinoExistente.setDataInicio(dataInicio);
                    fichaTreinoExistente.setDataFim(dataFinal);

                    FichaTreinoDAO.alterarFichaTreino(fichaTreinoExistente.getId_ficha(), fichaTreinoExistente);

                    System.out.println("Ficha de treino existente atualizada com sucesso.");
                } else {
                    System.out.println("Erro: Ficha de treino não encontrada para o aluno selecionado.");
                }

                limparCamposTreino();

                atualizarTabela();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void enviarTreino(ActionEvent event) {
        if (alunoSelecionado != null) {
            try {
                String descricao = tfDescricao.getText();
                String dataInicio = tfDataInicio.getText();
                String dataFinal = tfDataFinal.getText();
                Aluno a2 = AlunoDAO.buscarALogin(alunoSelecionado.getEmail());
                FichaTreino fichaTreino = new FichaTreino(a2.getId(), 0, dataInicio, dataFinal,
                        descricao);
                fichaTreino.setId_instrutor(UsuarioLogado.getId());
                fichaTreino.setDataInicio(dataInicio);
                fichaTreino.setDataFim(dataFinal);
                fichaTreino.setDescricao(descricao);

                FichaTreinoDAO.inserirFichaTreino(fichaTreino);

                limparCamposTreino();

                atualizarTabela();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    void limparCamposTreino() {
        tfDescricao.clear();
        tfDataInicio.clear();
        tfDataFinal.clear();
    }

    void criarColunasTabela() {
        TableColumn<Aluno2, String> colunaNome = new TableColumn<>("NOME");
        TableColumn<Aluno2, Integer> colunaIdade = new TableColumn<>("IDADE");
        TableColumn<Aluno2, String> colunaPeso = new TableColumn<>("PESO");
        TableColumn<Aluno2, String> colunaEmail = new TableColumn<>("EMAIL");

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        tableView.getColumns().addAll(colunaNome, colunaIdade, colunaPeso, colunaEmail);

        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaIdade.setCellValueFactory(new PropertyValueFactory<>("idade"));
        colunaPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        colunaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    @FXML
    void getCamposTabela() {
        alunoSelecionado = tableView.getSelectionModel().getSelectedItem();

        if (alunoSelecionado != null) {
            Aluno a2 = AlunoDAO.buscarALogin(alunoSelecionado.getEmail());
            tfId.setText(String.valueOf("ID: " + a2.getId()));
            tfNome.setText(alunoSelecionado.getNome());
            int id_instrutor = UsuarioLogado.getId();
            tfIdade.setText(String.valueOf(alunoSelecionado.getIdade()) + " anos");

            FichaTreino fichaTreino = FichaTreinoDAO.buscarFichaTreino(a2.getId(), id_instrutor);

            if (fichaTreino != null) {
                tfDescricao.setText(fichaTreino.getDescricao());
                tfDataInicio.setText(fichaTreino.getDataInicio());
                tfDataFinal.setText(fichaTreino.getDataFim());
            } else {
                limparCamposTreino();
            }
        }
    }

    void atualizarTabela() {
        observableList.clear();
        String termoPesquisa = tfPesquisa.getText();

        if (termoPesquisa.length() == 0) {
            lista = AlunoDAO.retornarListaCompletaCID();
        } else {
            lista = AlunoDAO.retornarListaCID(tfPesquisa.getText());
        }

        for (Aluno2 a1 : lista) {
            observableList.add(a1);
        }

        tableView.setItems(observableList);
        tableView.getSelectionModel().selectFirst();
    }

    @FXML
    void filtrarRegistros(KeyEvent event) {
        atualizarTabela();
    }

    @FXML
    void clicarTabela(MouseEvent event) {
        getCamposTabela();
    }

    @FXML
    void moverTabela(KeyEvent event) {
        getCamposTabela();
    }
}