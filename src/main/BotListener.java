package main;

import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.user.UserGameUpdateEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class BotListener extends ListenerAdapter {

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		if(event.getTextChannel().getName().startsWith("troll_game_")) {
			System.out.println("command entered: " + event.getMessage().getContent());
			Bot.trollGameController.handdleCommand(event);
			if(!event.getAuthor().isBot()) {
				event.getMessage().delete().queue();
			}
			return;
		}
		
		if (event.getMessage().getContent().startsWith("!") && !event.getMessage().getAuthor().isBot()) {
			Bot.handleCommand(Bot.parser.parse(event.getMessage().getContent().toLowerCase(), event));
		}
	}
	
	@Override
	public void onReady(ReadyEvent event) {
		//Bot.log("status", "Logged in ass: " + event.getJDA().getSelfInfo().getUsername());
	}
	
	@Override
	public void onUserGameUpdate(UserGameUpdateEvent event) {
		try {
			
			Game currentGame = event.getUser().getMutualGuilds().get(0).getMember(event.getUser()).getGame();
			
			for (VoiceChannel voiceChannel : event.getGuild().getVoiceChannels()) {
				if(voiceChannel.getName().equals(currentGame.getName())) {
					for (Member member : voiceChannel.getMembers()) {
						if(member.getUser().equals(event.getUser())) {
							return;
						}
					}
					event.getUser().openPrivateChannel().queue(channel ->
					{
						channel.sendMessage("There is a voice channel for **" + currentGame.getName() + "** in server **" + event.getGuild().getName() + "**, you should join it").queue();
					});
					return;
				}
			}
			if (event.getGuild().getOwner().getUser().equals(event.getUser())) {
				event.getUser().openPrivateChannel().queue(channel ->
				{
					channel.sendMessage("Do you want me to create a voice channel for: **" + currentGame.getName()  + "** in server **" + event.getGuild().getName() + "**?" ).queue();
				});
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	
}
