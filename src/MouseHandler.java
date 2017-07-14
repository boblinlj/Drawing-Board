
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

public class MouseHandler implements MouseListener, KeyListener{
	
	private BoardFrame frame;
	private Graphics2D g;
	// Start(x1, y1) End(x2, y2)
	private double x1, y1, x2, y2;
	private Color paint;
	private ButtonGroup toolBox;
	private int shapeSelected;
	
	//mark if a shape is selected
	private boolean selectMarker=false;
	
	//default tool is line
	private String cmd;
	
	//saved shapes
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	
	private int defaultStroke = 1;
	private int defaultFrontSize = 20;
	
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
			Shape line = new Line(x1, y1, x2, y2, g.getColor(),defaultStroke);
			line.Draw(g);
			shapes.add(line);
			
		}else if("Rec".equals(cmd)){
			Shape rec = new Rec(x1, y1, x2, y2, g.getColor(), defaultStroke);
			rec.Draw(g);
			shapes.add(rec);
			
		}else if("Cir".equals(cmd)){
			Shape cir = new Cir(x1, y1, x2, y2, g.getColor(),defaultStroke);
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
					Shape text = new Text(x1, y1, textInput, g.getColor(), defaultFrontSize);
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
					Shape newRec = new Rec(x2, y2, x2+shapes.get(shapeSelected).getWidth(), y2+shapes.get(shapeSelected).getHight(), g.getColor(), shapes.get(shapeSelected).getStroke());
					shapes.remove(shapeSelected);
					shapes.add(newRec);
					frame.centerPanel.repaint();
				}else if(shapes.get(shapeSelected) instanceof Cir){
					// if shape is cir, move shape
					Shape newCir = new Cir(x2, y2, x2+shapes.get(shapeSelected).getWidth(), y2+shapes.get(shapeSelected).getHight(), g.getColor(), shapes.get(shapeSelected).getStroke());
					System.out.println(shapes.get(shapeSelected).getStroke());
					shapes.remove(shapeSelected);
					shapes.add(newCir);
					frame.centerPanel.repaint();
				}else if(shapes.get(shapeSelected) instanceof Line){
					// if shape is Line, move shape
					Shape newLine = new Line(x2, y2, x2-shapes.get(shapeSelected).getWidth(), y2-shapes.get(shapeSelected).getHight(), g.getColor(), shapes.get(shapeSelected).getStroke());
					shapes.remove(shapeSelected);
					shapes.add(newLine);
					frame.centerPanel.repaint();
				}else if(shapes.get(shapeSelected) instanceof Text){
					Shape newText = new Text(x2, y2, shapes.get(shapeSelected).getString(), g.getColor(), shapes.get(shapeSelected).getStroke());
					System.out.println(shapes.get(shapeSelected).getStroke());
					shapes.remove(shapeSelected);
					shapes.add(newText);
					frame.centerPanel.repaint();
				}
			}
			System.out.println(selectMarker);
			frame.centerPanel.requestFocus();
		}
		
	}
	
	public void mouseClicked(MouseEvent e) {
		if("Pointer".equals(cmd)){
			if(selectMarker){
				Color newColor = paint;
				changeColor(newColor);
				
			}
		}
	}
  
    public void mouseEntered(MouseEvent e) {

    }  
  
    public void mouseExited(MouseEvent e) {  
          
    }

	@Override
	public void keyPressed(KeyEvent e2) {
		System.out.println(e2.getKeyCode());
		System.out.println(shapeSelected);
		
		if("Pointer".equals(cmd)){
			if(selectMarker){
				int key = e2.getKeyCode();
				
				if(key == KeyEvent.VK_R){
					remove(shapeSelected);
				}else if(key == KeyEvent.VK_PERIOD){
					iterateShapes(add());
				}else if(key == KeyEvent.VK_COMMA){
					iterateShapes(minus());
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}  
    
	private void remove(int shapeSelected){
		shapes.remove(shapeSelected);
		frame.centerPanel.paint(g);
	}
	
	
	private void iterateShapes(int step){
		Shape currentShape = shapes.get(shapeSelected);
		if((currentShape.getStroke() + step)>=1){
			if(currentShape instanceof Rec){
				Shape newRec = new Rec(currentShape.getInitX(),currentShape.getInitY(),currentShape.getEndX(),currentShape.getEndY(), currentShape.getColor(),currentShape.getStroke() + step);
				currentShape = newRec;
			}else if (currentShape instanceof Line){
				Shape newLine = new Line(currentShape.getInitX(),currentShape.getInitY(),currentShape.getEndX(),currentShape.getEndY(), currentShape.getColor(),currentShape.getStroke() + step);
				currentShape = newLine;
			}else if(currentShape instanceof Cir){
				Shape newCir = new Cir(currentShape.getInitX(),currentShape.getInitY(),currentShape.getEndX(),currentShape.getEndY(), currentShape.getColor(),currentShape.getStroke() + step);
				currentShape = newCir;
			}else if(currentShape instanceof Text){
				Shape newText = new Text(currentShape.getInitX(),currentShape.getInitY(),currentShape.getString(), currentShape.getColor(),currentShape.getStroke() + step);
				currentShape = newText;
			}
		}
		shapes.remove(shapeSelected);
		shapes.add(currentShape);
		frame.centerPanel.repaint();
	}
	
	private int add() {return 1;}
	
	private int minus(){return -1;}
	
	private void changeColor(Color c){
		Shape currentShape = shapes.get(shapeSelected);
		if(currentShape instanceof Rec){
			Shape newRec = new Rec(currentShape.getInitX(),currentShape.getInitY(),currentShape.getEndX(),currentShape.getEndY(), c, currentShape.getStroke());
			currentShape = newRec;
		}else if (currentShape instanceof Line){
			Shape newLine = new Line(currentShape.getInitX(),currentShape.getInitY(),currentShape.getEndX(),currentShape.getEndY(), c, currentShape.getStroke());
			currentShape = newLine;
		}else if(currentShape instanceof Cir){
			Shape newCir = new Cir(currentShape.getInitX(),currentShape.getInitY(),currentShape.getEndX(),currentShape.getEndY(), c, currentShape.getStroke());
			currentShape = newCir;
		}else if(currentShape instanceof Text){
			Shape newText = new Text(currentShape.getInitX(),currentShape.getInitY(),currentShape.getString(), c, currentShape.getStroke());
			currentShape = newText;
		}
		shapes.remove(shapeSelected);
		shapes.add(currentShape);
		frame.centerPanel.repaint();
	}
	
}
