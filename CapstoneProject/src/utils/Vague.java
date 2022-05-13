package utils;

import processing.core.PApplet;

import screens.ScreenSwitcher;
import core.DrawingSurface;



public class Vague
{
    public Vague()
    {

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
