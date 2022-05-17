package sprites;
import java.util.List;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;


public class StarShip extends Sprite {

	public static final int SHIP_WIDTH = 35;
	public static final int SHIP_HEIGHT = 35;
	private int health;
	private double xVel;


	public StarShip(PImage starShip) 
	{
		super(starShip, 175, 700, SHIP_WIDTH, SHIP_HEIGHT);
		xVel = 0;
		health = 100;
	
	}
	
	public void setup(PImage starShipIMG)
	{
		changeImage(starShipIMG);
	}
	
	public void draw(PApplet surface) {
		super.draw(surface);
		surface.rect(0, surface.height-20, surface.width, 20);
		surface.rect(10, surface.height-12, 100, 5);
		surface.fill(255,255,255);
		surface.rect(10, surface.height-12, health, 5);
	}

	public boolean hitsWall(List<List<Sprite>> wallBlocks) 
	{

		for(List<Sprite> ls : wallBlocks) 
		{
 			for(Sprite s : ls)
			{
				if(this.intersects(s) && s.hasImage())
				{
					health--;
					return true;
				}

			}
		}

		return false;
	}

	public int getHealth() {
		return health;
	}


	public void walk(int dir, int amount) 
	{
		super.moveByAmount(dir*amount, 0);
	}

}
