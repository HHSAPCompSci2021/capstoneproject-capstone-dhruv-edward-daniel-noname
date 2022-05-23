package screens;

import core.DrawingSurface;
import java.awt.Rectangle;

import processing.core.PApplet;
import processing.core.PImage;
import sprites.Sprite;
import utils.UserData;

public class GameOverScreen extends Screen{
	
	private PApplet surface;
	private PImage background;
	private Sprite ggs;
	private int T = 0;

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
		T++;
		if(T==1)
		{
			UserData.printData();
		}

		surface.background(0);

		surface.textSize(32);
		surface.text("$$$s collected: "+String.valueOf(UserData.coinsCollected*5*1000+UserData.distanceTraveled/100), 10, 300); 
		surface.fill(0, 408, 612);
		surface.text("time survived[ticks]: "+UserData.timeSurvived, 10, 400);
		surface.fill(0, 408, 612, 204);
		surface.text("time survived: "+UserData.timeSurvived, 10, 500);

		ggs.draw(surface);
	}
	

}
