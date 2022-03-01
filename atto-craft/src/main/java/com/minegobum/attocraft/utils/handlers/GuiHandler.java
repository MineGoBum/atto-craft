package com.minegobum.attocraft.utils.handlers;

import com.minegobum.attocraft.object.container.ContainerFurnaceAtto;
import com.minegobum.attocraft.object.gui.GuiFurnaceAtto;
import com.minegobum.attocraft.object.tileentity.TileEntityFurnaceAtto;
import com.minegobum.attocraft.references.GuiReferences;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == GuiReferences.GUI_FURNACE_ATTO_ID) return new ContainerFurnaceAtto(player.inventory, (TileEntityFurnaceAtto)world.getTileEntity(new BlockPos(x,y,z)));
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == GuiReferences.GUI_FURNACE_ATTO_ID) return new GuiFurnaceAtto(player.inventory, (TileEntityFurnaceAtto)world.getTileEntity(new BlockPos(x,y,z)));
		return null;
	}

}