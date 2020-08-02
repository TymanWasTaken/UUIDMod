package com.yuushi.uuidmod;

import com.google.gson.Gson;
import com.yuushi.uuidmod.utils.HttpRequest;
import net.minecraft.command.ICommandSender;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

public class GetPlayerUUIDThread implements Runnable {

	static ICommandSender sender = null;
	static String[] args;

	@Override
	public void run() {
		String name = args[0];
		String url = "https://api.ashcon.app/mojang/v2/user/" + name;
		String content = HttpRequest.get(url).body();
		if (content.length() < 110) {
			sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "[UUIDMod] Could not find name " + name + "."));
		}
		else {
			PlayerInfo info = new Gson().fromJson(content, PlayerInfo.class);
			String username = info.getUsername();
			String uuid = info.getUUID();
			String uuid_r = uuid.replace("-", "");
			ChatStyle name_style = new ChatStyle();
			name_style.setColor(EnumChatFormatting.BOLD);
			ChatStyle uuid_style = new ChatStyle();
			uuid_style.setChatClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/copy " + uuid));
			uuid_style.setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText(EnumChatFormatting.GRAY + "Click to copy UUID.")));
			ChatStyle uuid_r_style = new ChatStyle();
			uuid_r_style.setChatClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/copy " + uuid_r));
			uuid_r_style.setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText(EnumChatFormatting.GRAY + "Click to copy raw UUID.")));
			sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_PURPLE + "" + EnumChatFormatting.BOLD + "" + EnumChatFormatting.STRIKETHROUGH + "----------------------------------"));
			sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_PURPLE + "" + EnumChatFormatting.BOLD + username + EnumChatFormatting.LIGHT_PURPLE + "'s UUID"));
			sender.addChatMessage(new ChatComponentText(EnumChatFormatting.LIGHT_PURPLE + "> UUID:"));
			sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "  " + uuid).setChatStyle(uuid_style));
			sender.addChatMessage(new ChatComponentText(EnumChatFormatting.LIGHT_PURPLE + "> Raw UUID:"));
			sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "  " + uuid_r).setChatStyle(uuid_r_style));
			sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_PURPLE + "" + EnumChatFormatting.BOLD + "" + EnumChatFormatting.STRIKETHROUGH + "----------------------------------"));
		}

	}

	public static void main(ICommandSender isender, String[] strings) {
		sender = isender;
		args = strings;
		GetPlayerUUIDThread obj = new GetPlayerUUIDThread();
		Thread tobj = new Thread(obj);
		tobj.start();
	}
}
