package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import Utilities.Functions;
import interpreter.Interperter;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.Model;
import view_model.ViewModel;

public class ClientWindowController implements Observer ,Initializable {
	
	ViewModel vm;
	private Stage stage;
	int[][] mapData;
	//airplane data///////////////////////////
	
	public DoubleProperty startX;
	public DoubleProperty startY;
	public DoubleProperty cellSize;
	public DoubleProperty airplaneX;
	public DoubleProperty airplaneY;
	public DoubleProperty angle;
	public DoubleProperty destX;
	public DoubleProperty destY;
	
	private Image airplaneImg;
	private Image destinationImg;
	//////////////////////////////////////////
	private static String Popup =null;
	Joystick joystick;
	GraphicsContext gc;
	
	
	@FXML
	TextField ip_TextField;
	@FXML
	TextField port_TextField;
	@FXML 
	TextArea Interperter_TextArea;
	@FXML
	RadioButton manual,AutoPilot;
	@FXML
	Canvas joystickPaint;
	@FXML
	MapDisplayer mapDisplayer;
	@FXML
	Canvas airplane;
	@FXML
	Canvas destination;
	
	
	public ClientWindowController() {
		vm=new ViewModel();
		stage= new Stage();
		
		startX= new SimpleDoubleProperty();
		startY = new SimpleDoubleProperty();
		cellSize = new SimpleDoubleProperty();
		airplaneX = new SimpleDoubleProperty();
		airplaneY = new SimpleDoubleProperty();
		angle = new SimpleDoubleProperty();
		this.angle.set(0);
		try {
			this.airplaneImg = new Image(new FileInputStream("./resources/Airplane.png"));
			this.destinationImg = new Image(new FileInputStream("./resources/destination.png"));
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		
		
		this.Interperter_TextArea =new TextArea("none");
		this.ip_TextField=new TextField();
		this.port_TextField=new TextField();
		this.manual = new RadioButton();
		this.AutoPilot = new RadioButton();
		
		vm.ToInterpert.bind(this.Interperter_TextArea.textProperty());
		vm.ip.bind(ip_TextField.textProperty());
		vm.port.bind(port_TextField.textProperty());

		this.joystickPaint=new Canvas(189.0,189.0);
		this.joystickPaint.setMouseTransparent(true);
		//this.joystickPaint.setOnMouseDragged(me->this.joystick.drawCircle(this.gc,this.joystickPaint,(int)me.getX(),(int)me.getY()));
		
		///////// initialize the joystick location
		this.joystick=new Joystick((int)this.joystickPaint.getHeight()/2,(int)this.joystickPaint.getWidth()/2 ,(int)this.joystickPaint.getWidth()/4);
		this.gc = this.joystickPaint.getGraphicsContext2D();
		this.joystick.drawCircle(gc,this.joystickPaint);
		/// //// /   
		System.out.println("check");

		vm.startX.bind(this.startX);
		vm.startY.bind(this.startY);
		vm.cellSize.bind(this.cellSize);
	}
	
	
	public void Connect() {
		Parent root = null;
        try {
            FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("PopUp.fxml"));
            root = fxmlLoader.load();
            ClientWindowController cwc=fxmlLoader.getController();
            cwc.vm=this.vm;
       
            stage.setTitle("Connect");
            stage.setScene(new Scene(root));
            if(!stage.isShowing()) {
            	ClientWindowController.setPopup("Connect");
                stage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void CaclulatePath() {
		Parent root = null;
        try {
            FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("PopUp.fxml"));
            root = fxmlLoader.load();
            ClientWindowController cwc=fxmlLoader.getController();
            cwc.vm=this.vm;
            
            stage.setTitle("Calculate path");
            stage.setScene(new Scene(root));
            if(!stage.isShowing()) {
            	ClientWindowController.setPopup("CalculatePath");
                stage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	

	public void Enter() {
		
		if(ClientWindowController.getPopup().equals("Connect")) {
			int port= Integer.parseInt(this.port_TextField.textProperty().getValue());
			if(Functions.validIP(this.ip_TextField.textProperty().get()) && (port >0 && port < 65536))
				vm.connectSimulator();
		}
		if(ClientWindowController.getPopup().equals("CalculatePath")) {
			//need to fill
			vm.connectCalculatePath();
			
		}
		this.ip_TextField.clear();
		this.port_TextField.clear();
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
			String[] starts = line.split(",");
			this.startX.set(Double.parseDouble(starts[0]));
			this.startY.set(Double.parseDouble(starts[1]));
			this.airplaneX.setValue(this.startX.getValue());
			this.airplaneY.setValue(this.startY.getValue());
			
			line = in.readLine();
			this.cellSize.set(Double.parseDouble(line.split(",")[0]));
			
			ArrayList<String[]>row =new ArrayList<String[]>();
			 while((line= in.readLine())!=null) {
				 row.add(line.split(","));
			}
			 
			 mapData= new int[row.size()][];
			 for (int i = 0; i < row.size(); i++) {
				 mapData[i] = new int[row.get(i).length];
				 for (int j = 0; j < row.get(i).length; j++) {
					mapData[i][j] = Integer.parseInt(row.get(i)[j]);
				}
			}
			 
			 this.mapDisplayer.SetMapData(mapData);
			 this.drawAirplane();
			 
		}
	}
	
	public void LoadTxtFile() throws Exception {
		 FileChooser fc= new FileChooser();
		 fc.setTitle("open txt file");
		 fc.setInitialDirectory(new File("./resources"));
		 File chosen = fc.showOpenDialog(null);
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
	
	public void drawAirplane() {
		double W = airplane.getWidth();
		double H = airplane.getHeight();
		double w = W / mapData[0].length;
		double h = H / mapData.length;
		double offset =30;
		GraphicsContext gc = airplane.getGraphicsContext2D();
		gc.clearRect(0, 0,W, H);
		Rotate r = new Rotate(this.angle.getValue(),w*airplaneX.getValue()*(-1) + offset/2,h*airplaneY.getValue()+ offset/2);
		gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
		gc.drawImage(airplaneImg, w*airplaneX.getValue()*(-1),h*airplaneY.getValue(),offset,offset);
	}
	
	public void drawDestination() {
		double W = destination.getWidth();
		double H = destination.getHeight();
		double w = W / mapData[0].length;
		double h = H / mapData.length;
		double offset = 25;
		GraphicsContext gc = destination.getGraphicsContext2D();
		gc.clearRect(0, 0,W, H);
		gc.drawImage(destinationImg, destX.getValue(),destY.getValue(),offset,offset);
		
	}
	
	
	
	public void Interpert() {	
		if(manual.isSelected())
			manual.setSelected(false);
		System.out.println("check");
		//vm.Interpert();
	}
	
	//Paint the joystick at init point
	public void joystickReleased() {
		this.joystick.drawCircle(gc,this.joystickPaint);
	}
	//joystick Painter while mouse dragged
	public void joystickPainter() {
		
		
		//if(manual.isPressed()) {
			
			
		//}
	}

	EventHandler<MouseEvent> clickOnMap = new EventHandler<MouseEvent>() {
		
		@Override
		public void handle(MouseEvent event) {
			System.out.println("click on map event!!");
			destX.set(event.getX());
			destY.set(event.getY());
			drawDestination();
			
		}
	};

	public static String getPopup() {
		return Popup;
	}

	public static void setPopup(String popup) {
		Popup = popup;
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	
}
