package screens;


import java.awt.Point;
import processing.core.PImage;
import sprites.Map;
import utils.Vague;
import core.DrawingSurface;
//import dsharma578.shapes.*;
import java.awt.Rectangle;


public class StartScreen extends Screen {

	private DrawingSurface surface;
	private PImage background;
	private Rectangle button;
	private int y;
	public final static String fileSeparator = System.getProperty("file.separator");
	
	public StartScreen(DrawingSurface surface) {
		super(400,800);
		this.surface = surface;
		button = new Rectangle(surface.width/2-100,500,200,100);

		//Map2 temp = new Map2(surface);
		//Vague.print2dIntArray(temp.grid);

//		button.(250, 250, 255, 255);
	}

	public void setup()
	{	
		background = surface.loadImage("images"+fileSeparator+"menubg.jpg");
	}

	public void draw() 
	{

		//surface.background(background);
		surface.image(background, 0, 0, 400, 800);
	
		String str = "Start Game!";
		float w = surface.textWidth(str);
		//surface.rect(button.x, button.y, button.width, button.height, 10, 10, 10, 10);
		//surface.fill(0);
		surface.textSize(32);
		surface.fill(0, 408, 612);
		surface.text(str, button.x+button.width/2-w/2, button.y+button.height/2);
	
	}



	public void mousePressed() 
	{
		Point p = new Point(surface.mouseX,surface.mouseY);
		if (button.contains(p)){
		 	surface.switchScreen(ScreenSwitcher.GAME_SCREEN);
		}
	}
	

}

