package game_2.main;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;
import javax.swing.Timer;

import game_2.main.Game2.STATE;

public class CountDownTimer {
	public static int second,minute;
	private BufferedImage image;
	private Thread th;
	private Font font = new Font("Minecraft",Font.BOLD,100);
	private Timer timer;
	private DecimalFormat df;
	public CountDownTimer(int second,int minute,Thread th) {
//		boolean x = true;
//		long displayMinutes = 0;
//		long startTime = System.currentTimeMillis();
//		while(x) {
//			TimeUnit.SECONDS.sleep(1);
//			long timePassed = System.currentTimeMillis() - startTime;
//			long secondPassed = timePassed / 1000;
//			if(secondPassed == 60) {
//				secondPassed = 0;
//				startTime = System.currentTimeMillis();
//				break;
//			}
//			if(secondPassed % 60  == 0) {
//				displayMinutes++;
//			}
//			
//			System.out.println(displayMinutes + " : : "+secondPassed);
//		}
		try {
			image = ImageIO.read(getClass().getResource("/res/timer_bg.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.second = second;
		this.th = th;
		df = new DecimalFormat("00");
		this.minute = minute;
		
	}
	public void tick() {
		try {
			timerCountdown();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		timer.start();
	}
	
	public void timerCountdown() throws InterruptedException{
		timer = new Timer(1000,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(second > 0) {
					second--;
					System.out.println("Timer : "+second);
					try {
						th.sleep(500L);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
				if(second == 0) {
//					Game2.state = STATE.MENU;
					if(minute == 0 && second == 0) {
						((Timer)e.getSource()).stop();
						try {
							Thread.sleep(500L);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						IngameGui.foodCoin += 100;
					}
					else {
						second = 60;
						minute-=1;
					}
					
				}
				
				
			}
		});
	}
	
	public void render(Graphics g) {
		
//		g.drawImage(image, 0, 0, Game2.SCALE_WIDTH, Game2.SCALE_HEIGHT, null);
		g.setFont(font);
		g.drawString(df.format(minute)+" : "+df.format(second), 235, 300);
		
	}
	
}
