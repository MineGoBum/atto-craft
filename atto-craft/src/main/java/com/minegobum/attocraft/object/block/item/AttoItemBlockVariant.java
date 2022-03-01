package com.minegobum.attocraft.object.block.item;

import com.minegobum.attocraft.utils.interfaces.IMetaName;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class AttoItemBlockVariant extends ItemBlock {

	public AttoItemBlockVariant(Block block) {
		super(block);
		setHasSubtypes(true);
		setMaxDamage(0);
	}
	
	@Override
	public int getMetadata(int damage) {
		return damage;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName()+"_"+((IMetaName)this.block).getSpecialName(stack);
	}

	
	
}
