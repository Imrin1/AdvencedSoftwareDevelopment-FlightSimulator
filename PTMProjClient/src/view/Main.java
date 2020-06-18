package view;
	
import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import view_model.ViewModel;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
		
			Model m=new Model(); // Model
			ViewModel vm=new ViewModel(); // View-Model
			vm.setModel(m);
			m.addObserver(vm);
			
			FXMLLoader fxl =new FXMLLoader(getClass().getResource("ClientWindow.fxml"));
			BorderPane root = fxl.load();
			ClientWindowController cwc = fxl.getController();
			cwc.setViewmodel(vm);
			vm.addObserver(m);
			
			
			
			Scene scene = new Scene(root,800,400);
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
