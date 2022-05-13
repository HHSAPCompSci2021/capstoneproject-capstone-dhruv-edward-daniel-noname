package screens;


import java.awt.Point;
import processing.core.PImage;


import core.DrawingSurface;
import dsharma578.shapes.*;


public class StartScreen extends Screen {

	private DrawingSurface surface;
	private PImage background;
	private Rectangle button;
	private int y;
	
	public StartScreen(DrawingSurface surface) {
		super(400,800);
		this.surface = surface;
		button = new Rectangle(surface.width/2,surface.height/2,200,100);
		button.setFillColor(250, 250, 255, 255);
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
	
		button.draw(surface);
		surface.fill(0);
		String str = "Click me!";
		float w = surface.textWidth(str);
		//surface.text(str, surface.width+button.width/2-w/2, button.y+button.height/2);
	

		//fun
		/*
		surface.stroke(226, 204, 0);
		surface.line(0, y, surface.width, y);
	  
		y++;
		if (y > surface.height) {
		  y = 0;
		}*/

	}



	public void mousePressed() 
	{
		if (button.isPointInside(surface.mouseX,surface.mouseY))
			surface.switchScreen(ScreenSwitcher.MENU_SCREEN);
	}
	

}

