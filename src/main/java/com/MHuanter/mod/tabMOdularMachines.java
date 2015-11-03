package com.MHuanter.mod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class tabMOdularMachines extends CreativeTabs {

	public tabMOdularMachines(int i, String label) {
		super(i, label);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Item getTabIconItem() {
	
		return new ItemStack(MachBlocks.tblock).getItem();
	}

}
