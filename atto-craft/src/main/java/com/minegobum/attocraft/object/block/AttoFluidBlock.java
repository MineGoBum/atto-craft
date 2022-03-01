package com.minegobum.attocraft.object.block;

import com.minegobum.attocraft.init.BlockInit;
import com.minegobum.attocraft.init.ItemInit;
import com.minegobum.attocraft.utils.Utils;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class AttoFluidBlock extends BlockFluidClassic {

	public AttoFluidBlock(String name, Fluid fluid, Material material) {
		super(fluid, material);
		setUnlocalizedName(Utils.Names.GetUnlocalizedName(name));
		setRegistryName(Utils.Names.GetRegistryName(name));
		
		RegisterBlock();
		RegisterItem();
	}
	
	protected void RegisterBlock() {
		BlockInit.BLOCKS.add(this);
	}
	protected void RegisterItem() {
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));		
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
	
	
}
