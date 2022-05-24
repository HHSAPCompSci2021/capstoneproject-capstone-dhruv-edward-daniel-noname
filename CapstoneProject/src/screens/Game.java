package screens;
import processing.core.PImage;
import sprites.Map;
import sprites.StarShip;
import utils.Vague;
import core.DrawingSurface;
/**
 * This class represents a game where normal games are played with predetermined, set maps.
 *
 */
public class Game extends Screen {

	private DrawingSurface surface;
	private PImage starShipIMG;
	private StarShip ship;
	private Map map;
	private int scrollSpeed;

	/**
	 * Creates a GameScreen with scroll speed Speed that gets its map from the file gameMap
	 * @param surface the surface where this Game will be drawn
	 * @param speed the speed, in pixels per frame, that the screen will scroll downwards
	 * @param gameMap the name of the text file representing this Game's map
	 * @pre filename must be a .txt file and should not contain folder names, only the name of the file followed by ".txt"
	 * @pre speed must be a positive integer
	 */
	public Game(DrawingSurface surface, int speed, String gameMap) {
		super(surface.width, surface.height);
		this.surface = surface;
		ship = new StarShip(surface,surface.width/2-20);
		map = new Map("maps"+DrawingSurface.fileSeparator+gameMap);
		scrollSpeed = speed;
	}
	
	/**
	 * sets up the Screen by initializing sprites and setting up the map and ship.
	 */
	public void setup()
	{	
		starShipIMG = surface.loadImage("images"+DrawingSurface.fileSeparator+"StarShip.png");
		map.setup(surface);
		ship.setup();
	}
	
	/**
	 * Draws the game map and ship onto a DrawingSurface
	 */
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
	/**
	 * Checks if the ship's health is 0, and invokes the reset() method if so while switching the current active screen to the Game Over screen
	 */
	public void ifZero() {
		if(ship.getHealth()<0) {
			reset();
			surface.switchScreen(ScreenSwitcher.GAMEOVER_SCREEN);
		}
	}

	/**
	 * Reset's the ship's horizontal position to the middle of the screen and its health to its maximum.
	 */
	public void reset() {
		ship.resetHealth();
		ship.x=175;
	}
}