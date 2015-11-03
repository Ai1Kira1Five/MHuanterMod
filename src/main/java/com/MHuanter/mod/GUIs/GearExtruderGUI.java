package com.MHuanter.mod.GUIs;

import org.lwjgl.opengl.GL11;

import com.MHuanter.mod.ModulMach;
import com.MHuanter.mod.Containes.ContainerGearExtruder;
import com.MHuanter.mod.TileEntities.TileEntityGearExtruder;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GearExtruderGUI extends GuiContainer{

	private IInventory playerInv;
	private TileEntityGearExtruder te;
	 public static final ResourceLocation bground = new ResourceLocation(ModulMach.MODID + ":" + "textures/gui/GearExtruderGui.png");
	public GearExtruderGUI(IInventory playerInv, TileEntityGearExtruder entity) {
	    super (new ContainerGearExtruder(playerInv, entity));

        this.playerInv = entity;

	    this.xSize = 176;
	    this.ySize = 166;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) 
	{
		  GL11.glColor4f(1F, 1F, 1F, 1F);

	        Minecraft.getMinecraft().getTextureManager().bindTexture(bground);
	        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

	        if (this.te.isBurning()) {
	            int k = this.te.getBurnTimeRemainingScaled(14);
	            int j = 14 - k;
	            drawTexturedModalRect(guiLeft + 43, guiTop + 65 + j, 176, j, 14, k);
	            drawTexturedModalRect(guiLeft + 121, guiTop + 65 + j, 176, j, 14, k);
	            //drawTexturedModalRect(guiLeft + 29, guiTop + 65, 176, 0, 40 - j, 10);
	        }

	        int k = this.te.getCookProgressScaled(24);
	        drawTexturedModalRect(guiLeft + 77, guiTop + 35, 176, 14, k, 16);

	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) 
	{
		 String name = "Gear Extruder";

	        this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2, 6, 4210752);
	}

	@Override
    public void initGui() {
        buttonList.clear();
    }
	
	 @Override
	    public boolean doesGuiPauseGame() {
	        return false;
	    }
}
