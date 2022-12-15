package game_2.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game_2.main.Game2.STATE;

public class IngameGui {
	
	private BufferedImage bg,coin,foodButton,foodPressedButton,currentFoodButton;
	private BufferedImage workoutButton,workoutPressedButton,currentWorkoutButton;
	private BufferedImage sleepButton,sleepPressedButton,currentSleepButton;
	private BufferedImage cokeButton,cokePressedButton,currentCokeButton;
	private BufferedImage cakeButton,cakePressedButton,currentCakeButton;
	private BufferedImage leafButton,leafPressedButton,currentLeafButton;
	private BufferedImage backButton,backPressedButton,currentBackButton;
	private BufferedImage timerButton,timerPressedButton,currentTimerButton;
	private BufferedImage setTimeButton,setTimerPressedButton,currentSetTimeButton;
	private BufferedImage startTimerButton,startTimerPressedButton,currentStartTimerButton;
	private BufferedImage player;
	private BufferedImage[] playerEat = new BufferedImage[10];
	private BufferedImageLoader loader;
	private SpriteSheet ss,ss1;
	private Rect foodButRect,workoutButRect,sleepButRect,cokeButRect,cakeButRect,leafButRect,backButRect,timerButRect,anotherBackButRect,
	setTimerButRect,startTimerButRect;
	private Rect inTimerBackButton;
	private MouseL mouseListener;
	
	private BufferedImage bgTime;
	
	public static int foodCoin = 3000;
	private int scale = 2;
	private CountDownTimer timer;
	
	private SetTimerInput setTimer;
	public static int second = 0, minutes = 0;
	
	private Stamina stamina;
	
	public static enum INGAME_STATE{
		GEN,
		FOOD,
		WORKOUT,
		SLEEP,
		TIMER,
		STARTTIMER,
		CAKE,
		COKE,
		LEAF
	}
	
	public static INGAME_STATE ingameState = INGAME_STATE.GEN;
	public static enum FOOD_STATE{
		COKE,
		CAKE,
		LEAF
	}
	public static FOOD_STATE foodState = null;
	
