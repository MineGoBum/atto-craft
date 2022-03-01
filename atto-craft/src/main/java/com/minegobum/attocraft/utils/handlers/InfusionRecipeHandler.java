package com.minegobum.attocraft.utils.handlers;

import java.util.HashMap;
import java.util.Map;

import com.minegobum.attocraft.mechanics.infusion.InfusionRecipe;
import com.minegobum.attocraft.mechanics.infusion.InfusionType;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class InfusionRecipeHandler {

	private static Map<Item, InfusionRecipe> EssenceRecipes = new HashMap<Item, InfusionRecipe>();
	private static Map<Item, InfusionRecipe> TaintRecipes = new HashMap<Item, InfusionRecipe>();
	
	public static void RegisterTaintInfusionRecipe(Item input, int count, ItemStack result, int infusionAmmount) {
		TaintRecipes.put(input, new InfusionRecipe(input, result, infusionAmmount, InfusionType.TAINT));
	}
	
	public static InfusionRecipe GetTaintInfusionResult(Item item) {
		if(TaintRecipes.containsKey(item)) return TaintRecipes.get(item);
		return null;
	}

	public static InfusionRecipe GetEssenceInfusionResult(Item item) {
		if(EssenceRecipes.containsKey(item)) return EssenceRecipes.get(item);
		return null;
	}
	
	public static Map<Item, InfusionRecipe> GetTaintInfusionRecipes(){
		return TaintRecipes;
	}
	
}
