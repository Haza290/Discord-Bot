package trollGame;

import java.util.ArrayList;

import net.dv8tion.jda.core.entities.TextChannel;

public class TrollGame {
	
	public String playerID;
	private TextChannel textChannel;
	private Player player;
	private ArrayList<Troll> trolls;
	public String messageID;
	public Maze maze;
	
	public TrollGame(String playerID, TextChannel textChannel){
		this.playerID = playerID;
		this.textChannel = textChannel;
		maze = new Maze();
		createPlayer();
		messageID = textChannel.sendMessage("start up").complete().getId();
		drawMaze("");
	}
	
	public void command(String command){
		if(command.toLowerCase().equals("w") || command.toLowerCase().equals("s") || command.toLowerCase().equals("d") || command.toLowerCase().equals("a")) {
			player.move(command.toLowerCase());
		}
		else {
			drawMaze(command + " is not a valid command. ");
			return;
		}
		//trollsMove();
		
		drawMaze("");
	}
	
	@SuppressWarnings("unused")
	private void trollsMove() {
		for (Troll troll : trolls) {
			troll.move();
		}
	}
	
	private void createPlayer() {
		// Loops through the maze looking for the P
		for (int i = 0; i < maze.maze.length; i++) {
			for (int j = 0; j < maze.maze[i].length; j++) {
				if(maze.maze[i][j] == 'P') {
					System.out.println("Player found");
					player = new Player(i, j, "w", maze);
					return;
				}
			}
		}
		System.out.println("Player not found");
	}
	
	private void drawMaze(String text) {
		String mazeString = "";
		for (int i = 0; i < maze.maze.length; i++) {
			for (int j = 0; j < maze.maze[i].length; j++) {
				mazeString += maze.maze[i][j];
			}
			mazeString += "\n";
		}
		textChannel.editMessageById(messageID, "```" + mazeString + "```\n" + text + "Type w, a, s or d to move in a direction").queue();
	}

}
