package sprites;

import java.awt.Point;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import processing.core.PApplet;

public class Map {
	private char[][] grid;
	private int x,y;
	
	public Map() {
		grid = new char[10][3];
	}
	
	public Map(int lanes, int length, String filename, int x, int y) {
		this.x = x;
		this.y = y;
		grid = new char[length][lanes];
		readData(filename,grid);
	}
	
	public char[][] getGrid() {
		return grid;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void draw(PApplet marker, float x, float y, float width, float height) {
		marker.fill(255);
		float rectWidth = width/(grid[0].length);
		float rectHeight = height/(grid.length);
		
		for(int i = 0; i<grid.length; i++) {
			for(int j = 0; j<grid[i].length;j++) {
				float rectX = x + rectWidth*j;
				float rectY = y+rectHeight*i;
				if(grid[i][j]=='O') { //filled space(paintcan)
					marker.fill(100,180,220);
				}
				if(grid[i][j]=='X') { //empty space(paintcan)
					marker.fill(255);
				}
				marker.rect(rectX, rectY, rectWidth, rectHeight);
				marker.stroke(0);
				}	
			}
	}
	
	public void readData (String filename, char[][] gameData) {
		File dataFile = new File(filename);

		if (dataFile.exists()) {
			int count = 0;

			FileReader reader = null;
			Scanner in = null;
			try {
					reader = new FileReader(dataFile);
					in = new Scanner(reader);
					
					while (in.hasNext()) {
						String line = in.nextLine();
						for(int i = 0; i < line.length(); i++)
							if (count < gameData.length && i < gameData[count].length)
								gameData[count][i] = line.charAt(i);

						count++;
					}

			} catch (IOException ex) {
				throw new IllegalArgumentException("Data file " + filename + " cannot be read.");
			} finally {
				if (in != null)
					in.close();
			}
			
		} else {
			throw new IllegalArgumentException("Data file " + filename + " does not exist.");
		}
	}
	
}