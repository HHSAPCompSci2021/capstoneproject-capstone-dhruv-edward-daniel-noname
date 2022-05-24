package screens;

/**
 * An interface that can switch screens given an integer or defined integer constant
 */
public interface ScreenSwitcher {
	/**
	 * integer 0 represents the Start Screen
	 */
	public static final int START_SCREEN = 0;
	
	/**
	 * integer 1 represents the Menu Screen
	 */
	public static final int MENU_SCREEN = 1;
	
	/**
	 * integer 2 represents the Screen for Level 1 of the game
	 */
	public static final int GAME1_SCREEN = 2;
	
	/**
	 * integer 3 represents the Screen for Level 2 of the game
	 */
	public static final int GAME2_SCREEN = 3;
	
	/**
	 * integer 4 represents the Screen for Level 3 of the game
	 */
	public static final int GAME3_SCREEN = 4;
	
	/**
	 * integer 5 represents the infinitely generated Random Game Screen
	 */
	public static final int RANDOMGAME_SCREEN = 5;
	
	/**
	 * integer 6 represents the Game Over Screen
	 */
	public static final int GAMEOVER_SCREEN = 6;
	
	/**
	 * Switches the screen to the constant defined by i
	 * @param i the screen that will be switched to
	 * @pre i must be between 0 and 6, both inclusive
	 */
	public void switchScreen(int i);
}
