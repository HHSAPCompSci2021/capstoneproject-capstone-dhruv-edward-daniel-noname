package utils;

import processing.core.PApplet;
import processing.core.PImage;
import screens.ScreenSwitcher;
import core.DrawingSurface;
import java.util.*;


public class Vague
{
    private static char key = '0';
    public static char[][] grid;
    public static int chunkSize;
    public static Hashtable<String, Integer> db = new Hashtable<String, Integer>();

	public static final int STANDARD_WALL = 1;
	public static final int COIN_GOLD = 2;
    public static final int AMMO_CRATE = 3;
	public static final int HEALTH_PACKAGE = 4;
	public static final int BLACK_HOLE = 5;

    public static boolean IN_BLACK_HOLE = false;

    public static PImage destroyed;
    public static PImage barrier;
    public static PImage ammocrate;
    public static PImage healthpackage;
    public static PImage starShip, blackhole;
    public static PImage fire, bullet;

    public Vague()
    {

    }

    public static void KeyEvents(char newKey)
    {
        key  = newKey;
    }

    public static int randomIntFromRange(int min, int max) {
        return min + (int) (Math.random() * ((max - min)));
    }

    public static char getKey()
    {
        char out = key;
        key = '0';
        return out;
    }
    
    public static void ScreenSwitchFromInt(DrawingSurface surface, int screen)
    {
        switch (screen) {
            case 1:
                surface.switchScreen(ScreenSwitcher.START_SCREEN);
                break;
            case 2:
                surface.switchScreen(ScreenSwitcher.GAME_SCREEN);
                break;
            default:
                break;
        }

    }

    public static void printIntArray(int[] a)
    {
        for(int i=0; i<a.length; i++)
        {
            System.out.print(a[i]+" ");
        }
        System.out.println("");
    }

    public static void print2dIntArray(int[][] a)
    {
        for(int i=0; i<a.length; i++)
        {
            printIntArray(a[i]);
        }
    }

    public static void dT(int c)
    {
        System.out.println("@Debug "+c);
    }
}
