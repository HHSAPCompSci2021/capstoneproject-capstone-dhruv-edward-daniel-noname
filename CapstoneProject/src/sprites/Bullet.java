package sprites;

import java.util.List;

import processing.core.PApplet;

public class Bullet extends Sprite
{
    private PApplet surface;
    private int xdim = 3, ydim = 5, vel;

    public Bullet(PApplet surface, int x, int y, int vel)
    {
        super(x,y, 3, 5);
		this.vel = vel;
        this.surface = surface;
    }

    public void hitsWall(List<List<Sprite>> wallBlocks) 
	{

		for(int i=0; i<wallBlocks.size(); i++)
		{
			for(int j=0; j<wallBlocks.get(0).size(); j++)
			{
				Sprite s = wallBlocks.get(i).get(j);
				if( this.intersects(s) && s.getId() ==1 )
				{
					s = new Sprite(s.getRX(), s.getRY(), s.getRW(), s.getRW());
				}

			}
		}
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
