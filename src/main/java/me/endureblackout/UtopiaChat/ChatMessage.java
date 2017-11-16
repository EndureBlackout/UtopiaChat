
package me.endureblackout.UtopiaChat;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownyUniverse;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;

public class ChatMessage {

	ChatMain	core;
	String		message;
	UUID		sender;
	Chat		chat;
	Permission	perms;

	public ChatMessage(String message, UUID sender, ChatMain core) {
		this.message = message;
		this.sender = sender;
		this.core = core;
		setupPermissions();
	}

	public ChatMessage(String message) {
		this.message = message;
		this.sender = null;
	}

	public String getMessage() {
		return message;
	}

	public UUID getSender() {
		return sender;
	}

	public String formatMessage() {
		String fMessage = "";
		Player s = Bukkit.getPlayer(sender);

		try {
			Resident sRes = TownyUniverse.getDataSource().getResident(s.getName());

			if (sRes.hasTown()) {
				Town pTown = TownyUniverse.getDataSource().getResident(s.getName()).getTown();

				if (pTown.hasNation()) {
					Nation pNation = TownyUniverse.getDataSource().getResident(s.getName()).getTown().getNation();
					fMessage = ChatColor.GRAY + "[" + ChatColor.GREEN + pTown.getName() + ChatColor.WHITE + ":" + ChatColor.DARK_GRAY + pNation.getName() + ChatColor.GRAY + "] "
						+ ChatColor.translateAlternateColorCodes('&', chat.getGroupPrefix(s.getWorld(), perms.getPrimaryGroup(s))) + s.getName() + ChatColor.DARK_GRAY + " >> " + ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', message);
					return fMessage;
				}

				if (!pTown.hasNation()) {
					fMessage = ChatColor.GRAY + "[" + ChatColor.GREEN + pTown.getName() + ChatColor.GRAY + "] " + ChatColor.translateAlternateColorCodes('&', chat.getGroupPrefix(s.getWorld(), perms.getPrimaryGroup(s))) + s.getName()
						+ ChatColor.DARK_GRAY + " >> " + ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', message);
					return fMessage;
				}
			} else if (!sRes.hasTown()) {
				fMessage = ChatColor.translateAlternateColorCodes('&', chat.getGroupPrefix(s.getWorld(), perms.getPrimaryGroup(s))) + s.getName() + ChatColor.DARK_GRAY + " >> " + ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', message);
				return fMessage;
			}
		} catch (NotRegisteredException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void sendMessage() {
		for (Player online : Bukkit.getOnlinePlayers()) {
			online.sendMessage(formatMessage());
		}
	}

	private boolean setupPermissions() {
		RegisteredServiceProvider<Permission> rsp1 = core.getServer().getServicesManager().getRegistration(Permission.class);
		perms = rsp1.getProvider();

		RegisteredServiceProvider<Chat> rsp = core.getServer().getServicesManager().getRegistration(Chat.class);
		chat = rsp.getProvider();
		return chat != null && perms != null;
	}
}
