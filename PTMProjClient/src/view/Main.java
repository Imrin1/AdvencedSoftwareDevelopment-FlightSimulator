package view;
	
import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import view_model.ViewModel;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			Model m=new Model(); // Model
			ViewModel vm=new ViewModel(); // View-Model
			m.addObserver(vm);
		//	ClientWindowController v= new ClientWindowController();
			
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("ClientWindow.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
