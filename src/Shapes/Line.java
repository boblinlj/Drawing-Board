package Shapes;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;

public class Line extends Shape implements Serializable{
	
	private int x1, y1, x2, y2;
	private Color color;
	private int stroke;
	
	public Line(int x1,int y1,int x2,int y2,Color color,int stroke){
        this.x1=x1;  
        this.y1=y1;  
        this.x2=x2;  
        this.y2=y2;  
        this.color=color;  
        this.stroke=stroke;
	}
	
	@Override
	public void Draw(Graphics2D g) {
		g.setColor(color);
		g.setStroke(new BasicStroke(stroke));
		g.drawLine(x1, y1, x2, y2);
	}

	@Override
	public boolean inShape(int x, int y) {
		// in a line , for each x, there will be a unique y

		if (y==(Math.abs(x1-x) * Math.abs(y2-y1))/Math.abs(x2-x1)+y1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public int getHight() {
		// rise
		return y2-y1;
	}

	@Override
	public int getWidth() {
		// run
		return x2-x1;
	}
	
	public String getString(){
		return null;
	}

	@Override
	public int getInitX() {
		return x1;
	}

	@Override
	public int getInitY() {
		return y1;
	}

	@Override
	public int getEndY() {
		return y2;
	}

	@Override
	public int getEndX() {
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
