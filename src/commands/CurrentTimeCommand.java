package commands;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;


import main.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;


public class CurrentTimeCommand implements Command {

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		return true;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		Date date = new Date(System.currentTimeMillis());
		DateFormat formatter = new SimpleDateFormat("HH:mm");
		String dateFormatted = formatter.format(date);
		event.getTextChannel().sendMessage("Current time is " + dateFormatted).queue();
		
		List<net.dv8tion.jda.core.entities.Role> roles = event.getGuild().getRoles();
		for (net.dv8tion.jda.core.entities.Role role : roles) {
			System.out.println(role.getName());
		}
		//event.getGuild().getMembersWithRoles(event.getGuild().getRoleById("Admin"));
		
		//if(event.getGuild().getMembersWithRoles(event.getGuild().getRoleById("Admin")).contains(event.getAuthor())) {
		//	System.out.println("is admin");
		//}
		
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
