package com.minegobum.attocraft.tabs;

import com.minegobum.attocraft.init.ItemInit;
import com.minegobum.attocraft.references.References;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class AttoGeneralTab extends CreativeTabs {

	public AttoGeneralTab(String label) {
		super(References.MODID + ":"+ label);
	}

	public ItemStack getTabIconItem() {
		return new ItemStack(ItemInit.ITEM_ATTO);
	}

}
