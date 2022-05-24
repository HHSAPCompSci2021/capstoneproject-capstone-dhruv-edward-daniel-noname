package screens;
import processing.core.PImage;
import sprites.RandomMap;
import sprites.StarShip;
import utils.Vague;
import core.DrawingSurface;
/**
 * Represents a Random Game Screen with infinite randomly generated debris falling down to the player
 */
public class RandomGame extends Screen {

	private DrawingSurface surface;
	private StarShip ship;
	private RandomMap map;
	private int scrollSpeed;

	/**
	 * Creates a new Randomly generated endless level with a specified speed that will be drawn onto a DrawingSurface
	 * @param surface the DrawingSurface upon which the Random Game will be drawn on
	 * @param speed the speed at which the Random Game scrolls downwards
	 * @pre surface must be initialized beforehand
	 * @pre speed must be a positive integer
	 */
	public RandomGame(DrawingSurface surface, int speed) {
		super(surface.width, surface.height);
		this.surface = surface;
		ship = new StarShip(surface, surface.width/2-20);
		map = new RandomMap(surface);
		scrollSpeed = speed;
	}
	
	/**
	 * initializes images for the Random Game and sets up the Random map and ship
	 */
	public void setup()
	{	

		PImage fire = surface.loadImage("images"+DrawingSurface.fileSeparator+"fire_out.png");
		PImage bullet = surface.loadImage("images"+DrawingSurface.fileSeparator+"bullet.png");
		
		map.setup();
		ship.setup();
	}

	/**
	 * Draws an infinite randomly generated series of debris that scrolls downwards towards the player. Responds to pressed keys A, D, and W
	 */
	public void draw() 
	{
		surface.fill(0);
		surface.background(0,0,0);

		map.scroll(scrollSpeed);
		
		map.setWall(ship.bulletHitsWall(map.getWallRects()));
		ship.hits(map.getWallRects());

		char pressedKey = Vague.getKey();
		if(pressedKey == 'a' && ship.x-(surface.width/(map.getGrid()[0].length))>=-10)
			ship.walk(-1, surface.width/(map.getGrid()[0].length));

		if (pressedKey == 'd' && ship.x+(surface.width/(map.getGrid()[0].length))<=surface.width-10)
			ship.walk(1, surface.width/(map.getGrid()[0].length));

		if(pressedKey == 'w')
			ship.shoot();
		
		map.draw();
		ship.draw();
		surface.fill(100,200,100);
		//surface.rect(surface.width/2, surface.height-12, ship.getEnergy()/10, 5);
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