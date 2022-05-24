package screens;

import core.DrawingSurface;
import java.util.ArrayList;
import dsharma578.shapes.Rectangle;
/**
 * Represents the Menu Screen containing the different level select options 
 */
public class MenuScreen extends Screen{
	private DrawingSurface surface;
	private ArrayList<Rectangle> rects = new ArrayList<Rectangle>();
	
	/**
	 * Creates a Menu Screen with dimensions 400x800 pixels that will be drawn on a DrawingSurface
	 * @param surface the DrawingSurface upon which the Menu Screen will be drawn
	 */
    public MenuScreen(DrawingSurface surface) {
		super(400, 800);
		this.surface=surface;		
	}
    
    /**
     * Draws the level select menu onto the screen as 3 smaller Rectangles for levels and 1 larger Rectangle for the endless mode
     */
    public void draw() {
    	rects.clear();
    	rects.add(new Rectangle(surface.width/8,surface.height/8,3*surface.width/4,surface.height/10));
		rects.add(new Rectangle(surface.width/8,surface.height/4,3*surface.width/4,surface.height/10));
		rects.add(new Rectangle(surface.width/8,3*surface.height/8,3*surface.width/4,surface.height/10));
		rects.add(new Rectangle(surface.width/8,surface.height/2, 3*surface.width/4,surface.height/5));
    	surface.background(0);
    	surface.image(surface.loadImage("images"+DrawingSurface.fileSeparator+"GameLogo.jpg"), surface.width/10, surface.height/40, 8*surface.width/10,surface.height/10);
    	for(Rectangle r : rects) {
    		r.setFillColor(200, 200, 200, 255);
    		r.draw(surface);
    		surface.fill(0);
    	}    
    	for(int i = 0; i<3; i++) {
    		surface.text("Level "+(i+1), surface.width/2, 3*surface.height/16+i*(surface.height/8));
    	}
    	surface.text("Endless", surface.width/2, (float)(surface.height*0.6125));
		surface.fill(255);
    	surface.text("Controls\nMove Right: D\n Move Left: A\n Shoot: W", surface.width/2, 3*surface.height/4);
    }
    
    /**
     * Checks if the mouse is pressed and switches the screen accordingly using switchScreen() depending on which rectangle has the mouse point inside it.
     */
    public void mousePressed() {
    	for(int i = 0; i<rects.size();i++) {
    		if(rects.get(i).isPointInside(surface.mouseX, surface.mouseY)) {
    			surface.switchScreen(i+2);
    		}
    	}
    }
}