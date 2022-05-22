package screens;
import java.awt.Point;
import java.awt.Rectangle;
import processing.core.PImage;
//import processing.event.KeyEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.text.TabableView;

import sprites.Map;
import sprites.Sprite;
import sprites.StarShip;
import utils.UserData;
import utils.Vague;
import core.DrawingSurface;
import jay.jaysound.JayLayer;


public class Game extends Screen {

	private DrawingSurface surface;
	private StarShip ship;
	private Map map;
	private int y=0, scrollSpeed, currentFrame=0, numFrames = 47, blackhole_tick=0;
	PImage[] background = new PImage[numFrames];
	//public final static String fileSeparator = System.getProperty("file.separator");

	public Game(DrawingSurface surface, int speed) {
		super(surface.width, surface.height);
		this.surface = surface;

		ship = new StarShip(surface ,surface.width/2-20);
		map = new Map(surface);
		System.out.println(""+DrawingSurface.fileSeparator);

		scrollSpeed = speed;
	}

	public void setup()
	{	



		map.setup();
		ship.setup();
	}

	public void draw() 
	{
		UserData.timeSurvived++;
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
		ifZero();

		if(Vague.IN_BLACK_HOLE)
		{
			if(blackhole_tick == 0)
				blackhole_tick = UserData.timeSurvived;

			if((UserData.timeSurvived - blackhole_tick) > 100)
			{
				surface.switchScreen(ScreenSwitcher.GAMEOVER_SCREEN);
			}else
			{
				surface.background(0);
				new Sprite(Vague.blackhole, 50, 300, 300, 300).draw(surface);
			}
		}

	}

	public void ifZero() {
		if(ship.getHealth()<0) {
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