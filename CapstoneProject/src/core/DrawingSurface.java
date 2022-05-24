package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import processing.core.PApplet;


import screens.ScreenSwitcher;
import screens.Game;
import screens.GameOverScreen;
import screens.MenuScreen;
import screens.RandomGame;
import screens.StartScreen;
import utils.Vague;

import screens.Screen;


public class DrawingSurface extends PApplet implements ScreenSwitcher
{    
    private Screen active;
    private ArrayList<Screen> screens;
    private Map<Character, Record> map = new HashMap<Character, Record>();
	public final static String fileSeparator = System.getProperty("file.separator");

    public DrawingSurface()
    {	setSize(400,800);
        screens = new ArrayList<Screen>();
        screens.add(new StartScreen(this));
        screens.add(new MenuScreen(this));
        screens.add(new Game(this, 5, "map.txt"));
        screens.add(new Game(this, 3, "map2.txt"));
        screens.add(new Game(this, 3, "map3.txt"));
        screens.add(new RandomGame(this, 6));
        screens.add(new GameOverScreen(this));
        
        active = screens.get(0);

    }

    public void setup()
    {
        Vague.fire = loadImage("images"+fileSeparator+"fire_out.png");
		Vague.starShip = loadImage("images"+fileSeparator+"StarShip.png");
		Vague.destroyed = loadImage("images"+fileSeparator+"exploded.png");
        Vague.ammocrate = loadImage("images"+fileSeparator+"ammocrate.png");
        Vague.healthpackage= loadImage("images"+fileSeparator+"ramen.png");
        Vague.barrier = loadImage("images"+fileSeparator+"wallchunk.png");
		Vague.bullet = loadImage("images"+fileSeparator+"bullet.png");
        Vague.blackhole = loadImage("images"+fileSeparator+"blackhole.png");



        frameRate(60);
        for (Screen s : screens)
            s.setup();
    }

    public void draw()
    {
    	textAlign(CENTER);
        active.draw();

    }


    // Mouse Actions

	public void mousePressed() {
		active.mousePressed();    
	}

	


    @Override
    public void switchScreen(int i) {
        active = screens.get(i);
    }

    public void keyPressed() {    
        // only if map doesn't hold this key yet
        if (!map.containsKey(key)) {
    
            // create new record with this key and start timestamp
            Record record = new Record(key, millis());
            map.put(key, record);
        }
    }
    
    public void keyReleased() {
    
        // retrieve record for this key
        Record record = map.get(key);
    
        // store release timestamp (milliseconds from program startup)
        record.releaseTime = millis();
    
        map.remove(key);
    
        // now record has full info
        Vague.KeyEvents(key);
    }
}

class Record {
    char key;
    long pressedTime;
    long releaseTime;

    Record(char key, long pressedTime) {
        this.key = key;
        this.pressedTime = pressedTime;
    }
}
