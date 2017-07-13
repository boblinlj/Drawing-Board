
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JFrame;
import javax.swing.JTextField;


import Shapes.Cir;
import Shapes.Line;
import Shapes.Rec;
import Shapes.Shape;
import Shapes.Text;

public class MouseHandler implements MouseListener, MouseMotionListener, KeyListener{
	
	private BoardFrame frame;
	private Graphics2D g;
	// Start(x1, y1) End(x2, y2)
	private int x1, y1, x2, y2;
	private Color paint;
	private ButtonGroup toolBox;
	private int shapeSelected;
	
	//mark if a shape is selected
	private boolean selectMarker=false;
	
	//default tool is line
	private String cmd;
	
	//saved shapes
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	
	//constructor
	public MouseHandler(Graphics g, ButtonGroup bg, BoardFrame frame, ArrayList<Shape> shapes) {
		this.g = (Graphics2D)g;
		this.toolBox = bg;
		this.frame = frame;
		this.shapes = shapes;
	}
	
	//collect the starting
	public void mousePressed(MouseEvent e){
		
//		selectMarker = false;
		x1 = e.getX();
		y1 = e.getY();
		
		//which button was pressed
		ButtonModel bm = toolBox.getSelection();
		
		//which tool is associated to that button
		cmd = bm.getActionCommand();
		System.out.println(cmd);
		//assign currentColor from the frame as the color of my paint
		paint = frame.currentColor;
		
		g.setColor(paint);
		
		Stroke s = new BasicStroke(1);
		g.setStroke(s);
		
		if("Pointer".equals(cmd)){

			//select a shape, get shape index
			for(shapeSelected=0;shapeSelected<shapes.size();shapeSelected++){
				if(shapes.get(shapeSelected).inShape(x1,y1)){
					selectMarker = true;
					System.out.println("Shape "+shapeSelected+" is selected");
					break;
				}else{
					selectMarker = false;
				}
			}
		}
		
	}

	public void mouseReleased(MouseEvent e){
		x2 = e.getX();
		y2 = e.getY();
		
		if("Line".equals(cmd))
		{
			Shape line = new Line(x1, y1, x2, y2, g.getColor(),1);
			line.Draw(g);
			shapes.add(line);
			
		}else if("Rec".equals(cmd)){
			Shape rec = new Rec(x1, y1, x2, y2, g.getColor(), 1);
			rec.Draw(g);
			shapes.add(rec);
			
		}else if("Cir".equals(cmd)){
			Shape cir = new Cir(x1, y1, x2, y2, g.getColor(),1);
			cir.Draw(g);
			shapes.add(cir);
			
		}else if("Text".equals(cmd)){
			//create a window that allow clients to type words
			JFrame word = new JFrame();
			word.setTitle("Please insert a string:");
			word.setLocationRelativeTo(null);
			word.setDefaultCloseOperation(3);
			word.setSize(400,100);
			word.setBackground(Color.black);;
			JTextField textField = new JTextField(20);
			word.add(textField);
			word.setVisible(true);
			
			textField.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String textInput = textField.getText();
					Shape text = new Text(x1, y1, textInput, g.getColor(),24);
					text.Draw(g);
					shapes.add(text);
					// after inserting the content, textfield will be closed
					word.dispose();
					
				}
			});
		}else if("Pointer".equals(cmd)){
			
			if(selectMarker){
			// select a shape
				if(shapes.get(shapeSelected) instanceof Rec){
					//if shape is rec, move shape
					Shape newRec = new Rec(x2, y2, x2+shapes.get(shapeSelected).getWidth(), y2+shapes.get(shapeSelected).getHight(), g.getColor(), 1);
					shapes.remove(shapeSelected);
					shapes.add(newRec);
					frame.centerPanel.repaint();
				}else if(shapes.get(shapeSelected) instanceof Cir){
					// if shape is cir, move shape
					Shape newCir = new Cir(x2, y2, x2+shapes.get(shapeSelected).getWidth(), y2+shapes.get(shapeSelected).getHight(), g.getColor(), 1);
					shapes.remove(shapeSelected);
					shapes.add(newCir);
					frame.centerPanel.repaint();
				}else if(shapes.get(shapeSelected) instanceof Line){
					// if shape is Line, move shape
					Shape newLine = new Line(x2, y2, x2+shapes.get(shapeSelected).getWidth(), y2+shapes.get(shapeSelected).getHight(), g.getColor(), 1);
					shapes.remove(shapeSelected);
					shapes.add(newLine);
					frame.centerPanel.repaint();
				}else if(shapes.get(shapeSelected) instanceof Text){
					Shape newText = new Text(x2, y2, shapes.get(shapeSelected).getString(), g.getColor(), 24);
					shapes.remove(shapeSelected);
					shapes.add(newText);
					frame.centerPanel.repaint();
				}
			}
			System.out.println(selectMarker);
		}
		
	}
	
	public void mouseClicked(MouseEvent e) {
		
	}
  
    public void mouseEntered(MouseEvent e) {

    }  
  
    public void mouseExited(MouseEvent e) {  
          
    }

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {

		if("Pointer".equals(cmd)){
			if(selectMarker){
				int key=e.getKeyCode();
				
				if(key == KeyEvent.VK_R){
					System.out.println(key);
					remove(shapeSelected);
				}else if(key == KeyEvent.VK_PERIOD){
					addStroke(shapeSelected);
				}else if(key == KeyEvent.VK_COMMA){
					minusStroke(shapeSelected);
				}else if(key == KeyEvent.VK_EQUALS){
					extend(shapeSelected);
				}else if(key == KeyEvent.VK_MINUS){
					shrink(shapeSelected);
				}
			}
		}
		
	}  
    
	private void remove(int shapeSelected){
		shapes.remove(shapeSelected);
	}
	
	private void addStroke(int shapeSelected){
		if(shapes.get(shapeSelected) instanceof Line){
			Shape newRec = new Rec(x2, y2, x2+shapes.get(shapeSelected).getWidth(), y2+shapes.get(shapeSelected).getHight(), g.getColor(), 1);
			shapes.remove(shapeSelected);
			shapes.add(newRec);
		}
	}
	
	private void minusStroke(int shapeSelected){
		
	}
	
	private void extend(int shapeSelected){
		
	}
	
	private void shrink(int shapeSelected){
		
	}

}
