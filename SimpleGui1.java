import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleGui1 {
	int x;
	int y;
	
	JFrame frame;
	JLabel label;
	
	public static void main(String[] args) {
		SimpleGui1 gui = new SimpleGui1();
		gui.go();
	}
	
	public void go() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton labelButton = new JButton("Change Label");
		labelButton.addActionListener(new LabelListener());
		
		JButton colorButton = new JButton("Change Circle Color");
		colorButton.addActionListener(new ColorListener());
		
		label = new JLabel("I'm a Label");
		MyDrawPanel drawPanel = new MyDrawPanel();
		
		frame.getContentPane().add(BorderLayout.SOUTH, colorButton);
		frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
		frame.getContentPane().add(BorderLayout.EAST, labelButton);
		frame.getContentPane().add(BorderLayout.WEST, label);
		
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
	
	class LabelListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			label.setText("Ouch!");
		}
	}
	
	class ColorListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			frame.repaint();
		}
	}

		

}
