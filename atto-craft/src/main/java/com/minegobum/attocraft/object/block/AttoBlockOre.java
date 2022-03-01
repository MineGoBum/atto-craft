package com.minegobum.attocraft.object.block;

import java.util.Random;

import com.minegobum.attocraft.AttoCraft;
import com.minegobum.attocraft.init.ItemInit;
import com.minegobum.attocraft.object.block.item.AttoItemBlockVariant;
import com.minegobum.attocraft.utils.interfaces.IHasModel;
import com.minegobum.attocraft.utils.interfaces.IMetaName;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class AttoBlockOre extends AttoBlockBase implements IHasModel, IMetaName{

	public static final PropertyEnum<AttoBlockOre.EnumType> VARIANT = 
			PropertyEnum.<AttoBlockOre.EnumType>create("variant", AttoBlockOre.EnumType.class);
	private String dimension;
	private String name;
	
	public AttoBlockOre(String name, String dimension, int harvestLevel) {
		super("ore_"+name+"_"+dimension, Material.ROCK);
		setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumType.NORMAL));
		this.dimension = dimension;
		this.name = name;

		setSoundType(SoundType.GLASS);
		setHardness(5.0F);
		setResistance(15.0F);
		setHarvestLevel("pickaxe", harvestLevel);
	}

	@Override
	protected void RegisterItem() {
		ItemInit.ITEMS.add(new AttoItemBlockVariant(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public String getSpecialName(ItemStack stack) {		
		return EnumType.values()[stack.getItemDamage()].getName();
	}
	@Override
	public int damageDropped(IBlockState state) {
		return ((EnumType)state.getValue(VARIANT)).getMeta();
	}
	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumType)state.getValue(VARIANT)).getMeta();
	}
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(VARIANT, EnumType.byMetadata(meta));
	}
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
			EntityPlayer player) {
		return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(state));
	}
	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		for(EnumType variant: EnumType.values()) {
			items.add(new ItemStack(this, 1, variant.getMeta()));
		}
	}
	@Override
	protected BlockStateContainer createBlockState() {		
		return new BlockStateContainer(this,  new IProperty[] { VARIANT });
	}
	@Override
	public void registerModel() {
		for(int i = 0; i < EnumType.values().length; i++) {
			AttoCraft.proxy.registerVariantRenderer(Item.getItemFromBlock(this), i, "ore_"+name+"_"+dimension+"_"+EnumType.values()[i].getName(), "inventory");
		}
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return ItemInit.ITEM_ATTO;
	}	
	@Override
	public int quantityDropped(IBlockState state, int fortune, Random random) {
		return getMetaFromState(state)*3+1;
	}
	
	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state,
			int fortune) {
		int meta = getMetaFromState(state);
		int chunks = 0;
		int dusts = 0;
		Random rnd = new Random(System.nanoTime());
		switch(meta) {
			case 0:
				chunks = 0;
				dusts = 1;
				break;
			case 1:
				chunks = rnd.nextInt(2);
				dusts = 1+rnd.nextInt(2);
				break;
			case 2:
				chunks = 1+rnd.nextInt(3);
				dusts = 2+rnd.nextInt(3);
				break;
		}
		if(chunks > 0)
			drops.add(new ItemStack(ItemInit.ITEM_ATTO, chunks));
		if(dusts > 0)
			drops.add(new ItemStack(ItemInit.DUST_ATTO, dusts));
	}
	
	public static enum EnumType implements IStringSerializable{
		POOR(0, "poor"),
		NORMAL(1, "normal"),
		DENSE(2, "dense");

		private static final EnumType[] META_LOOKUP = new EnumType[values().length];
		private final int meta;
		private final String name, unlocalizedName;
		
		private EnumType(int meta, String name) {
			this(meta, name, name);
		}

		private EnumType(int meta, String name, String unlocalizedName) {
			this.meta = meta;
			this.name = name;
			this.unlocalizedName = unlocalizedName;
		}
		
		@Override
		public String getName() {
			return this.name;
		}
		public int getMeta() {
			return this.meta;
		}
		public String getUnlocalizedName() {
			return this.unlocalizedName;
		}
		
		@Override
		public String toString() {
			return this.name;
		}
		
		public static EnumType byMetadata(int meta) {
			return META_LOOKUP[meta];
		}
		
		static {
			for(EnumType type: values()){
				META_LOOKUP[type.getMeta()] = type;
			}
		}
	}	
}
