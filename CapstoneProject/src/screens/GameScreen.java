package screens;
import java.awt.Point;
import java.awt.Rectangle;
import processing.core.PImage;
import sprites.Map;
import core.DrawingSurface;


public class GameScreen extends Screen {

	private DrawingSurface surface;
	private PImage background;
	private Rectangle button;
	private int y;

	public GameScreen(DrawingSurface surface) {
		super(400,800);
		this.surface = surface;

		button = new Rectangle(surface.width/2,surface.height/2,200,100);
	}

	public void setup()
	{	
		System.out.println("IN");
		background = surface.loadImage("/images/menubg.jpg");
	}

	public void draw() 
	{

		//surface.background(background);
		surface.rect(0, 0, 400, 800);
		Map lv1 = new Map(5,16,"images/map.txt", 10, y);
		lv1.draw(surface);
		y+=5;
	}



	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (button.contains(p))
			surface.switchScreen(ScreenSwitcher.MENU_SCREEN);
	}
	

}