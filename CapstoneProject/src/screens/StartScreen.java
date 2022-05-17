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
	public final static String fileSeparator = System.getProperty("file.separator");
	
	public StartScreen(DrawingSurface surface) {
		super(400,800);
		this.surface = surface;
		button = new Rectangle(surface.width/2+50,surface.height/2+50,200,100);
		button.setFillColor(250, 250, 255, 255);
	}

	public void setup()
	{	
		System.out.println("IN");
		background = surface.loadImage("images"+fileSeparator+"menubg.jpg");
	}

	public void draw() 
	{

		//surface.background(background);
		surface.image(background, 0, 0, 400, 800);
	
		button.draw(surface);
		surface.fill(0);
		String str = "Click me!";
		float w = surface.textWidth(str);
	}



	public void mousePressed() 
	{
		if (button.isPointInside(surface.mouseX,surface.mouseY))
			surface.switchScreen(ScreenSwitcher.MENU_SCREEN);
	}
	

}

