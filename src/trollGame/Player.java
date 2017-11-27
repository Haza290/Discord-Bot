package trollGame;

import java.util.HashMap;
import java.util.Map;

public class Player {
	
	int xPos, yPos;
	String facing;
	Maze maze;
	Map<String, Character> facingToChar;
	
	public Player(int xPos, int yPos, String facing, Maze maze) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.facing = facing;
		this.maze = maze;
		
		facingToChar = new HashMap<>();
		facingToChar.put("w", '^');
		facingToChar.put("s", 'v');
		facingToChar.put("d", '>');
		facingToChar.put("a", '<');
	}
	
	public void move(String direction) {
		if(!facing.equals(direction)) {
			facing = direction;
			maze.changeTile(xPos, yPos, facingToChar.get(facing));
			return;
		}
		
		int newXPos = xPos;
		int newYPos = yPos;
		
		if(direction.equals("w")) {
			newXPos -= 1;
		}
		else if(direction.equals("s")) {
			newXPos += 1;
		}
		else if(direction.equals("d")) {
			newYPos += 1;
		}
		else if(direction.equals("a")) {
			newYPos -= 1;
		}
		
		if(maze.move(newXPos, newYPos, xPos, yPos, facingToChar.get(facing))) {
			xPos = newXPos;
			yPos = newYPos;
		}
	}

}
