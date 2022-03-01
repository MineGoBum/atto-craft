package com.minegobum.attocraft.proxy;

import net.minecraft.item.Item;

public interface IProxy {

	public void registerItemRenderer(Item item, int meta, String id);
	public void registerVariantRenderer(Item item, int meta, String filename, String id);
	
}
