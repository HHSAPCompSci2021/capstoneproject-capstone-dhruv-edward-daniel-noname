package sprites;


import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import processing.core.PImage;
import screens.Screen;

import java.util.Scanner;

import utils.UserData;
import utils.Vague;

import processing.core.PApplet;

public class Map
{
	private int[][] grid;
    private int currentRow = 0, chunkSize, scrollDistance = 0, cols = 11;
    private PImage[] goldCoinAnimation;
    private List<List<Sprite>> wall = new ArrayList<List<Sprite>>();

    private PApplet surface;
	public final static String fileSeparator = System.getProperty("file.separator");

    
	public Map(PApplet surface) 
    {

        this.surface = surface;
        grid = new int[/*(surface.height/chunkSize + 1)*2*/100][11];

        chunkSize = surface.width/grid[0].length;

        Vague.chunkSize = chunkSize;
        
        this.generateInitialGrid();
	}

	
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

    public void setWall(List<List<Sprite>> wallBlocks)
    {
        wall = wallBlocks;
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
        goldCoinAnimation = new PImage[6];
        
        goldCoinAnimation[0] = surface.loadImage("images"+fileSeparator+"coins-images"+fileSeparator+"Gold"+fileSeparator+"gold_coin_hexagon_1.png");
        goldCoinAnimation[1] = surface.loadImage("images"+fileSeparator+"coins-images"+fileSeparator+"Gold"+fileSeparator+"gold_coin_hexagon_2.png");
        goldCoinAnimation[2] = surface.loadImage("images"+fileSeparator+"coins-images"+fileSeparator+"Gold"+fileSeparator+"gold_coin_hexagon_3.png");
        goldCoinAnimation[3] = surface.loadImage("images"+fileSeparator+"coins-images"+fileSeparator+"Gold"+fileSeparator+"gold_coin_hexagon_4.png");
        goldCoinAnimation[4] = surface.loadImage("images"+fileSeparator+"coins-images"+fileSeparator+"Gold"+fileSeparator+"gold_coin_hexagon_5.png");
        goldCoinAnimation[5] = surface.loadImage("images"+fileSeparator+"coins-images"+fileSeparator+"Gold"+fileSeparator+"gold_coin_hexagon_6.png");

        //goldCoin = new Coins(surface, goldCoinAnimation, 100, 100, 100);

        makeSprites();
    }

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
                    wall.get(i+1).add(new Coins(surface, goldCoinAnimation,chunkSize*j, wall.get(i).get(0).getRY()-chunkSize, 100));
                    break;
                case Vague.AMMO_CRATE:
                    wall.get(i+1).add(new AmmoCrate(surface,chunkSize*j, wall.get(i).get(0).getRY()-chunkSize));
                    break;
                case Vague.HEALTH_PACKAGE:
                    wall.get(i+1).add(new HealthPackage(surface, chunkSize*j, wall.get(i).get(0).getRY()-chunkSize));
                    break;
                case Vague.BLACK_HOLE:
                    wall.get(i+1).add(new BlackHole(surface, chunkSize*j, wall.get(i).get(0).getRY()-chunkSize));
                    break;
                default:
                    wall.get(i+1).add(new Sprite(chunkSize*j, wall.get(i).get(0).getRY()-chunkSize, chunkSize, chunkSize));
                    wall.get(i+1).get(j).setFillColor(0, 0, 0, 255);
            }
            // if(newRow[j] == 2)
            // {

            // }
        }
        wall.remove(0);
    }



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
                        wall.get(i).add(new Coins(surface, goldCoinAnimation,x, y, 100));
                        break;
                    default:
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

        // Sprite x = new Sprite(Vague.barrier, 100, 100, 100, 100);
        // x.draw(surface);
        for(int i=0; i<wall.size(); i++)
        {
            for(int j=0; j<grid[i].length; j++)
            {
                wall.get(i).get(j).draw(surface);
            }
        }

        //goldCoin.draw();


    }


    public List<List<Sprite>> getWallRects() {
        return wall;
    }


    public int[][] getGrid() {
        return grid;
    }

	
}
