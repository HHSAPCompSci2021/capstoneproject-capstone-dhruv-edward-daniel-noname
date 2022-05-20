package screens;


public interface ScreenSwitcher {
	public static final int START_SCREEN = 0;
	public static final int MENU_SCREEN = 1;
	public static final int GAME1_SCREEN = 2;
	public static final int GAME2_SCREEN = 3;
	public static final int GAME3_SCREEN = 4;
	public static final int RANDOMGAME_SCREEN = 5;
	public static final int GAMEOVER_SCREEN = 6;
	
	public void switchScreen(int i);
}
