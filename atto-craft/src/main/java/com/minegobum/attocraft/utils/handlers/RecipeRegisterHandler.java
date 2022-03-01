package com.minegobum.attocraft.utils.handlers;

import com.minegobum.attocraft.init.ItemInit;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RecipeRegisterHandler {

	public static void RegisterInfusionRecipes() {
		InfusionRecipeHandler.RegisterTaintInfusionRecipe(Items.COAL, 1, new ItemStack(ItemInit.COAL_ATTO_DIRTY, 1), 250);
		InfusionRecipeHandler.RegisterTaintInfusionRecipe(Item.getItemFromBlock(Blocks.COBBLESTONE), 1, new ItemStack(ItemInit.POWDER_TAINT, 1), 100);
	}
	
}
