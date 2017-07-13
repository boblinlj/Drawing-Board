import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import Shapes.Shape;


public class BoardFrame extends JFrame{
	
	public Color currentColor = Color.black;
	public ArrayList<Shape> shapes = new ArrayList<Shape>();//why do I have to new this object
	public JPanel centerPanel;
	
	//initiate my drawing board
	public void initFrame() throws Exception{
		
		// window features
		this.setSize(600,500);
		this.setTitle("My Picture");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		addMenu();
		
		// add Main panel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		this.add(mainPanel);
		
		// add center panel as the painting area
		centerPanel = new JPanel(){
			// redraw all shapes every time the window is resumed
			public void paint(Graphics g1){
				super.paint(g1);
				Graphics2D g = (Graphics2D)g1;
				System.out.println("Redrawing");
				for(int i=0 ; i<shapes.size() ; i++){
					Shape shape=(Shape)shapes.get(i);
					shape.Draw(g);
				}
			}
			
		};
		centerPanel.setPreferredSize(new Dimension(495,0));
		centerPanel.setBackground(Color.WHITE);
		mainPanel.add(centerPanel, BorderLayout.WEST);
		
		// add right panel
		JPanel rightPanel = new JPanel();
		rightPanel.setPreferredSize(new Dimension(105, 0));
		rightPanel.setLayout(new BorderLayout());
		mainPanel.add(rightPanel, BorderLayout.EAST);
		
		// add tool panel in right panel
		JPanel toolPanel = new JPanel();
		toolPanel.setPreferredSize(new Dimension(0, 350));
		toolPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,0,0));
		rightPanel.add(toolPanel, BorderLayout.NORTH);
		
		// add tool buttons
		ButtonGroup tools = new ButtonGroup();
		String[] allTools = "Line,Rec,Cir,Text,Pointer".split(",");
		for(int i=0 ; i<5 ; i++ ){

			JRadioButton toolOption = new JRadioButton(allTools[i]);
			toolOption.setText(allTools[i]);
			toolOption.setPreferredSize(new Dimension(90, 40));
				
			toolOption.setActionCommand(toolOption.getText());
			
			tools.add(toolOption);
			toolPanel.add(toolOption);
			//default tool as nothing
			if(i == 4){
				toolOption.setSelected(true);
			}
		}

		
		
		// add color panel in right panel
		JPanel colorPanel = new JPanel();
		colorPanel.setPreferredSize(new Dimension(0, 150));
		colorPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,0,0));
		rightPanel.add(colorPanel, BorderLayout.SOUTH);
		
		//add button listener, to listen color panel
		ButtonHandler bl = new ButtonHandler(this);
		
		// add color buttons
		for (int i=0 ; i<6 ; i++){
			JButton colorOption = new JButton();
			Color c = new Color(i*12,i*20+50,i*1+7);
			colorOption.setBackground(c);
			colorOption.setPreferredSize(new Dimension(43, 42));
			colorOption.addActionListener(bl);
			colorPanel.add(colorOption);
		}
		
		
		this.setVisible(true);
		
		//add mouse listener
		Graphics screen =  centerPanel.getGraphics();
		MouseHandler mouseListener = new MouseHandler(screen, tools, this, shapes);
//		KeyboardHandler keyboard = new KeyboardHandler(screen, tool, this, shapes);
		centerPanel.addMouseListener(mouseListener);
		centerPanel.addMouseMotionListener(mouseListener);
		centerPanel.addKeyListener(mouseListener);
		centerPanel.setFocusable(true);
		centerPanel.setFocusTraversalKeysEnabled(false);
	}
	
	//add menu bar
	public void addMenu()throws Exception{
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");

		MenuHandler menuHandler = new MenuHandler(this);
		
		JMenuItem option1 = new JMenuItem("Open");
		JMenuItem option2 = new JMenuItem("Save");

		option1.addActionListener(menuHandler);
		option2.addActionListener(menuHandler);
		
		this.setJMenuBar(menuBar);
		menuBar.add(menu);
		menu.add(option1);
		menu.add(option2);
		
	}
}
