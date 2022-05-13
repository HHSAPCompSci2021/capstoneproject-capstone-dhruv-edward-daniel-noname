package screens;
import java.awt.Point;
import java.awt.Rectangle;
import processing.core.PImage;
import sprites.Map;
import sprites.StarShip;
import core.DrawingSurface;


public class GameScreen extends Screen {

	private DrawingSurface surface;
	private PImage background;
	private StarShip ship;
	private int y;

	public GameScreen(DrawingSurface surface) {
		super(surface.width, surface.height);
		y=0;
		this.surface = surface;
	}

	public void setup()
	{	
		background = surface.loadImage("/images/menubg.jpg");
	}

	public void draw() 
	{
		//background.resize(this.DRAWING_WIDTH, this.DRAWING_HEIGHT);
		//surface.background(background);
		surface.rect(0, 0, 400, 800);
		surface.fill(0);
		surface.rect(154, 0, 77, 800);
		Map lv1 = new Map(5,16,"images/map.txt",y);
		lv1.draw(surface);
		ship.draw(surface);
		y+=5;
	}



	public void mousePressed() {
			surface.switchScreen(ScreenSwitcher.MENU_SCREEN);
	}
	

}