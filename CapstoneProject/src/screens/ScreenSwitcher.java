package screens;


public interface ScreenSwitcher {
	public static final int START_SCREEN = 0;
	public static final int MENU_SCREEN = 1;
	public static final int GAME_LEVEL_1 = 2;
	public static final int GAME_LEVEL_2 = 3;
	public static final int GAME_LEVEL_3 = 4;

	public static final int GAMEOVER_SCREEN = 5;
	
	public void switchScreen(int i);
}
