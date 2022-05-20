package screens;

import core.DrawingSurface;
import java.awt.Point;
import java.util.ArrayList;

import utils.Vague;
import dsharma578.shapes.Rectangle;

import processing.core.PApplet;
import processing.core.PShapeSVG.Gradient;

public class MenuScreen extends Screen{
	private DrawingSurface surface;
	ArrayList<Rectangle> rects = new ArrayList<Rectangle>();

    public MenuScreen(DrawingSurface surface) {
		super(400, 800);
		this.surface=surface;
		rects.add(new Rectangle(surface.width/8,100,surface.width-100,80));
		rects.add(new Rectangle(surface.width/8,200,surface.width-100,80));
		rects.add(new Rectangle(surface.width/8,300,surface.width-100,80));
		rects.add(new Rectangle(surface.width/8,400,surface.width-100,100));
	}
    
    public void draw() {
    	surface.background(75,0,125);
    	for(Rectangle r : rects) {
    		r.setFillColor(200, 200, 200, 255);
    		r.draw(surface);
    	}    	
    }
    public void mousePressed() {
    	for(int i = 0; i<rects.size();i++) {
    		if(rects.get(i).isPointInside(surface.mouseX, surface.mouseY)) {
    			surface.switchScreen(i+2);
    		}
    	}
    }
}