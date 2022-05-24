package sprites;


import java.awt.Point;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import processing.core.PImage;
import screens.Screen;

import java.util.Scanner;

import core.DrawingSurface;
import utils.Vague;

import processing.core.PApplet;
/**
 * Represents the map that contains level data for the premade level Games
 */
public class Map
{
	private char[][] grid;
    private int y, mapLength,mapWidth;
    private PImage wallChunk, clearLine;
    private List<List<Sprite>> wall = new ArrayList<List<Sprite>>();

    /**
     * initializes images for debris walls/clear lines and loads these Sprites onto a PApplet
     * @param marker the PApplet where the Sprites will be drawn
     */
    public void setup(PApplet marker)
    {
        wallChunk = marker.loadImage("images"+DrawingSurface.fileSeparator+"wallchunk.png");
        clearLine = marker.loadImage("images"+DrawingSurface.fileSeparator+"clearLine.png");
        makeSprites(marker);
    }

    /**
     * Scrolls the entire map downward by the specified number of pixels per frame
     * @param scrollSpeed the speed, in pixels per frame, that the map will scroll down by
     */
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

    /**
     * Initializes a 2D character array with information representing the level map from a text file
     * @param filename the name and file path of the .txt file containing the map data
     * @pre filename must use a valid file path with the correct file Separators and should refer to a .txt file
     */
	public Map(String filename) 
    {
		y=0;
		setDimensions(filename);

		grid = new char[mapLength][mapWidth];
        Vague.grid = grid;
		readData(filename, grid);
	}
	
	/**
	 * Adds Sprites representing the walls and clear lines of the map to a List with their position and image data
	 * @param marker the marker upon which these Sprites will be drawn
	 */
    public void makeSprites(PApplet marker)
    {
        int width =  marker.width/(grid[0].length);
        Vague.db.put("map width: ", width);

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
                    wall.get(i).add(new Sprite(wallChunk, x, y, width, width, 1));
                }
                if(grid[i][j]=='C') {
                	wall.get(i).add(new Sprite(clearLine, x, y, width, width, 0));
                }
            }
        }
    }
    
    /**
     * Returns a List of all Sprites comprising the map
     * @return a List of all Sprites that comprise the map
     */
    public List<List<Sprite>> getWallRects()
    {
        return wall;
    }
    
    /**
     * Returns a 2D character array read from a text file that represents the Game map
     * @return a 2d character array read from a text file that represents the Game map
     */
    public char[][] getGrid() {
    	return grid;
    }

    /**
     * Draws the map onto a PApplet
     * @param marker the PApplet upon which the map will be drawn
     */
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

    /**
     * Reads data from a text file and converts that information to a 2D character array that will represent the map
     * @param filename the text file that holds the map information
     * @param gameData the 2D character array that will be filled with map information from the text file
     */
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
						for(int i = 0; i < line.length(); i++) {
							if (count < gameData.length && i < gameData[count].length)
								gameData[count][i] = line.charAt(i);
						}
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
	
	/**
	 * Correctly sets the dimensions of the 2D character array that will represent the level's map
	 * @param file the file that will be read to determine its dimensions and give the 2D array the correct dimensions
	 */
	public void setDimensions(String file) {
		try {
			mapLength = (int)Files.lines(Paths.get(file)).count();
			Scanner in = new Scanner(new FileReader(new File(file)));
			mapWidth = in.nextLine().length();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
