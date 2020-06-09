package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import Interperter.Interperter;
import Utilities.Utilities;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.Model;
import view_model.ViewModel;

public class ClientWindowController implements Observer {
	private Stage stage;
	ViewModel vm;
	int[][] mapData;
	@FXML
	TextField ip_TextField;
	@FXML
	TextField port_TextField;
	@FXML
	TextArea Interperter_TextArea;
	@FXML
	RadioButton manual,AutoPilot;
	
	public ClientWindowController() {
		vm=new ViewModel();
		stage= new Stage();
		this.Interperter_TextArea =new TextArea("none");
		this.ip_TextField=new TextField();
		this.port_TextField=new TextField();
		vm.ToInterpert.bind(this.Interperter_TextArea.textProperty());
		vm.ip.bind(ip_TextField.textProperty());
		vm.port.bind(port_TextField.textProperty());
		
	}
	
	public void OpenPopup() {
		Parent root = null;
        try {
            FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("PopUp.fxml"));
            root = fxmlLoader.load();
            ClientWindowController cwc=fxmlLoader.getController();
            cwc.vm=this.vm;
            stage.setTitle("Connect");
            stage.setScene(new Scene(root));
            if(!stage.isShowing()) {
                stage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	public void LoadData() throws Exception {
		 FileChooser fc= new FileChooser();
		 fc.setTitle("open csv file");
		 fc.setInitialDirectory(new File("./resources"));
		 File chosen = fc.showOpenDialog(null);
		 if(!chosen.getName().endsWith(".csv")) {
			 throw new Exception("wrong file type");
		 }
		 if(chosen!=null) {
			 System.out.println(chosen.getName());
		 }
		 try (BufferedReader in = new BufferedReader(new FileReader(chosen))) {
			String line = in.readLine();
			String[] attributes = null;
			int i=0;
			
			 while(line!=null) {
				 attributes = line.split(",");
				 for (int j = 0; j < attributes.length; j++) {
					mapData[i][j]=Integer.parseInt(attributes[j]);
				}
				 line=in.readLine();
				 i++;
			}
			 
			 
			 
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
		try (BufferedReader in = new BufferedReader(new FileReader(chosen))) {
			String line = in.readLine();
			 Interperter_TextArea.clear();
			 while(line!=null) {
				 Interperter_TextArea.appendText(line);
				 Interperter_TextArea.appendText("\n");
				 line=in.readLine();
			}
		}
		 
		 
	}
	
	
	public void Interpert() {	
		if(manual.isSelected())
			manual.setSelected(false);
		System.out.println("check");
		//vm.Interpert();
	}
	
	public void Connect() {
		int port= Integer.parseInt(this.port_TextField.textProperty().get());
		if(Utilities.validIP(this.ip_TextField.textProperty().get()) && (port >0 && port < 65536))
			vm.connect();
	}


	@Override
	public void update(Observable o, Object arg) {}
	
	
}
