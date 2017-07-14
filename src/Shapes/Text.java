package Shapes;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.Serializable;

public class Text extends Shape implements Serializable{
	
	private double x1, y1;
	private Color color;
	private int stroke;
	private String string;
	
	public Text ( double x1, double y1, String string, Color color, int stroke){
		this.x1 = x1;
		this.y1 = y1;
		this.string = string;
		this.color = color;
		this.stroke = stroke;
		
	}

	@Override
	public void Draw(Graphics2D g) {
		g.setColor(color);
		//stroke as front size
		g.setFont(new Font("TimesRoman", Font.PLAIN, stroke));
		g.drawString(string, (int)x1, (int)y1);

	}

	@Override
	public boolean inShape(double x, double y) {
		final int Xbox = this.stroke;
		final int Ybox = this.stroke;
		
		//select the text, since this typical shape does not have an actual shape, user will select an area on the first letter
		if ((x >= x1 && x <= x1 + Xbox) && ( y <= y1 && y >= y1 - Ybox)){
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public double getHight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String getString(){
		return this.string;
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
	public Color getColor() {
		return color;
	}


	@Override
	public int getStroke() {
		return stroke;
	}

	@Override
	public double getEndY() {
		return 0;
	}

	@Override
	public double getEndX() {
		return 0;
	}

}
