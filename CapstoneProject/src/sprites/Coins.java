package sprites;

import processing.core.PApplet;
import processing.core.PImage;
import utils.Vague;

public class Coins extends Sprite
{
    private PApplet surface;
    private PImage coinImage;
    private int value = 5, adj = 0;


    public Coins(PApplet surface, PImage coinImage,int x, int y, int value)
    {
        super(coinImage, x+5,y+5, Vague.chunkSize-5,Vague.chunkSize-5,2);
        this.coinImage = coinImage;
        this.surface = surface;
        this.value = value;
    }
    
    public void draw()
    {
        super.draw(surface);
    }

}
