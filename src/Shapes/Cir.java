package Shapes;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;

public class Cir extends Shape implements Serializable{
	
	private double x1, y1, x2, y2;
	private double width, hight;
	private Color color;
	private int stroke;

	public Cir(double x1,double y1,double x2,double y2,Color color,int stroke){
        this.x1=x1;  
        this.y1=y1;  
        this.x2=x2;  
        this.y2=y2;  
        this.color=color;  
        this.stroke=stroke;
	}
	
	// calculate the width
	public double setWidth(){
		width = Math.abs(x2-x1);
		return width;
	}

	//calculate the height
	public double setHight(){
		hight = Math.abs(y1-y2);
		return hight;
	}
	
	public double getWidth(){
		return width;
	}
	
	public double getHight(){
		return hight;
	}
	
	@Override
	public void Draw(Graphics2D g) {
		g.setColor(color);
		g.setStroke(new BasicStroke(stroke));
		g.draw(new Ellipse2D.Double(Math.min(x2, x1),Math.min(y2, y1), this.setWidth(), this.setHight()));
	}

	@Override
	public boolean inShape(double x, double y) {
		if( ( x >= Math.min(x1, x2) && x <= Math.max(x1, x2) )&&( y >= Math.min(y1, y2) && y <= Math.max(y1, y2) )){
			return true;
		}else{
			return false;
		}
	}
	
	public String getString(){
		return null;
	}
	
	@Override
	public double getInitX() {
		return x1;
	}


	@Override
	public double getInitY() {
		return y1;
	}


	@Override
	public double getEndY() {
		return y2;
	}


	@Override
	public double getEndX() {
		return x2;
	}


	@Override
	public Color getColor() {
		return color;
	}


	@Override
	public int getStroke() {
		return stroke;
	}
}
