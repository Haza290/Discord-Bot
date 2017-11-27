package trollGame;

import java.util.ArrayList;

import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class TrollGameController {

	public static ArrayList<TrollGame> trollGames = new ArrayList<TrollGame>();
	
	public void handdleCommand(MessageReceivedEvent event) {
		
		for (int i = 0; i < trollGames.size(); i++ ) {
			if(trollGames.get(i).playerID.equals(event.getAuthor().getId())) {
				trollGames.get(i).command(event.getMessage().getContent());
				event.getMessage().delete();
			}
		}		
	}
	
	public void newGame(MessageReceivedEvent event){
		System.out.println("newGame running");
		// Creates the new text channel for the game
		String textChannelName = "Troll_Game_" + event.getAuthor().getName();
		event.getGuild().getController().createTextChannel(textChannelName).complete();
		// Waits till textChannel is created
		//while (event.getGuild().getTextChannelsByName(textChannelName, true).isEmpty()) {			
		//}
		TextChannel textChannel = event.getGuild().getTextChannelsByName(textChannelName, true).get(0);
		System.out.println("text channel created");
		
		// Create the role needed to access the games text channel and adds it to the user
		event.getGuild().getController().createRole().setName(event.getAuthor().getId()).setHoisted(false).complete();
		event.getGuild().getController().addRolesToMember(event.getMember(), event.getGuild().getRolesByName(event.getAuthor().getId(), true).get(0)).complete();
		trollGames.add(new TrollGame(event.getAuthor().getId(), textChannel));
	}
	
	public void endGame(MessageReceivedEvent event) {
		// Deletes the text channel
		event.getTextChannel().delete().queue();
		// Deletes the role
		event.getGuild().getRolesByName(event.getAuthor().getId(), false).get(0).delete().queue();
		// Removes the trollGame from the array	
		for (int i = 0; i < trollGames.size(); i++ ) {
			if(trollGames.get(i).playerID.equals(event.getAuthor().getId())) {
				trollGames.remove(i);
			}
		}
	}
	
}
