package com.MHuanter.mod;

import com.MHuanter.mod.Handlers.GuiHandler;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.creativetab.CreativeTabs;

@Mod(modid = "ModulMach", version = "ModulMach")
	public class ModulMach 
	{
	    public static final String MODID = "ModulMach";
	    public static final String VERSION = "0.1.12";
	    
	    public static CreativeTabs tabModularMachines = new tabMOdularMachines(CreativeTabs.getNextID() , "ModularMachines");
	    
	    
	    @SidedProxy(clientSide="com.MHuanter.mod.ClientProxy", serverSide="com.MHuanter.mod.ServerProxy")
	    public static CommonProxy proxy;
		
	    @Mod.Instance(MODID)
	    public static ModulMach instance;
	    public static final int guiGearExtruder = 1;
	    
	    
	    @EventHandler
	    public void preInit(FMLPreInitializationEvent event)
	    {
	    	proxy.preInit(event);
	        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

	    }
	    @EventHandler
	    public void init(FMLInitializationEvent event)
	    {
	    	proxy.init(event);
	    }
	    @EventHandler
	    public void postInit(FMLPostInitializationEvent event)
	    {
	    	proxy.postInit(event);
	    }

	    	
	   
}