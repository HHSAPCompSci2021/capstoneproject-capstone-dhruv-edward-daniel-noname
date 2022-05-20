package sprites;
import java.util.List;

import core.DrawingSurface;
import processing.core.PApplet;
import processing.core.PImage;
import utils.Vague;

import java.util.ArrayList;

import java.awt.Point;
import processing.core.PImage;



public class StarShip extends Sprite {

	public static final int SHIP_WIDTH = 36;
	public static final int SHIP_HEIGHT = 35;
	private ArrayList<Bullet> bullets = new ArrayList<>();
	private PApplet surface;
	private int health;
	private double xVel;

	public StarShip(PApplet surface, PImage starShip, int x) 
	{
		super(starShip, x, 700, SHIP_WIDTH, SHIP_HEIGHT);
		this.surface =  surface;
		xVel = 0;
		health = 100;
	
	}
	
	public void setup(PImage starShipIMG)
	{
		changeImage(starShipIMG);
	}
	
	public void draw() 
	{
		super.draw(surface);

		for(int i=0; i<bullets.size(); i++)
			bullets.get(i).draw();

		for(int i=0; i<bullets.size(); i++)
			if(bullets.get(i).getCenterY() < 0)
				bullets.remove(i);

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
				if(this.intersects(s) && (s.getId() == 1))
				{
					health--;
					return true;
				}

			}
		}

		return false;
	}
	
	public void shoot() 
	{
		if(bullets.size() < 2)
		{
			bullets.add(new Bullet(surface, super.getRX()+(int)(super.width/2), super.getRY(), 9));
		}
	}
	
	public ArrayList<Bullet> getBullets()
	{
		return bullets;
	}
	
	public int getHealth() {
		return health;
	}

	public List<List<Sprite>> bulletHitsWall(List<List<Sprite>> walls)
	{

		for(int i=0; i<bullets.size(); i++)
		{
			if(bullets.get(i).hitsWall(walls))
			{
				bullets.remove(i);
				i--;
			}
		}

		return walls;
	}
	public void walk(int dir, int amount) 
	{
		super.moveByAmount(dir*amount, 0);
	}

	public void resetHealth() {
		health = 100;
	}

}
