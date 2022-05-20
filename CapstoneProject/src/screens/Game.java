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
import jay.jaysound.JayLayer;


public class Game extends Screen {

	private DrawingSurface surface;
	private PImage background, starShipIMG;
	private StarShip ship;
	private Map map;
	private int y, scrollSpeed;
	public final static String fileSeparator = System.getProperty("file.separator");

	public Game(DrawingSurface surface, int speed, String gameMap) {
		super(surface.width, surface.height);
		y=0;
		this.surface = surface;
		ship = new StarShip(surface, starShipIMG,surface.width/2-20);
		map = new Map("maps"+fileSeparator + gameMap);
		scrollSpeed = speed;
	}

	public void setup()
	{	
		background = surface.loadImage("images"+fileSeparator+"menubg.jpg");
		starShipIMG = surface.loadImage("images"+fileSeparator+"StarShip.png");
		PImage fire = surface.loadImage("images"+fileSeparator+"fire_out.png");
		PImage bullet = surface.loadImage("images"+fileSeparator+"bullet.png");
		
		map.setup(surface);
		ship.setup(starShipIMG, fire, bullet);
	}

	public void draw() 
	{
		surface.fill(0);
		surface.rect(0, 0, 400, 800);
		map.scroll(scrollSpeed);
		
		ship.hitsWall(map.getWallRects());
		
		char pressedKey = Vague.getKey();
		if(pressedKey == 'a' && ship.x-(surface.width/(map.getGrid()[0].length))>=-10)
		{
			ship.walk(-1, surface.width/(map.getGrid()[0].length));
		}

		if (pressedKey == 'd' && ship.x+(surface.width/(map.getGrid()[0].length))<=surface.width-10)
		{
			ship.walk(1, surface.width/(map.getGrid()[0].length));
		}
		map.draw(surface);
		ship.draw();
		ifZero();
	}

	public void ifZero() {
		if(ship.getHealth()<0) {
			reset();
			surface.switchScreen(ScreenSwitcher.MENU_SCREEN);
		}
	}
	public void reset() {
		y=0;
		ship.resetHealth();
		ship.x=175;
	}
}