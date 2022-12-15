package game_2.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import game_2.main.IngameGui.INGAME_STATE;

public class Stamina {
	
	private BufferedImageLoader loader;
	
	private BufferedImage[] energyStamina = new BufferedImage[5];
	private BufferedImage energy;
	private BufferedImage[] foodStamina = new BufferedImage[5];
	private BufferedImage lifeIcon,energyIcon;
	
	private Rect eRect,e2Rect;
	
	public static int staminaNum = 0;
	public static int lifeNum = 0;
	
	public Stamina() {
		loader = new BufferedImageLoader();
		try {
			SpriteSheet ssStamina = new SpriteSheet(loader.loadImage("/res/energy_stamina_36_15.png"));
			for(int i = 0;i<energyStamina.length;i++) {
				energyStamina[i] = ssStamina.grabImage(i+1, 1, 36, 15);
				foodStamina[i] = ssStamina.grabImage(i+1, 1, 36, 15);
			}
			lifeIcon = loader.loadImage("/res/life_icon_21_21.png");
			energyIcon = loader.loadImage("/res/enery_pix_20_20.png");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		eRect = new Rect(200,15,energyStamina[0].getWidth()*2, energyStamina[0].getHeight()*2);
		e2Rect = new Rect(300,15,energyStamina[0].getWidth()*2, energyStamina[0].getHeight()*2);
		
	}
	
	public void tick() {
		if(Game2.timecountdown % 1800 == 0) {
			
			
			if(staminaNum > 0)
				staminaNum-= 1;
			if(lifeNum > 0)
				lifeNum-=1;
			
			
		}
		if(IngameGui.ingameState == INGAME_STATE.COKE || IngameGui.ingameState == INGAME_STATE.CAKE || IngameGui.ingameState == INGAME_STATE.LEAF) {
			
			if(staminaNum < 4) {

				staminaNum+=1;
			}
			
		}
	}
	
	public void render(Graphics g) {
		
		
		g.drawImage(energyIcon, 175, 17, energyIcon.getWidth(), energyIcon.getHeight(), null);
		g.drawImage(lifeIcon, 275, 17, lifeIcon.getWidth(), lifeIcon.getHeight(), null);
		g.drawImage(energyStamina[staminaNum], (int)eRect.x, (int)eRect.y, (int)eRect.width, (int)eRect.height, null);
		g.drawImage(foodStamina[lifeNum], (int)e2Rect.x, (int)e2Rect.y, (int)e2Rect.width, (int)e2Rect.height, null);
		
	}
}
