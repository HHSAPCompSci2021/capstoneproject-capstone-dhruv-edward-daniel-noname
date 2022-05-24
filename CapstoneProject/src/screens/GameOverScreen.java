package screens;

import core.DrawingSurface;
import processing.core.PApplet;
import processing.core.PImage;
import sprites.Sprite;
/**
 * Represents the "Game Over" screen players are shown if they lose all their health
 */
public class GameOverScreen extends Screen{
	
	private DrawingSurface surface;
	private PImage background;
	private Sprite ggs;

	/**
	 * Creates a Game Over screen
	 * @param surface the surface upon which the Game Over screen will be created
	 */
	public GameOverScreen(DrawingSurface surface) {
		super(400, 800);
		this.surface = surface;
	}
	
	/**
	 * sets up the Game Over screen by initializing the background image as a Sprite
	 */
	public void setup() {
		background=surface.loadImage("images" + DrawingSurface.fileSeparator + "GameOver.PNG");
		ggs = new Sprite(background, surface.height/4, surface.height/8, 200, 100);
	}
	
	/**
	 * Draws the Game Over image from setup() onto surface on a black background
	 */
	public void draw() 
	{
		surface.background(0);
		ggs.draw(surface);
	}
	

}
