package com.minegobum.attocraft.object.fluids;

import com.minegobum.attocraft.utils.Utils;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class AttoFluid extends Fluid {

	public AttoFluid(String fluidName, ResourceLocation still, ResourceLocation flowing) {
		super(fluidName, still, flowing);
		
		setUnlocalizedName(Utils.Names.GetUnlocalizedName(fluidName));
	}

}
