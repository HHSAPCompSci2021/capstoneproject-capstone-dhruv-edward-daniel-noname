package sprites;

import processing.core.PApplet;
import utils.Vague;
/**
 * Represents an Ammo Crate that refills the Ship's ammo supply in the endless Random Game mode
 */
public class AmmoCrate extends Sprite
{
    private PApplet surface;
	
    /**
     * Creates a new AmmoCrate Sprite with an image and coordinates (x,y) that will be drawn on a PApplet
     * @param surface the PApplet this AmmoCrate will be drawn on
     * @param x the x-coordinate of the AmmoCrate on the map
     * @param y the y-coordinate of the AmmoCrate on the map
     */
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
