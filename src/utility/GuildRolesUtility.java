package utility;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import java.io.File;
import java.util.ArrayList;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.User;

public class GuildRolesUtility {

	public boolean isUserInRole(User user, String roleName, Guild guild) {
		// Checks for the role with roleName
		for (Role role : guild.getRoles()) {
			if (role.getName().toLowerCase().equals(roleName.toLowerCase())) {
				// when roleName is found check if user is in the list of members with that role
				for (Member member : guild.getMembersWithRoles(role)) {
					if(member.getUser().getId().equals(user.getId())) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Checks if a member has the right role to run a command
	 * 
	 * @param  member  - The member running the command
	 * @param  command - The command that is trying to be run
	 * @return Boolean - True can run this command, False can't run the command
	 */
	public boolean canUserRunThis(Member member, String command) {
		
		// if member is owner of the guild they can run any command
		if (member.isOwner()) {
			return true;
		}
		
		try {
			// Creates string array of all the roles the member has
			ArrayList<String> membersRoles = new ArrayList<String>();
			for (Role role : member.getRoles()) {
				membersRoles.add(role.getName().toLowerCase());
			}
			
			// Opens the XML permissions document for the guild
			File file = new File(member.getGuild().getName() + "_" + member.getGuild().getId() + "_Permissions.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();
			
			// Gets all the role nodes
			NodeList roleNodes = doc.getElementsByTagName("role");
			// Iterates through all the nodes with tag "role"
			for (int i = 0; i < roleNodes.getLength(); i++) {
				Element eElement = (Element) roleNodes.item(i);
				// If the member has the same role as the node, check the nodes commands
				if(membersRoles.contains(eElement.getAttribute("name"))) {
					NodeList commandNodeList = eElement.getElementsByTagName("command");
					// Iterates through all the commands tags in the role
					for (int j = 0; j < commandNodeList.getLength(); j++) {
						// if the command used == the command in the role then user can use command
						if(command.equals(commandNodeList.item(j).getTextContent())) {
							return true;
						}
					}
				}
			}
			
		} catch (Exception e) {
			System.err.println(e);
		}
		
		return false;
	}
	
	public ArrayList<String> getGuildRolesString(Guild guild){
		
		ArrayList<String> roles = new ArrayList<String>();
		
		for (Role role : guild.getRoles()) {
			roles.add(role.getName().toLowerCase());
		}
		
		return roles;
	}
	
}
