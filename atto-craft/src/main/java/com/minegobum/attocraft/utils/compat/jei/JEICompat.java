package com.minegobum.attocraft.utils.compat.jei;

import java.util.IllegalFormatException;

import com.minegobum.attocraft.object.gui.GuiFurnaceAtto;
import com.minegobum.attocraft.utils.compat.jei.infusion.InfusionRecipeMaker;
import com.minegobum.attocraft.utils.compat.jei.infusion.TaintInfusionRecipeCategory;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientRegistry;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.util.text.translation.I18n;

//@JEIPlugin
public class JEICompat implements IModPlugin{

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		final IJeiHelpers helpers = registry.getJeiHelpers();
		final IGuiHelper gui = helpers.getGuiHelper();
		
		registry.addRecipeCategories(new TaintInfusionRecipeCategory(gui));
	}
	
	@Override
	public void register(IModRegistry registry) {
		final IIngredientRegistry ingredientRegistry = registry.getIngredientRegistry();
		final IJeiHelpers jeiHelpers = registry.getJeiHelpers();
		IRecipeTransferRegistry recipeTransfer = registry.getRecipeTransferRegistry();
		
		registry.addRecipes(InfusionRecipeMaker.getRecipes(jeiHelpers), RecipeCategories.TAINT_INFUSION);
		registry.addRecipeClickArea(GuiFurnaceAtto.class, 110, 0, 50, 50, RecipeCategories.TAINT_INFUSION);
		recipeTransfer.addRecipeTransferHandler(ContainerFurnace.class, RecipeCategories.TAINT_INFUSION, 3, 4, 5, 36);
	}
	
	public static String translateToLocal(String key) {
		if(I18n.canTranslate(key)) return I18n.translateToLocal(key);
		else return I18n.translateToFallback(key);
	}
	
	public static String translateToLocalFormatted(String key, Object... format) {
		String s = translateToLocal(key);
		try {
			return String.format(s, format);
		}catch(IllegalFormatException e) {
			return "Format error: "+s;
		}
	}
}
