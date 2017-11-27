package commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import main.Command;

public class TestCommand implements Command{

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		event.getGuild().getController().createRole().setName(event.getAuthor().getId()).setHoisted(false).queue();
		event.getGuild().getController().addRolesToMember(event.getMember(), event.getGuild().getRolesByName(event.getAuthor().getId(), true).get(0)).queue();
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
