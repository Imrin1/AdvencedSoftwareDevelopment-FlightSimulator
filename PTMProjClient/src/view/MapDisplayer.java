package view;


import Utilities.Utilities;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.paint.Color;

public class MapDisplayer extends Canvas {
	int [][] mapData;
	
	public MapDisplayer() {
		// TODO Auto-generated constructor stub
	}

	public void SetMapData(int[][]data) {
		this.mapData=data;
	}
	
	public void redraw() {
		if(mapData!=null) {
			double W = getWidth();
			double H = getHeight();
			double w=W / mapData[0].length;
			double h=H / mapData.length;
			int maxHeight=Utilities.getMaxValue(mapData);
			int minHeight=0;
			
			GraphicsContext gc = getGraphicsContext2D();
		 
			gc.clearRect(0, 0, W, H);
			for (int i = 0; i < mapData.length; i++) {
				for (int j = 0; j < mapData[i].length; j++) {
					if(mapData[i][j]!=0) {
						gc.setFill(new Color(255-255*(mapData[i][j]-minHeight)/maxHeight, 255*(mapData[i][j]-minHeight)/maxHeight,0,1));
						gc.fillRect(j*w, i*h, w, h);
							
					}
				}
			}
		}
	}
	
	
	
}
