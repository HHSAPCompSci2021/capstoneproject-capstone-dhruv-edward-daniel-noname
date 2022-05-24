package screens;


import java.awt.Point;
import processing.core.PImage;
import core.DrawingSurface;
//import dsharma578.shapes.*;
import java.awt.Rectangle;

/**
 * Represents the Start/Title screen of the game
 */
public class StartScreen extends Screen {

	private DrawingSurface surface;
	private PImage background;
	private Rectangle button;
	
	/**
	 * Creates a new StartScreen with dimensions 400x800 pixels that will be drawn on a DrawingSurface
	 * @param surface the DrawingSurface StartScreen will be drawn on
	 */
	public StartScreen(DrawingSurface surface) {
		super(400,800);
		this.surface = surface;
	}
	
	/**
	 * Initializes the background image for the first screen
	 */
	public void setup()
	{	
		background = surface.loadImage("images"+DrawingSurface.fileSeparator+"menubg.jpg");
	}
	
	/**
	 * Draws the background and a starting button on the Screen. The button is invisible and seen by a "Start Game" text message
	 */
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


	/**
	 * Switches the screen to the Menu Screen in the event the mouse is pressed and its coordinates are within the "Start Game" button
	 */
	public void mousePressed() 
	{
		Point p = new Point(surface.mouseX,surface.mouseY);
		if (button.contains(p)){
		 	surface.switchScreen(ScreenSwitcher.MENU_SCREEN);
		}
	}
}