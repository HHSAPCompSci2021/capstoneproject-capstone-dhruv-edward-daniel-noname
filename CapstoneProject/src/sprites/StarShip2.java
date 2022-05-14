
package sprites;

import dsharma578.shapes.Rectangle;
import processing.core.PApplet;
import java.util.ArrayList;

public class StarShip2 {
	
	private Rectangle hitbox;
	private int x, y; 
	public int ShipHealth;
	
	public StarShip2() {
		x = 167; y = 700;
		//75 * 75
		hitbox = new Rectangle(x, y, 50, 50);
	}
	
	public boolean hitsWall(ArrayList<Rectangle> wallBlocks) 
	{
		for(Rectangle r : wallBlocks) 
		{
			if(hitbox.intersects(r)) 
			{
				return true;
			}
		}
		return false;
	}

	public void shiftLeft()
	{
		System.out.println("Left");

		hitbox.move(-50, 0);
	}
	
	public void shiftRight()
	{
		System.out.println("Right");
		hitbox.move(50, 0);
	}

	public int getShipHealth() {
		return ShipHealth;
	}
	
	public void setShipeHealth(int x) {
		ShipHealth = x;
	}
	
	public boolean isDead() {
		if(ShipHealth == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public void draw(PApplet surface) 
	{
		hitbox.setFillColor(200, 200, 250, 250);
		hitbox.draw(surface);
	}
}
