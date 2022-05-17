package screens;
import java.awt.Point;
import java.awt.Rectangle;
import processing.core.PImage;
//import processing.event.KeyEvent;
import java.awt.event.KeyEvent;

import sprites.Map;
import sprites.StarShip;
import utils.Vague;
import core.DrawingSurface;


public class GameL1 extends Screen {

	private DrawingSurface surface;
	private PImage background, starShipIMG;
//	private Map map;
	private StarShip ship;
	private Map map;
	private int y, scrollSpeed;
	public final static String fileSeparator = System.getProperty("file.separator");

	public GameL1(DrawingSurface surface, int speed) {
		super(surface.width, surface.height);
		y=0;
		this.surface = surface;

		ship = new StarShip(starShipIMG);
		map = new Map(11,17,"images"+fileSeparator+"map.txt", y);

		
		scrollSpeed = speed;

	}

	public void setup()
	{	
		background = surface.loadImage("images"+fileSeparator+"menubg.jpg");
		starShipIMG = surface.loadImage("images"+fileSeparator+"StarShip.png");
		
		map.setup(surface);
		ship.setup(starShipIMG);
	}

	public void draw() 
	{
		//background.resize(this.DRAWING_WIDTH, this.DRAWING_HEIGHT);
		//surface.background(background);
		surface.fill(0);
		surface.rect(0, 0, 400, 800);
		surface.fill(0);
		surface.rect(154, 0, 77, 800);
		map.scroll(scrollSpeed);
		

		if(ship.hitsWall(map.getWallRects())) 
		{
			System.out.println("collides ");
			System.out.println(ship.getHealth());

		}

		char pressedKey = Vague.getKey();
		if(pressedKey == 'a')
		{
			ship.walk(-1, surface.width/(map.getGrid()[0].length));
		}

		if (pressedKey == 'd')
		{
			ship.walk(1, surface.width/(map.getGrid()[0].length));
		}
		map.draw(surface);
		ship.draw(surface);
	}

	public void ifZero() {
		if(ship.getHealth()<0) {
			surface.switchScreen(ScreenSwitcher.GAMEOVER_SCREEN);
		}
	}
	
	

}