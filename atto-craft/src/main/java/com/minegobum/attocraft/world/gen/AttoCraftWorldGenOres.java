package com.minegobum.attocraft.world.gen;

import java.util.Random;

import com.minegobum.attocraft.object.block.AttoBlockOre;

import net.minecraft.block.Block;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class AttoCraftWorldGenOres implements IWorldGenerator {

	private WorldGenerator dense_generator, normal_generator, poor_generator;
	
	public AttoCraftWorldGenOres(Block block) {
		dense_generator = new WorldGenMinable(block.getDefaultState().withProperty(AttoBlockOre.VARIANT, AttoBlockOre.EnumType.DENSE), 1, BlockMatcher.forBlock(Blocks.STONE));
		normal_generator = new WorldGenMinable(block.getDefaultState().withProperty(AttoBlockOre.VARIANT, AttoBlockOre.EnumType.NORMAL), 4, BlockMatcher.forBlock(Blocks.STONE));
		poor_generator = new WorldGenMinable(block.getDefaultState().withProperty(AttoBlockOre.VARIANT, AttoBlockOre.EnumType.POOR), 8, BlockMatcher.forBlock(Blocks.STONE));
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		switch(world.provider.getDimension()) {
		case -1:break;
		case 0:
			runGenerator(normal_generator, world, random, chunkX, chunkZ, 50, 0, 100); 
			runGenerator(dense_generator, world, random, chunkX, chunkZ, 50, 0, 100); 
			runGenerator(poor_generator, world, random, chunkX, chunkZ, 50, 0, 100); 
			break;
		case 1:break;
		}
	}
	
	private void runGenerator(WorldGenerator gen, World world, Random rand, int chunkX, int chunkZ, int chance, int minHeight, int maxHeight) {
		if(minHeight > maxHeight || minHeight < 0 || maxHeight > 256) throw new IllegalArgumentException("Ore generated out ob bounds.");
		
		int heightDiff = maxHeight - minHeight + 1;
		
		for( int i = 0; i < chance; i++) {
			int x = chunkX*16 + rand.nextInt(16);
			int z = chunkZ*16 + rand.nextInt(16);
			int y = minHeight + rand.nextInt(heightDiff);
			gen.generate(world, rand, new BlockPos(x, y, z));
		}
	}

}
