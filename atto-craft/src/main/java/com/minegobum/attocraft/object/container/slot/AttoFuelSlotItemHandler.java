package com.minegobum.attocraft.object.container.slot;

import com.minegobum.attocraft.utils.handlers.AttoEssenceRegistryHandler;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class AttoFuelSlotItemHandler extends SlotItemHandler {

	public AttoFuelSlotItemHandler(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return AttoEssenceRegistryHandler.IsItemAttoFuel(stack);
	}

}
