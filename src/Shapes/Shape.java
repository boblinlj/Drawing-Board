package Shapes;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;

public abstract class Shape implements Serializable{
	private int x1, y1, x2, y2;
	private Color color;
	private int stroke;
	
	//draw shape
	public abstract void Draw(Graphics2D g);
	
	// return shape parameters
	public abstract int getInitX();
	public abstract int getInitY();
	public abstract int getEndY();
	public abstract int getEndX();
	public abstract int getHight();
	public abstract int getWidth();
	public abstract Color getColor();
	public abstract int getStroke();
	
	//return string for shape Text
	public abstract String getString();
	
	
	public void setColor(Graphics2D g, Color newColor){
		this.color = newColor;
	}
	
	public void setStroke(Graphics2D g, int stroke){
		this.stroke = stroke;
	}
	
	public boolean inShape(int x, int y){return false;}
	

}