	public IngameGui(MouseL mouseListener) {
		this.mouseListener = mouseListener;
		timer = new CountDownTimer(second,minutes ,new Thread());
		try {
			
			loader = new BufferedImageLoader();
			bg = ImageIO.read(getClass().getResource("/res/bg1.png"));
			coin = loader.loadImage("/res/coin.png");
			
			ss = new SpriteSheet(loader.loadImage("/res/food_button.png"));
			foodButton = ss.grabImage(1, 1, 32, 32);
			foodPressedButton = ss.grabImage(2, 1, 32, 32);
			
			ss = new SpriteSheet(loader.loadImage("/res/workout_button.png"));
			workoutButton = ss.grabImage(1, 1, 32, 32);
			workoutPressedButton = ss.grabImage(2, 1, 32, 32);
			
			ss = new SpriteSheet(loader.loadImage("/res/sleep_button.png"));
			sleepButton = ss.grabImage(1, 1, 32, 32);
			sleepPressedButton = ss.grabImage(2, 1, 32, 32);
			
			ss = new SpriteSheet(loader.loadImage("/res/coke_button.png"));
			cokeButton = ss.grabImage(1, 1, 32, 32);
			cokePressedButton = ss.grabImage(2, 1, 32, 32);
			
			ss = new SpriteSheet(loader.loadImage("/res/cake_button.png"));
			cakeButton = ss.grabImage(1, 1, 32, 32);
			cakePressedButton = ss.grabImage(2, 1, 32, 32);
			
			ss = new SpriteSheet(loader.loadImage("/res/leaf_button.png"));
			leafButton = ss.grabImage(1, 1, 32, 32);
			leafPressedButton = ss.grabImage(2, 1, 32, 32);
			
			ss = new SpriteSheet(loader.loadImage("/res/back_button.png"));
			backButton = ss.grabImage(1, 1, 32, 32);
			backPressedButton = ss.grabImage(2, 1, 32, 32);
			
			ss = new SpriteSheet(loader.loadImage("/res/timer_button.png"));
			timerButton = ss.grabImage(1, 1, 32, 32);
			timerPressedButton = ss.grabImage(2, 1, 32, 32);
			
			bgTime = loader.loadImage("/res/timer_background.png");
			
			ss1 = new SpriteSheet(loader.loadImage("/res/settimer_button.png"));
			setTimeButton = ss1.grabImage(1, 1, 36, 21);
			setTimerPressedButton = ss1.grabImage(2, 1, 36, 21);
			
			ss1 = new SpriteSheet(loader.loadImage("/res/start_timer_button.png"));
			startTimerButton = ss1.grabImage(1, 1, 36, 21);
			startTimerPressedButton = ss1.grabImage(2, 1, 36, 21);
			
			SpriteSheet ssPlayerEat = new SpriteSheet(loader.loadImage("/res/duck_eat_animate.png"));
			for(int i = 0;i < playerEat.length; i++) 
				playerEat[i] = ssPlayerEat.grabImage(i+1, 1, 64, 64);
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
		foodButRect = new Rect(128,515,32*scale,32*scale);
		workoutButRect = new Rect(256,515,32*scale,32*scale);
		sleepButRect = new Rect(384,515,32*scale,32*scale);
		timerButRect = new Rect(512,515,32*scale,32*scale);
		anotherBackButRect = new Rect(640,515,32*scale,32*scale);
		
		
		
		cokeButRect = new Rect(100,515,32*scale,32*scale);
		cakeButRect = new Rect(275,515,32*scale,32*scale);
		leafButRect = new Rect(450,515,32*scale,32*scale);
		backButRect = new Rect(625,515,32*scale,32*scale);
		inTimerBackButton = new Rect(25,25,32*scale,32*scale);
		setTimerButRect = new Rect(235,450-((21*3)/2),setTimeButton.getWidth()*3,setTimeButton.getHeight()*3);
		startTimerButRect = new Rect(455,450-((21*3)/2),startTimerButton.getWidth()*3,startTimerButton.getHeight()*3);
		
		currentFoodButton = foodButton;
		currentWorkoutButton = workoutButton;
		currentSleepButton = sleepButton;
		
		currentCokeButton = cokeButton;
		currentCakeButton = cakeButton;
		currentLeafButton = leafButton;
		currentBackButton = backButton;
		
		currentTimerButton = timerButton;
		currentSetTimeButton = setTimeButton;
		currentStartTimerButton = startTimerButton;
		
		setTimer = new SetTimerInput(second,minutes);
		
		stamina = new Stamina();
	}
	
	
	public void tick() {
		//gen
		if(ingameState == INGAME_STATE.GEN) {
			if(mouseListener.getX() >= foodButRect.x && mouseListener.getX() <= foodButRect.x + foodButRect.width &&
					mouseListener.getY() >= foodButRect.y && mouseListener.getY() <= foodButRect.y + foodButRect.height) {
				currentFoodButton = foodPressedButton;
				if(mouseListener.isPressed()) {
					ingameState = INGAME_STATE.FOOD;
					
				}
			}
			else {
				currentFoodButton = foodButton;
			}
			
			if(mouseListener.getX() >= workoutButRect.x && mouseListener.getX() <= workoutButRect.x + workoutButRect.width &&
					mouseListener.getY() >= workoutButRect.y && mouseListener.getY() <= workoutButRect.y + workoutButRect.height) {
				currentWorkoutButton = workoutPressedButton;
				if(mouseListener.isPressed()) {
					ingameState = INGAME_STATE.WORKOUT;
				}
			}
			else {
				currentWorkoutButton = workoutButton;
			}
			
			if(mouseListener.getX() >= sleepButRect.x && mouseListener.getX() <= sleepButRect.x + sleepButRect.width &&
					mouseListener.getY() >= sleepButRect.y && mouseListener.getY() <= sleepButRect.y + sleepButRect.height) {
				currentSleepButton = sleepPressedButton;
				if(mouseListener.isPressed()) {
					ingameState = INGAME_STATE.SLEEP;
				}
			}
			else {
				currentSleepButton = sleepButton;
			}
			
			if(mouseListener.getX() >= timerButRect.x && mouseListener.getX() <= timerButRect.x + timerButRect.width &&
					mouseListener.getY() >= timerButRect.y && mouseListener.getY() <= timerButRect.y + timerButRect.height) {
				currentTimerButton = timerPressedButton;
				if(mouseListener.isPressed()) {
					ingameState = INGAME_STATE.TIMER;
					if(ingameState == INGAME_STATE.STARTTIMER) 
						timer.tick();
				}
			}
			else {
				currentTimerButton = timerButton;
			}
			
			if(mouseListener.getX() >= anotherBackButRect.x && mouseListener.getX() <= anotherBackButRect.x + anotherBackButRect.width &&
					mouseListener.getY() >= anotherBackButRect.y && mouseListener.getY() <= anotherBackButRect.y + anotherBackButRect.height) {
				currentBackButton = backPressedButton;
				if(mouseListener.isPressed()) {
					Game2.state =STATE.MENU;
				}
			}
			else {
				currentBackButton = backButton;
			}
		}
		
		//food
		if(ingameState == INGAME_STATE.FOOD) {
			if(mouseListener.getX() >= cokeButRect.x && mouseListener.getX() <= cokeButRect.x + cokeButRect.width &&
					mouseListener.getY() >= cokeButRect.y && mouseListener.getY() <= cokeButRect.y + cokeButRect.height) {
				currentCokeButton = cokePressedButton;
				if(mouseListener.isPressed()) {
					
					foodCoin -= 100;
					foodState = FOOD_STATE.COKE;
					
					ingameState = INGAME_STATE.COKE;
				}
			}
			else {
				currentCokeButton = cokeButton;
			}
			
			if(mouseListener.getX() >= cakeButRect.x && mouseListener.getX() <= cakeButRect.x + cakeButRect.width &&
					mouseListener.getY() >= cakeButRect.y && mouseListener.getY() <= cakeButRect.y + cakeButRect.height) {
				currentCakeButton = cakePressedButton;
				if(mouseListener.isPressed()) {
					foodCoin -= 100;
					foodState = FOOD_STATE.CAKE;
					
					ingameState = INGAME_STATE.CAKE;
				}
			}
			else {
				currentCakeButton = cakeButton;
			}
			
			if(mouseListener.getX() >= leafButRect.x && mouseListener.getX() <= leafButRect.x + leafButRect.width &&
					mouseListener.getY() >= leafButRect.y && mouseListener.getY() <= leafButRect.y + leafButRect.height) {
				currentLeafButton = leafPressedButton;
				if(mouseListener.isPressed()) {
					foodCoin -= 100;
					foodState = FOOD_STATE.LEAF;
					
					ingameState = INGAME_STATE.LEAF;
				}
			}
			else {
				currentLeafButton = leafButton;
			}
			
			if(mouseListener.getX() >= backButRect.x && mouseListener.getX() <= backButRect.x + backButRect.width &&
					mouseListener.getY() >= backButRect.y && mouseListener.getY() <= backButRect.y + backButRect.height) {
				currentBackButton = backPressedButton;
				if(mouseListener.isPressed()) {
					ingameState = INGAME_STATE.GEN;
				}
			}
			else {
				currentBackButton = backButton;
			}
		}
		
		//timer
		if(ingameState == INGAME_STATE.TIMER) {
			if(mouseListener.getX() >= inTimerBackButton.x && mouseListener.getX() <= inTimerBackButton.x + inTimerBackButton.width &&
					mouseListener.getY() >= inTimerBackButton.y && mouseListener.getY() <= inTimerBackButton.y + inTimerBackButton.height) {
				currentBackButton = backPressedButton;
				if(mouseListener.isPressed()) {
					ingameState = INGAME_STATE.GEN;
				}
			}
			else {
				currentBackButton = backButton;
			}
			
			if(mouseListener.getX() >= setTimerButRect.x && mouseListener.getX() <= setTimerButRect.x + setTimerButRect.width &&
					mouseListener.getY() >= setTimerButRect.y && mouseListener.getY() <= setTimerButRect.y + setTimerButRect.height) {
				currentSetTimeButton = setTimerPressedButton;
				if(mouseListener.isPressed()) {
					setTimer.start();
				}
			}
			else {
				currentSetTimeButton = setTimeButton;
			}
			
			if(mouseListener.getX() >= startTimerButRect.x && mouseListener.getX() <= startTimerButRect.x + startTimerButRect.width &&
					mouseListener.getY() >= startTimerButRect.y && mouseListener.getY() <= startTimerButRect.y + startTimerButRect.height) {
				currentStartTimerButton = startTimerPressedButton;
				if(mouseListener.isPressed()) {
					timer.tick();
				}
			}
			else {
				currentStartTimerButton = startTimerButton;
			}
		}
		
		//workout
		if(ingameState == INGAME_STATE.WORKOUT) {
			if(mouseListener.getX() >= anotherBackButRect.x && mouseListener.getX() <= anotherBackButRect.x + anotherBackButRect.width &&
					mouseListener.getY() >= anotherBackButRect.y && mouseListener.getY() <= anotherBackButRect.y + anotherBackButRect.height) {
				currentBackButton = backPressedButton;
				if(mouseListener.isPressed()) {
					if(Stamina.staminaNum > 0)
						Stamina.staminaNum-=1;
					ingameState = INGAME_STATE.GEN;
				}
			}
			else {
				currentBackButton = backButton;
			}
		}
		
		//sleep
		if(ingameState == INGAME_STATE.SLEEP) {
			if(mouseListener.getX() >= anotherBackButRect.x && mouseListener.getX() <= anotherBackButRect.x + anotherBackButRect.width &&
					mouseListener.getY() >= anotherBackButRect.y && mouseListener.getY() <= anotherBackButRect.y + anotherBackButRect.height) {
				currentBackButton = backPressedButton;
				if(mouseListener.isPressed()) {
					if(Stamina.lifeNum <4)
						Stamina.lifeNum+=1;
					ingameState = INGAME_STATE.GEN;
				}
			}
			else {
				currentBackButton = backButton;
			}
		}
		
		//coke
		if(ingameState == INGAME_STATE.COKE) {
			if(mouseListener.getX() >= cokeButRect.x && mouseListener.getX() <= cokeButRect.x + cokeButRect.width &&
					mouseListener.getY() >= cokeButRect.y && mouseListener.getY() <= cokeButRect.y + cokeButRect.height) {
				currentCokeButton = cokePressedButton;
				if(mouseListener.isPressed()) {
					
					foodCoin -= 100;
					
					
					ingameState = INGAME_STATE.COKE;
				}
			}
			else {
				currentCokeButton = cokeButton;
			}
			
			if(mouseListener.getX() >= cakeButRect.x && mouseListener.getX() <= cakeButRect.x + cakeButRect.width &&
					mouseListener.getY() >= cakeButRect.y && mouseListener.getY() <= cakeButRect.y + cakeButRect.height) {
				currentCakeButton = cakePressedButton;
				if(mouseListener.isPressed()) {
					foodCoin -= 100;
					foodState = FOOD_STATE.CAKE;
					
					ingameState = INGAME_STATE.CAKE;
				}
			}
			else {
				currentCakeButton = cakeButton;
			}
			
			if(mouseListener.getX() >= leafButRect.x && mouseListener.getX() <= leafButRect.x + leafButRect.width &&
					mouseListener.getY() >= leafButRect.y && mouseListener.getY() <= leafButRect.y + leafButRect.height) {
				currentLeafButton = leafPressedButton;
				if(mouseListener.isPressed()) {
					foodCoin -= 100;
					foodState = FOOD_STATE.LEAF;
					ingameState = INGAME_STATE.LEAF;
				}
			}
			else {
				currentLeafButton = leafButton;
			}
			
			if(mouseListener.getX() >= backButRect.x && mouseListener.getX() <= backButRect.x + backButRect.width &&
					mouseListener.getY() >= backButRect.y && mouseListener.getY() <= backButRect.y + backButRect.height) {
				currentBackButton = backPressedButton;
				if(mouseListener.isPressed()) {
					ingameState = INGAME_STATE.GEN;
				}
			}
			else {
				currentBackButton = backButton;
			}
		}
		
		if(ingameState == INGAME_STATE.CAKE) {
			if(mouseListener.getX() >= cokeButRect.x && mouseListener.getX() <= cokeButRect.x + cokeButRect.width &&
					mouseListener.getY() >= cokeButRect.y && mouseListener.getY() <= cokeButRect.y + cokeButRect.height) {
				currentCokeButton = cokePressedButton;
				if(mouseListener.isPressed()) {
					
					foodCoin -= 100;
					
					
					ingameState = INGAME_STATE.COKE;
				}
			}
			else {
				currentCokeButton = cokeButton;
			}
			
			if(mouseListener.getX() >= cakeButRect.x && mouseListener.getX() <= cakeButRect.x + cakeButRect.width &&
					mouseListener.getY() >= cakeButRect.y && mouseListener.getY() <= cakeButRect.y + cakeButRect.height) {
				currentCakeButton = cakePressedButton;
				if(mouseListener.isPressed()) {
					foodCoin -= 100;
					foodState = FOOD_STATE.CAKE;
					ingameState = INGAME_STATE.CAKE;
				}
			}
			else {
				currentCakeButton = cakeButton;
			}
			
			if(mouseListener.getX() >= leafButRect.x && mouseListener.getX() <= leafButRect.x + leafButRect.width &&
					mouseListener.getY() >= leafButRect.y && mouseListener.getY() <= leafButRect.y + leafButRect.height) {
				currentLeafButton = leafPressedButton;
				if(mouseListener.isPressed()) {
					foodCoin -= 100;
					foodState = FOOD_STATE.LEAF;
					ingameState = INGAME_STATE.LEAF;
				}
				
			}
			else {
				currentLeafButton = leafButton;
			}
			
			if(mouseListener.getX() >= backButRect.x && mouseListener.getX() <= backButRect.x + backButRect.width &&
					mouseListener.getY() >= backButRect.y && mouseListener.getY() <= backButRect.y + backButRect.height) {
				currentBackButton = backPressedButton;
				if(mouseListener.isPressed()) {
					ingameState = INGAME_STATE.GEN;
				}
			}
			else {
				currentBackButton = backButton;
			}
		}
		
		if(ingameState == INGAME_STATE.LEAF) {
			if(mouseListener.getX() >= cokeButRect.x && mouseListener.getX() <= cokeButRect.x + cokeButRect.width &&
					mouseListener.getY() >= cokeButRect.y && mouseListener.getY() <= cokeButRect.y + cokeButRect.height) {
				currentCokeButton = cokePressedButton;
				if(mouseListener.isPressed()) {
					
					foodCoin -= 100;
					
					
					ingameState = INGAME_STATE.COKE;
				}
			}
			else {
				currentCokeButton = cokeButton;
			}
			
			if(mouseListener.getX() >= cakeButRect.x && mouseListener.getX() <= cakeButRect.x + cakeButRect.width &&
					mouseListener.getY() >= cakeButRect.y && mouseListener.getY() <= cakeButRect.y + cakeButRect.height) {
				currentCakeButton = cakePressedButton;
				if(mouseListener.isPressed()) {
					foodCoin -= 100;
					foodState = FOOD_STATE.CAKE;
					ingameState = INGAME_STATE.CAKE;
				}
			}
			else {
				currentCakeButton = cakeButton;
			}
			
			if(mouseListener.getX() >= leafButRect.x && mouseListener.getX() <= leafButRect.x + leafButRect.width &&
					mouseListener.getY() >= leafButRect.y && mouseListener.getY() <= leafButRect.y + leafButRect.height) {
				currentLeafButton = leafPressedButton;
				if(mouseListener.isPressed()) {
					foodCoin -= 100;
					foodState = FOOD_STATE.LEAF;
					ingameState = INGAME_STATE.LEAF;
				}
			}
			else {
				currentLeafButton = leafButton;
			}
			
			if(mouseListener.getX() >= backButRect.x && mouseListener.getX() <= backButRect.x + backButRect.width &&
					mouseListener.getY() >= backButRect.y && mouseListener.getY() <= backButRect.y + backButRect.height) {
				currentBackButton = backPressedButton;
				if(mouseListener.isPressed()) {
					ingameState = INGAME_STATE.GEN;
				}
			}
			else {
				currentBackButton = backButton;
			}
		}
//		stamina.tick();
		
	}
	public void render(Graphics g) {
		
		//draw background image
		g.drawImage(bg, 0, 0, Game2.SCALE_WIDTH, Game2.SCALE_HEIGHT, null);
		
		//set banner
//		g.setColor(new Color(100,100,100));
//		g.fillRect(5, 0, 800+5, 60+5);
		g.setColor(new Color(107,142,35));
		g.fillRect(0, 0, 800, 60);
		
		//bottom banner
//		g.setColor(new Color(244,164,96));
//		g.fillRoundRect(0, 510, 800, 80);
		g.fillRect(0, 510, 800, 80);
		g.setColor(new Color(255,215,0));
		g.fillRoundRect(300,510,200,5,5,5);
	
		//Font setting
		g.setFont(new Font("Minecraft",Font.BOLD,18));
		g.setColor(new Color(100,100,100));
		g.drawString("Pet Name : "+Game2.userName, 560+2, 35+2);
		g.setColor(Color.white);
		g.drawString("Pet Name : "+Game2.userName, 560, 35);
		
		//set food coin
		g.drawImage(coin, 10, 15, coin.getWidth(), coin.getHeight(), null);
		g.setColor(new Color(100,100,100));
		g.drawString(" : "+Integer.toString(foodCoin), 44+2, 35+2);
		g.setColor(Color.white);
		g.drawString(" : "+Integer.toString(foodCoin), 44, 35);
		
		//draw stamina banner
		stamina.render(g);
		
		//draw button
		if(ingameState ==  INGAME_STATE.GEN) {
			g.drawImage(currentFoodButton, (int)foodButRect.x,(int)foodButRect.y,(int)foodButRect.width,(int)foodButRect.height,null);
			g.drawImage(currentWorkoutButton, (int)workoutButRect.x, (int)workoutButRect.y, (int)workoutButRect.width, (int)workoutButRect.height, null);
			g.drawImage(currentSleepButton, (int)sleepButRect.x, (int)sleepButRect.y, (int)sleepButRect.width, (int)sleepButRect.height, null);
			g.drawImage(currentTimerButton,(int)timerButRect.x, (int)timerButRect.y, (int)timerButRect.width, (int)timerButRect.height, null);
			g.drawImage(currentBackButton, (int)anotherBackButRect.x, (int)anotherBackButRect.y, (int)anotherBackButRect.width, (int)anotherBackButRect.height, null);
		}
		//draw food button
		else if(ingameState == INGAME_STATE.FOOD) {
			g.drawImage(currentCokeButton, (int)cokeButRect.x, (int)cokeButRect.y, (int)cokeButRect.width, (int)cokeButRect.height, null);
			g.drawImage(currentCakeButton, (int)cakeButRect.x, (int)cakeButRect.y, (int)cakeButRect.width, (int)cakeButRect.height, null);
			g.drawImage(currentLeafButton, (int)leafButRect.x, (int)leafButRect.y, (int)leafButRect.width, (int)leafButRect.height, null);
			g.drawImage(currentBackButton, (int)backButRect.x, (int)backButRect.y, (int)backButRect.width, (int)backButRect.height, null);
			
			
		}
		else if(ingameState == INGAME_STATE.SLEEP) {
			g.drawImage(currentBackButton, (int)anotherBackButRect.x, (int)anotherBackButRect.y, (int)anotherBackButRect.width, (int)anotherBackButRect.height, null);
		}
		else if(ingameState == INGAME_STATE.WORKOUT) {
			g.drawImage(currentBackButton, (int)anotherBackButRect.x, (int)anotherBackButRect.y, (int)anotherBackButRect.width, (int)anotherBackButRect.height, null);
		}
		else if(ingameState == INGAME_STATE.TIMER) {
			g.drawImage(bgTime, 0, 0, bgTime.getWidth()*scale, bgTime.getHeight()*scale, null);
			timer.render(g);
			g.drawImage(currentBackButton, (int)inTimerBackButton.x, (int)inTimerBackButton.y, (int)inTimerBackButton.width, (int)inTimerBackButton.height, null);
			g.drawImage(currentSetTimeButton, (int)setTimerButRect.x, (int)setTimerButRect.y, (int)setTimerButRect.width, (int)setTimerButRect.height, null);
			g.drawImage(currentStartTimerButton, (int)startTimerButRect.x, (int)startTimerButRect.y, (int)startTimerButRect.width, (int)startTimerButRect.height, null);
		}
		
		else if(ingameState == INGAME_STATE.COKE) {
			g.drawImage(currentCokeButton, (int)cokeButRect.x, (int)cokeButRect.y, (int)cokeButRect.width, (int)cokeButRect.height, null);
			g.drawImage(currentCakeButton, (int)cakeButRect.x, (int)cakeButRect.y, (int)cakeButRect.width, (int)cakeButRect.height, null);
			g.drawImage(currentLeafButton, (int)leafButRect.x, (int)leafButRect.y, (int)leafButRect.width, (int)leafButRect.height, null);
			g.drawImage(currentBackButton, (int)backButRect.x, (int)backButRect.y, (int)backButRect.width, (int)backButRect.height, null);
		}
		else if(ingameState == INGAME_STATE.CAKE) {
			g.drawImage(currentCokeButton, (int)cokeButRect.x, (int)cokeButRect.y, (int)cokeButRect.width, (int)cokeButRect.height, null);
			g.drawImage(currentCakeButton, (int)cakeButRect.x, (int)cakeButRect.y, (int)cakeButRect.width, (int)cakeButRect.height, null);
			g.drawImage(currentLeafButton, (int)leafButRect.x, (int)leafButRect.y, (int)leafButRect.width, (int)leafButRect.height, null);
			g.drawImage(currentBackButton, (int)backButRect.x, (int)backButRect.y, (int)backButRect.width, (int)backButRect.height, null);
		}
		else if(ingameState == INGAME_STATE.LEAF) {
			g.drawImage(currentCokeButton, (int)cokeButRect.x, (int)cokeButRect.y, (int)cokeButRect.width, (int)cokeButRect.height, null);
			g.drawImage(currentCakeButton, (int)cakeButRect.x, (int)cakeButRect.y, (int)cakeButRect.width, (int)cakeButRect.height, null);
			g.drawImage(currentLeafButton, (int)leafButRect.x, (int)leafButRect.y, (int)leafButRect.width, (int)leafButRect.height, null);
			g.drawImage(currentBackButton, (int)backButRect.x, (int)backButRect.y, (int)backButRect.width, (int)backButRect.height, null);
		}
	}
	
}
