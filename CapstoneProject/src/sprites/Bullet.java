package sprites;

import java.util.List;

import processing.core.PApplet;
import processing.core.PImage;
import utils.Vague;

public class Bullet extends Sprite
{
    private PApplet surface;
    private int xdim = 3, ydim = 5, vel;


	public Bullet(PApplet surface, int x, int y, int vel)
    {
        super(Vague.bullet, x,y, 15, 20);
		this.vel = vel;
        this.surface = surface;
    }

	public Bullet(PApplet surface,PImage bulletImage, int x, int y, int vel)
    {
        super(bulletImage, x,y, 15, 20);
		this.vel = vel;
        this.surface = surface;
    }


	public void setup()
	{
		
	}

    public boolean hitsWall(List<List<Sprite>> wallBlocks) 
	{

		boolean intersects = false;
		for(int i=0; i<wallBlocks.size(); i++)
		{
			for(int j=0; j<wallBlocks.get(0).size(); j++)
			{
				Sprite s = wallBlocks.get(i).get(j);

				if( this.intersects(s) && s.getId() ==1 )
				{
					wallBlocks.get(i).remove(j);
					wallBlocks.get(i).add(j, new Sprite(s.getRX(), s.getRY(), s.getRW(), s.getRW()));
					wallBlocks.get(i).get(j).setFillColor(0, 0, 0, 255);

					return true;
				}

			}
		}

		return false;
	}

    public void eject(int velocity) 
	{
		if(velocity>0)
			velocity*=-1;
			
		super.moveByAmount(0,velocity);
	}

	public void draw()
	{
		eject(vel);
		super.draw(surface);
	}


}
package sprites;

import java.util.List;

import processing.core.PApplet;
import processing.core.PImage;
import utils.Vague;

public class Bullet extends Sprite
{
    private PApplet surface;
    private int xdim = 3, ydim = 5, vel;


	public Bullet(PApplet surface, int x, int y, int vel)
    {
        super(Vague.bullet, x,y, 15, 20);
		this.vel = vel;
        this.surface = surface;
    }


	public void setup()
	{
		
	}

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

    public void eject(int velocity) 
	{
		if(velocity>0)
			velocity*=-1;
			
		super.moveByAmount(0,velocity);
	}

	public void draw()
	{
		eject(vel);
		super.draw(surface);
	}


}
