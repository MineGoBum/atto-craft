package com.minegobum.attocraft.init;

import java.util.ArrayList;
import java.util.List;

import com.minegobum.attocraft.energy.AttoEssence;
import com.minegobum.attocraft.object.block.AttoBlockBase;
import com.minegobum.attocraft.object.block.AttoBlockOre;
import com.minegobum.attocraft.object.block.AttoFluidBlock;
import com.minegobum.attocraft.object.block.machines.AttoBlockFurnace;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockInit {

	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block BLOCK_ATTO = new AttoBlockBase("block_atto", Material.ROCK, AttoEssence.FromBaseUnit(9*4f, 10), 1.5f, 10f);
	
	public static final Block BLOCK_COAL_ATTO = new AttoBlockBase("block_coal_atto", Material.ROCK, AttoEssence.FromBaseUnit(9*16f, 7.5f), 0.75f, 10f);
	public static final Block BLOCK_COAL_ATTO_PURE = new AttoBlockBase("block_coal_atto_pure", Material.ROCK, AttoEssence.FromBaseUnit(9*16f, 0), 0.5f, 10f);
	public static final Block BLOCK_COAL_ATTO_DIRTY = new AttoBlockBase("block_coal_atto_dirty", Material.ROCK, AttoEssence.FromBaseUnit(9*8f, 17.5f), 1.5f, 10f);
	
	public static final Block ORE_ATTO_OVERWORLD = new AttoBlockOre("atto", "overworld", 1);
	
	public static final Block FURNACE_ATTO = new AttoBlockFurnace("furnace_atto");
	
	public static final Block BLOCK_FLUID_TAINT = new AttoFluidBlock("block_fluid_taint", FluidInit.FLUID_TAINT, Material.WATER);
}
