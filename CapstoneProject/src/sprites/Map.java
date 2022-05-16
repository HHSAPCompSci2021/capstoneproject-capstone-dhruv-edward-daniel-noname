package sprites;

import java.awt.Point;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import processing.core.PImage;
import java.util.Scanner;
import dsharma578.shapes.Rectangle;

import java.awt.geom.Rectangle2D;
import processing.core.PApplet;

public class Map{
	private char[][] grid;
    private int y;
    private PImage wallChunk;
    private List<List<Sprite>> wall = new ArrayList<List<Sprite>>();
	
	public Map() 
    {
		grid = new char[166][3];
	}

    public void setup(PApplet marker)
    {
        wallChunk = marker.loadImage("CapstoneProject\\images\\wallchunk.png");
        makeSprites(marker);
    }

    public void scroll(int scrollSpeed)
    {
        for(int i=0; i<grid.length; i++)
        {
            for(int j=0; j<grid[i].length; j++)
            {
                wall.get(i).get(j).moveByAmount(0, scrollSpeed);
            }
        }
    }

	public Map(int lanes, int length, String filename,  int y) 
    {
		this.y = y;
		grid = new char[length][lanes];
		readData(filename, grid);

        //makeSprites();
	}
	
    public void makeSprites(PApplet marker)
    {
        int width =  marker.width/(grid[0].length);

        for(int i=0; i<grid.length; i++)
        {
            wall.add(new ArrayList<Sprite>());
            for(int j=0; j<grid[i].length; j++)
            {
                int x = width*j;
                int y = this.y+width*(i-grid.length);

                if(grid[i][j]=='O')
                {
                    wall.get(i).add(new Sprite(x, y, width, width));
                    wall.get(i).get(j).setFillColor(0, 0, 0, 255);
                }
                if(grid[i][j]=='X')
                {
                    wall.get(i).add(new Sprite(wallChunk, x, y, width, width));
                }
            }
        }
    }

    public List<List<Sprite>> getWallRects()
    {
        return wall;
    }

    public void draw(PApplet marker)
    {
        marker.fill(255);
        marker.noStroke();

        for(int i=0; i<grid.length; i++)
        {
            for(int j=0; j<grid[i].length; j++)
            {
                wall.get(i).get(j).draw(marker);
            }
        }

    }

	public void readData (String filename, char[][] gameData) 
	{
		File dataFile = new File(filename);

		if (dataFile.exists()) {
			int count = 0;

			FileReader reader = null;
			Scanner in = null;
			try {
					reader = new FileReader(dataFile);
					in = new Scanner(reader);
					
					while (in.hasNext()) {
						String line = in.nextLine();
						for(int i = 0; i < line.length(); i++)
							if (count < gameData.length && i < gameData[count].length)
								gameData[count][i] = line.charAt(i);

						count++;
					}

			} catch (IOException ex) {
				throw new IllegalArgumentException("Data file " + filename + " cannot be read.");
			} finally {
				if (in != null)
					in.close();
			}
			
		} else {
			throw new IllegalArgumentException("Data file " + filename + " does not exist.");
		}
	}
	
}