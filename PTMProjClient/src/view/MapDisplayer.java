package view;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Utilities.Functions;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

public class MapDisplayer extends Canvas {
	int [][] mapData;
	public DoubleProperty airplaneX, airplaneY;
	public DoubleProperty destX, destY;
	public DoubleProperty startX, startY;
	public DoubleProperty angle;
	public String[] solution;
	Image planeImg;
	Image destinationImg;
	
	public double W;
	public double H;
	public double w;
	public double h;
	public static GraphicsContext gc;
	
	public MapDisplayer() {
		this.airplaneX = new SimpleDoubleProperty();
		this.airplaneY = new SimpleDoubleProperty();
		this.destX = new SimpleDoubleProperty();
		this.destY = new SimpleDoubleProperty();
		this.startX = new SimpleDoubleProperty();
		this.startY = new SimpleDoubleProperty();
		this.angle= new SimpleDoubleProperty();
		
		gc = getGraphicsContext2D();
		try {
			this.planeImg = new Image(new FileInputStream("./resources/Airplane.png"));
			this.destinationImg = new Image(new FileInputStream("./resources/destination.png"));
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		this.airplaneX.set(startX.getValue());
		this.airplaneY.set(startY.getValue());
	}

	public void SetMapData(int[][]data) {
		this.mapData=data;
		W = getWidth();
		H = getHeight();
		w = W / mapData[0].length;
		h = H / mapData.length;
		redraw();
	}	
	
	public void redraw() {
		if(mapData!=null) {
			int maxHeight=Functions.getMaxValue(mapData);
			int minHeight=0;
			int maxHex = 255;
			int minHex = 0;
		 
			gc.clearRect(0, 0, W, H);
			for (int i = 0; i < mapData.length; i++) {
				for (int j = 0; j < mapData[i].length; j++) {
		
						gc.setFill(Color.rgb(maxHex - maxHex*(mapData[i][j]-minHeight)/(maxHeight -minHeight), minHex + maxHex*(mapData[i][j]-minHeight)/(maxHeight -minHeight),0));
						gc.fillRect(j*w, i*h, w, h);
				}
			}
			this.drawAirplane();
		}
	}
	
	public void drawAirplane() {
		double offset =30;
		//gc.clearRect(0, 0,W, H);
		Rotate r = new Rotate(this.angle.getValue(),w*airplaneX.getValue()*(-1) + offset/2,h*airplaneY.getValue()+ offset/2);
		gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
		gc.drawImage(planeImg, w*airplaneX.getValue()*(-1),h*airplaneY.getValue(),offset,offset);
	}
	
	public void drawDestination() {
		double offset = 25;
		gc.clearRect(0, 0,W, H);
		redraw();
		gc.drawImage(destinationImg, destX.getValue(),destY.getValue(),offset,offset);

	}
	
	public void drawRoute() {
		gc.setStroke(Color.BLACK);
		double routeX = airplaneX.getValue();
		double routeY = airplaneY.getValue();
		for (int i = 0; i < solution.length; i++) {
				if(solution[i]=="right") {
					gc.strokeLine(routeX, routeY,routeX + w, routeY);
					routeX+=w;
				}
				if(solution[i]=="left") {
					gc.strokeLine(routeX, routeY,routeX - w, routeY);
					routeX -= w;
				}
				if(solution[i]=="down") {
					gc.strokeLine(routeX, routeY,routeX, routeY + h);
					routeY += h;
				}	
				if(solution[i]=="up") {
					gc.strokeLine(routeX, routeY,routeX, routeY - h);
					routeY -=h;
				}
				
		}
	}
	
	
	
}
