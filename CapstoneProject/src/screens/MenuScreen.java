package screens;



import java.awt.Point;
import java.awt.Rectangle;
import processing.core.PImage;


import core.DrawingSurface;


public class MenuScreen extends Screen {

	private DrawingSurface surface;
	private PImage background;
	private Rectangle button;
	private int y;

	public MenuScreen(DrawingSurface surface) {
		super(800,600);
		this.surface = surface;

		button = new Rectangle(800/2-100,600/2-50,200,100);
	}

	public void setup()
	{	
		System.out.println("IN");
		background = surface.loadImage("/images/menubg.jpg");
	}

	public void draw() 
	{

		//surface.background(background);
		surface.image(background, 0, 0, 400, 800);

		surface.rect(button.x, button.y, button.width, button.height, 10, 10, 10, 10);
		surface.fill(0);
		String str = "Click me!";
		float w = surface.textWidth(str);
		surface.text(str, button.x+button.width/2-w/2, button.y+button.height/2);
	

		//fun
		surface.stroke(226, 204, 0);
		surface.line(0, y, surface.width, y);
	  
		y++;
		if (y > surface.height) {
		  y = 0;
		}

	}



	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (button.contains(p))
			surface.switchScreen(ScreenSwitcher.GAME_SCREEN);
	}
	

}

