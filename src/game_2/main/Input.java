package game_2.main;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game_2.main.Game2.STATE;

public class Input{
	
	
	private String input;
	private TextField textField;
	private JButton button;
	
	public Input(String input) {
		this.input = input;
		
	}
	public void start() {
		JFrame frame = new JFrame("Another panel");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		
		JPanel mainPanel = new JPanel(new GridBagLayout());
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(400,80));
		panel.setLayout(new FlowLayout());
		
		textField = new TextField(20);
		panel.add(new JLabel("Enter Pet Name : "));
		panel.add(textField);
		textField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Game2.userName = textField.getText();
				System.out.println(Game2.userName);
				Game2.state = STATE.INGAME;
				frame.dispose();
			}
			
		});
		
		button = new JButton("Enter");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Game2.userName+= textField.getText();
				System.out.println(Game2.userName);
				Game2.state = STATE.INGAME;
				frame.dispose();
				
			}
			
		});
		panel.add(button);
		
		panel.setBorder(BorderFactory.createTitledBorder("Enter your details"));
		mainPanel.add(panel);
		frame.add(mainPanel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
		
		
		
	}
	
	public String getInput() {
		return input;
	}
}
