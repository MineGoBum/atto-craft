package com.minegobum.attocraft.utils.compat.jei.infusion;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.minegobum.attocraft.mechanics.infusion.InfusionRecipe;
import com.minegobum.attocraft.utils.handlers.InfusionRecipeHandler;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class InfusionRecipeMaker {

	public static List<JEIInfusionRecipe> getRecipes(IJeiHelpers helper){
		IStackHelper stackHelper = helper.getStackHelper();

		List<JEIInfusionRecipe> jeiRecipes = Lists.newArrayList();
		Map<Item, InfusionRecipe> recipes = InfusionRecipeHandler.GetTaintInfusionRecipes();
		for(InfusionRecipe recipe: recipes.values()) {
			ItemStack input = new ItemStack(recipe.getInputItem());
			ItemStack output = recipe.getResult();
			JEIInfusionRecipe jeiRecipe = new JEIInfusionRecipe(input, output);
			jeiRecipes.add(jeiRecipe);
		}
		
		return jeiRecipes;
	}
	
}
