package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

import Interperter.Interperter;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.util.Pair;
import view_model.ViewModel;

public class ClientWindowController implements Observer {
	ViewModel vm;
	@FXML
	TextField ip;
	@FXML
	TextField port;
	@FXML
	TextArea Interperter_TextArea;
	@FXML
	RadioButton manual,AutoPilot;
	
	public ClientWindowController() {
		vm=new ViewModel();
		this.Interperter_TextArea =new TextArea("none");
		this.ip=new TextField();
		this.port=new TextField();
		vm.ToInterpert.bind(this.Interperter_TextArea.textProperty());
		vm.ip.bind(ip.textProperty());
		vm.port.bind(port.textProperty());
		
	}
	
	
	public void LoadData() {
		 FileChooser fc= new FileChooser();
		 fc.setTitle("open file");
		 fc.setInitialDirectory(new File("./resources"));
		 File chosen = fc.showOpenDialog(null);
		 if(chosen!=null) {
			 System.out.println(chosen.getName());
		 }
	}
	
	public void LoadTxtFile() throws Exception {
		 FileChooser fc= new FileChooser();
		 fc.setTitle("open txt file");
		 fc.setInitialDirectory(new File("./resources"));
		 File chosen = fc.showOpenDialog(null);
		 if(!chosen.getName().endsWith(".txt")) {
			 throw new Exception("wrong file type");
		 }
		 if(chosen!=null) {
			 System.out.println(chosen.getName());
		 }
		BufferedReader in = new BufferedReader(new FileReader(chosen));
		String line = in.readLine();
		 Interperter_TextArea.clear();
		 while(line!=null) {
			 Interperter_TextArea.appendText(line);
			 Interperter_TextArea.appendText("\n");
			 line=in.readLine();
		}
		 
		 
	}
	
	public void Interpert() {	
		if(manual.isSelected())
			manual.setSelected(false);
		System.out.println("check");
		//vm.Interpert();
	}
	
	public void Connect() {
		// Create the custom dialog.
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Login Dialog");
		dialog.setHeaderText("Look, a Custom Login Dialog");

		//need to add image!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		
		// Set the icon (must be included in the project).
	//	dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));

		// Set the button types.
		ButtonType ConnectButtonType = new ButtonType("Connect", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(ConnectButtonType, ButtonType.CANCEL);

		// Create the ip and port labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		ip = new TextField();
		ip.setPromptText("IP Adress");
		port = new TextField();
		port.setPromptText("Destination Port");
		
		grid.add(new Label("IP Adress:"), 0, 0);
		grid.add(ip, 1, 0);
		grid.add(new Label("Destination Port:"), 0, 1);
		grid.add(port, 1, 1);

		// Enable/Disable Connect button depending on whether a ip was entered.
		Node ConnectButton = dialog.getDialogPane().lookupButton(ConnectButtonType);
		ConnectButton.setDisable(true);

		// Do some validation (using the Java 8 lambda syntax).
		ip.textProperty().addListener((observable, oldValue, newValue) -> {
		    ConnectButton.setDisable(newValue.trim().isEmpty());
		});

		dialog.getDialogPane().setContent(grid);

		// Request focus on the ip field by default.
		Platform.runLater(() -> ip.requestFocus());

		// Convert the result to a username-password-pair when the login button is clicked.
		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == ConnectButtonType) {
		        //return new Pair<>(ip.getText(), port.getText());
		    	System.out.println(ip.getText()+port.getText());
		    	try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	vm.connect();
		    }
		    return null;
		});

		dialog.showAndWait();

	}


	@Override
	public void update(Observable o, Object arg) {}
}
