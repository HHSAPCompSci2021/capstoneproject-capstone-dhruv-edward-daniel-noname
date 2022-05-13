package sprites;

import java.awt.Point;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import dsharma578.shapes.Rectangle;

import processing.core.PApplet;

public class Map {
	private char[][] grid;
	private int y;
	ArrayList<Rectangle> mapRects = new ArrayList<Rectangle>();
	
	public Map() {
		grid = new char[10][3];
	}
	
	public Map(int lanes, int length, String filename,  int y) {
		this.y = y;
		grid = new char[length][lanes];
		readData(filename,grid);
	}
	
	public char[][] getGrid() {
		return grid;
	}
	
	public int getY() {
		return y;
	}
	
	public ArrayList<Rectangle> getRects() {
		System.out.println(mapRects);
		return mapRects;
	}
	
	public void draw(PApplet marker) {
		marker.fill(255);
		marker.noStroke();
		float rectWidth = marker.width/(grid[0].length);
		int startY= y;
		for(int i = 0; i<grid.length; i++) {
			for(int j = 0; j<grid[i].length;j++) {
				float rectX = rectWidth*j;
				float rectY = startY+rectWidth*(i-grid.length);
				Rectangle r = new Rectangle(rectX, rectY, rectWidth, rectWidth);
				System.out.println(r.toString());
				mapRects.add(r);
				if(grid[i][j]=='O') { 
					r.setFillColor(0, 0, 0, 255);
				}
				if(grid[i][j]=='X') {
					r.setFillColor(90,20,20,255);
				}
				r.draw(marker);
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