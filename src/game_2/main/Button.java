package game_2.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import game_2.main.Game2.STATE;

public class Button {
	
	int x,y,width,height;
	
	private boolean isPressedStart,enable;
	
	
	
	private MouseL mouseListener;
	
	
	private BufferedImageLoader loader;
	private BufferedImage startButton,startPressedButton,infoButton,infoPressedButton,exitButton,exitPressedButton
	,currentStartButton,currentInfoButton,currentExitButton;
	
	public Rect startRect,infoRect,exitRect;
	
	private Input inputGui = new Input(Game2.userName);
	
	
	public Button(MouseL mouseListener) {
		
		
		this.mouseListener = mouseListener;
		
		enable = true;
		
		try {
			loader = new BufferedImageLoader();
			SpriteSheet ss = new SpriteSheet(loader.loadImage("/res/start_button_36_15.png"));
			startButton = ss.grabImage(1, 1, 36, 15);
			startPressedButton = ss.grabImage(2, 1, 36, 15);
			ss = new SpriteSheet(loader.loadImage("/res/info_button_36_15.png"));
			infoButton = ss.grabImage(1, 1, 36, 15);
			infoPressedButton = ss.grabImage(2, 1, 36, 15);
			ss = new SpriteSheet(loader.loadImage("/res/exit_button_36_15.png"));
			exitButton = ss.grabImage(1, 1, 36, 15);
			exitPressedButton = ss.grabImage(2, 1, 36, 15);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		currentStartButton = startButton;
		currentInfoButton = infoButton;
		currentExitButton = exitButton;
		
		startRect = new Rect(Game2.SCALE_WIDTH / 2 - 72 ,200,startButton.getWidth()*4,startButton.getHeight()*4);
		infoRect = new Rect(Game2.SCALE_WIDTH / 2 - 72 ,325,infoButton.getWidth()*4,infoButton.getHeight()*4);
		exitRect = new Rect(Game2.SCALE_WIDTH / 2 - 72 ,450,exitButton.getWidth()*4,exitButton.getHeight()*4);
		
		
	}
	
	
	public void tick() {
		
		//start button
		if(mouseListener.getX() >= startRect.x && mouseListener.getX() <= startRect.x + startRect.width 
				&& mouseListener.getY() >= startRect.y && mouseListener.getY() <= startRect.y + startRect.height) {
//			isPressedStart = true;
			currentStartButton = startPressedButton;
			
			if(mouseListener.isPressed()) {
				Game2.state = STATE.GAME;
				inputGui.start();
				
			}
		}else {
//			isPressedStart = false;
			currentStartButton = startButton;
		}
		
		//info button
		if(mouseListener.getX() >= infoRect.x && mouseListener.getX() <= infoRect.x + infoRect.width 
				&& mouseListener.getY() >= infoRect.y && mouseListener.getY() <= infoRect.y + infoRect.height) {
//			isPressedStart = true;
			currentInfoButton = infoPressedButton;
			
			if(mouseListener.isPressed()) {
				System.out.println("Start button is pressed!");
				Game2.state = STATE.INFO;
			}
		}else {
//			isPressedStart = false;
			currentInfoButton = infoButton;
		}
		
		//exit button
		if(mouseListener.getX() >= exitRect.x && mouseListener.getX() <= exitRect.x + exitRect.width 
				&& mouseListener.getY() >= exitRect.y && mouseListener.getY() <= exitRect.y + exitRect.height) {
//			isPressedStart = true;
			currentExitButton = exitPressedButton;
			
			if(mouseListener.isPressed()) {
				System.exit(0);
			}
		}else {
//			isPressedStart = false;
			currentExitButton = exitButton;
		}
		
	}
	
	public void render(Graphics g) {
		
		
//		if(isPressedStart) {
////			g.setColor(Color.DARK_GRAY);
//			g.drawImage(startPressedButton,(int)startRect.x ,(int)startRect.y,(int)startRect.width,(int)startRect.height,null);
//			g.drawImage(infoPressedButton, (int)infoRect.x, (int)infoRect.y, (int)infoRect.width, (int)infoRect.height, null);
//			g.drawImage(exitPressedButton, (int)exitRect.x, (int)exitRect.y, (int)exitRect.width, (int)exitRect.height, null);
//		}
		
//			g.setColor(Color.LIGHT_GRAY);
		g.drawImage(currentStartButton,(int)startRect.x ,(int)startRect.y,(int)startRect.width,(int)startRect.height, null);
		g.drawImage(currentInfoButton, (int)infoRect.x, (int)infoRect.y, (int)infoRect.width, (int)infoRect.height, null);
		g.drawImage(currentExitButton, (int)exitRect.x, (int)exitRect.y, (int)exitRect.width, (int)exitRect.height, null);
	
		
		
		if(enable) {
//			g.fillRect(x, y, width, height);
//			g.setFont(font1);
//			int stringWidth = g.getFontMetrics().stringWidth(text);
//			g.setColor(Color.black);
//			g.drawString(text, width/2 + x, height/2 + y);
			
			
			
			
		}
		
		
		
	}
	
	
}
