package me.endureblackout.UtopiaChat;

import org.bukkit.plugin.java.JavaPlugin;

public class ChatMain extends JavaPlugin {
	
	public void onEnable() {
		getCommand("msg").setExecutor(new CommandHandler(this));
		
		getServer().getPluginManager().registerEvents(new ChatEvent(this), this);
	}
}
