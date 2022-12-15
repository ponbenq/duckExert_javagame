package game_2.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import game_2.main.IngameGui.FOOD_STATE;
import game_2.main.IngameGui.INGAME_STATE;

public class Player {
	
	private double x;
	private double y;
	
	private BufferedImage player,coke,cake,leaf;
	private BufferedImage playerF1,playerF2,playerF3,playerF4,playerF5,playerF6,playerF7;
	
	private BufferedImage playerRun[] = new BufferedImage[9];
	private BufferedImageLoader loader;
	private BufferedImage[] playerEat = new BufferedImage[7];
	private BufferedImage[] cokeImg = new BufferedImage[8];
	private BufferedImage[] cakeImg = new BufferedImage[6];
	private BufferedImage[] leafImg = new BufferedImage[8];
	private BufferedImage[] playerSleep = new BufferedImage[5];
	
	private int scale = 2;
	
	
	private int count = 0;
	
	public Player(double x , double y,Game2 game) throws IOException {
		this.x = x ;
		this.y = y;
		loader = new BufferedImageLoader();
		SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
		
		playerF1 = ss.grabImage(1, 1, 128, 128);
		playerF2 = ss.grabImage(2, 1, 128, 128);
		playerF3 = ss.grabImage(3, 1, 128, 128);
		playerF4 = ss.grabImage(4, 1, 128, 128);
		playerF5 = ss.grabImage(5, 1, 128, 128);
		playerF6 = ss.grabImage(6, 1, 128, 128);
		playerF7 = ss.grabImage(7, 1, 128, 128);
		
		SpriteSheet ss2 = new SpriteSheet(loader.loadImage("/res/run128.png"));
		for(int i = 0;i < playerRun.length;i++) {
			playerRun[i] = ss2.grabImage(i+1, 1, 128, 128);
		}
		
		SpriteSheet ss3 = new SpriteSheet(loader.loadImage("/res/player/eatAnimate.png"));
		for(int i = 0;i < playerEat.length;i++) {
			playerEat[i] = ss3.grabImage(i+1, 1, 128, 128);
		}
		
		SpriteSheet ssCake = new SpriteSheet(loader.loadImage("/res/cake_animate.png"));
		for(int i = 0; i < cakeImg.length; i++) {
			cakeImg[i] = ssCake.grabImage(i+1, 1, 64, 64);
		}
		
		SpriteSheet ssCoke = new SpriteSheet(loader.loadImage("/res/coke_animate.png"));
		for(int i = 0; i < cokeImg.length; i++)
			cokeImg[i] = ssCoke.grabImage(i+1, 1, 64, 64);
		
		SpriteSheet ssLeaf = new SpriteSheet(loader.loadImage("/res/leaf_animate.png"));
		for(int i = 0; i < leafImg.length; i++)
			leafImg[i] = ssLeaf.grabImage(i+1, 1, 64, 64);
		coke = cokeImg[0];
		
		SpriteSheet ssSleep = new SpriteSheet(loader.loadImage("/res/sleep128.png"));
		for(int i = 0; i < playerSleep.length; i++)
			playerSleep[i] = ssSleep.grabImage(i+1, 1, 128, 128);
	}
	
	public void tick() {} // update method
	
	public void render(Graphics g) {
		count++;
		if(IngameGui.ingameState == INGAME_STATE.GEN || IngameGui.ingameState == INGAME_STATE.FOOD ) {
			switch(count) {
			
				case 1: player = playerF1;
						break;
				case 8:player = playerF2;
						break;
				case 16:player = playerF3;
						break;
				case 24:player = playerF4;
						break;
				case 32:player = playerF5;
						break;
				case 40:player = playerF6;
						break;
				case 48:player = playerF7;
						count = 0;
						break;
						
			}
			
		}
		
		else if(IngameGui.ingameState == INGAME_STATE.WORKOUT) {
			
		
			switch(count) {
			
			case 1: player = playerRun[0];
					break;
			case 6:player = playerRun[1];
					break;
			case 12:player = playerRun[2];
					break;
			case 18:player = playerRun[3];
					break;
			case 24:player = playerRun[4];
					break;
			case 30:player = playerRun[5];
					break;
			case 36:player = playerRun[6];
					break;
			case 42:player = playerRun[7];
					break;
			case 48:player = playerRun[8];
					count = 0;
					break;
					
			}
			
			
		}
		else if(IngameGui.ingameState == INGAME_STATE.SLEEP) {
			switch(count) {
			
			case 1: player = playerSleep[0];
					break;
			case 12:player = playerSleep[1];
					break;
			case 24:player = playerSleep[2];
					break;
			case 36:player = playerSleep[3];
					break;
			case 48:player = playerSleep[4];
					count = 0;
					break;
					
			}
		}
		 if(IngameGui.ingameState == INGAME_STATE.COKE || IngameGui.ingameState == INGAME_STATE.CAKE || IngameGui.ingameState == INGAME_STATE.LEAF) {
			switch(count) {
			case 1: player = playerEat[0];
					break;
			case 8:player = playerEat[1];
					break;
			case 16:player = playerEat[2];
					break;
			case 24:player = playerEat[3];
					break;
			case 32:player = playerEat[4];
					break;
			case 40:player = playerEat[5];
					break;
			case 48:player = playerEat[6];
					break;
			case 56:player = playerEat[0];
					break;
			case 64:player = playerEat[1];
					break;
			case 72:player = playerEat[2];
					break;
			case 80:player = playerEat[3];
					break;
			case 88:player = playerEat[4];
					break;
			case 96:player = playerEat[5];
					IngameGui.ingameState = INGAME_STATE.GEN;
					count = 0;
					break;
			}
		}
		if(IngameGui.ingameState == INGAME_STATE.GEN || IngameGui.ingameState == INGAME_STATE.WORKOUT || 
				IngameGui.ingameState == INGAME_STATE.FOOD || IngameGui.ingameState == INGAME_STATE.SLEEP)
			g.drawImage(player, (int)x, (int)y, player.getWidth()*scale,player.getHeight()*scale, null);
		else if(IngameGui.ingameState == INGAME_STATE.COKE || IngameGui.ingameState == INGAME_STATE.CAKE || IngameGui.ingameState == INGAME_STATE.LEAF) {
			g.drawImage(player, (int)x, (int)y, player.getWidth()*scale,player.getHeight()*scale, null);
//			g.drawImage(coke,200,400,coke.getWidth()*scale,coke.getHeight()*scale,null);
		}
		
	}
}

