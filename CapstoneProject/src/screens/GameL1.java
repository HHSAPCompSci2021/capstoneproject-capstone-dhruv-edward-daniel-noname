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

	public GameL1(DrawingSurface surface, int speed) {
		super(surface.width, surface.height);
		y=0;
		this.surface = surface;

		ship = new StarShip(starShipIMG);
		map = new Map(11,17,"CapstoneProject\\images\\map.txt", y);

		
		scrollSpeed = speed;

	}

	public void setup()
	{	
		background = surface.loadImage("/images/menubg.jpg");
		starShipIMG = surface.loadImage("CapstoneProject\\images\\StarShip.png");
		
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
			scrollSpeed=-20;
			System.out.println("collides ");

		}

		// System.out.println(map.getWallRects().size());
		char pressedKey = Vague.getKey();
		if(pressedKey == 'a')
		{
			ship.walk(-1);
		}

		if (pressedKey == 'd')
		{
			ship.walk(1);
		}


		map.draw(surface);
		ship.draw(surface);

	}



	

}