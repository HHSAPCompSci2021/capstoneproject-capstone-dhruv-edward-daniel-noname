package sprites;

import java.util.List;

import processing.core.PApplet;
import processing.core.PImage;
import utils.Vague;
/**
 * Represents a Bullet that can be shot and hit/destroy blocks and pickups in the endless mode
 */
public class Bullet extends Sprite
{
    private PApplet surface;
    private int xdim = 3, ydim = 5, vel;

    /**
     * Creates a new Bullet from position (x,y) with a specified velocity that will be drawn on a PApplet
     * @param surface the surface the Bullet will be drawn on
     * @param x the x-coordinate of the Bullet
     * @param y the y-coordinate of the Bullet
     * @param vel the speed at which the Bullet will travel
     * @pre velocity must be positive and greater than the movement speed of the level
     */
	public Bullet(PApplet surface, int x, int y, int vel)
    {
        super(Vague.bullet, x,y, 15, 20);
		this.vel = vel;
        this.surface = surface;
    }

	/**
	 * Determines if this Bullet collides with any Sprite from a list of Sprites wallBlocks
	 * @param wallBlocks a list of Sprites that Bullet will be checked to be interesecting with or not
	 * @return a boolean for whether or not the Bullet is hitting any other Sprite
	 */
    public boolean hitsWall(List<List<Sprite>> wallBlocks) 
	{

		for(int i=0; i<wallBlocks.size(); i++)
		{
			for(int j=0; j<wallBlocks.get(0).size(); j++)
			{
				Sprite s = wallBlocks.get(i).get(j);

				if( this.intersects(s) && (s.getId() ==1 || s.getId() == 2))
				{
					wallBlocks.get(i).remove(j);
					wallBlocks.get(i).add(j, new Sprite(Vague.destroyed, s.getRX(), s.getRY(), s.getRW(), s.getRW()));
					wallBlocks.get(i).get(j).setFillColor(0, 0, 0, 255);

					return true;
				}

			}
		}

		return false;
	}

    /**
     * Moves the bullet with a specified velocity
     * @param velocity the velocity at which the Bullet will travel
     * @pre velocity can be positive or negative and still work correctly
     */
    public void eject(int velocity) 
	{
		if(velocity>0)
			velocity*=-1;
			
		super.moveByAmount(0,velocity);
	}

    /**
     * Moves and draws the bullet onto a PApplet
     */
	public void draw()
	{
		eject(vel);
		super.draw(surface);
	}


}
