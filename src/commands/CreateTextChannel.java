package commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import main.Command;

public class CreateTextChannel implements Command{

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		event.getGuild().getController().createTextChannel(args[0]).queue();
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
