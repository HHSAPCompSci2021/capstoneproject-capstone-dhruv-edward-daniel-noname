package screens;
import java.awt.Point;
import java.awt.Rectangle;
import processing.core.PImage;

import java.awt.event.KeyEvent;

import sprites.Map;
import sprites.StarShip;
import core.DrawingSurface;


public class GameL1 extends Screen {
	public final static String fileSeparator = System.getProperty("file.separator");
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
		map = new Map(5,16,"images"+fileSeparator+"map.txt", y);

		
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
			scrollSpeed=-20;
			System.out.println("collides ");

		}

		// System.out.println(map.getWallRects().size());

		if (surface.isPressed(KeyEvent.VK_LEFT))
			ship.walk(-1);

		if (surface.isPressed(KeyEvent.VK_RIGHT))
			ship.walk(1);


		map.draw(surface);
		ship.draw(surface);

	}



	

}