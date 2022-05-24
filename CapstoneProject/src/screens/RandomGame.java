package screens;
import java.awt.Point;
import java.awt.Rectangle;
import processing.core.PImage;
//import processing.event.KeyEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import sprites.Map;
import sprites.RandomMap;
import sprites.StarShip;
import utils.Vague;
import core.DrawingSurface;
import jay.jaysound.JayLayer;


public class RandomGame extends Screen {

	private DrawingSurface surface;
	private StarShip ship;
	private Map map;
	private int scrollSpeed;
	public final static String fileSeparator = System.getProperty("file.separator");

	public RandomGame(DrawingSurface surface, int speed) {
		super(surface.width, surface.height);
		this.surface = surface;
		ship = new StarShip(surface, surface.width/2-20);
		map = new Map(surface);
		scrollSpeed = speed;
	}

	public void setup()
	{	

		PImage fire = surface.loadImage("images"+fileSeparator+"fire_out.png");
		PImage bullet = surface.loadImage("images"+fileSeparator+"bullet.png");
		
		map.setup();
		ship.setup();
	}

	public void draw() 
	{
		surface.fill(0);
		surface.background(0,0,0);

		//currentFrame = (currentFrame+1) % numFrames;  // Use % to cycle through frames
		//surface.image(background[(currentFrame) % numFrames], -10, 200);

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
		surface.noFill();
		surface.rect(surface.width/2, surface.height-12, 30, 5);
		surface.rect(surface.width/2+30, surface.height-12, 30, 5);
		surface.rect(surface.width/2+60, surface.height-12, 30, 5);
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