package com.yuushi.uuidmod.commands;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class CopyCommand extends CommandBase {

	UUIDMod uuidmod;

	public CopyCommand(UUIDMod uuidmod) {
		this.uuidmod = uuidmod;
	}

	@Override
	public String getCommandName() {
		return "umcopy";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "umcopy <text>";
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return true;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		if (args.length < 1) {
			return;
		}
		else {
			String text = String.join(" ", args);
			StringSelection stringSelection = new StringSelection(text);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(stringSelection, null);
			sender.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + "Copied to clipboard"));
		}
	}

}
