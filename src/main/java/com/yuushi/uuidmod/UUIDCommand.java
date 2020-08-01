package com.yuushi.uuidmod;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import com.google.gson.Gson;
import com.yuushi.uuidmod.HttpRequest;
import com.yuushi.uuidmod.PlayerInfo;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;


public class UUIDCommand extends CommandBase {
	
	UUIDMod uuidmod;

    public UUIDCommand(UUIDMod uuidmod) {
        this.uuidmod = uuidmod;
    }
    
	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return "uuid";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return "uuid <username>";
	}
	
	@Override
    public boolean canCommandSenderUseCommand(ICommandSender sender)
    {
        return true;
    }
	
	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		// TODO Auto-generated method stub
		if (args.length < 1 || args.length > 1) {
			sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "[UUIDMod] Usage: /uuid <username>"));
            return;
		}
		else {
			GetPlayerUUIDThread.main(sender, args);
		}
	}

}
