package sprites;

import processing.core.PApplet;
import utils.Vague;

public class BlackHole extends Sprite
{
    private PApplet surface;
	
    public BlackHole(PApplet surface, int x, int y)
    {
        super(Vague.blackhole, x+5,y+5,Vague.chunkSize-5,Vague.chunkSize-5, Vague.BLACK_HOLE);
        this.surface = surface;
    }
    
    public void draw()
    {
        super.draw(surface);
    }

}
