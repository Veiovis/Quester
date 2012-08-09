package com.gmail.molnardad.quester.qevents;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.Player;

@SerializableAs("QuesterMessageQevent")
public final class MessageQevent extends Qevent {

	private final String TYPE = "MSG";
	private final String message;
	private final String rawmessage;
	
	public MessageQevent(int occ, int del, String msg) {
		super(occ, del);
		this.rawmessage = msg;
		this.message = ChatColor.translateAlternateColorCodes('&', rawmessage).replaceAll("\\\\n", "\n");
	}
	
	@Override
	public String getType() {
		return TYPE;
	}
	
	@Override
	public int getOccasion() {
		return occasion;
	}
	
	@Override
	public String toString() {
		return TYPE + ": ON-" + parseOccasion(occasion) + "; MSG: " + message;
	}

	@Override
	public Map<String, Object> serialize() {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("message", rawmessage);
		map.put("occasion", occasion);
		map.put("delay", delay);
		
		return map;
	}
	
	public static MessageQevent deserialize(Map<String, Object> map) {
		String msg;
		int occ, del;
		
		msg = (String) map.get("message");
		occ = (Integer) map.get("occasion");
		del = (Integer) map.get("delay");
		
		return new MessageQevent(occ, del, msg);
	}

	@Override
	public void run(Player player) {
		player.sendMessage(message);
	}
}
