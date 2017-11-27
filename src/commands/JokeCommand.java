package commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import main.Command;

public class JokeCommand implements Command{

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		return true;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		try {
			FileReader file = new FileReader("jokes.txt");
			BufferedReader reader = new BufferedReader(file);
			ArrayList<String> jokes = new ArrayList<String>();
			while(true) {
				String stringBuff = reader.readLine();
				if (stringBuff == null) {
					break;
				}
				jokes.add(stringBuff);
			}
			reader.close();
			Random rand = new Random();
			int randInt = rand.nextInt(jokes.size());
			
			event.getTextChannel().sendMessage(jokes.get(randInt)).queue();
			
		} catch (Exception e) {
			System.err.println(e);
		}
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
