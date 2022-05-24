package sprites;

import java.awt.geom.Rectangle2D;
import processing.core.PApplet;
import processing.core.PImage;


/**
 * Represents a Game Sprite
 */
public class Sprite extends Rectangle2D.Double {
	
	// FIELDS
	private PImage image;
	private boolean noImage;
	private int r=100, g = 100, b=100, a = 255, id = -1;
	
	// CONSTRUCTORS
	/**
	 * Creates a Sprite with position (x,y) and dimensions width x height
	 * @param x the x-coordinate of the Sprite
	 * @param y the y-coordinate of the Sprite
	 * @param w the width of the Sprite
	 * @param h the height of the Sprite
	 */
	public Sprite(int x, int y, int w, int h) 
	{
		this(null, x, y, w, h);
		noImage = true;
	}
	
	/**
	 * Creates a Sprite with position (x,y) and dimensions width x height and an image representing it
	 * @param img the PImage representing the Sprite
	 * @param x the x-coordinate of the Sprite
	 * @param y the y-coordinate of the Sprite
	 * @param w the width of the Sprite
	 * @param h the height of the Sprite
	 */
	public Sprite(PImage img, int x, int y, int w, int h) {
		super(x,y,w,h);
		image = img;
		noImage = false;

	}
	
	/**
	 * Creates a Sprite with position (x,y) and dimensions width x height and an image representing it with an identifying integer id
	 * @param img the PImage representing the Sprite
	 * @param x the x-coordinate of the Sprite
	 * @param y the y-coordinate of the Sprite
	 * @param w the width of the Sprite
	 * @param h the height of the Sprite
	 * @param id an integer that can serve to identify what type of Sprite this is
	 */
	public Sprite(PImage img, int x, int y, int w, int h, int id) {
		super(x,y,w,h);
		image = img;
		this.id = id;
		noImage = false;
	}
	
	/**
	 * Creates a Sprite with position (x,y) and dimensions width x height with an identifying integer id
	 * @param x the x-coordinate of the Sprite
	 * @param y the y-coordinate of the Sprite
	 * @param w the width of the Sprite
	 * @param h the height of the Sprite
	 * @param id an integer that can serve to identify what type of Sprite this is
	 */
	public Sprite(int x, int y, int w, int h, int id) {
		super(x,y,w,h);
		this.id = id;
		noImage = false;

	}

	/**
	 * Changes the image of this sprite to a given PImage and sets the boolean noImage to false
	 * @param img the PImage that this Sprite's image will be changed to
	 */
	public void changeImage(PImage img)
	{
		noImage = false;
		image = img;
	}
	
	/**
	 * Returns a boolean representing whether or not this Sprite has an image
	 * @return a boolean true if there is an Image and false if this Sprite has no image
	 */
	public boolean hasImage(){return !noImage;}
	
	/**
	 * Returns the x-coordinate of the top left corner of the Rectangle representing this Sprite(its hitbox)
	 * @return the x-coordinate of the top left corner of the Rectangle representing this Sprite(its hitbox)
	 */
	public int getRX(){return (int)super.x;}
	
	/**
	 * Returns the y-coordinate of the top left corner of the Rectangle representing this Sprite(its hitbox)
	 * @return the y-coordinate of the top left corner of the Rectangle representing this Sprite(its hitbox)
	 */
	public int getRY(){return (int)super.y; }
	
	/**
	 * Returns the width of the Rectangle representing this Sprite(its hitbox)
	 * @return the width of the Rectangle representing this Sprite(its hitbox)
	 */
	public int getRW(){return (int)super.width;}
	
	/**
	 * Returns the height of the Rectangle representing this Sprite(its hitbox)
	 * @return the height of the Rectangle representing this Sprite(its hitbox)
	 */
	public int getRH(){return (int)super.height;}
	
	/**
	 * Returns the integer id of this Sprite
	 * @return the integer id of this Sprite
	 */
	public int getId(){return id;}
	
	/**
	 * Moves the top left corner of this sprite to a specified point
	 * @param x the x-coordinate of where the top left corner of this Sprite will be moved
	 * @param y the y-coordinate of where the top left corner of this Sprite will be moved
	 */
	public void moveToLocation(double x, double y) {
		super.x = x;
		super.y = y;
	}

	/**
	 * Determines if this sprite's hitbox Rectangle intersects with another Sprite's
	 * @param other the other Sprite that will be examined to be intersecting or not
	 * @return a boolean value for whether or not the two Sprite's hitboxes are intersecting
	 */
	public boolean intersects(Sprite other)
	{
		return super.intersects(other);
	}
	
	/**
	 * Moves this sprite by a specified amount in the x and y directions
	 * @param x the amount this Sprite should be shifted in the x direction, in pixels
	 * @param y the amount this Sprite should be shifted in the y direction, in pixels
	 */
	public void moveByAmount(double x, double y) {
		super.x += x;
		super.y += y;
	}
	
	/**
	 * 
	 * @param windowWidth
	 * @param windowHeight
	 */
	public void applyWindowLimits(int windowWidth, int windowHeight) {
		x = Math.min(x,windowWidth-width);
		y = Math.min(y,windowHeight-height);
		x = Math.max(0,x);
		y = Math.max(0,y);
	}
	
	/**
	 * Sets the fill color of this Sprite
	 * @param r The amount of red that will comprise the fill color
	 * @param g The amount of green that will comprise the fill color
	 * @param b The amount of blue that will comprise the fill color
	 * @param a The transparency of the fill color, where 0 is transparent and 255 is opaque
	 * @pre all paramters must be between 0 and 255, inclusive
	 */
	public void setFillColor(int r, int g, int b, int a)
	{
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}

	/**
	 * Draws this Sprite onto a PApplet
	 * @param g the PApplet this Sprite will be drawn on
	 */
	public void draw(PApplet g) {
		if (image != null){
			g.image(image,(float)x,(float)y,(float)width,(float)height);
		}else {
			
			g.fill(r, this.g, b, a);
		}
	}
	
	
}