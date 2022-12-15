package game_2.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class Menu {
	private int fontSize ;
	private String text;
	private MouseL mouselistener;
	private Button bttStart;
	
	public Menu(String text,int fontSize,MouseL mouselistener) {
		this.fontSize = fontSize;
		this.text = text;
		this.mouselistener = mouselistener;
		
		bttStart = new Button(this.mouselistener);
	}
	
	public void tick() {
		bttStart.tick();
	}
	
	public void render(Graphics g) {
		Font font1 = new Font("8-bit Arcade In",Font.BOLD,fontSize);
		
		
		FontMetrics fm = g.getFontMetrics();
		
		g.setColor(new Color(2,48,32,50));
		g.fillRect(0, 0, Game2.SCALE_WIDTH, Game2.SCALE_HEIGHT);
//		g.fillRect(150, 80, 500, 90);
		g.setFont(font1);
		g.setColor(new Color(80,200,120));
		g.drawString(text,Game2.SCALE_WIDTH / 2 - 230, 145);
		g.setColor(new Color(170, 255, 0));
		g.drawString(text,Game2.SCALE_WIDTH / 2 - 235, 150);
		
		bttStart.render(g);
		
		
	}
}
