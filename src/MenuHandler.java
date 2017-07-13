

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


import Shapes.Shape;

public class MenuHandler implements ActionListener{
	
	private BoardFrame frame;

	public MenuHandler(BoardFrame frame) {
		this.frame = frame;
	}


	public void actionPerformed(ActionEvent e){
		String cmd = e.getActionCommand();
		System.out.println(cmd);
		
		if("Save".equals(cmd)){
			saveFile();
		}else if("Open".equals(cmd))

			openFile();
		
	}
	
	public void saveFile(){
		JFileChooser chooser = new JFileChooser();
		chooser.showSaveDialog(null);
		File fileToSave = chooser.getSelectedFile();
		
		if(fileToSave == null){
			JOptionPane.showMessageDialog(null, "Please choose a file");
		}else {
			try{
				
				FileOutputStream fileOutput = new FileOutputStream(fileToSave);

				System.out.println(fileToSave.getPath());
				ObjectOutputStream objOutput = new ObjectOutputStream(fileOutput);
				
				objOutput.writeObject(frame.shapes);

				JOptionPane.showMessageDialog(null, "Saved!");
				objOutput.close();
				
			} catch (Exception e2){
				e2.printStackTrace();
			}
		}
	}
	
	public void openFile(){
		frame.shapes.removeAll(frame.shapes);
		frame.centerPanel.repaint();
		
		try{
			JFileChooser chooser = new JFileChooser();
			chooser.showOpenDialog(null);
			File fileToOpen = chooser.getSelectedFile();
			
			if (fileToOpen == null){
				JOptionPane.showMessageDialog(null, "Please choose a file");
			} else {
				FileInputStream fileInput = new FileInputStream(fileToOpen);
				ObjectInputStream optInput = new ObjectInputStream(fileInput);
				System.out.println(fileToOpen.getPath());
				
				ArrayList<Shape> shapes = (ArrayList<Shape>)optInput.readObject();
				for(int i=0 ; i<shapes.size() ; i++){
					Shape shape = (Shape)shapes.get(i);
					frame.shapes.add(shape);
					frame.centerPanel.repaint();
				}
				optInput.close();
			}
			
		}catch (Exception e3){
			e3.printStackTrace();
		}
		
		
	}
}
