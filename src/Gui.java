

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui {
	
	public static void launchGui() {
		
		JFrame frame = new JFrame("Exercise App");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000,500);
		frame.setLocationRelativeTo(null);
		
		
		// Buttons for Printing, Adding, and Removing
		JButton button1 = new JButton("Print");
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Clear the screen, print out all exercises
			}
		});
		
		JButton button2 = new JButton("Add");
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Put in text box to add a new exercise
			}
		});
		
		JButton button3 = new JButton("Remove");
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Put in text box to remove an existing exercise
			}
		});
		JPanel panel = new JPanel();
		
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		
		
		frame.getContentPane().add(panel);
		frame.setVisible(true);
		
	}
}
