package visao;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import persistencia.*;

public class Principal extends Application{
	public static void main(String[] args) {

		Conexao c1 = new Conexao();
		AlunoDAO.setConexao(c1);
		InstrutorDAO.setConexao(c1);
		FichaTreinoDAO.setConexao(c1);
		AlunoInstrutorDAO.setConexao(c1);
		PagamentoMensalidadeDAO.setCon(c1);

		c1.conectar();
		c1.desconectar();

		launch(args);

	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
        // Carregar o arquivo FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("telaInicial.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

		primaryStage.setTitle("ACADEMIA");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}