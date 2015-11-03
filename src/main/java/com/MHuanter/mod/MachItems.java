package com.MHuanter.mod;

import com.MHuanter.mod.Items.BlankModule;
import com.MHuanter.mod.Items.ControllerModule;
import com.MHuanter.mod.Items.IronGear;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class MachItems extends ModulMach
{
	 //Creating
	public static Item IronGear = new IronGear().setUnlocalizedName("IronGear").setTextureName("mod:IronGear").setCreativeTab(ModulMach.tabModularMachines);
	public static Item BlankModule = new BlankModule().setUnlocalizedName("BlankModule").setTextureName("mod:").setCreativeTab(ModulMach.tabModularMachines);
	public static Item ControllerModule = new ControllerModule().setUnlocalizedName("ControllerModule").setTextureName("mod:").setCreativeTab(ModulMach.tabModularMachines);

	
	//Registry
	public static void RegistryItemsPreInit()
	    {
	    	GameRegistry.registerItem(IronGear, "IronGear");
	    	GameRegistry.registerItem(BlankModule, "BlankModule");
	    	GameRegistry.registerItem(ControllerModule, "ControllerModule");
	    }
}
