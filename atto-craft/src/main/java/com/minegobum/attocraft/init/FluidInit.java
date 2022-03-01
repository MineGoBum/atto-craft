package com.minegobum.attocraft.init;

import com.minegobum.attocraft.object.fluids.AttoFluid;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidInit {

	public static final Fluid FLUID_TAINT = new AttoFluid("fluid_taint", new ResourceLocation("attocraft:blocks/fluid_taint_still"), new ResourceLocation("attocraft:blocks/fluid_taint_flow"));
	
	public static void RegisterFluids() {
		RegisterFluid(FLUID_TAINT);
	}
	
	public static void RegisterFluid(Fluid fluid) {
		FluidRegistry.registerFluid(fluid);
		FluidRegistry.addBucketForFluid(fluid);
	}
}
