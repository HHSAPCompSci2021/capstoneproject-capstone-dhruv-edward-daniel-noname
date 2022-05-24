package sprites;

import processing.core.PApplet;
import utils.Vague;

public class AmmoCrate extends Sprite
{
    private PApplet surface;
	
    public AmmoCrate(PApplet surface, int x, int y)
    {
        super(Vague.ammocrate, x+5,y+5,Vague.chunkSize-5,Vague.chunkSize-5, Vague.AMMO_CRATE);
        this.surface = surface;
    }
    
    public void draw()
    {
        super.draw(surface);
    }

}
