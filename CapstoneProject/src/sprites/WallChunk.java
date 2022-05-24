package sprites;

/**
 * Represents the chunks of debris that occupy both Random and Normal Game maps
 */
public class WallChunk extends Sprite
{
	/**
	 * Creates a new Wallchunk with a specified width and height at a specified point
	 * @param x the x-coordinate of the top-left corner of this wallChunk
	 * @param y the y-coordinate of the top-left corner of this wallChunk
	 * @param w the width of this wallChunk
	 * @param h the height of this wallChunk
	 */
	public WallChunk(int x, int y, int w,  int h) 
	{
		super(x,y,w,h);

	}
}