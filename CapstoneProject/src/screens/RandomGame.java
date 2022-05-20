package screens;
import java.awt.Point;
import java.awt.Rectangle;
import processing.core.PImage;
//import processing.event.KeyEvent;
import java.awt.event.KeyEvent;

import sprites.RandomMap;
import sprites.StarShip;
import utils.Vague;
import core.DrawingSurface;
import jay.jaysound.JayLayer;


public class RandomGame extends Screen {

	private DrawingSurface surface;
	private PImage background, starShipIMG;
	private StarShip ship;
	private RandomMap map;
	private int y, scrollSpeed;
	public final static String fileSeparator = System.getProperty("file.separator");

	public RandomGame(DrawingSurface surface, int speed) {
		super(surface.width, surface.height);
		y=0;
		this.surface = surface;
		ship = new StarShip(surface, starShipIMG,surface.width/2-20);
		map = new RandomMap(surface);
		scrollSpeed = speed;
	}

	public void setup()
	{	
		background = surface.loadImage("images"+fileSeparator+"menubg.jpg");
		starShipIMG = surface.loadImage("images"+fileSeparator+"StarShip.png");
		
		map.setup();
		ship.setup(starShipIMG);
	}

	public void draw() 
	{
		surface.fill(0);
		surface.rect(0, 0, 400, 800);
		map.scroll(scrollSpeed);
		
		ship.hitsWall(map.getWallRects());
		
		char pressedKey = Vague.getKey();
		if(pressedKey == 'a' && ship.x-(surface.width/(map.getGrid()[0].length))>=-10)
			ship.walk(-1, surface.width/(map.getGrid()[0].length));

		if (pressedKey == 'd' && ship.x+(surface.width/(map.getGrid()[0].length))<=surface.width-10)
			ship.walk(1, surface.width/(map.getGrid()[0].length));

		if(pressedKey == 'w')
			ship.shoot();
		
		map.draw();
		ship.draw();
		ifZero();
	}

	public void ifZero() {
		if(ship.getHealth()<0) {
			int score = 1000*map.getCurrentRow();
			reset();
			surface.switchScreen(ScreenSwitcher.GAMEOVER_SCREEN);
		}
	}

	public void reset() {
		y=0;
		ship.resetHealth();
		ship.x=175;
	}
}