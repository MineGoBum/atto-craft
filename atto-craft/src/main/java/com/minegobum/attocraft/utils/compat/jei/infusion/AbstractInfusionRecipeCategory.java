package com.minegobum.attocraft.utils.compat.jei.infusion;

import com.minegobum.attocraft.references.References;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.util.ResourceLocation;

public abstract class AbstractInfusionRecipeCategory<T extends IRecipeWrapper> implements IRecipeCategory<T> {
	
	protected static final ResourceLocation TEXTURES = new ResourceLocation(References.MODID + ":textures/gui/gui_furnace_atto.png");
	
	protected static final int INPUT = 0;
	protected static final int OUTPUT = 1;

	protected final IDrawableStatic staticBar;
	protected final IDrawableAnimated animatedBar;
	protected final IDrawableAnimated progressdBar;
	
	public AbstractInfusionRecipeCategory(IGuiHelper helper) {
		staticBar = helper.createDrawable(TEXTURES, 176, 0, 14, 14);
		animatedBar = helper.createAnimatedDrawable(staticBar, 300, IDrawableAnimated.StartDirection.TOP, true);
		
		IDrawableStatic staticProgress = helper.createDrawable(TEXTURES, 176, 14, 24, 17);		
		progressdBar = helper.createAnimatedDrawable(staticBar, 200, IDrawableAnimated.StartDirection.LEFT, false);
	}
	
}
