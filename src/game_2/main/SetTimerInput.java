package game_2.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SetTimerInput {
	
	
	private JTextField textField;

	private int second , minute;
	public SetTimerInput(int second, int minute) {
		this.minute = minute;
		this.second = second;
	}
	
	public void start() {
		JFrame frame = new JFrame("input");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		JPanel mainPanel = new JPanel();
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(400,200));
		panel.setLayout(new FlowLayout());
		
		textField = new JTextField(20);
		panel.add(new JLabel("Enter Time (second): "));
		panel.add(textField);
		
		textField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(Integer.parseInt(textField.getText()) < 60) {
					CountDownTimer.minute = 0;
					CountDownTimer.second = Integer.parseInt(textField.getText());
					
				}else {
					CountDownTimer.minute = Integer.parseInt(textField.getText()) / 60;
					CountDownTimer.second= Integer.parseInt(textField.getText()) % 60;
				}
				frame.dispose();
			}
			
		});
		
		JButton button = new JButton("Enter");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
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
}
