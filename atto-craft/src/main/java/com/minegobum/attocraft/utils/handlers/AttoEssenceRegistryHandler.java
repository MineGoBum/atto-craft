package com.minegobum.attocraft.utils.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.minegobum.attocraft.energy.AttoEssence;
import com.minegobum.attocraft.energy.IAttoEssenceProvider;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AttoEssenceRegistryHandler {

	
	private static Map<Item, AttoEssence> Mappings = new HashMap<Item, AttoEssence>();
	private static List<Block> RegisteredBlocks = new ArrayList<Block>();
	private static List<Item> RegisteredItems = new ArrayList<Item>();
	
	public static void RegisterAttoFuelItem(Item item, AttoEssence essence) {
		if(item instanceof IAttoEssenceProvider) {
			RegisteredItems.add(item);
		}
	}
	public static void RegisterAttoFuelBlock(Block block, AttoEssence essence) {
		if(block instanceof IAttoEssenceProvider) {
			RegisteredBlocks.add(block);
		}
	}
	
	public static void FinalizeRegistries() {
		for(Block block: RegisteredBlocks) {
			if(block instanceof IAttoEssenceProvider) {
				Mappings.put(Item.getItemFromBlock(block), ((IAttoEssenceProvider)block).GetAttoEssence().Copy());
			}
		}
		RegisteredBlocks.clear();
		for(Item item: RegisteredItems) {
			if(item instanceof IAttoEssenceProvider) {
				Mappings.put(item, ((IAttoEssenceProvider)item).GetAttoEssence().Copy());
			}
		}
			RegisteredItems.clear();
	}
	
	public static int GetAttoFuelValue(ItemStack stack) {
		AttoEssence essence = Mappings.get(stack.getItem());
		if(essence != null) {
			return essence.getEssence();
		}
		return 0;
	}
	
	public static int GetAttoFuelWaste(ItemStack stack) {
		AttoEssence essence = Mappings.get(stack.getItem());
		if(essence != null) {
			return essence.getTaint();
		}
		return 0;
	}
	
	public static boolean IsItemAttoFuel(ItemStack stack) {
		return GetAttoFuelValue(stack) > 0;
	}
	
}
