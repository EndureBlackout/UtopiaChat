
package me.endureblackout.UtopiaChat;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {
	ChatMain core;
	
	public ChatEvent(ChatMain core) {
		this.core = core;
	}
	
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		ChatMessage message = new ChatMessage(e.getMessage(), e.getPlayer().getUniqueId(), core);
		
		message.sendMessage();
		e.setCancelled(true);
	}
}
