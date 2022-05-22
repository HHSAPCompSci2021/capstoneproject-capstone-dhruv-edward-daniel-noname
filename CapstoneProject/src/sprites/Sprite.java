package sprites;

import java.awt.geom.Rectangle2D;
import processing.core.PApplet;
import processing.core.PImage;


 
public class Sprite extends Rectangle2D.Double {
	
	// FIELDS
	private PImage image;
	private boolean noImage;
	private int r=100, g = 100, b=100, a = 255, id = -1;
	
	// CONSTRUCTORS
	
	public Sprite(int x, int y, int w, int h) 
	{
		this(null, x, y, w, h);
		noImage = true;
	}
	
	public Sprite(PImage img, int x, int y, int w, int h) {
		super(x,y,w,h);
		image = img;
		noImage = false;

	}

	public Sprite(PImage img, int x, int y, int w, int h, int id) {
		super(x,y,w,h);
		image = img;
		this.id = id;
		noImage = false;

	}
	public Sprite(int x, int y, int w, int h, int id) {
		super(x,y,w,h);
		this.id = id;
		noImage = false;

	}

	public void changeImage(PImage img)
	{
		noImage = false;
		image = img;
	}
	
	public boolean hasImage(){return !noImage;}
	public int getRX(){return (int)super.x;}
	public int getRY(){return (int)super.y; }
	public int getRW(){return (int)super.width;}
	public int getRH(){return (int)super.height;}
	public int getId(){return id;}
	
	// METHODS	
	public void moveToLocation(double x, double y) {
		super.x = x;
		super.y = y;
	}

	public boolean intersects(Sprite other)
	{
		return super.intersects(other);
	}
	
	public void moveByAmount(double x, double y) {
		super.x += x;
		super.y += y;
	}
	
	public void applyWindowLimits(int windowWidth, int windowHeight) {
		x = Math.min(x,windowWidth-width);
		y = Math.min(y,windowHeight-height);
		x = Math.max(0,x);
		y = Math.max(0,y);
	}
	
	public  void setFillColor(int r, int g, int b, int a)
	{
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}

	public void draw(PApplet g) {
		if (image != null){
			g.stroke(204, 102, 0);
			g.rect((float)x,(float)y,(float)width,(float)height);
			g.image(image,(float)x,(float)y,(float)width,(float)height);
		}else {
			
			g.fill(r, this.g, b, a);
			g.stroke(204, 102, 0);
			g.rect((float)x,(float)y,(float)width,(float)height);
		}
	}
	
	
}