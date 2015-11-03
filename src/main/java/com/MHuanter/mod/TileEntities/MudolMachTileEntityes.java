package com.MHuanter.mod.TileEntities;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.tileentity.TileEntity;

public class MudolMachTileEntityes extends TileEntity{
	

	    public static void init() {
	        GameRegistry.registerTileEntity(TileEntityGearExtruder.class, "TileEntityGearExtruder");
	    }

}
