package sprites;

import processing.core.PApplet;
import processing.core.PImage;
import utils.Vague;
/**
 * Represents the coin collectible that can be picked up during the endless mode to increase the player's score
 */
public class Coins extends Sprite
{
    private PApplet surface;
    private PImage coinImage;
    private int value = 5, adj = 0;

    /**
     * Creates a new Coin at a specified x and y with a certain image and value
     * @param surface the surface upon which the Coin will be drawn
     * @param coinImage the image representing how the Coin will look
     * @param x the x-coordinate of the Coin
     * @param y the y-coordinate of the Coin
     * @param value the value of this Coin, the amount that the score will be increased by picking up this Coin
     */
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
