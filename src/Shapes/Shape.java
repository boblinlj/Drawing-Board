package Shapes;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;

public abstract class Shape implements Serializable{
	
	//draw shape
	public abstract void Draw(Graphics2D g);
	
	// return shape parameters
	public abstract double getInitX();
	public abstract double getInitY();
	public abstract double getEndY();
	public abstract double getEndX();
	public abstract double getHight();
	public abstract double getWidth();
	public abstract Color getColor();
	public abstract int getStroke();
	
	//return string for shape Text
	public abstract String getString();
	
	
	public boolean inShape(double x, double y){return false;}
	

}
