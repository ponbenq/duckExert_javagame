package game_2.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import game_2.main.Game2.STATE;

public class Info {
	private BufferedImageLoader loader;
	private BufferedImage info,backButton,backPressedButton,currentBackButton;
	private BufferedImage backgroundInfo;
	
	private MouseL mouseListener;
	private Rect backButRect;
	public Info(MouseL mouseListener) {
		
		this.mouseListener = mouseListener;
		
		loader = new BufferedImageLoader();
		try {
			info = loader.loadImage("/res/info_game.png");
			SpriteSheet ssBack = new SpriteSheet(loader.loadImage("/res/back_button_36_15.png"));
			backButton = ssBack.grabImage(1, 1, 36, 15);
			backPressedButton = ssBack.grabImage(2, 1, 36, 15);
			
			backgroundInfo = loader.loadImage("/res/info_background.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		backButRect = new Rect(30,30,backButton.getWidth()*4,backButton.getHeight()*4);
		currentBackButton = backButton;
	}
	public void tick() {
		if(mouseListener.x >= backButRect.x && mouseListener.x <= backButRect.x + backButRect.width &&
				mouseListener.y >= backButRect.y && mouseListener.y <= backButRect.y + backButRect.height) {
			currentBackButton = backPressedButton;
			if(mouseListener.isPressed()) {
				Game2.state = STATE.MENU;
			}
			
		}
		else {
			currentBackButton = backButton;
		}
	}
	
	public void start(Graphics g) {
//		g.drawImage(backgroundInfo, 80, 80, (int)((info.getWidth()/3) * 1.5) , (int)((info.getHeight()/3) * 1.75), null);
		g.drawImage(info, 100, 100, info.getWidth()/3, info.getHeight()/3, null);
		g.drawImage(currentBackButton, (int)backButRect.x, (int)backButRect.y, (int)backButRect.width, (int)backButRect.height, null);
	}
	
	
}
