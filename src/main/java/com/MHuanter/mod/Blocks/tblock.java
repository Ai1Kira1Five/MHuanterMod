package com.MHuanter.mod.Blocks;

import com.MHuanter.mod.ModulMach;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public class tblock extends Block {

	

	public tblock() {
		super(Material.rock);
		this.setCreativeTab(ModulMach.tabModularMachines);
		this.getRenderType( );
		this.setStepSound(soundTypeStone);
		this.blockHardness = 2F;
		this.setHarvestLevel(getUnlocalizedName(), 2);
		
		
	}






}
