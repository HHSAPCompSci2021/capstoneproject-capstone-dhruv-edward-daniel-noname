package utils;

import processing.core.PApplet;

import screens.ScreenSwitcher;
import core.DrawingSurface;
import java.util.*;


public class Vague
{
    private static char key = '0';
    public static char[][] grid;
    public static int chunkSize;
    public static Hashtable<String, Integer> db = new Hashtable<String, Integer>();

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
