package com.minegobum.attocraft.utils.handlers;

import java.util.Map;

import com.minegobum.attocraft.init.BlockInit;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;

public class RenderHandler {

	public static void registerCustomMeshesAndStates() {
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(BlockInit.BLOCK_FLUID_TAINT), new ItemMeshDefinition() {
			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return new ModelResourceLocation("attocraft:fluid_taint", "fluid");
			}
		});
		
		ModelLoader.setCustomStateMapper(BlockInit.BLOCK_FLUID_TAINT, new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return new ModelResourceLocation("attocraft:fluid_taint", "fluid");
			}
		});
	}
	
}
