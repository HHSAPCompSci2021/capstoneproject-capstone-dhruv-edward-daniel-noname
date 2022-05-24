package screens;
import processing.core.PImage;
import sprites.Map;
import sprites.StarShip;
import utils.Vague;
import core.DrawingSurface;


public class Game extends Screen {

	private DrawingSurface surface;
	private PImage starShipIMG;
	private StarShip ship;
	private Map map;
	private int scrollSpeed;

	public Game(DrawingSurface surface, int speed, String gameMap) {
		super(surface.width, surface.height);
		this.surface = surface;
		ship = new StarShip(surface,surface.width/2-20);
		map = new Map("maps"+DrawingSurface.fileSeparator+gameMap);
		scrollSpeed = speed;
	}

	public void setup()
	{	
		starShipIMG = surface.loadImage("images"+DrawingSurface.fileSeparator+"StarShip.png");
		map.setup(surface);
		ship.setup();
	}

	public void draw() 
	{
		surface.fill(0);
		surface.rect(0, 0, 400, 800);
		map.scroll(scrollSpeed);
		ship.hits(map.getWallRects());
		
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
		ship.draw(surface);
		surface.rect(0, surface.height-20, surface.width, 20);
		surface.rect(10, surface.height-12, 100, 5);
		surface.stroke(255);
		surface.fill(255,255,255);
		surface.rect(10, surface.height-12, ship.getHealth(), 5);
		ifZero();
}
	public void ifZero() {
		if(ship.getHealth()<0) {
			reset();
			surface.switchScreen(ScreenSwitcher.GAMEOVER_SCREEN);
		}
	}

	public void reset() {
		ship.resetHealth();
		ship.x=175;
	}
}