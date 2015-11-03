package com.MHuanter.mod.Containes;

import org.apache.commons.lang3.ArrayUtils;

import com.MHuanter.mod.TileEntities.TileEntityGearExtruder;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

public class ContainerGearExtruder extends Container {
	
    private TileEntityGearExtruder GearTe;

	
	public int lastBurnTime;
    public int lastCurrentItemBurnTime;
    public int lastCookTime;
    private int[] slots_after = new int[]{0, 1};
    private int[] slots_before = new int[]{2, 3};
    private int[] slots_fuel = new int[]{4};


	
	/*
	 Slots : 0,1 - Result
	 		 2,3 - Raw
	 		 4   - Fuel
	 */
	
	public ContainerGearExtruder(IInventory playerInv, TileEntityGearExtruder te) {
		this.addSlotToContainer(new fuelSlot((IInventory) GearTe, 4, 62, 64));
		this.addSlotToContainer(new inputSlot((IInventory) GearTe, 2, 26, 27));
	    this.addSlotToContainer(new outputSlot((IInventory) GearTe, 0, 116, 27));
	    this.addSlotToContainer(new inputSlot((IInventory) GearTe, 3, 44, 27));
	    this.addSlotToContainer(new outputSlot((IInventory) GearTe, 1, 134, 27));


	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		
		return true;
	}
	

	
	 public void detectAndSendChanges() {
	        super.detectAndSendChanges();
	        for (Object crafter : this.crafters) {
	            ICrafting icrafting = (ICrafting) crafter;

	            if (this.lastCookTime != this.GearTe.cookTime) {
	                icrafting.sendProgressBarUpdate(this, 0, this.GearTe.cookTime);
	            }

	            if (this.lastBurnTime != this.GearTe.burnTime) {
	                icrafting.sendProgressBarUpdate(this, 1, this.GearTe.burnTime);
	            }

	            if (this.lastCurrentItemBurnTime != this.GearTe.currentItemBurnTime) {
	                icrafting.sendProgressBarUpdate(this, 2, this.GearTe.currentItemBurnTime);
	            }
	        }
	        this.lastCookTime = this.GearTe.cookTime;
	        this.lastBurnTime = this.GearTe.burnTime;
	        this.lastCurrentItemBurnTime = this.GearTe.currentItemBurnTime;
	    }
	 
	 @SideOnly(Side.CLIENT)
	    public void updateProgressBar(int par1, int par2) {
	        if (par1 == 0) {
	            this.GearTe.cookTime = par2;
	        }

	        if (par1 == 1) {
	            this.GearTe.burnTime = par2;
	        }

	        if (par1 == 2) {
	            this.GearTe.currentItemBurnTime = par2;
	        }
	    }
	 
	 public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
	        ItemStack itemstack = null;
	        Slot slot = (Slot) this.inventorySlots.get(par2);

	        if (slot != null && slot.getHasStack()) {
	            ItemStack itemstack1 = slot.getStack();
	            itemstack = itemstack1.copy();

	            if (ArrayUtils.contains(slots_after, par2)) {
	                if (!this.mergeItemStack(itemstack1, GearTe.getSizeInventory(), GearTe.getSizeInventory() + 36, true)) {
	                    return null;
	                }
	                slot.onSlotChange(itemstack1, itemstack);
	            } else if (!ArrayUtils.contains(slots_before, par2) && !ArrayUtils.contains(slots_fuel, par2)) {
	                if (FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null) {
	                    if (!this.mergeItemStack(itemstack1, 3, 7, false)) {
	                        return null;
	                    }
	                    slot.onSlotChanged();
	                } else if (TileEntityFurnace.isItemFuel(itemstack1)) {
	                    if (!this.mergeItemStack(itemstack1, 0, 3, false)) {
	                        return null;
	                    }
	                    slot.onSlotChanged();
	                } else if (par2 >= GearTe.getSizeInventory() && par2 < (GearTe.getSizeInventory() + 27)) {
	                    if (!this.mergeItemStack(itemstack1, GearTe.getSizeInventory() + 27, GearTe.getSizeInventory() + 36, false)) {
	                        return null;
	                    }
	                    slot.onSlotChanged();
	                } else if (par2 >= (GearTe.getSizeInventory() + 27) && par2 < (GearTe.getSizeInventory() + 36)) {
	                    if (!this.mergeItemStack(itemstack1, GearTe.getSizeInventory(), GearTe.getSizeInventory() + 27, false)) {
	                        return null;
	                    }
	                    slot.onSlotChanged();
	                }
	            } else if (!this.mergeItemStack(itemstack1, GearTe.getSizeInventory(), GearTe.getSizeInventory() + 36, false)) {
	                return null;
	            }

	            if (itemstack1.stackSize == 0) {
	                slot.putStack((ItemStack) null);
	            } else {
	                slot.onSlotChanged();
	            }

	            if (itemstack1.stackSize == itemstack.stackSize) {
	                return null;
	            }

	            slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
	        }
	        return itemstack;
	    }
	 
	 public class inputSlot extends Slot{

	        public inputSlot(IInventory inventory, int index, int x, int y) {
	            super(inventory, index, x, y);
	        }

	        @Override
	        public boolean isItemValid(ItemStack itemStack) {
	            return null != null;
	        }
	    }
	 
	 public class fuelSlot extends Slot{

	        public fuelSlot(IInventory inventory, int index, int x, int y) {
	            super(inventory, index, x, y);
	        }

	        @Override
	        public boolean isItemValid(ItemStack itemStack) {
	            return null != null;
	        }
	    }
	    public class outputSlot extends Slot{
	        public outputSlot(IInventory inventory, int index, int x, int y) {
	            super(inventory, index, x, y);
	        }

	        @Override
	        public boolean isItemValid(ItemStack itemStack) {
	            return false;
	        }
	    }
}
