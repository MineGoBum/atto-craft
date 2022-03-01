package com.minegobum.attocraft.object.item;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.minecraft.item.ItemStack;

public class AttoItemUtility extends AttoItemBase {

	private final int durability;
	
	public AttoItemUtility(String name, int durability) {
		super(name);
		setMaxStackSize(1);
		this.durability = durability;
	}
	
	@Override
	public int getMaxDamage(ItemStack stack) {
		return durability;
	}
	
	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack) {
		ItemStack newStack = itemStack.copy();
		newStack.setItemDamage(newStack.getItemDamage()+1);	
		return newStack;
	}

}
