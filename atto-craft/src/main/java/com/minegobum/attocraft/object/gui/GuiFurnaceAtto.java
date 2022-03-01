package com.minegobum.attocraft.object.gui;

import com.minegobum.attocraft.object.container.ContainerFurnaceAtto;
import com.minegobum.attocraft.object.tileentity.TileEntityFurnaceAtto;
import com.minegobum.attocraft.references.References;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiFurnaceAtto extends GuiContainer {

	private static final ResourceLocation TEXTURES = new ResourceLocation(References.MODID + ":textures/gui/gui_furnace_atto.png");
	private final InventoryPlayer player;
	private final TileEntityFurnaceAtto tileEntity;
	
	public GuiFurnaceAtto(InventoryPlayer player, TileEntityFurnaceAtto tileEntity) {
		super(new ContainerFurnaceAtto(player, tileEntity));
		this.player = player; 
		this.tileEntity = tileEntity;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		
		if(tileEntity.IsActive()) {
			int f = GetFuelLeftScale(13);
			this.drawTexturedModalRect(this.guiLeft+35, this.guiTop+36+13-f, 176, 13-f, 13, f);
		}
		int p = GetProgressScale(23);
		this.drawTexturedModalRect(this.guiLeft+57, this.guiTop+34, 176, 14, p, 16);		
		int w = GetWasteScaled(53);
		this.drawTexturedModalRect(this.guiLeft+158, this.guiTop+16+53-w, 176, 31+53-w, 7, w);
		int i = GetInfusionScale(16);
		this.drawTexturedModalRect(this.guiLeft+138, this.guiTop+34, 184, 31, 16, i);
		
		
	}

	private int GetFuelLeftScale(int pixels) {
		return (int) (pixels * (1f*tileEntity.getFuelRemaining()/tileEntity.getFuelLevel()));
	}

	private int GetProgressScale(int pixels) {
		return (int) (pixels * (1f*tileEntity.getSmeltProgress()/tileEntity.GetSmeltSpeed()));
	}
	private int GetWasteScaled(int pixels) {
		return (int) (pixels * (1f*tileEntity.getWasteLevel()/tileEntity.getWasteCapacity()));		
	}
	private int GetInfusionScale(int pixels) {
		return (int) (pixels * (1f*tileEntity.getInfusionProgress()/tileEntity.getInfusionRequire()));
	}
	
}
