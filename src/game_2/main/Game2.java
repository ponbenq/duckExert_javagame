package game_2.main;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.TextField;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JTextField;

import game_2.main.IngameGui.INGAME_STATE;

public class Game2 extends Canvas implements Runnable{
	public static final int WIDTH = 160;
	private static final int HEIGHT = WIDTH / 12 * 9;
	private static final int SCALE = 5;
	public static final int SCALE_WIDTH = WIDTH * SCALE;
	public static final int SCALE_HEIGHT = HEIGHT * SCALE;
	private static final String NAME = "My 2D Game";
	
	private JFrame frame;
	
	private boolean running;
	private int tickCount = 0;
	
	private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	private Object rec = new Object();
	
	private BufferedImage spriteSheet = null,background = null;
	
	private MouseL mouseListener = new MouseL();
	private Menu menu = new Menu("duck exert!",90,mouseListener);
	
	private Image timerBg;
	
	private CountDownTimer countdownTimer;
	
	public static String userName = "";
	
	private BufferedImage bg2;
	private IngameGui ingameGui;
	
	private Info info;
	
	public static int timecountdown = 0;
	private Stamina stamina;
	
	public Game2() {
		setMinimumSize(new Dimension(WIDTH * SCALE,HEIGHT * SCALE));
		setMaximumSize(new Dimension(WIDTH * SCALE,HEIGHT * SCALE));
		setPreferredSize(new Dimension(WIDTH * SCALE,HEIGHT * SCALE));
		
		frame = new JFrame(NAME);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		frame.setLayout(new BorderLayout());
		frame.add(this,BorderLayout.CENTER);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		
		frame.setResizable(false);
		frame.setVisible(true);
		
		addMouseListener(mouseListener);
		addMouseMotionListener(mouseListener);
		
		countdownTimer = new CountDownTimer(10,10,new Thread());
		
		info = new Info(mouseListener);
		
		stamina = new Stamina();
		
	}
	
	public static enum STATE{
		GAME,
		MENU,
		INFO,
		INGAME
	}
	
	public static STATE state = STATE.MENU;
	
	
	private Player p;
	public void init() throws IOException{

		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			spriteSheet = loader.loadImage("/res/128_idle.png");
			background = loader.loadImage("/res/bgDuck.png");
			timerBg = ImageIO.read(getClass().getResource("/res/timer_bg.jpg"));
			bg2 = loader.loadImage("/res/bg2.jpg");
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		p = new Player(400-128,300-96,this);
		ingameGui = new IngameGui(mouseListener);
		
	}
	
	public synchronized void start() {
		running = true;
		new Thread(this).start();
		//Thread is an instance of Runnable
		//when the thread start it will call the "run" method
	}
	
	public synchronized void stop() {
		running = false;
	}
	
	
	
	
	public void tick() {
		//update method
		//used to update some instance or variable
		
		tickCount++;
		
		for(int i = 0;i < pixels.length;i++) {
			pixels[i] = i + tickCount;
		}
//		rec.update();
		
		if(state == STATE.GAME) {
//			p.tick();
//			countdownTimer.tick();
			
		}
		if(state == STATE.INGAME) {
			ingameGui.tick();
		}
		if(state == STATE.MENU) {
			menu.tick();
		}
		if(state == STATE.INFO) {
			info.tick();
		}
		
	}
	
	public void render() throws InterruptedException {
		BufferStrategy bs = getBufferStrategy();
		//bufferStrategy is a object to organize the canvas.
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.cyan);
		g.fillRect(0, 0, SCALE_WIDTH, SCALE_HEIGHT);
		
		
		//background image
		g.drawImage(background, 0,0,this.getWidth(), this.getHeight(),null );
//		Rectangle re = rec.getRec();
//		g.setColor(Color.BLUE);
//		g.fillRect((int)re.getX(), (int)re.getY(),(int)re.getWidth(), (int)re.getHeight());
		
		
		if(state == STATE.GAME) {
			//p.render(g);
			//g.drawImage(timerBg, 0, 0, getWidth(),getHeight(), null);
			
//			countdownTimer.render(g);
			
			
			g.drawImage(bg2, 0, 0, SCALE_WIDTH, SCALE_HEIGHT, null);
			g.setColor(new Color(95,158,160));
			g.fillRect(0, 0, SCALE_WIDTH, SCALE_HEIGHT);
		}
		if(state == STATE.INGAME) {
			if(IngameGui.ingameState == INGAME_STATE.TIMER) {
			ingameGui.render(g);
			}else {
				ingameGui.render(g);
				p.render(g);
			}
			
			
		}
		else if(state == STATE.MENU) {
			menu.render(g);
		}
		else if(state == STATE.INFO) {
			info.start(g);
		}
		//draw the image
//		g.drawImage(image,0,0,getWidth(),getHeight(),null); 
		
		g.dispose();
		bs.show();
	}
	
	@Override
	public void run() {
		
		try {
			init();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//The core game loop 
		//Sometime game loop is run very fast
		//Then we need to spacific run time 
		
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000d/60d;
		
		int ticks = 0;
		int frames = 0;
		
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		while(running) {
			long now = System.nanoTime();
			
			delta += (now - lastTime) / nsPerTick;
			
			lastTime = now;
			
			boolean shouldRender = false;
			
				while(delta >= 1) {
					ticks++;
					tick();
					delta -= 1;
					shouldRender = true;
				}
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(shouldRender) {
				frames++;
				try {
					render();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				System.out.println("FPS "+frames + " ups "+ticks);
				frames = 0;
				ticks = 0;
				timecountdown++;
				System.out.println("Time : "+timecountdown);
				stamina.tick();			
				}
			
		}
	}
	
	public static void main(String[] args) {
		new Game2().start();
	}

	public BufferedImage getSpriteSheet() {
		// TODO Auto-generated method stub
		return spriteSheet;
	}
	
}
