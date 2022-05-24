package sprites;
import java.util.List;

import core.DrawingSurface;
import screens.ScreenSwitcher;
import utils.UserData;
import utils.Vague;

import java.util.ArrayList;

/**
 * This class represents the player-controlled StarShip
 */
public class StarShip extends Sprite {

	public static final int SHIP_WIDTH = 36, SHIP_HEIGHT = 35;
	private ArrayList<Bullet> bullets = new ArrayList<>();
	private Sprite fires;
	private int ammo = 23;
	private DrawingSurface surface;
	private int health, ticks = 1000;
	public final static String fileSeparator = System.getProperty("file.separator");

	/**
	 * Creates a new Starship that will be drawn on a specified DrawingSurface at a specified x-coordinate
	 * @param surface the DrawingSurface this StarShip will be drawn on
	 * @param x the x-coordinate of this StarShip
	 */
	public StarShip(DrawingSurface surface, int x) 
	{
		super(Vague.starShip, x, 700, SHIP_WIDTH, SHIP_HEIGHT*2);
		this.surface =  surface;
		health = 100;
	
	}
	
	/**
	 * Initializes this Starship's image and firing Sprites
	 */
	public void setup()
	{
		changeImage(Vague.starShip);
		fires = new Sprite(Vague.fire, 10, 10, 20, 20);
	}
	
	/**
	 * Draws this StarShip on a DrawingSurface along with relevant information about the ship like health and a money score in the endless mode
	 */
	public void draw() 
	{
		if(ticks>0 && ticks<(20))
		{
			fires.moveToLocation((double)super.getRX()+Vague.chunkSize/6, (double)super.getRY()-Vague.chunkSize/2);
			fires.draw(surface);
		}
		ticks++;

		super.draw(surface);

		for(int i=0; i<bullets.size(); i++)
			bullets.get(i).draw();

		for(int i=0; i<bullets.size(); i++)
			if(bullets.get(i).getCenterY() < 0)
				bullets.remove(i);

		surface.fill(255,255,255);
		surface.rect(10, surface.height-12, health, 5);

		surface.textSize(14);
		surface.stroke(204, 102, 0);
		surface.text("ammo: "+String.valueOf(ammo), surface.width-75, surface.height-7);

		surface.textSize(14);
		surface.stroke(204, 102, 0);
		surface.text("$$: "+String.valueOf(UserData.coinsCollected*5*1000+UserData.distanceTraveled/100), surface.width-230, surface.height-7);


	}
	
	/**
	 * Determines if this StarShip is hitting another Sprite from a List
	 * @param wallBlocks the List of Sprites that this StarShip will be checked against for intersections
	 * @return a boolean value for whether or not this Ship is intersecting any of the Sprites in the list passed as a parameter
	 */
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
							case(Vague.CLEAR_LINE):
								surface.switchScreen(ScreenSwitcher.MENU_SCREEN);

						}


						return true;
					}

	
				}
			}
		}
			

		return false;
	}
	
	/**
	 * Shoots a bullet from this Starship
	 */
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
	
	/**
	 * Returns an ArrayList containing the bullets currently shot and in the Game(not having destroyed something already)
	 * @return an ArrayList containing the bullets currently shot and in the Game(not having destroyed something already)
	 */
	public ArrayList<Bullet> getBullets()
	{
		return bullets;
	}
	
	/**
	 * Returns this ship's current health
	 * @return this ship's current health
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * Determines if this StarShips is hitting any other Sprites in a List of Sprites passed to it and returns the list after Sprites that bullets are intersecting have been removed
	 * @param walls the List of Sprites that will be checked with this ship's bullets to check for any collisions
	 * @return a List of Sprites representing the remaining walls that have not been destroyed
	 */
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
	
	/**
	 * Moves this ship by a specified amount in a specified horizontal direction
	 * @param dir the direction the ship should move horizontally, 1 if it should move to the right and -1 if it should move to the left
	 * @param amount the amount of pixels the ship should translate horizontally
	 */
	public void walk(int dir, int amount) 
	{
		super.moveByAmount(dir*amount, 0);
	}
	
	/**
	 * Resets this ship's health to its maximum amount of 100
	 */
	public void resetHealth() {
		health = 100;
	}

}
