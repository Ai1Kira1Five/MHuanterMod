package com.MHuanter.mod;

import com.MHuanter.mod.Blocks.GearExtruder;
import com.MHuanter.mod.Blocks.tblock;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class MachBlocks extends ModulMach{
	
	public static Block tblock = new tblock().setBlockName("Tblock").setBlockTextureName("mod:TBlock");
	public static Block GearExtruder = new GearExtruder().setBlockName("GearExtruder");
	
    public static void RegistryBlocksPreInit()
    {
    	GameRegistry.registerBlock(tblock, "TBlock");
    	GameRegistry.registerBlock(GearExtruder, "GearExtruder");
    }

	
}
