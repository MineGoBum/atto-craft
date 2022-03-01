package com.minegobum.attocraft.init;

import java.util.ArrayList;
import java.util.List;

import com.minegobum.attocraft.energy.AttoEssence;
import com.minegobum.attocraft.object.item.AttoItemBase;
import com.minegobum.attocraft.object.item.AttoItemUtility;

import net.minecraft.item.Item;

public class ItemInit {

	public static final List<Item> ITEMS = new ArrayList<Item>();

	// BASIC ITEMS
	public static Item ITEM_ATTO = new AttoItemBase("item_atto", AttoEssence.FromBaseUnit(4f, 10));
	public static Item DUST_ATTO = new AttoItemBase("dust_atto", AttoEssence.FromBaseUnit(2.5f, 7.5f));
	public static Item POWDER_TAINT = new AttoItemBase("powder_taint", AttoEssence.FromBaseUnit(10, 100));
	public static Item SILICON_ATTO = new AttoItemBase("compound_atto", AttoEssence.FromBaseUnit(4, 15));

	public static Item INGOT_ATTO_PURE = new AttoItemBase("ingot_atto_pure");
	public static Item INGOT_ATTO_IRON = new AttoItemBase("ingot_atto_iron");
	public static Item INGOT_ATTO_GOLD = new AttoItemBase("ingot_atto_gold");
	
	// FUEL ITEMS
	public static Item COAL_ATTO_PURE = new AttoItemBase("coal_atto_pure", AttoEssence.FromBaseUnit(16f, 0));
	public static Item COAL_ATTO = new AttoItemBase("coal_atto", AttoEssence.FromBaseUnit(16f, 5));
	public static Item COAL_ATTO_DIRTY = new AttoItemBase("coal_atto_dirty", AttoEssence.FromBaseUnit(8f, 15));
	
	// UTILITY ITEMS
	public static Item ITEM_MORTAR = new AttoItemUtility("utility_mortar", 16);
	
}
