package screens;

import core.DrawingSurface;
import java.awt.Point;
import java.awt.Rectangle;


import java.util.ArrayList;

import org.w3c.dom.css.Rect;

import processing.core.PApplet;
import processing.core.PShapeSVG.Gradient;

public class MenuScreen extends Screen{

    private DrawingSurface surface;

    private final int COLUMSIZE = 5, MARGIN = 25;
    private final int DIM = 60, GAP = 15, LEN = DIM+GAP;
    
    private Level[][] Menu = new Level[COLUMSIZE][COLUMSIZE];


    private int levels = 1;

    public MenuScreen(DrawingSurface surface) 
    {
        super(800, 600);

        this.surface = surface;
        //TODO Auto-generated constructor stub
    }
    
    public void setup()
    {
        System.out.println("@DEGUB");


        for(int r = 0; r!=COLUMSIZE; ++r)
        {
            for(int c = 0; c!=COLUMSIZE; ++c)
            {
                Menu[r][c] = new Level(c*LEN + MARGIN*2, r*LEN+MARGIN, DIM, DIM, "HOLA");
                System.out.println("f");
            }
        }
    }   

    public void draw()
    {
        surface.background(100);


        for(int r = 0; r!=COLUMSIZE; ++r)
            for(int c = 0; c!=COLUMSIZE; ++c)
            {
                Menu[r][c].display(surface);
            }

    }
}


class Level
{
    int x, y, w,h, textSize = 12;
    String text = "";
    int TEXTOFFSET = 10;
    boolean state, clickable = true, hasText = false;

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

    boolean clicked(Point p)
    {
        if(clickable)
        {
            return (new Rectangle(x,y,w,h).contains(p));
        }

        return clickable;
    }

    void display(DrawingSurface surface)
    {
        surface.rect(x,y,w,h);
        if(hasText)
        {
            surface.textSize(textSize);
            surface.fill(200);
            surface.text(text, x+TEXTOFFSET, y+TEXTOFFSET);    
        }
    }
}