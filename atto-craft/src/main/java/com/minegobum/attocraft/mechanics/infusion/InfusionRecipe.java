package com.minegobum.attocraft.mechanics.infusion;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class InfusionRecipe {
	private Item InputItem;
	private int InputCount;
	private ItemStack Result;
	private int InfusionAmmount;
	private InfusionType Type;
	
	public InfusionRecipe(Item inputItem, ItemStack result, int infusionAmmount, InfusionType type) {
		this(inputItem, 1, result, infusionAmmount, type);
	}
	public InfusionRecipe(Item inputItem, int inputCount, ItemStack result, int infusionAmmount, InfusionType type) {
		super();
		InputItem = inputItem;
		InputCount = inputCount;
		Result = result;
		InfusionAmmount = infusionAmmount;
		Type = type;
	}
	public Item getInputItem() {
		return InputItem;
	}
	public int getInputCount() {
		return InputCount;
	}
	public ItemStack getResult() {
		return Result.copy();
	}
	public int getInfusionAmmount() {
		return InfusionAmmount;
	}
	public InfusionType getType() {
		return Type;
	}
	
	
	
	
}
