package screens;

import core.DrawingSurface;
import java.awt.Point;
import java.util.ArrayList;

import javax.management.relation.RelationTypeSupport;
import dsharma578.shapes.Rectangle;
import processing.core.PApplet;
import processing.core.PImage;
import sprites.Sprite;



public class GameOverScreen extends Screen{
	
	private PApplet surface;
	private PImage background;
	private Sprite ggs;
	public final static String fileSeparator = System.getProperty("file.separator");
	ArrayList<Rectangle> rects = new ArrayList<Rectangle>();

	

	public GameOverScreen(PApplet surface) {
		super(400, 800);
		this.surface = surface;
		rects.add(new Rectangle(surface.width/8,400,surface.width-100,100));
	}
	
	public void setup() {
		background=surface.loadImage("images" + fileSeparator + "GameOver.PNG");
		ggs = new Sprite(background, 100, 200, 200, 100);
	}
	
	
	public void draw() 
	{
		surface.background(0);
		ggs.draw(surface);
	}
	
	public void mousePressed() {
		for(int i = 0; i<rects.size();i++) {
    		if(rects.get(i).isPointInside(surface.mouseX, surface.mouseY)) {
    			surface.switchScreen(i+2);
    		}
    	}
	}

}
