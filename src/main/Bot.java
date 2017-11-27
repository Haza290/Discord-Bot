package main;
import java.util.HashMap;

import javax.security.auth.login.LoginException;

import trollGame.TrollGameController;
import utility.GuildRolesUtility;
import commands.*;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

public class Bot {
	
	public JDA jda;
	public static CommandParser parser = new CommandParser();
	public static final String token = "Mjk0OTYyMTQxNjc5ODQ1Mzc2.C7cwfg.45AMlZAbCyiLDHj-3eAHujwFRkk";
	
	public static HashMap<String, Command> commands = new HashMap<String, Command>();
	public static TrollGameController trollGameController = new TrollGameController();

	public static void main(String[] args) {
		new Bot();
	}
		
	public Bot() {
		try {
			jda = new JDABuilder(AccountType.BOT).addListener(new BotListener()).setToken(token).buildBlocking();
			jda.setAutoReconnect(true);
		} catch (LoginException | IllegalArgumentException | InterruptedException | RateLimitedException e) {
			e.printStackTrace();
		}
		
		// Add's commands to the bot
		commands.put("ping", new PingCommand());
		commands.put("currenttime", new CurrentTimeCommand());
		commands.put("setcommandrolelvl", new SetCommandRoleLvlCommand());
		commands.put("roll", new RoleCommand());
		commands.put("joke", new JokeCommand());
		commands.put("createtextchannel", new CreateTextChannel());
		commands.put("test", new TestCommand());
		commands.put("playtroll", new PlayTrollCommand());
	}
	
	/**
	 * Handles the commands
	 * @param cmd - parses commands
	 */
	public static void handleCommand(CommandParser.CommandContainer cmd) {
		GuildRolesUtility gru = new GuildRolesUtility();
		System.out.println("Checking if " + cmd.command + " is a command"); //TODO Remove this (just for debugging)
		
		// Checks if command entered in an actual command
		if(commands.containsKey(cmd.command)) {
			System.out.println(cmd.command + " is trying to be run"); //TODO Remove this (just for debugging)
			
			// Checks if user has the right role to run this command
			if (gru.canUserRunThis(cmd.event.getMember(), cmd.command)) {
				System.out.println(cmd.command + " can be run by user"); //TODO Remove this (just for debugging)
				// checks if command can be called
				boolean safe = commands.get(cmd.command).called(cmd.args, cmd.event);
				if(safe) {
					System.out.println(cmd.command + " is safe");
					commands.get(cmd.command).action(cmd.args, cmd.event);
					commands.get(cmd.command).executed(safe, cmd.event);
				} else {
					commands.get(cmd.command).executed(safe, cmd.event);
				}
			} else {
				cmd.event.getTextChannel().sendMessage("You do not have the right role lvl (i.e your not a Admin)").queue();
			}
		}
	}
}
