package com.MHuanter.mod.Handlers;

import com.MHuanter.mod.ModulMach;
import com.MHuanter.mod.Containes.ContainerGearExtruder;
import com.MHuanter.mod.GUIs.GearExtruderGUI;
import com.MHuanter.mod.TileEntities.TileEntityGearExtruder;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler{

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity entity = world.getTileEntity(x, y, z);

                if (entity != null) {
                            if (entity instanceof TileEntityGearExtruder) {
                                return new ContainerGearExtruder(player.inventory, (TileEntityGearExtruder) entity);
                            }    
                }
       

        return null;
   
    }
	@Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity entity = world.getTileEntity(x, y, z);

        if (entity != null) {
            switch (ID) {
                case ModulMach.guiGearExtruder:
                    if (entity instanceof TileEntityGearExtruder) {
                        return new GearExtruderGUI(player.inventory, (TileEntityGearExtruder) entity);
                    }
                
            }
        }
        
        return null;
    }

}
