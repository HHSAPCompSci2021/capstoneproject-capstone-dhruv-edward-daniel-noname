package sprites;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import processing.core.PImage;
import screens.Screen;

import java.util.Scanner;

import core.DrawingSurface;
import utils.UserData;
import utils.Vague;

import processing.core.PApplet;

/**
 * Represents a map that is infinite and randomly generated
 *
 */
public class RandomMap
{
	private int[][] grid;
    private int currentRow = 0, chunkSize, scrollDistance = 0, cols = 11;
    private PImage goldCoin;
    private List<List<Sprite>> wall = new ArrayList<List<Sprite>>();

    private DrawingSurface surface;

    /**
     * Creates a new randomly generated map on the DrawingSurface given
     * @param surface the DrawingSurface upon which the infinite random map will be generated
     */
	public RandomMap(DrawingSurface surface) 
    {

        this.surface = surface;
        grid = new int[100][11];

        chunkSize = surface.width/grid[0].length;

        Vague.chunkSize = chunkSize;
        
        this.generateInitialGrid();
	}

	/**
	 * Creates an initial grid layout that the player will begin at
	 */
    public void generateInitialGrid()
    {

        int obrow = Vague.randomIntFromRange(2, 5);

        for(int r = 0; r<grid.length; r++)
        {
            int empty[] = {0,0,0,0,0,0,0,0,0,0,0};   
            if(r == obrow)
            {
                grid[r] = generateObstacle();
                obrow+=Vague.randomIntFromRange(4, 6);
            }else
            {
                
                grid[r] = empty;
            }

        }


        int coina = Vague.randomIntFromRange(1, 7);

        for(int i=0; i<coina; i++)
        {
            int coinr = Vague.randomIntFromRange(0, grid.length-1);
            int coinc = Vague.randomIntFromRange(0, grid[0].length-1);

            grid[coinr][coinc] = 2;
        }



    }

    /**
     * Sets the defined wallChunks of this map to the same as those specified in wallBlocks
     * @param wallBlocks the List containing the WallBlocks that will be copied to this map
     */
    public void setWall(List<List<Sprite>> wallBlocks)
    {
        wall = wallBlocks;
    }

    /**
     * Generates an obstacle between 1 and 5 blocks in length
     * @return 
     */
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

    /**
     * initializes the Sprites of this map and the Coin's image
     */
    public void setup()
    {
        goldCoin = surface.loadImage("images"+DrawingSurface.fileSeparator+"coins-images"+DrawingSurface.fileSeparator+"Gold"+DrawingSurface.fileSeparator+"gold_coin_hexagon_1.png");

        makeSprites();
    }

    /**
     * Scrolls the entire map downwards at the specified speed and adds more rows of map accordingly
     * @param scrollSpeed the speed, in pixels per frame, that the map will scroll down at
     */
    public void scroll(int scrollSpeed)
    {
        scrollDistance += scrollSpeed;
        currentRow = scrollDistance/chunkSize;
        UserData.distanceTraveled = currentRow*chunkSize;
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

    /**
     * Adds a new row to the Random Map containing a random mix of obstacles, coins, and other pickups based on probabilities
     */
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

        //coin
        int prob2 = Vague.randomIntFromRange(1, 15);
        if(prob2 == 1)
            newRow[Vague.randomIntFromRange(0, 10)] = Vague.COIN_GOLD;

        //ammo crate
        int prob3 = Vague.randomIntFromRange(1, 69+21*2);
        if(prob3 == 1)
            newRow[Vague.randomIntFromRange(0, 10)] = Vague.AMMO_CRATE;

        //healf
        int prob4 = Vague.randomIntFromRange(1, 69+21*7);
        if(prob4 == 1)
            newRow[Vague.randomIntFromRange(0, 10)] = Vague.HEALTH_PACKAGE;
        
        //Vague.BLACK_HOLE
        int prob5 = Vague.randomIntFromRange(1, 30);
        if(prob5 == 1)
            newRow[Vague.randomIntFromRange(0, 10)] = Vague.BLACK_HOLE;


        int i = wall.size()-2;

        for(int j=0; j < grid[j].length; j++)
        {
            switch(newRow[j])
            {
                case Vague.STANDARD_WALL:
                    wall.get(i+1).add(new Sprite(Vague.barrier, chunkSize*j, wall.get(i).get(0).getRY()-chunkSize, chunkSize, chunkSize, 1));
                    break;
                case Vague.COIN_GOLD:
                    wall.get(i+1).add(new Coins(surface, goldCoin,chunkSize*j, wall.get(i).get(0).getRY()-chunkSize, 100));
                    break;
                case Vague.AMMO_CRATE:
                    wall.get(i+1).add(new AmmoCrate(surface,chunkSize*j, wall.get(i).get(0).getRY()-chunkSize));
                    break;
                case Vague.HEALTH_PACKAGE:
                    wall.get(i+1).add(new HealthPackage(surface, chunkSize*j, wall.get(i).get(0).getRY()-chunkSize));
                    break;
//                case Vague.BLACK_HOLE:
//                    wall.get(i+1).add(new BlackHole(surface, chunkSize*j, wall.get(i).get(0).getRY()-chunkSize));
//                    break;
                default:
                    wall.get(i+1).add(new Sprite(chunkSize*j, wall.get(i).get(0).getRY()-chunkSize, chunkSize, chunkSize));
                    wall.get(i+1).get(j).setFillColor(0, 0, 0, 255);
            }
        }
        wall.remove(0);
    }

	
	/**
	 * Initializes Sprites with the correct images and positions and adds them to a List of Sprites that represents the Random Map
	 */
    public void makeSprites()
    {
        int width = chunkSize;

        for(int i=0; i<grid.length; i++)
        {
            wall.add(new ArrayList<Sprite>());

            for(int j=0; j<grid[i].length; j++)
            {
                int x = width*j;
                int y = (surface.height-i*width);
                
                switch(grid[i][j])
                {
                    case 1:
                        wall.get(i).add(new Sprite(Vague.barrier, x, y, width, width, 1));
                        break;
                    case 2:
                        wall.get(i).add(new Coins(surface, goldCoin,x, y, 100));
                        break;
                    default:
                        wall.get(i).add(new Sprite(x, y, width, width));
                        wall.get(i).get(j).setFillColor(0, 0, 0, 255);
                }

                


            }
        }
    }
    
    /**
     * Draws the Random Map onto the PApplet
     */
    public void draw()
    {
        surface.fill(255);
        surface.noStroke();
        for(int i=0; i<wall.size(); i++)
        {
            for(int j=0; j<grid[i].length; j++)
            {
                wall.get(i).get(j).draw(surface);
            }
        }
    }
	
	/**
	 * Returns a List of Sprites containing the Sprites that comprise the grid
	 * @return
	 */
    public List<List<Sprite>> getWallRects() {
        return wall;
    }

    /**
     * Returns a 2D character array representing the Random Map grid
     * @return a 2D character array representing the Random Map grid
     */
    public int[][] getGrid() {
        return grid;
    }

	
}