package commands;

import java.util.ArrayList;
import java.util.Random;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import main.Command;

public class RoleCommand implements Command {

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		// Checks if at least 1 dice is being rolled
		if(args.length < 1) { 
			event.getTextChannel().sendMessage("Need to roll atleast 1 dice").queue();
			return false;
			}
		
		// Checks each of the dice to make sure they are valid
		for (String string : args) {
			String[] buff = string.toLowerCase().split("d");
			if(buff.length != 2) {
				event.getTextChannel().sendMessage("**" + string + "** is not a valid dice").queue();
				return false;
			}
			for (String string2 : buff) {
				if(!string2.matches("[0-9]+")) {
					event.getTextChannel().sendMessage("**" + string + "** is not a valid dice").queue();
					return false;
				}
			}
		}
		
		return true;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		
		ArrayList<Integer> results = new ArrayList<Integer>();
		
		for (String string : args) {
			String[] nums = string.toLowerCase().split("d");
			int amount = Integer.parseInt(nums[0]);
			int limit = Integer.parseInt(nums[1]);
			results.add(roll(amount, limit));			
		}
		
		if(results.size() == 1) {
			event.getTextChannel().sendMessage("**" + Integer.toString(results.get(0)) + "**").queue();
			return;
		}
		
		String resultsString = "";
		int total = 0;
		for (int i : results) {
			total += i;
			resultsString += "**" + Integer.toString(i) + "**, ";
		}
		resultsString += " Total = **" + Integer.toString(total) + "**";
		
		event.getTextChannel().sendMessage(resultsString).queue();
	}
	
	private int roll(int amount, int limit) {
		Random rand = new Random();
		int result = 0;
		for (int i = 0; i < amount; i++) {
			result += rand.nextInt(limit) + 1;
		}
		return result;
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
