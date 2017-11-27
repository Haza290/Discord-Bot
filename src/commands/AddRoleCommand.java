package commands;

import java.util.ArrayList;
import java.util.List;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import main.Command;

public class AddRoleCommand implements Command {

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		// Checks if enough args are present 
		if(args.length < 2) {
			event.getTextChannel().sendMessage("You need to specify a role and atleast 1 user").queue();
			return false;
		}
		
		// Checks if the first arg is a role in the guild 
		List<Role> roles = event.getGuild().getRoles();
		ArrayList<String> roleNames = new ArrayList<String>();
		for (Role role : roles) {
			roleNames.add(role.getName());
		}
		if(!roleNames.contains(args[0])) {
			event.getTextChannel().sendMessage("**" + args[0] + "** is not a valid Role").queue();
			return false;
		}
		
		// Checks if the rest of the args are valid members names
		List<Member> members = event.getGuild().getMembers();
		ArrayList<String> membersNames = new ArrayList<String>();
		for (Member member : members) {
			membersNames.add(member.getUser().getName());
		}
		for (int i = 1; i < args.length; i++) {
			if(!membersNames.contains(args[i])) {
				event.getTextChannel().sendMessage("**" + args[i] + "** is not a valid User").queue();
				return false;
			}
		}
		return true;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		// TODO Finish this
		event.getGuild().getMembersByName(args[1], true);
		
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
