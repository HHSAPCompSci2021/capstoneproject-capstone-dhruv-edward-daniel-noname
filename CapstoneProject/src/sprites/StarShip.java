package sprites;
import java.util.List;

import processing.core.PApplet;
import processing.core.PImage;

import dsharma578.shapes.Rectangle;
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

	public boolean hitsWall(ArrayList<Rectangle> wallBlocks) 
	{

		for(Rectangle r : wallBlocks) 
		{
			System.out.println("hit");

			if(new Rectangle(super.getRX(), super.getRY(), super.getRW(), super.getRH()).intersects(r)) 
			{
				return true;
			}
		}
		return false;
	}




	public void walk(int dir) {
		if(dir==-1) { //0=left
			super.moveByAmount(-10, 0);
		}
		if(dir==1) { //1=right
			super.moveByAmount(10, 0);
		}
	}

}
