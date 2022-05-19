package screens;

import core.DrawingSurface;
import processing.core.PImage;
import core.DrawingSurface;
import dsharma578.shapes.*;

public class GameOverScreen extends Screen{
	
	private DrawingSurface surface;
	private Rectangle button;
	private PImage background;
	public final static String fileSeparator = System.getProperty("file.separator");

	public GameOverScreen(int width, int height) {
		super(400, 800);
		this.surface = surface;
		button = new Rectangle(surface.width/2+50,500,200,100);
		button.setFillColor(250, 250, 255, 255);
	}
	
	public void setup() {
		System.out.println("its in");
		background=surface.loadImage("images" + fileSeparator + "GameOver.PNG");
	}
	
	
	public void draw() 
	{
		surface.image(background, 0, 0, 400, 800);
		button.draw(surface);
		surface.fill(0);
		String str = "Click me!";
		float w = surface.textWidth(str);
	}
	
	public void mousePressed() 
	{
		if (button.isPointInside(surface.mouseX,surface.mouseY))
			surface.switchScreen(ScreenSwitcher.START_SCREEN);
	}

}
