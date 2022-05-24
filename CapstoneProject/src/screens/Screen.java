package screens;

/**
 * Represents the basic Screen that all other Screens build off of
 */
public abstract class Screen {

	/**
	 * The width of the Screen
	 */
	public final int DRAWING_WIDTH;
	/**
	 * the height of the Screen
	 */
	public final int DRAWING_HEIGHT;
	
	/**
	 * Creates a new Screen with the specified width and height, and initializes the Drawing width and height fields to those dimensions
	 * @param width the width of the Screen, in pixels
	 * @param height the height of the Screen, in pixels
	 * @pre both width and height must be positive integers
	 */
	public Screen(int width, int height) {
		this.DRAWING_WIDTH = width;
		this.DRAWING_HEIGHT = height;
	}
	
	/**
	 * Sets up this Screen
	 */
	public void setup() {
		
	}
	
	/**
	 * Draws this screen
	 */
	public void draw() {
		
	}
	
	/**
	 * Determines what action should be taken when the mouse is pressed
	 */
	public void mousePressed() {
		
	}
	
}
