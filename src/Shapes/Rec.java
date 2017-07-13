package Shapes;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;

public class Rec extends Shape implements Serializable{
	
	private int x1, y1, x2, y2; // value from drawing panel (as shape)
	private int xStart, yStart, width, height; // value for rec specific
	private Color color;
	private int stroke;
	
	// referenced when new shape is created
	public Rec(int x1,int y1,int x2,int y2,Color color,int stroke){
        this.x1=x1;  
        this.y1=y1;  
        this.x2=x2;  
        this.y2=y2;  
        this.color=color;  
        this.stroke=stroke;
	}
	
	
	//calculate the starting point X
	public int setX(int x){
		this.xStart = x;
		return xStart;
	}

	//calculate the ending point Y
	public int setY(int y){
		this.yStart = y;
		return yStart;
	}
	
	public int getX(){
		return this.xStart;
	}
	
	public int getY(){
		return this.yStart;
	}
	
	// calculate the width
	public int setWidth(){
		width = Math.abs(x2-x1);
		return width;
	}

	//calculate the height
	public int setHight(){
		height = Math.abs(y1-y2);
		return height;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHight(){
		return height;
	}
	
	@Override
	public void Draw(Graphics2D g) {
		g.setColor(color);
		g.setStroke(new BasicStroke(stroke));
		g.drawRect(this.setX(Math.min(x2, x1)),this.setY(Math.min(y2, y1)), this.setWidth(), this.setHight());
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
}
