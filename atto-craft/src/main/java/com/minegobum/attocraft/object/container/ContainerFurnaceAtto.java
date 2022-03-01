package com.minegobum.attocraft.object.container;

import com.minegobum.attocraft.AttoCraft;
import com.minegobum.attocraft.object.container.slot.AttoFuelSlotItemHandler;
import com.minegobum.attocraft.object.container.slot.AttoOutputSlotItemHandler;
import com.minegobum.attocraft.object.tileentity.TileEntityFurnaceAtto;
import com.minegobum.attocraft.utils.handlers.AttoEssenceRegistryHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerFurnaceAtto extends Container {

	private final TileEntityFurnaceAtto tileEntity;
	
	private int fuel_level, fuel_remaining, smelt_progress, waste_level, infusion_require, infusion_progress;

	public static final int ITEM_INPUT_SLOT_INDEX = 0;
	public static final int FUEL_INPUT_SLOT_INDEX = 1;
	public static final int ITEM_OUTPUT_SLOT_INDEX = 2;
	public static final int INFUSION_INPUT_SLOT_INDEX = 3;
	public static final int INFUSION_OUTPUT_SLOT_INDEX = 4;

	public final int INVENTORY_FIRST_SLOT_INDEX = 5;
	public final int HOTBAR_FIRST_SLOT_INDEX = 32;
		
	public ContainerFurnaceAtto(InventoryPlayer player, TileEntityFurnaceAtto tileEntity) {
		this.tileEntity = tileEntity;
		IItemHandler handler = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

		this.addSlotToContainer(new SlotItemHandler(handler, 0, 34, 17)); //Item IN
		this.addSlotToContainer(new AttoFuelSlotItemHandler(handler, 1, 34, 53)); //Fuel IN
		this.addSlotToContainer(new AttoOutputSlotItemHandler(handler, 2, 94, 35)); //Item OUT
		this.addSlotToContainer(new SlotItemHandler(handler, 3, 139, 17)); //Side IN
		this.addSlotToContainer(new AttoOutputSlotItemHandler(handler, 4, 139, 53)); //Side OUT
		
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				this.addSlotToContainer(new Slot(player, x + y*9 +9, 8 + x*18, 84 + y*18));
			}
		}
		for (int x = 0; x < 9; x++) {
			this.addSlotToContainer(new Slot(player, x, 8 + x*18, 142));
		}
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		
		for (int i = 0; i < this.listeners.size(); i++) {
			IContainerListener listener = (IContainerListener)this.listeners.get(i);

			if(this.waste_level != this.tileEntity.getWasteLevel()) listener.sendWindowProperty(this, TileEntityFurnaceAtto.FIELD_INDEX_WASTE, this.tileEntity.getWasteLevel());
			if(this.smelt_progress != this.tileEntity.getSmeltProgress()) listener.sendWindowProperty(this, TileEntityFurnaceAtto.FIELD_INDEX_PROGRESS, this.tileEntity.getSmeltProgress());
			if(this.fuel_level != this.tileEntity.getFuelLevel()) listener.sendWindowProperty(this, TileEntityFurnaceAtto.FIELD_INDEX_FUEL_LVL, this.tileEntity.getFuelLevel());
			if(this.fuel_remaining != this.tileEntity.getFuelRemaining()) listener.sendWindowProperty(this, TileEntityFurnaceAtto.FIELD_INDEX_FUEL_REM, this.tileEntity.getFuelRemaining());
			if(this.infusion_progress != this.tileEntity.getInfusionProgress()) listener.sendWindowProperty(this, TileEntityFurnaceAtto.FIELD_INDEX_INFUSUION_PROGRESS, this.tileEntity.getInfusionProgress());
			if(this.infusion_require != this.tileEntity.getInfusionRequire()) listener.sendWindowProperty(this, TileEntityFurnaceAtto.FIELD_INDEX_INFUSUION_REQUIREMENT, this.tileEntity.getInfusionRequire());
		}

		this.waste_level = this.tileEntity.getWasteLevel();
		this.smelt_progress = this.tileEntity.getSmeltProgress();
		this.fuel_level = this.tileEntity.getFuelLevel();
		this.fuel_remaining = this.tileEntity.getFuelRemaining();
		this.infusion_progress = this.tileEntity.getInfusionProgress();
		this.infusion_require = this.tileEntity.getInfusionRequire();
		
		
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.tileEntity.isUsableByPlayer(playerIn);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		this.tileEntity.setField(id, data);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		AttoCraft.logger.info(index+"");
		
		Slot fromSlot = inventorySlots.get(index);
		ItemStack stack = fromSlot.getStack();
		
		if(fromSlot == null || !fromSlot.getHasStack()) return ItemStack.EMPTY;
		
		if(index < INVENTORY_FIRST_SLOT_INDEX) {
			mergeItemStack(stack, INVENTORY_FIRST_SLOT_INDEX, HOTBAR_FIRST_SLOT_INDEX+9, false);
		}else {
			boolean tryPlaceInput = true;
			if(AttoEssenceRegistryHandler.IsItemAttoFuel(stack)) {
				tryPlaceInput &= !mergeItemStack(stack, FUEL_INPUT_SLOT_INDEX, FUEL_INPUT_SLOT_INDEX+1, false);
			}
			if(tryPlaceInput)
				mergeItemStack(stack, ITEM_INPUT_SLOT_INDEX, ITEM_INPUT_SLOT_INDEX+1, false);
		}
		
		
		
		//fromSlot.onTake(playerIn, stack);
		return ItemStack.EMPTY;
	}
	

}
