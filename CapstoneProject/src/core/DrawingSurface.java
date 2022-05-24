package core;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jay.jaysound.JayLayer;
import processing.core.PApplet;
import java.awt.event.KeyEvent;


import screens.ScreenSwitcher;
import screens.Game;
import screens.GameOverScreen;
import screens.MenuScreen;
import screens.RandomGame;
import screens.StartScreen;
import screens.GameOverScreen;
import utils.Vague;
import screens.MenuScreen;

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
        // screens.add(new GameL1(this, 6, "map.txt"));
        // screens.add(new GameL1(this, 7, "map2.txt"));
        
//        JayLayer sound = new JayLayer("audio"+fileSeparator, "audio"+fileSeparator, false);
//        String[] soundEffects = new String[] {"Miami Disco.mp3"};
//		String[] songs = new String[] {"Miami Disco.mp3"};
//        sound.addPlayList();
//        sound.addSongs(0, songs);
//        sound.addSoundEffects(soundEffects);
//        sound.changePlayList(0);
//        sound.nextSong();
        
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
        System.out.println(mouseX+"  "+mouseY);
    
	}

	
//	public void mouseMoved() {
//		active.mouseMoved();
//	}
//	
//	public void mouseDragged() {
//		active.mouseDragged();
//	}
//	
//	public void mouseReleased() {
//		active.mouseReleased();
//	}

	// public void keyPressed() {
	// 	keys.add(keyCode);
	// 	if (key == ESC)  // This prevents a processing program from closing on escape key
	// 		key = 0;
	// }

	// public void keyReleased() 
    // {
	// 	while(keys.contains(keyCode))
	// 		keys.remove(new Integer(keyCode));
	// }

	// public boolean isPressed(Integer code) {
	// 	return keys.contains(code);
	// }
	

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
