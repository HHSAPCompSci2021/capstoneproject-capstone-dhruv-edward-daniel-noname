package sprites;

import processing.core.PApplet;
import processing.core.PImage;
import utils.Vague;

public class Coins extends Sprite
{
    private PApplet surface;
    private PImage[] coinImages;
    private int value = 5, adj = 0;


    public Coins(PApplet surface, PImage[] coinImages,int x, int y, int value)
    {
        super(coinImages[0], x+5,y+5, Vague.chunkSize-5,Vague.chunkSize-5,2);
        this.coinImages = coinImages;
        this.surface = surface;
        this.value = value;
    }
    
    public void draw()
    {
        super.draw(surface);
    }

}
