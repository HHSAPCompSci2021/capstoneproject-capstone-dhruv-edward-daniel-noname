package sprites;

import java.awt.Point;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import dsharma578.shapes.Rectangle;

import java.awt.geom.Rectangle2D;
import processing.core.PApplet;

public class WallChunk extends Sprite
{

	public WallChunk(int x, int y, int w,  int h) 
	{
		super(x,y,w,h);

	}
	
	
	// public void draw(PApplet marker) 
	// {
	// 	marker.fill(255);
	// 	marker.noStroke();

	// 	float rectWidth = marker.width/(grid[0].length);
	// 	int startY= y;

	// 	for(int i = 0; i<grid.length; i++) 
	// 	{
	// 		for(int j = 0; j<grid[i].length;j++) 
	// 		{

	// 			float rectX = rectWidth*j;
	// 			float rectY = startY+rectWidth*(i-grid.length);

	// 			Rectangle2D r = new Rectangle2D(null, (int)rectX, (int)rectY,(int)rectWidth, (int)rectWidth);
	// 			wallRectangles.add(new Rectangle2D(rectX, rectY, rectWidth, rectWidth));
	// 			if(grid[i][j]=='O') {r.setFillColor(0, 0, 0, 255);}
	// 			if(grid[i][j]=='X') {r.setFillColor(90,20,20,255);}

	// 			r.draw(marker);
	// 		}	
	// 	}
	// }
	
	
}