package visao;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import aplicacao.Aluno;
import aplicacao.PagamentoMensalidade;
import aplicacao.UsuarioLogado;
import javafx.event.ActionEvent;
import persistencia.PagamentoMensalidadeDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import persistencia.AlunoDAO;

public class RegistroAlunopt2Controller implements Initializable {

    @FXML
    private Button btnConcluirCad;

    @FXML
    private DatePicker datePickerDataPag;

    @FXML
    private TextField textFieldIdade;

    @FXML
    private TextField textFieldPeso;

    @FXML
    private ChoiceBox<String> choiceBoxPlano;

    private String[] planos = {"FAMILIA", "INDIVIDUAL"};

    @FXML
    void concluirCadastroUser(ActionEvent event) {
        String plano = choiceBoxPlano.getValue();
        String idade = textFieldIdade.getText();
        String peso = textFieldPeso.getText();
        String dataPagamento = "";
    
        // Verificar se algum campo está vazio
        if (plano == null || idade.isEmpty() || peso.isEmpty()) {
            System.out.println("Por favor, preencha todos os campos antes de concluir o cadastro.");
            return;
        }
    
        try {
            int idadeInt = Integer.parseInt(idade);
            float pesoFloat = Float.parseFloat(peso);
            LocalDate localDate = datePickerDataPag.getValue();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dataPagamento = localDate.format(formatter);
    
            System.out.println("Todos os campos estão corretos!");
    
            int valor;
    
            if (plano.equals("FAMILIA")) {
                valor = 55;
            } else {
                valor = 60;
            }
    
            Aluno a1 = AlunoDAO.buscarALogin(UsuarioLogado.getEmail());

            AlunoDAO.inserirInfoExtra(UsuarioLogado.getEmail(), idadeInt, pesoFloat);
            PagamentoMensalidade pgm = new PagamentoMensalidade(a1.getId(), dataPagamento, valor);
            PagamentoMensalidadeDAO.inserirPagamentoMensalidade(pgm);

            Stage stage = (Stage) btnConcluirCad.getScene().getWindow();
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
    
        } catch (Exception e) {
            System.out.println("Algum dos campos de número está como string");
        }
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceBoxPlano.getItems().addAll(planos);
    }
}