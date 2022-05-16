package sprites;
import java.util.List;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;


public class StarShip extends Sprite {

	public static final int SHIP_WIDTH = 50;
	public static final int SHIP_HEIGHT = 50;

	private double xVel;


	public StarShip(PImage starShip) 
	{
		super( starShip, 167, 700, SHIP_WIDTH, SHIP_HEIGHT);
		xVel = 0;
	
	}
	
	public void setup(PImage starShipIMG)
	{
		changeImage(starShipIMG);
	}

	public boolean hitsWall(List<List<Sprite>> wallBlocks) 
	{

		for(List<Sprite> ls : wallBlocks) 
		{
 			for(Sprite s : ls)
			{
				if(this.intersects(s) && s.hasImage())
				{
					return true;
				}

			}
		}

		return false;
	}




	public void walk(int dir) {
		if(dir==-1) { //0=left
			super.moveByAmount(-4, 0);
		}
		if(dir==1) { //1=right
			super.moveByAmount(4, 0);
		}
	}

}
