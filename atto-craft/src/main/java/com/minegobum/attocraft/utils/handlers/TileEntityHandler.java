package com.minegobum.attocraft.utils.handlers;

import com.minegobum.attocraft.object.tileentity.TileEntityFurnaceAtto;
import com.minegobum.attocraft.references.References;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {

	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityFurnaceAtto.class, new ResourceLocation(References.MODID, "furnace_atto"));
	}
	
}
