package Shapes;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.io.Serializable;

public class Line extends Shape implements Serializable{
	
	private double x1, y1, x2, y2;
	private Color color;
	private int stroke;
	
	public Line(double x1,	double y1, double x2, double y2,Color color,int stroke){
        this.x1=x1;  
        this.y1=y1;  
        this.x2=x2;  
        this.y2=y2;  
        this.color=color;  
		if (stroke<=1){
			this.stroke = 1;
		}else{
			this.stroke = stroke;
		}
	}
	
	@Override
	public void Draw(Graphics2D g) {
		g.setColor(color);
		g.setStroke(new BasicStroke(stroke));
		g.draw(new Line2D.Double(x1, y1, x2, y2));
	}

	@Override
	public boolean inShape(double x, double y) {
		// in a line , for each x, there will be a unique y
		double Y=0;
		
		if (y1>y2){
			Y = Math.abs((Math.abs(x1-x) * Math.abs(y1-y2))/Math.abs(x2-x1)-y1);
		}else if(y2>=y1){
			Y = Math.abs((Math.abs(x2-x) * Math.abs(y1-y2))/Math.abs(x2-x1)-y2);
		}
		
		final double range = 5;

		if (y >= Y - range && y <= Y + range){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public double getHight() {
		// rise
		return y2-y1;
	}

	@Override
	public double getWidth() {
		// run
		return x2-x1;
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
