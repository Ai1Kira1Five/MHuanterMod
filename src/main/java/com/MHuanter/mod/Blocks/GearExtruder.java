package com.MHuanter.mod.Blocks;

import com.MHuanter.mod.ModulMach;
import com.MHuanter.mod.TileEntities.TileEntityGearExtruder;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GearExtruder extends Block implements ITileEntityProvider{

	public GearExtruder() {
		super(Material.iron);
		this.setCreativeTab(ModulMach.tabModularMachines);
		this.setResistance(5.0F);
		this.setHardness(1.0F);

	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
	
		return new TileEntityGearExtruder();
	}
	
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            FMLNetworkHandler.openGui(player, ModulMach.instance, ModulMach.guiGearExtruder, world, x, y, z);
        }
        return true;
    }

}
