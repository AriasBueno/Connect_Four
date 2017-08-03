package Connect4;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Game.ShowConnect4GUI;


public class Frames extends ShowConnect4GUI implements ActionListener{
	
	
	//introduction gui
	private JFrame introFrame;
	
	private JLabel introLabel1;
	
	private JLabel introLabel2;
	
	private JButton introButton;
	
	//instructions gui
	
	private JFrame instructionsFrame;
	
	private JLabel instructionsLabel;
	
	private JButton instructionsButton;
	
	//game gui

	protected ShowConnect4GUI gameFrame;
	
	
	public Frames(){
		myGame();
	}
	
	private void myGame(){
		//introduction gui
		
		introFrame = new JFrame("Connect 4");
		
		introFrame.setBounds(100, 100, 516, 393);
		
		introFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		introFrame.getContentPane().setBackground(Color.cyan);
		
		introFrame.getContentPane().setLayout(null);
		
		
		
		
		introLabel1 = new JLabel("CONNECT 4");
		
		introLabel1.setFont(new Font("Showcard Gothic", Font.PLAIN, 28));
		
		introLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		
		introLabel1.setForeground(Color.black);
		
		introLabel1.setBounds(149, 20, 186, 80);
		
		introFrame.getContentPane().add(introLabel1);
		
		
		introButton = new JButton("Start");
		
		introButton.setBounds(160, 311, 161, 38);
		
		introButton.setBackground(Color.white);
		
		introButton.setForeground(Color.black);
		
		introButton.addActionListener(this);
		
		introButton.setFont(new Font("Showcard Gothic", Font.PLAIN, 20)); 
		
		introFrame.getContentPane().add(introButton);
		
		
		//instructions gui
		
		instructionsFrame = new JFrame("Connect 4 Instructions");
		
		instructionsFrame.setBounds(200, 200, 600, 450);
		
		instructionsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		instructionsFrame.getContentPane().setBackground(Color.cyan);
		
		instructionsFrame.getContentPane().setLayout(null);
		
		instructionsLabel = new JLabel();
		
		instructionsLabel.setText("<html><body><h3>The goal of this game is to connect 4 spots"
																
									+ " on the grid either diagonally, vertically, or horizontally.</h3>"
									
									+ "<h3>Make sure to start from the bottom and work your way up.</h3></body></html>");
		
		instructionsLabel.setFont(new Font("Showcard Gothic", Font.PLAIN, 20));
		
		instructionsLabel.setForeground(Color.BLACK);
		
		instructionsLabel.setBounds(190, 29, 211, 183);
				
		instructionsFrame.getContentPane().add(instructionsLabel);
		
		
		instructionsButton = new JButton("Play");
		
		instructionsButton.setBounds(180, 300, 161, 41);
		
		instructionsButton.setFont(new Font("Showcard Gothic", Font.PLAIN, 20));
		
		instructionsButton.addActionListener(this);
		
		instructionsFrame.getContentPane().add(instructionsButton);
		
		
		
		
		
	}
	
	
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				
				Frames window = new Frames();
				
				window.introFrame.setVisible(true);
				
				Frames window2 = new Frames();
				
				window2.instructionsFrame.setVisible(false);
								
				
			}
		});
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		
		try{
			
			switch(action){
				
			case "Start":
				
				introFrame.setVisible(false);

				instructionsFrame.setVisible(true);
												
				break;
				
			case "Play":
				
				instructionsFrame.setVisible(false);
				
				gameFrame.main(null);
				
				
			}
		}
		catch(Exception ex){
			System.out.println("Something is wrong!");
		}
		
	}

}
