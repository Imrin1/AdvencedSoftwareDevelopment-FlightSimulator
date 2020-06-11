package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.RadialGradient;

public class Joystick {

	
	private Point center;
	private int Radius;
	
	public Joystick(int x,int y,int radius) {
		this.center=new Point(x,y);
		this.Radius=radius;
		
	}
	
	
	public void drawCircle(GraphicsContext gc,Canvas canvas) { 
        
        gc.setFill(Color.BLACK);
        gc.setStroke(Color.BLACK);
        System.out.println(center.x+","+ center.y+","+Radius);
       // gc.fillOval(center.x, center.y, Radius, Radius);
        gc.fillOval(100, 100, 47, 47);
        
        canvas.getGraphicsContext2D().strokeOval(center.x-Radius, center.y-Radius, Radius, Radius);
        canvas.getGraphicsContext2D().fillOval(center.x-Radius, center.y-Radius, Radius * 2, Radius * 2);

    }
	
public void drawCircle(GraphicsContext gc,Canvas canvas,int x, int y) { 
        this.center.setX(x);
        this.center.setY(y);
        gc.setFill(Color.GOLD);
        //gc.setStroke(Color.BLACK);
        gc.fillOval(center.x, center.y, Radius, Radius);
        
       // RadialGradient r = new 
       // canvas.getGraphicsContext2D().strokeOval(center.x-Radius, center.y-Radius, Radius, Radius);
       // canvas.getGraphicsContext2D().fillOval(center.x-Radius, center.y-Radius, Radius * 2, Radius * 2);

    }

	private class Point{

		private int x;
		private int y;
		
		public Point () {
			this.setX(0);
			this.setY(0);
		}

		public Point(int x2, int y2) {
			this.x=x2;
			this.y=y2;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}
	}

}
