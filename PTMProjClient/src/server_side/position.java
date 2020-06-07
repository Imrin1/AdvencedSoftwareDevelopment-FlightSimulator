package server_side;

public class position {
		 
	int x; 
	int y; 
	
public position (int X, int Y) {
this.x=X;
this.y=Y;
		
}
	
public int getX() {
	return x;
}

public int getY() {
	return y;
}
public boolean equals(Object o) {
	position p=(position) o;
	return (this.getX()==p.getX()&&this.getY()==p.getY());
}

}

