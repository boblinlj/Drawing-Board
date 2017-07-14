package Shapes;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;

public class Cir extends Shape implements Serializable{
	
	private int x1, y1, x2, y2;
	private int width, hight;
	private Color color;
	private int stroke;

	public Cir(int x1,int y1,int x2,int y2,Color color,int stroke){
        this.x1=x1;  
        this.y1=y1;  
        this.x2=x2;  
        this.y2=y2;  
        this.color=color;  
        this.stroke=stroke;
	}
	
	// calculate the width
	public int setWidth(){
		width = Math.abs(x2-x1);
		return width;
	}

	//calculate the height
	public int setHight(){
		hight = Math.abs(y1-y2);
		return hight;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHight(){
		return hight;
	}
	
	@Override
	public void Draw(Graphics2D g) {
		g.setColor(color);
		g.setStroke(new BasicStroke(stroke));
		g.drawOval(Math.min(x2, x1),Math.min(y2, y1), this.setWidth(), this.setHight());
	}

	@Override
	public boolean inShape(int x, int y) {
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
