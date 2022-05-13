package sprites;

import dsharma578.shapes.Rectangle;
import processing.core.PApplet;
import java.util.ArrayList;

public class StarShip {
	
	private Rectangle hitbox;
	private int x,y; 
	public int ShipHealth;
	
	public StarShip() {
		//75 * 75
		hitbox = new Rectangle(167, 700, 50, 50);
	}
	
	public boolean hitsWall(ArrayList<Rectangle> wallBlocks) 
	{
		for(Rectangle r : wallBlocks) {
			if(hitbox.intersects(r)) {
				return true;
			}
		}
		return false;
	}
	
	public int getShipHealth() {
		return ShipHealth;
	}
	
	
	public void draw(PApplet surface) {
		hitbox.setFillColor(200, 200, 250, 250);
		hitbox.draw(surface);
	}
}
