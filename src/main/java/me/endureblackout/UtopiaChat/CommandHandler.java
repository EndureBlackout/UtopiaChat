
package me.endureblackout.UtopiaChat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHandler implements CommandExecutor {

	ChatMain core;

	public CommandHandler(ChatMain core) {
		this.core = core;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("msg")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				
				if(args.length > 1) {
					String message = "";
					
					for(int i = 1; i < args.length; i++) {
						String word = args[i];
						message = message + " " + word;
					}
					
					PrivateMessage pm = new PrivateMessage(message, p.getUniqueId(), args[0]);
					pm.sendMessage();
				}
			} 
		}

		return true;
	}

}
