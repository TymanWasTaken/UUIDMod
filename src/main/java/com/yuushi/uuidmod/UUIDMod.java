package com.yuushi.uuidmod;

import net.minecraft.init.Blocks;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = UUIDMod.MODID, name= UUIDMod.MOD_NAME, version = UUIDMod.VERSION)
public class UUIDMod
{
    public static final String MODID = "uuidmod";
    public static final String MOD_NAME = "UUIDMod";
    public static final String VERSION = "0.1";
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	ClientCommandHandler.instance.registerCommand(new UUIDCommand(this));
    	ClientCommandHandler.instance.registerCommand(new CopyCommand(this));
    }
}
