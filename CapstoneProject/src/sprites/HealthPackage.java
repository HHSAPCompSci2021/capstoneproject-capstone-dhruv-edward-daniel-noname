package sprites;

import processing.core.PApplet;
import processing.core.PImage;
import utils.Vague;

public class HealthPackage extends Sprite
{
    private PApplet surface;
	
    public HealthPackage(PApplet surface, int x, int y)
    {
        super(Vague.healthpackage, x+5,y+5,Vague.chunkSize-5,Vague.chunkSize-5, Vague.HEALTH_PACKAGE);
        this.surface = surface;
    }
    
    public void draw()
    {
        super.draw(surface);
    }

}
