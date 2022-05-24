package screens;

import processing.core.PApplet;
import processing.core.PImage;
import sprites.Sprite;

public class GameOverScreen extends Screen{
	
	private PApplet surface;
	private PImage background;
	private Sprite ggs;

	public final static String fileSeparator = System.getProperty("file.separator");

	public GameOverScreen(PApplet surface) {
		super(400, 800);
		this.surface = surface;
	}
	
	public void setup() {
		background=surface.loadImage("images" + fileSeparator + "GameOver.PNG");
		ggs = new Sprite(background, 100, 100, 200, 100);
	}
	
	
	public void draw() 
	{
		surface.background(0);
		ggs.draw(surface);
	}
	

}
