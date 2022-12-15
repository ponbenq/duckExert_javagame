package game_2.main;

import java.awt.Rectangle;

public class Object {
	Rectangle rec;
	
	public Object() {
		 rec = new Rectangle(0,0,50,50);
		
	}
	
	public Rectangle getRec() {
		return rec;
	}
	
	public void update() {
		rec.setLocation((int)rec.getX() + 1,(int)rec.getY()+1);
	}
}
