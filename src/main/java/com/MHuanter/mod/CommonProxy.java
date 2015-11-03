package com.MHuanter.mod;

import com.MHuanter.mod.TileEntities.TileEntityGearExtruder;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

public class CommonProxy {

	    public void preInit(FMLPreInitializationEvent e) {
	    	//Blocks
	    	MachBlocks MBObject = new MachBlocks();
	    	//Items
	    	MachItems MIObject = new MachItems();
	    	//Entities
	    	
	    	//RegistryBlocks
	    	MachBlocks.RegistryBlocksPreInit();
	    	//RegisteryItems
	    	MachItems.RegistryItemsPreInit();
	    	//RegistryEntities
	    	
	    	 GameRegistry.registerTileEntity(TileEntityGearExtruder.class, ModulMach.MODID + ":GearExtruder");
	    }

	    public void init(FMLInitializationEvent e) {
	    	
	    }

	    public void postInit(FMLPostInitializationEvent e) {

	    }
	}
	

