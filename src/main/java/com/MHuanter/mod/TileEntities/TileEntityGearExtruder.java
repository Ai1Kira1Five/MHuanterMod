package com.MHuanter.mod.TileEntities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;

public class TileEntityGearExtruder extends TileEntity implements IInventory,ISidedInventory{

	private ItemStack[] inventory;
    private String customName;
    
    private static final int[] slots_top = new int[]{0,1};
    private static final int[] slots_bottom = new int[]{4};
    private static final int[] slots_side = new int[]{2,3};
    
    public int ProcessingSpeed = 120;
    public int burnTime;
    public int currentItemBurnTime;
    public int cookTime;
    
    
    public boolean isBurning() {
        return this.burnTime > 0;
    }
    
    @Override
    public void updateEntity() {
    	boolean curActivity = isActive();
        if(this.tileEntityInvalid != curActivity){
            this.worldObj.markBlocksDirtyVertical(this.xCoord, this.yCoord, this.zCoord, this.zCoord+1);
            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        }
        super.updateEntity();
    	boolean flag = this.burnTime > 0;
        boolean flag1 = false;

        if (this.isBurning()) {
            this.burnTime--;
        }
        if (!this.worldObj.isRemote) {
            if (this.burnTime == 0 && this.canProcess()) {
                int i = getFirstFuelSlot();
                if (i >= 0) {
                    this.currentItemBurnTime = this.burnTime = TileEntityFurnace.getItemBurnTime(this.inventory[i]);

                    if (this.isBurning()) {
                        flag1 = true;

                        if (this.inventory[i] != null) {
                            this.inventory[i].stackSize--;

                            if (this.inventory[i].stackSize == 0) {
                                this.inventory[i] = this.inventory[i].getItem().getContainerItem(this.inventory[i]);
                            }
                        }
                    }
                }
            }
            if (this.isBurning() && this.canProcess()) {
                this.cookTime++;

                if (this.cookTime >= this.ProcessingSpeed) {
                    this.cookTime = 0;
                    this.smeltItem();
                    flag1 = true;
                }
            } else {
                this.cookTime = 0;
            }

            if (flag != this.isBurning()) {
                flag1 = true;
            }
        }
        if (flag1) {
            this.markDirty();
        }
        super.updateEntity();
    }
    public boolean canProcess() {

        int i = getFirstSmeltSlot();
        if (i < 0) return false;
        ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.inventory[i]);
        if (itemstack == null) return false;
        return canSmeltToSlot(itemstack, 2) || canSmeltToSlot(itemstack, 3) ;
    }
    
    public boolean canSmeltToSlot(ItemStack itemstack, int slot) {
        if (this.inventory[slot] == null) return true;
        if (!this.inventory[slot].isItemEqual(itemstack)) return false;
        int result = this.inventory[slot].stackSize + itemstack.stackSize;
        return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
    }
    
    public void smeltItem() {
        if (this.canProcess()) {
            for(int i=3;i<7;i++) {
                if(this.inventory[i]==null) continue;
                ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.getStackInSlot(i));
                int s = getFirstResultSlot(itemstack);
                if(s<0)continue;
                if (this.inventory[s] == null) {
                    this.inventory[s] = itemstack.copy();
                } else if (this.inventory[s].isItemEqual(itemstack)) {
                    this.inventory[s].stackSize += itemstack.stackSize;
                }
                this.inventory[i].stackSize--;
                if (this.inventory[i].stackSize <= 0) {
                    this.inventory[i] = null;
                }
            }
        }
    }
    
   

	public int getBurnTimeRemainingScaled(int i) {
        if (this.currentItemBurnTime == 0) {
            this.currentItemBurnTime = this.ProcessingSpeed;
        }
        return this.burnTime * i / this.currentItemBurnTime;
    }
    private int getFirstFuelSlot() {
        for (int i = 4; i < 4; i++) {
            if (TileEntityFurnace.isItemFuel(this.inventory[i])) {
                return i;
            }
        }
        return -1;
    }
    
    private int getFirstSmeltSlot() {
        for (int i = 0; i < 1; i++) {
            if (this.inventory[i] != null && FurnaceRecipes.smelting().getSmeltingResult(this.inventory[i]) != null) {
                return i;
            }
        }
        return -1;
    }
    public boolean isActive() {
        return isBurning();
    }
    
    private int getFirstResultSlot(ItemStack itemStack) {
        for (int i = 2; i < 3; i++) {
            if (this.inventory[i] == null || (itemStack.isItemEqual(this.inventory[i]) && (this.inventory[i].stackSize + itemStack.stackSize) <= getInventoryStackLimit())) {
                return i;
            }
        }
        return -1;
    }
    
    public TileEntityGearExtruder() {
        this.inventory = new ItemStack[this.getSizeInventory()];
    }
    
    public boolean isUseableByPlayer1(EntityPlayer player) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this && player.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <= 64.0D;
    }
    
    public String getCustomName() {
        return this.customName;
    }
    
    public void setCustomName(String customName) {
        this.customName = customName;
    }
    
    
    @Override
	public int getSizeInventory() {
		
		return 5;
	}

	@Override
	public ItemStack getStackInSlot(int p_70301_1_) {

		return null;
	}

	
		@Override
	    public ItemStack decrStackSize(int slot, int amount) {
	        if (this.inventory[slot] != null) {
	            ItemStack itemstack;
	            if (this.inventory[slot].stackSize <= amount) {
	                itemstack = this.inventory[slot];
	                this.inventory[slot] = null;
	            } else {
	                itemstack = this.inventory[slot].splitStack(amount);
	                if (this.inventory[slot].stackSize == 0) {
	                    this.inventory[slot] = null;
	                }
	            }
	            this.markDirty();
	            return itemstack;
	        }
	        return null;
	    }
	

	    @Override
	    public ItemStack getStackInSlotOnClosing(int slot) {
	        if (this.inventory[slot] != null) {
	            ItemStack itemstack = this.inventory[slot];
	            this.inventory[slot] = null;
	            return itemstack;
	        }
	        return null;
	    }

	    @Override
	    public void setInventorySlotContents(int slot, ItemStack itemstack) {
	        this.inventory[slot] = itemstack;

	        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
	            itemstack.stackSize = this.getInventoryStackLimit();
	        }
	    }

	@Override
	public String getInventoryName()
	{	
		 return this.hasCustomInventoryName() ? this.customName : "container.GearExtruder";
	}

	@Override
	public boolean hasCustomInventoryName() {
		 return this.customName != null && !this.customName.equals("");
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		 return true;
	}

	@Override
	public void openInventory() {
		
	}

	@Override
	public void closeInventory() {
		
	}

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
		return true;
	}
	

	public void clearInventory() {
	    for (int i = 0; i < this.getSizeInventory(); i++)
	        this.setInventorySlotContents(i, null);
	}
	
    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        NBTTagList list = nbt.getTagList("Items", 10);
        this.inventory = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < list.tagCount(); i++) {
            NBTTagCompound compound = (NBTTagCompound) list.getCompoundTagAt(i);
            byte b = compound.getByte("Slot");

            if (b >= 0 && b < this.inventory.length) {
                this.inventory[b] = ItemStack.loadItemStackFromNBT(compound);
            }
        }

    }
    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

       

        NBTTagList list = new NBTTagList();

        for (int i = 0; i < this.inventory.length; i++) {
            if (this.inventory[i] != null) {
                NBTTagCompound compound = new NBTTagCompound();
                compound.setByte("Slot", (byte) i);
                this.inventory[i].writeToNBT(compound);
                list.appendTag(compound);
            }
        }

        nbt.setTag("Items", list);
        if (this.hasCustomInventoryName()) {
            nbt.setString("CustomName", this.customName);
        }
    }


	public int getCookProgressScaled(int i) {
		return 0;
	}

	@Override
	public void markDirty() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
	
}