package core;

import java.awt.Point;
import java.util.ArrayList;

import processing.core.PApplet;
import screens.ScreenSwitcher;
import screens.GameScreen;
import screens.StartScreen;
import screens.MenuScreen;

import screens.Screen;


public class DrawingSurface extends PApplet implements ScreenSwitcher
{

    public float ratioX,ratioY;
    
    private Screen active;
    private ArrayList<Screen> screens;

    public DrawingSurface()
    {
        screens = new ArrayList<Screen>();
        
        screens.add(new StartScreen(this));
        screens.add(new MenuScreen(this));
        screens.add(new GameScreen(this));
        active = screens.get(0);

    }

    public void setup()
    {
        for (Screen s : screens)
            s.setup();
    }

    public void draw()
    {
        
        ratioX = (float)width/active.DRAWING_WIDTH;
		ratioY = (float)height/active.DRAWING_HEIGHT;

        push();

        scale(ratioX,ratioY);

        active.draw();
        pop();

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
	

    //Point Math

    public Point assumedCoordinatesToActual(Point assumed) 
    {
		return new Point((int)(assumed.getX()*ratioX), (int)(assumed.getY()*ratioY));
	}

	public Point actualCoordinatesToAssumed(Point actual) 
    {
		return new Point((int)(actual.getX()/ratioX) , (int)(actual.getY()/ratioY));
	}

    
    @Override
    public void switchScreen(int i) {
        active = screens.get(i);
        System.out.println(i);

        
    }
}
