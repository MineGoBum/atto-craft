package com.minegobum.attocraft.utils.compat.jei.infusion;

import com.minegobum.attocraft.references.References;
import com.minegobum.attocraft.utils.compat.jei.RecipeCategories;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.client.Minecraft;

public class TaintInfusionRecipeCategory extends AbstractInfusionRecipeCategory<JEIInfusionRecipe> {

	private final IDrawable background;
	private final String name;
	
	public TaintInfusionRecipeCategory(IGuiHelper helper) {
		super(helper);
		background = helper.createDrawable(TEXTURES, 4, 12, 150, 60);
		name = "Taint Infusion";
	}
	
	@Override
	public IDrawable getBackground() {
		return background;
	}
	
	@Override
	public void drawExtras(Minecraft minecraft) {
		animatedBar.draw(minecraft, 4, 42);
		progressdBar.draw(minecraft, 40, 23);
	}
	
	@Override
	public String getTitle() {
		return name;
	}
	@Override
	public String getModName() {
		return References.NAME;
	}
	@Override
	public String getUid() {
		return RecipeCategories.TAINT_INFUSION;
	}
	
	public void setRecipe(IRecipeLayout recipeLayout, JEIInfusionRecipe recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup stacks = recipeLayout.getItemStacks();
		stacks.init(INPUT, true, 21, 2);
		stacks.init(OUTPUT, false, 21, 42);
		stacks.set(ingredients);
	}
}
