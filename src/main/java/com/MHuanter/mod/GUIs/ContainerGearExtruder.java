package com.MHuanter.mod.GUIs;

import com.MHuanter.mod.TileEntities.TileEntityGearExtruder;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;

public class ContainerGearExtruder extends Container {

	public ContainerGearExtruder(IInventory playerInv, TileEntityGearExtruder te) {
		
	}

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		
		return true;
	}

}
