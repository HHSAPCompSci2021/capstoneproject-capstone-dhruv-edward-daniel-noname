package sprites;


import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import processing.core.PImage;
import screens.Screen;

import java.util.Scanner;
import utils.Vague;

import processing.core.PApplet;

public class RandomMap
{
	private int[][] grid;
    private int currentRow = 0, chunkSize, scrollDistance = 0, cols = 11;
    private PImage wallChunk;
    private List<List<Sprite>> wall = new ArrayList<List<Sprite>>();

    private PApplet surface;
	public final static String fileSeparator = System.getProperty("file.separator");

    
	public RandomMap(PApplet surface) 
    {

        this.surface = surface;
        grid = new int[/*(surface.height/chunkSize + 1)*2*/100][cols];
        chunkSize = surface.width/grid[0].length;
        
        this.generateInitialGrid();
	}

	
    public void generateInitialGrid()
    {
        int empty[] = {0,0,0,0,0,0,0,0,0,0,0};

        int obrow = Vague.randomIntFromRange(2, 5);

        for(int r = 0; r<grid.length; r++)
        {
            
            if(r == obrow)
            {
                grid[r] = generateObstacle();
                obrow+=Vague.randomIntFromRange(4, 6);
            }else
            {
                
                grid[r] = empty;
            }

        }
    }

    public int[] generateObstacle()
    {
        int size = Vague.randomIntFromRange(2, 5);
        int pos = Vague.randomIntFromRange(0, cols-size), max = pos+size;


        int r[] = new int[cols];

        for(int i=0; i<r.length; i++)
        {
            if(i<pos || i > max)
            {
                r[i] = 0;
            }else
            {
                r[i] = 1;
            }
        }

        return r;

    }

    public void setup()
    {
        wallChunk = surface.loadImage("images"+fileSeparator+"wallchunk.png");
        makeSprites();
    }

    public void scroll(int scrollSpeed)
    {
        scrollDistance += scrollSpeed;
        currentRow = scrollDistance/chunkSize;
//        System.out.println(currentRow);
        for(int i=0; i<grid.length; i++)
        {
            for(int j=0; j<grid[i].length; j++)
            {
                wall.get(i).get(j).moveByAmount(0, scrollSpeed);
            }
        }
        
        if(scrollDistance%chunkSize == 0)
        {
            addNewRow();
        }
    }

    public void addNewRow()
    {
        wall.add(new ArrayList<Sprite>());


        int prob = Vague.randomIntFromRange(1, 6);
        int newRow[], empty[] = {0,0,0,0,0,0,0,0,0,0,0};

        if(prob== 1)
        {
            newRow = generateObstacle();
        }else
        {
            newRow = empty;
        }

        int i = wall.size()-2;

        for(int j=0; j < grid[j].length; j++)
        {
            if(newRow[j]==1)
            {
                wall.get(i+1).add(new Sprite(wallChunk, chunkSize*j, wall.get(i).get(0).getRY()-chunkSize, chunkSize, chunkSize, 1));
                continue;
            }

            if(newRow[j]==0)
            {

                wall.get(i+1).add(new Sprite(chunkSize*j, wall.get(i).get(0).getRY()-chunkSize, chunkSize, chunkSize));
                wall.get(i+1).get(j).setFillColor(0, 0, 0, 255);
                continue;
            }
        }
        wall.remove(0);
    }



    public void makeSprites()
    {
        int width = chunkSize;
        Vague.dT(grid.length);

        for(int i=0; i<grid.length; i++)
        {
            wall.add(new ArrayList<Sprite>());

            for(int j=0; j<grid[i].length; j++)
            {
                int x = width*j;
                int y = (surface.height-i*width);
                
                if(grid[i][j]==1)
                {
                    wall.get(i).add(new Sprite(wallChunk, x, y, width, width, 1));
                    continue;
                }

                if(grid[i][j]==0)
                {

                    wall.get(i).add(new Sprite(x, y, width, width));
                    wall.get(i).get(j).setFillColor(0, 0, 0, 255);
                }

            }
        }
    }

    // public List<List<Sprite>> getWallRects()
    // {
    //     return wall;
    // }
    
    // public char[][] getGrid() {
    // 	return grid;
    // }

    public void draw()
    {
        surface.fill(255);
        surface.noStroke();
        //surface.rect(100, 100, 100, 100);

        // Sprite x = new Sprite(wallChunk, 100, 100, 100, 100);
        // x.draw(surface);
        for(int i=0; i<wall.size(); i++)
        {
            for(int j=0; j<grid[i].length; j++)
            {
                wall.get(i).get(j).draw(surface);
            }
        }

    }


    public List<List<Sprite>> getWallRects() {
        return wall;
    }


    public int[][] getGrid() {
        return grid;
    }

	
}
