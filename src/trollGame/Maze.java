package trollGame;

import java.io.BufferedReader;
import java.io.FileReader;


public class Maze {

	char[][] maze;
	
	public Maze() {
		readMaze();
	}
	
	public void changeTile(int x, int y, char c) {
		maze[x][y] = c;
	}
	
	public boolean move(int x, int y, int oldX, int oldY, char c) {
		if(maze[x][y] == '#') {
			return false;
		}
		maze[oldX][oldY] = ' ';
		maze[x][y] = c;
		return true;
	}
	
	private void readMaze() {
		try {
			// Gets the size of the maze
			FileReader file = new FileReader("maze.txt");
			BufferedReader reader = new BufferedReader(file);	
			int w = reader.readLine().length();
			int h = 1;
			while(reader.readLine() != null) {
				h ++;
			}
			maze = new char[h][w];
			
			// Fills out the maze
			file = new FileReader("maze.txt");
			reader = new BufferedReader(file);
			for (int i = 0; i < h; i++) {
				String buff = reader.readLine();
				for (int j = 0; j < buff.length(); j++) {
					maze[i][j] = buff.charAt(j);
				}
			}
			reader.close();
			
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
