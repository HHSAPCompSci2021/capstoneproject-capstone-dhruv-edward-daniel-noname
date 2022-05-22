
package sprites;
import java.util.List;

import core.DrawingSurface;
import processing.core.PApplet;
import processing.core.PImage;
import utils.UserData;
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


	public StarShip(PApplet surface, int x) 
	{
		super(Vague.starShip, x, 700, SHIP_WIDTH, SHIP_HEIGHT*2);
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

	public boolean hits(List<List<Sprite>> wallBlocks) 
	{

		for(List<Sprite> ls : wallBlocks) 
		{
				for(Sprite s : ls)
			{
				if(this.intersects(s) && (s.getId()==1))
				{
					Sprite s = wallBlocks.get(i).get(j);

					if(this.intersects(s))
					{
						switch(s.getId())
						{
							case(Vague.COIN_GOLD):
								UserData.coinsCollected++;
								wallBlocks.get(i).remove(j);
								wallBlocks.get(i).add(j, new Sprite(s.getRX(), s.getRY(), s.getRW(), s.getRW()));
								wallBlocks.get(i).get(j).setFillColor(0, 0, 0, 255);
								break;
							case(Vague.STANDARD_WALL):
								health --;
								break;

							case(Vague.AMMO_CRATE):
								this.ammo+=15;
								wallBlocks.get(i).remove(j);
								wallBlocks.get(i).add(j, new Sprite(s.getRX(), s.getRY(), s.getRW(), s.getRW()));
								wallBlocks.get(i).get(j).setFillColor(0, 0, 0, 255);
								break;

							case(Vague.HEALTH_PACKAGE):
								if(this.health <50)
									this.health = 70;
								else
									this.health = 100;


								wallBlocks.get(i).remove(j);
								wallBlocks.get(i).add(j, new Sprite(s.getRX(), s.getRY(), s.getRW(), s.getRW()));
								wallBlocks.get(i).get(j).setFillColor(0, 0, 0, 255);
								break;
							
							case(Vague.BLACK_HOLE):
								Vague.IN_BLACK_HOLE = true;
								break;


						}


						return true;
					}

	
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
package sprites;
import java.util.List;

import core.DrawingSurface;
import processing.core.PApplet;
import processing.core.PImage;
import utils.UserData;
import utils.Vague;

import java.util.ArrayList;

import java.awt.Point;
import processing.core.PImage;



public class StarShip extends Sprite {

	public static final int SHIP_WIDTH = 36, SHIP_HEIGHT = 35;
	private ArrayList<Bullet> bullets = new ArrayList<>();
	private Sprite fires;
	private int ammo = 23;
	private PApplet surface;
	private int health, ticks = 1000;
	public final static String fileSeparator = System.getProperty("file.separator");


	public StarShip(PApplet surface, int x) 
	{
		super(Vague.starShip, x, 700, SHIP_WIDTH, SHIP_HEIGHT*2);
		this.surface =  surface;
		health = 100;
	
	}
	
	public void setup()
	{
		changeImage(Vague.starShip);
		fires = new Sprite(Vague.fire, 10, 10, 20, 20);
	}
	
	public void draw() 
	{
		if(ticks>0 && ticks<(20))
		{
			fires.moveToLocation((double)super.getRX()+Vague.chunkSize/4-7, (double)super.getRY()-Vague.chunkSize/2);
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
		surface.fill(255,255,255);
		surface.rect(10, surface.height-12, health, 5);

		surface.textSize(14);
		surface.stroke(204, 102, 0);
		surface.text("ammo: "+String.valueOf(ammo), surface.width-75, surface.height-7);

		surface.textSize(14);
		surface.stroke(204, 102, 0);
		surface.text("Δx: "+String.valueOf(UserData.distanceTraveled), surface.width-150, surface.height-7);

		surface.textSize(14);
		surface.stroke(204, 102, 0);
		surface.text("$$: "+String.valueOf(UserData.coinsCollected*5*1000+UserData.distanceTraveled/100), surface.width-230, surface.height-7);


	}

	public boolean hits(List<List<Sprite>> wallBlocks) 
	{

		for(List<Sprite> ls : wallBlocks) 
		{
			for(int i=0; i<wallBlocks.size(); i++)
			{
				for(int j=0; j<wallBlocks.get(0).size(); j++)
				{
					Sprite s = wallBlocks.get(i).get(j);

					if(this.intersects(s))
					{
						switch(s.getId())
						{
							case(Vague.COIN_GOLD):
								UserData.coinsCollected++;
								wallBlocks.get(i).remove(j);
								wallBlocks.get(i).add(j, new Sprite(s.getRX(), s.getRY(), s.getRW(), s.getRW()));
								wallBlocks.get(i).get(j).setFillColor(0, 0, 0, 255);
								break;
							case(Vague.STANDARD_WALL):
								health --;
								break;

							case(Vague.AMMO_CRATE):
								this.ammo+=15;
								wallBlocks.get(i).remove(j);
								wallBlocks.get(i).add(j, new Sprite(s.getRX(), s.getRY(), s.getRW(), s.getRW()));
								wallBlocks.get(i).get(j).setFillColor(0, 0, 0, 255);
								break;

							case(Vague.HEALTH_PACKAGE):
								if(this.health <50)
									this.health = 70;
								else
									this.health = 100;


								wallBlocks.get(i).remove(j);
								wallBlocks.get(i).add(j, new Sprite(s.getRX(), s.getRY(), s.getRW(), s.getRW()));
								wallBlocks.get(i).get(j).setFillColor(0, 0, 0, 255);
								break;
							
							case(Vague.BLACK_HOLE):
								Vague.IN_BLACK_HOLE = true;
								break;


						}


						return true;
					}

	
				}
			}
		}
			

		return false;
	}
	
	public void shoot() 
	{
		ticks = 0;
		if(ammo>0)
			ammo --; 
		
		if(bullets.size() < 2 && ammo > 0)
		{
			bullets.add(new Bullet(surface, super.getRX()+(int)(15/2)+4 , super.getRY(), 9));
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
