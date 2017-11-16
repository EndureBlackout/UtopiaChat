
package me.endureblackout.UtopiaChat;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PrivateMessage {

	String	message;
	UUID	sender;
	String	receiver;

	public PrivateMessage(String message, UUID sender, String receiver) {
		this.message = message;
		this.sender = sender;
		this.receiver = receiver;
	}

	public String message() {
		return this.message;
	}

	public Player sender() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (p.getUniqueId().equals(sender)) { return p; }
		}

		return null;
	}

	public Player receiver() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (p.getName().equalsIgnoreCase(receiver)) { return p; }
		}

		sender().sendMessage(ChatColor.RED + "This user is not online or doesn't exist.");
		return null;
	}

	public String formatMessage() {
		if (receiver() != null) { return ChatColor.DARK_GRAY + "[" + ChatColor.WHITE + sender().getName() + ChatColor.YELLOW + " -> " + ChatColor.WHITE + receiver().getName() + ChatColor.DARK_GRAY + "] "
			+ ChatColor.translateAlternateColorCodes('&', message()); }

		return null;
	}

	public void sendMessage() {
		if (formatMessage() != null) {
			receiver().sendMessage(formatMessage());
		}
	}
}
