package core;

import java.awt.Point;
import java.util.ArrayList;

import processing.core.PApplet;
import screens.ScreenSwitcher;
import screens.MenuScreen;
import screens.Screen;


public class DrawingSurface extends PApplet implements ScreenSwitcher
{

    public float x,y;
    
    private Screen active;
    private ArrayList<Screen> screens;

    public DrawingSurface()
    {
        MenuScreen s1 = new MenuScreen(this);

    }

    public Point assumedCoordinatesToActual(Point assumed) {
		return new Point((int)(assumed.getX()*x), (int)(assumed.getY()*y));
	}

	public Point actualCoordinatesToAssumed(Point actual) {
		return new Point((int)(actual.getX()/x) , (int)(actual.getY()/y));
	}

    
    @Override
    public void switchScreen(int i) {
        active = screens.get(i);
        
    }
}
