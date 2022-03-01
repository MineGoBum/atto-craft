package com.minegobum.attocraft.object.item;

import com.minegobum.attocraft.AttoCraft;
import com.minegobum.attocraft.energy.AttoEssence;
import com.minegobum.attocraft.energy.IAttoEssenceProvider;
import com.minegobum.attocraft.init.ItemInit;
import com.minegobum.attocraft.utils.Utils;
import com.minegobum.attocraft.utils.handlers.AttoEssenceRegistryHandler;
import com.minegobum.attocraft.utils.interfaces.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class AttoItemBase extends Item implements IHasModel, IAttoEssenceProvider {

	private AttoEssence Essence;
	
	public AttoItemBase(String name) {
		this(name, AttoEssence.Empty());
	}
	public AttoItemBase(String name, AttoEssence essence) {
		setUnlocalizedName(Utils.Names.GetUnlocalizedName(name));
		setRegistryName(Utils.Names.GetRegistryName(name));

		Essence = essence;	
		if(Essence != null && Essence.getEssence() > 0) {
			AttoEssenceRegistryHandler.RegisterAttoFuelItem(this, Essence);
		}

		setCreativeTab(AttoCraft.GeneralTab);
		
		registerItem();
	}

	protected void registerItem() {
		ItemInit.ITEMS.add(this);		
	}
	
	@Override
	public void registerModel() {
		AttoCraft.proxy.registerItemRenderer(this, 0, "inventory");
	}

	@Override
	public AttoEssence GetAttoEssence() {
		return Essence;
	}
	
	
}
