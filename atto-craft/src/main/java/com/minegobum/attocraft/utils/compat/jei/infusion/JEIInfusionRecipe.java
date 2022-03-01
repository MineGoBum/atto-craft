package com.minegobum.attocraft.utils.compat.jei.infusion;

import java.awt.Color;
import java.util.List;

import com.minegobum.attocraft.utils.compat.jei.JEICompat;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;

public class JEIInfusionRecipe implements IRecipeWrapper {

	private final ItemStack input;
	private final ItemStack output;	
	
	
	public JEIInfusionRecipe(ItemStack input, ItemStack output) {
		this.input = input;
		this.output = output;
	}



	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInput(ItemStack.class, input);
		ingredients.setOutput(ItemStack.class, output);
	}
	
	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		String s = JEICompat.translateToLocalFormatted("gui.jei.category.smelting.experience", 15);
		FontRenderer renderer = minecraft.fontRenderer;
		int stringWidth = renderer.getStringWidth(s);
		renderer.drawString(s, recipeWidth-stringWidth, 0, Color.GRAY.getRGB());
	}
	
}
