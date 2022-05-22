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
	private int y, scrollSpeed;
	public final static String fileSeparator = System.getProperty("file.separator");

	public Game(DrawingSurface surface, int speed, String gameMap) {
		super(surface.width, surface.height);
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
			surface.switchScreen(ScreenSwitcher.MENU_SCREEN);
		}
	}
	public void reset() {
		y=0;
		ship.resetHealth();
		ship.x=175;
	}
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
	public final static String fileSeparator = System.getProperty("file.separator");

	public Game(DrawingSurface surface, int speed) {
		super(surface.width, surface.height);
		this.surface = surface;

		ship = new StarShip(surface ,surface.width/2-20);
		map = new Map(surface);

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
	public final static String fileSeparator = System.getProperty("file.separator");

	public Game(DrawingSurface surface, int speed) {
		super(surface.width, surface.height);
		this.surface = surface;

		ship = new StarShip(surface ,surface.width/2-20);
		map = new Map(surface);

		scrollSpeed = speed;
	}

	public void setup()
	{	
		// background[0] = surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_00_delay-0.04s.gif");
		// background[1] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_01_delay-0.04s.gif"); 
		// background[2] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_02_delay-0.04s.gif");
		// background[3] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_03_delay-0.04s.gif"); 
		// background[4] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_04_delay-0.04s.gif"); 
		// background[5] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_05_delay-0.04s.gif");
		// background[6] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_06_delay-0.04s.gif");
		// background[7] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_07_delay-0.04s.gif");
		// background[8] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_08_delay-0.04s.gif");
		// background[9] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_09_delay-0.04s.gif");
		// background[10] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_10_delay-0.04s.gif"); 
		// background[11] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_11_delay-0.04s.gif"); 
		// background[12] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_12_delay-0.04s.gif"); 
		// background[13] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_13_delay-0.04s.gif"); 
		// background[14] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_14_delay-0.04s.gif"); 
		// background[15] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_15_delay-0.04s.gif"); 
		// background[16] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_16_delay-0.04s.gif"); 
		// background[17] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_17_delay-0.04s.gif"); 
		// background[18] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_18_delay-0.04s.gif"); 
		// background[19] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_19_delay-0.04s.gif"); 
		// background[20] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_20_delay-0.04s.gif"); 
		// background[21] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_21_delay-0.04s.gif"); 
		// background[22] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_22_delay-0.04s.gif"); 
		// background[23] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_23_delay-0.08s.gif"); 
		// background[24] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_24_delay-0.04s.gif"); 
		// background[25] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_25_delay-0.04s.gif"); 
		// background[26] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_26_delay-0.04s.gif"); 
		// background[27] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_27_delay-0.04s.gif"); 
		// background[28] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_28_delay-0.04s.gif"); 
		// background[29] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_29_delay-0.04s.gif"); 
		// background[30] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_30_delay-0.04s.gif"); 
		// background[31] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_31_delay-0.04s.gif"); 
		// background[32] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_32_delay-0.04s.gif"); 
		// background[33] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_33_delay-0.04s.gif"); 
		// background[34] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_34_delay-0.04s.gif"); 
		// background[35] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_35_delay-0.04s.gif"); 
		// background[36] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_36_delay-0.04s.gif"); 
		// background[37] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_37_delay-0.04s.gif"); 
		// background[38] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_38_delay-0.04s.gif"); 
		// background[39] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_39_delay-0.04s.gif"); 
		// background[40] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_40_delay-0.04s.gif"); 
		// background[41] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_41_delay-0.04s.gif"); 
		// background[42] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_42_delay-0.04s.gif"); 
		// background[43] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_43_delay-0.04s.gif"); 
		// background[44] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_44_delay-0.04s.gif"); 
		// background[45] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_45_delay-0.04s.gif"); 
		// background[46] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_46_delay-0.04s.gif"); 



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
	public final static String fileSeparator = System.getProperty("file.separator");

	public Game(DrawingSurface surface, int speed) {
		super(surface.width, surface.height);
		this.surface = surface;

		ship = new StarShip(surface ,surface.width/2-20);
		map = new Map(surface);

		scrollSpeed = speed;
	}

	public void setup()
	{	
		// background[0] = surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_00_delay-0.04s.gif");
		// background[1] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_01_delay-0.04s.gif"); 
		// background[2] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_02_delay-0.04s.gif");
		// background[3] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_03_delay-0.04s.gif"); 
		// background[4] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_04_delay-0.04s.gif"); 
		// background[5] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_05_delay-0.04s.gif");
		// background[6] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_06_delay-0.04s.gif");
		// background[7] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_07_delay-0.04s.gif");
		// background[8] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_08_delay-0.04s.gif");
		// background[9] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_09_delay-0.04s.gif");
		// background[10] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_10_delay-0.04s.gif"); 
		// background[11] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_11_delay-0.04s.gif"); 
		// background[12] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_12_delay-0.04s.gif"); 
		// background[13] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_13_delay-0.04s.gif"); 
		// background[14] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_14_delay-0.04s.gif"); 
		// background[15] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_15_delay-0.04s.gif"); 
		// background[16] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_16_delay-0.04s.gif"); 
		// background[17] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_17_delay-0.04s.gif"); 
		// background[18] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_18_delay-0.04s.gif"); 
		// background[19] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_19_delay-0.04s.gif"); 
		// background[20] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_20_delay-0.04s.gif"); 
		// background[21] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_21_delay-0.04s.gif"); 
		// background[22] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_22_delay-0.04s.gif"); 
		// background[23] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_23_delay-0.08s.gif"); 
		// background[24] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_24_delay-0.04s.gif"); 
		// background[25] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_25_delay-0.04s.gif"); 
		// background[26] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_26_delay-0.04s.gif"); 
		// background[27] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_27_delay-0.04s.gif"); 
		// background[28] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_28_delay-0.04s.gif"); 
		// background[29] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_29_delay-0.04s.gif"); 
		// background[30] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_30_delay-0.04s.gif"); 
		// background[31] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_31_delay-0.04s.gif"); 
		// background[32] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_32_delay-0.04s.gif"); 
		// background[33] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_33_delay-0.04s.gif"); 
		// background[34] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_34_delay-0.04s.gif"); 
		// background[35] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_35_delay-0.04s.gif"); 
		// background[36] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_36_delay-0.04s.gif"); 
		// background[37] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_37_delay-0.04s.gif"); 
		// background[38] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_38_delay-0.04s.gif"); 
		// background[39] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_39_delay-0.04s.gif"); 
		// background[40] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_40_delay-0.04s.gif"); 
		// background[41] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_41_delay-0.04s.gif"); 
		// background[42] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_42_delay-0.04s.gif"); 
		// background[43] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_43_delay-0.04s.gif"); 
		// background[44] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_44_delay-0.04s.gif"); 
		// background[45] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_45_delay-0.04s.gif"); 
		// background[46] =surface.loadImage("images"+fileSeparator+"bg"+fileSeparator+"frame_46_delay-0.04s.gif"); 



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
	public final static String fileSeparator = System.getProperty("file.separator");

	public Game(DrawingSurface surface, int speed) {
		super(surface.width, surface.height);
		this.surface = surface;

		ship = new StarShip(surface ,surface.width/2-20);
		map = new Map(surface);

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
	public final static String fileSeparator = System.getProperty("file.separator");

	public Game(DrawingSurface surface, int speed) {
		super(surface.width, surface.height);
		this.surface = surface;

		ship = new StarShip(surface ,surface.width/2-20);
		map = new Map(surface);

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
	public final static String fileSeparator = System.getProperty("file.separator");

	public Game(DrawingSurface surface, int speed) {
		super(surface.width, surface.height);
		this.surface = surface;

		ship = new StarShip(surface ,surface.width/2-20);
		map = new Map(surface);

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