
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

	public static final int SHIP_WIDTH = 36, SHIP_HEIGHT = 35;
	private ArrayList<Bullet> bullets = new ArrayList<>();
	private Sprite fires;
	private PApplet surface;
	private PImage bulletImage;
	private int health, ticks = 1000;
	private int energy;

	public StarShip(PApplet surface, PImage starShip, int x) 
	{
		super(starShip, x, 700, SHIP_WIDTH, SHIP_HEIGHT);
		this.surface =  surface;
		health = 100;
		energy = 900;
	}

	
	public void setup(PImage starShipIMG, PImage fire, PImage bullet)
	{
		changeImage(starShipIMG);
		this.bulletImage = bullet;
		fires = new Sprite(fire, 10, 10, 20, 20);
	}
	
	public void draw() 
	{
		if(ticks>0 && ticks<(20))
		{
			fires.moveToLocation((double)super.getRX()+Vague.chunkSize/4-4, (double)super.getRY()-Vague.chunkSize/2);
			fires.draw(surface);
		}
		ticks++;

		super.draw(surface);

		for(int i=0; i<bullets.size(); i++)
			bullets.get(i).draw();

		for(int i=0; i<bullets.size(); i++)
			if(bullets.get(i).getCenterY() < 0)
				bullets.remove(i);

		surface.rect(0, surface.height-20, surface.width, 20);
		surface.rect(10, surface.height-12, 100, 5);
		surface.stroke(255);
		surface.fill(255);
		surface.rect(10, surface.height-12, health, 5);
		if(energy<900) {energy++;}
		
	}

	public boolean hitsWall(List<List<Sprite>> wallBlocks) 
	{

		for(List<Sprite> ls : wallBlocks) 
		{
				for(Sprite s : ls)
			{
				if(this.intersects(s) && (s.getId()==1))
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
		ticks = 0;
		
		if(bullets.size() < 2 && energy>=300)
		{
			bullets.add(new Bullet(surface, bulletImage, super.getRX()+(int)(15/2)+4 , super.getRY(), 9));
			energy-=300;
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
	public int getEnergy() {
		return energy;
	}

}
