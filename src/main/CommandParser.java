package main;
import java.util.ArrayList;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;


public class CommandParser {
	
	public CommandContainer parse(String rw, MessageReceivedEvent event) {
		ArrayList<String> split = new ArrayList<String>();
		String raw = rw;
		String beheaded = raw.substring(1, raw.length());
		String[] splitBeheaded = beheaded.split(" ");
		for (String string : splitBeheaded) {
			split.add(string);
		}
		String command = split.get(0);
		String[] args = new String[split.size() - 1];
		split.subList(1, split.size()).toArray(args);
		
		return new CommandContainer(raw, beheaded, splitBeheaded, command, args, event);
	}
	
	public class CommandContainer {
		public final String raw;
		public final String beheaded;
		public final String[] splitBeheaded;
		public final String command;
		public final String[] args;
		public final MessageReceivedEvent event;
		
		public CommandContainer(String raw, String beheaded, String[] splitBeheaded, String command, String[] args, MessageReceivedEvent event) {
			this.raw = raw;
			this.beheaded = beheaded;
			this.splitBeheaded = splitBeheaded;
			this.command = command.toLowerCase();
			this.args = args;
			this.event = event;
		}
	}

}
