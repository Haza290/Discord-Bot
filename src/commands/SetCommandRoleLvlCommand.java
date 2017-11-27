package commands;

import utility.GuildRolesUtility;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import main.Bot;
import main.Command;

public class SetCommandRoleLvlCommand implements Command{

	GuildRolesUtility gru = new GuildRolesUtility();
	
	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		// checks if there are more than 1 args in the array
		if(args.length > 1) {
			System.out.println(args[0] + " " + args[1]);
			// checks if the command that role lvl is being changed is a real command
			if(Bot.commands.containsKey(args[0])) {
				if (gru.getGuildRolesString(event.getGuild()).contains(args[1])) {
					return true;
				}
				event.getTextChannel().sendMessage("**" + args[1] + "** is not a role").queue();
				return false;
			}
			event.getTextChannel().sendMessage("**" + args[0] + "** is not a command").queue();
			return false;
		}
		event.getTextChannel().sendMessage("Need to add a **command** and a **role** which can use it").queue();
		return false;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		
	}

	@Override
	public String help() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void executed(boolean success, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	

}
