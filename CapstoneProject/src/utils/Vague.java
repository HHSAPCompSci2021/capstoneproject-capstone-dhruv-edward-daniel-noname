package utils;

import processing.core.PApplet;

import screens.ScreenSwitcher;
import core.DrawingSurface;



public class Vague
{
    private static char key = '0';

    public Vague()
    {

    }

    public static void KeyEvents(char newKey)
    {
        key  = newKey;
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
                surface.switchScreen(ScreenSwitcher.MENU_SCREEN);
                break;
            case 3:
                surface.switchScreen(ScreenSwitcher.GAME_SCREEN_1);
                break;
            default:
                break;
        }

    }
    
}
