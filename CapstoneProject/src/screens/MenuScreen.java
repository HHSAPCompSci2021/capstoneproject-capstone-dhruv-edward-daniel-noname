package screens;

import core.DrawingSurface;
import java.awt.Point;
import java.awt.Rectangle;

import utils.Vague;

import processing.core.PApplet;
import processing.core.PShapeSVG.Gradient;

public class MenuScreen extends Screen{

    private DrawingSurface surface;

    // private final int COLUMSIZE = 5, MARGIN = 25;
    // private final int DIM = 60, GAP = 15, LEN = DIM+GAP;
    
    private final int COLUMSIZE = 4, ROWSIZE = 10, MARGIN = 25;
    private final int DIM = 60, GAP = 15, LEN = DIM+GAP;
	

	private Level[][] Menu = new Level[ROWSIZE][COLUMSIZE];


    private int levels = 7;

    public MenuScreen(DrawingSurface surface) 
    {
        super(400, 800);

        this.surface = surface;
        //TODO Auto-generated constructor stub
    }
    
    public void setup()
    {
        System.out.println("@DEGUB");


        for(int r = 0; r!=ROWSIZE; ++r)
        {
            for(int c = 0; c!=COLUMSIZE; ++c)
            {
                Menu[r][c] = new Level(c*LEN + MARGIN*2, r*LEN+MARGIN, DIM, DIM);
            }
        }



        for(int i = 0; i < levels; i++)
        {
            int c = i/COLUMSIZE;
            int r = i%COLUMSIZE;

            Menu[c][r].addText(Integer.toString(i+1));
            Menu[c][r].clickable = true;

        }
    }   

    public void draw()
    {
        surface.background(0100);

        for(int r = 0; r!=ROWSIZE; ++r)
            for(int c = 0; c!=COLUMSIZE; ++c)
            {
            
                Menu[r][c].display(surface);
            
            }
        surface.rect(0, 170, 400, 600);
        surface.fill(255);
        surface.text("Controls\nMOVE LEFT: <- Key\nMOVE RIGHT: -> key", 130, 300);
        surface.stroke(0);

    }

    public void mousePressed() {
		Point p = new Point(surface.mouseX,surface.mouseY);
        for(int r = 0; r!=ROWSIZE; ++r)
        {
            for(int c = 0; c!=COLUMSIZE; ++c)
            {
                if(Menu[r][c].clickable)
                {
					int levelID = Menu[r][c].clicked(p);
					if( levelID != -1)
                    {
						Vague.ScreenSwitchFromInt(surface, levelID+2);
                    }
                }
            }
        }

	}


}


class Level
{
    int x, y, w,h, textSize = 12;
    String text = "";
    int TEXTOFFSET_X = 10, TEXTOFFSET_Y = 20, RADIUS = 12;
    boolean state = false, clickable = false, hasText = false;

    Level(int xx, int yy, int ww, int hh, boolean clickablee)
    {
        x = xx;
        y = yy;
        w = ww;
        h = hh;
        clickable = clickablee;
    }

    Level(int xx, int yy, int ww, int hh)
    {
        x = xx;
        y = yy;
        w = ww;
        h = hh;

    }

    Level(int xx, int yy, int ww, int hh, String s)
    {
        x = xx;
        y = yy;
        w = ww;
        h = hh;

        clickable = true;
        addText(s);
    }


    void addText(String s, int size)
    {
        hasText = true;
        text = s;
        textSize = size;
    }

    void addText(String s)
    {
        hasText = true;
        text = s;
    }

    boolean toggle()
    {
        return state = !state;
    }

    int clicked(Point p)
    {
        if(clickable)
        {
            boolean bool = new Rectangle(x,y,w,h).contains(p); //error

            if(bool)
            {
                this.state = !this.state;
				return Integer.parseInt(text);

            }
        }

        return -1;
    }

    void display(DrawingSurface surface)
    {
        if(state)
        {
            surface.fill(64, 112, 224);
            surface.rect(x,y,w,h,RADIUS,RADIUS,RADIUS,RADIUS);

        }else
        {
            surface.fill(64, 112, 144);
            surface.rect(x,y,w,h,RADIUS,RADIUS,RADIUS,RADIUS);
        }

        if(hasText)
        {
            surface.textSize(textSize);
            surface.fill(144, 208, 240);
            surface.text(text, x+TEXTOFFSET_X, y+TEXTOFFSET_Y);
        }
    }
}