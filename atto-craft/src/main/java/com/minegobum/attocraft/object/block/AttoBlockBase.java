package com.minegobum.attocraft.object.block;

import com.minegobum.attocraft.AttoCraft;
import com.minegobum.attocraft.energy.AttoEssence;
import com.minegobum.attocraft.energy.IAttoEssenceProvider;
import com.minegobum.attocraft.init.BlockInit;
import com.minegobum.attocraft.init.ItemInit;
import com.minegobum.attocraft.utils.Utils;
import com.minegobum.attocraft.utils.handlers.AttoEssenceRegistryHandler;
import com.minegobum.attocraft.utils.interfaces.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class AttoBlockBase extends Block implements IHasModel, IAttoEssenceProvider {

	private AttoEssence Essence;
	
	public AttoBlockBase(String name, Material materialIn) {
		this(name, materialIn, null, 1, 0);
	}
	public AttoBlockBase(String name, Material materialIn, float hardness) {
		this(name, materialIn, null, hardness, 0);
	}
	public AttoBlockBase(String name, Material materialIn, float hardness, float resistance) {
		this(name, materialIn, null, hardness, resistance);
	}
	
	public AttoBlockBase(String name, Material materialIn, AttoEssence essence) {
		this(name, materialIn, essence, 1, 0);
	}
	public AttoBlockBase(String name, Material materialIn, AttoEssence essence, float hardness) {
		this(name, materialIn, essence, hardness, 0);
	}
	
	public AttoBlockBase(String name, Material materialIn, AttoEssence essence, float hardness, float resistance) {
		super(materialIn);
		setUnlocalizedName(Utils.Names.GetUnlocalizedName(name));
		setRegistryName(Utils.Names.GetRegistryName(name));
		setHardness(hardness);
		setResistance(resistance);
		
		Essence = essence;
		if(Essence != null && Essence.getEssence() > 0) {
			AttoEssenceRegistryHandler.RegisterAttoFuelBlock(this, Essence);
		}
			
		setCreativeTab(AttoCraft.GeneralTab);
		
		RegisterBlock();
		RegisterItem();
	}

	protected void RegisterBlock() {
		BlockInit.BLOCKS.add(this);
	}
	protected void RegisterItem() {
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));		
	}
	
	@Override
	public void registerModel() {
		AttoCraft.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");		
	}
	@Override
	public AttoEssence GetAttoEssence() {
		return Essence;
	}

	
	
}
