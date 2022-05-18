package sprites;
import java.util.List;

import processing.core.PApplet;
import processing.core.PImage;
import utils.Vague;
import java.util.ArrayList;


public class StarShip extends Sprite {

	private static int SHIP_WIDTH = 35;
	private static int SHIP_HEIGHT = 35;

	private double xVel;


	public StarShip(PImage starShip, PApplet marker) 
	{
		super( starShip, 35*5, 700, 35, 35);

		
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




	public void walk(int dir) 
	{
		if(dir==-1) { //0=left
			super.moveByAmount(-35, 0);
		}
		if(dir==1) { //1=right
			super.moveByAmount(35, 0);
		}
	}

}
