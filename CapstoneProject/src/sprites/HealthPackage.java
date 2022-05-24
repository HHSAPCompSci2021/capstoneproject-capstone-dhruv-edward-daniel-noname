package sprites;

import processing.core.PApplet;
import processing.core.PImage;
import utils.Vague;

/**
 * Represents the Health Pickup optainable in the endless mode that replenishes player health
 */
public class HealthPackage extends Sprite
{
    private PApplet surface;
	
    /**
     * Creates a new HealthPackage at coordinate (x,y)
     * @param surface the PApplet on which the Health Package will be drawn on
     * @param x the x-coordinate of the Health Package
     * @param y the y-coordinate of the Health Package
     */
    public HealthPackage(PApplet surface, int x, int y)
    {
        super(Vague.healthpackage, x+5,y+5,Vague.chunkSize-5,Vague.chunkSize-5, Vague.HEALTH_PACKAGE);
        this.surface = surface;
    }
    
    /**
     * Draws the health package onto the PApplet surface
     */
    public void draw()
    {
        super.draw(surface);
    }

}
