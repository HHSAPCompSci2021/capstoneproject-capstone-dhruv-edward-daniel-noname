package screens;


import java.awt.Point;
import processing.core.PImage;
import core.DrawingSurface;
//import dsharma578.shapes.*;
import java.awt.Rectangle;


public class StartScreen extends Screen {

	private DrawingSurface surface;
	private PImage background;
	private Rectangle button;
	public final static String fileSeparator = System.getProperty("file.separator");
	
	public StartScreen(DrawingSurface surface) {
		super(400,800);
		this.surface = surface;
	}

	public void setup()
	{	
		background = surface.loadImage("images"+fileSeparator+"menubg.jpg");
	}

	public void draw() 
	{
		button = new Rectangle(surface.width/4, (int)(surface.height*0.675), surface.width/2, surface.height/8);
		surface.image(background, 0, 0, surface.width, surface.height);
		surface.fill(150);
		String str = "Start Game!";
		surface.textSize(32);
		surface.fill(0, 408, 612);
		surface.text(str, surface.width/2, (float)(surface.height*0.75));
	
	}



	public void mousePressed() 
	{
		Point p = new Point(surface.mouseX,surface.mouseY);
		if (button.contains(p)){
		 	surface.switchScreen(ScreenSwitcher.MENU_SCREEN);
		}
	}
	

}

