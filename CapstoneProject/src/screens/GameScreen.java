package screens;



import java.awt.Point;
import java.awt.Rectangle;
import processing.core.PImage;


import core.DrawingSurface;


public class GameScreen extends Screen {

	private DrawingSurface surface;
	private PImage background;
	private Rectangle button;
	private int y;

	public GameScreen(DrawingSurface surface) {
		super(800,600);
		this.surface = surface;

		button = new Rectangle(800/2-200,600/2-100,200,100);
	}

	public void setup()
	{	
		System.out.println("IN");
		background = surface.loadImage("/images/menubg.jpg");
	}

	public void draw() 
	{

		//surface.background(background);
		surface.rect(0, 0, 800, 500);
		
	}



	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (button.contains(p))
			surface.switchScreen(ScreenSwitcher.MENU_SCREEN);
	}
	

}

