package core;

import java.awt.Point;
import java.util.ArrayList;

import processing.core.PApplet;
import java.awt.event.KeyEvent;


import screens.ScreenSwitcher;
import screens.GameL1;
import screens.StartScreen;
import screens.MenuScreen;

import screens.Screen;


public class DrawingSurface extends PApplet implements ScreenSwitcher
{    
    private Screen active;
    private ArrayList<Screen> screens;
    private ArrayList<Integer> keys;


    public DrawingSurface()
    {
        keys = new ArrayList<Integer>();
        screens = new ArrayList<Screen>();
        
        screens.add(new StartScreen(this));
        screens.add(new MenuScreen(this));
        screens.add(new GameL1(this, 10));

        active = screens.get(0);

    }

    public void setup()
    {
        frameRate(60);
        for (Screen s : screens)
            s.setup();
    }

    public void draw()
    {

        active.draw();

    }


    // Mouse Actions

	public void mousePressed() {
		active.mousePressed();
        System.out.println(mouseX+"  "+mouseY);
    
	}
	
	public void mouseMoved() {
		active.mouseMoved();
	}
	
	public void mouseDragged() {
		active.mouseDragged();
	}
	
	public void mouseReleased() {
		active.mouseReleased();
	}

	public void keyPressed() {
		keys.add(keyCode);
		if (key == ESC)  // This prevents a processing program from closing on escape key
			key = 0;
	}

	public void keyReleased() {
		while(keys.contains(keyCode))
			keys.remove(new Integer(keyCode));
	}

	public boolean isPressed(Integer code) {
		return keys.contains(code);
	}
	

    
    @Override
    public void switchScreen(int i) {
        active = screens.get(i);
        System.out.println(i);

        
    }
}
