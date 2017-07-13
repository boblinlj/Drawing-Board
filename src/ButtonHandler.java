
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ButtonHandler  implements ActionListener{
	private BoardFrame frame;

	public ButtonHandler(BoardFrame frame) {
		this.frame = frame;
	}
	
	public void actionPerformed(ActionEvent e){
		JButton bt = (JButton)e.getSource();
		// pass the color to current color
		Color c = bt.getBackground();
		frame.currentColor = c;
	}
	
}