package com.minegobum.attocraft.object.tileentity;

import javax.annotation.Nullable;

import com.minegobum.attocraft.AttoCraft;
import com.minegobum.attocraft.init.FluidInit;
import com.minegobum.attocraft.mechanics.infusion.InfusionRecipe;
import com.minegobum.attocraft.object.block.machines.AttoBlockFurnace;
import com.minegobum.attocraft.object.container.ContainerFurnaceAtto;
import com.minegobum.attocraft.utils.Utils;
import com.minegobum.attocraft.utils.handlers.AttoEssenceRegistryHandler;
import com.minegobum.attocraft.utils.handlers.InfusionRecipeHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityFurnaceAtto extends TileEntity implements ITickable {

	private ItemStackHandler inventory = new ItemStackHandler(5);
	private String customName;

	public static final int FIELD_INDEX_PROGRESS = 0;
	public static final int FIELD_INDEX_FUEL_LVL = 1;
	public static final int FIELD_INDEX_FUEL_REM = 2;
	public static final int FIELD_INDEX_WASTE = 3;
	public static final int FIELD_INDEX_INFUSUION_PROGRESS = 4;
	public static final int FIELD_INDEX_INFUSUION_REQUIREMENT = 5;
	
	private int fuel_level;
	private int fuel_remaining;
	private int smelt_progress;
	private int waste_level;
	private int infusion_progress;
	private int infusion_require;
	
	private final int WASTE_CAPACITY = 4000;
	private final int BASE_SMELT_SPEED = 100;
	private final int PENALTY_SMELT_SPEED = 120;
	
	public boolean IsActive() { 
		return fuel_remaining > 0; 
	}
	public int getFuelLevel() {
		return fuel_level;
	}
	public int getFuelRemaining() {
		return fuel_remaining;
	}
	public int getWasteLevel() {
		return waste_level;
	}
	public int getSmeltProgress() {
		return smelt_progress;
	}
	public int getWasteCapacity() {
		return WASTE_CAPACITY;
	}
	public int GetSmeltSpeed() {
		return BASE_SMELT_SPEED + Math.round(PENALTY_SMELT_SPEED*((1f*waste_level)/WASTE_CAPACITY));
	}
	public int getInfusionProgress() {
		return infusion_progress;
	}
	public int getInfusionRequire() {
		return infusion_require;
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T)this.inventory;
		return super.getCapability(capability, facing);
	}
	
	public boolean hasCustomName(){
		return this.customName != null && !this.customName.isEmpty();
	}
	
	public void setCustomName(String customName) {
		this.customName = customName;
	}
	
	@Override
	public ITextComponent getDisplayName() {
		return this.hasCustomName() ? new TextComponentString(this.customName) : new TextComponentTranslation("container.furtnace_atto");
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.inventory.deserializeNBT(compound.getCompoundTag("Inventory"));
		
		this.fuel_level = compound.getInteger("fuel_level");
		this.fuel_remaining = compound.getInteger("fuel_remaining");
		this.smelt_progress = compound.getInteger("smelt_progress");
		this.waste_level = compound.getInteger("waste_level");
		this.infusion_progress = compound.getInteger("infusion_progress");
		
		if(compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setTag("Inventory", this.inventory.serializeNBT());

		compound.setInteger("fuel_level", this.fuel_level);
		compound.setInteger("fuel_remaining", this.fuel_remaining);
		compound.setInteger("smelt_progress", this.smelt_progress);
		compound.setInteger("waste_level", this.waste_level);
		compound.setInteger("infusion_progress", this.infusion_progress);
		
		if(this.hasCustomName()) compound.setString("CustomName", this.customName);
		return compound;
	}
	
	
	public boolean isUsableByPlayer(EntityPlayer player) {
		return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX()+0.5D, (double)this.pos.getY()+0.5D, (double)this.pos.getZ()+0.5D) <= 64.0D;
	}
	
	public int getField(int id) {
		switch(id) {
			case FIELD_INDEX_PROGRESS:
				return smelt_progress;
			case FIELD_INDEX_FUEL_LVL:
				return fuel_level;
			case FIELD_INDEX_FUEL_REM:
				return fuel_remaining;
			case FIELD_INDEX_WASTE:
				return waste_level;
			case FIELD_INDEX_INFUSUION_PROGRESS:
				return infusion_progress;
			case FIELD_INDEX_INFUSUION_REQUIREMENT:
				return infusion_require;
			default: 
				return 0;
		}
	}
	
	public void setField(int id, int value) {
		switch(id) {
			case FIELD_INDEX_PROGRESS:
				smelt_progress = value; break;
			case FIELD_INDEX_FUEL_LVL:
				fuel_level = value; break;
			case FIELD_INDEX_FUEL_REM:
				fuel_remaining = value; break;
			case FIELD_INDEX_WASTE:
				waste_level = value; break;
			case FIELD_INDEX_INFUSUION_PROGRESS:
				infusion_progress = value; break;
			case FIELD_INDEX_INFUSUION_REQUIREMENT:
				infusion_require = value; break;
		}
	}
	
	private int StateUpdateTick = 0;
	@Override
	public void update() {
		if(world.isRemote) return; //world.isRemote == CLIENT
		
		boolean innitialActiveState = IsActive();
		if(CanSmelt()) {
			if(IsActive()) {
				fuel_remaining--;
				smelt_progress++;
			}
			else {
				ItemStack fuel = inventory.getStackInSlot(ContainerFurnaceAtto.FUEL_INPUT_SLOT_INDEX);
				int fuelAmmount = AttoEssenceRegistryHandler.GetAttoFuelValue(fuel);
				if(fuelAmmount > 0) {
					fuel_level = fuelAmmount;
					fuel_remaining = fuelAmmount;
					int wasteAmmount = AttoEssenceRegistryHandler.GetAttoFuelWaste(fuel);
					waste_level = Utils.Math.ClampInt(waste_level+wasteAmmount, 0, getWasteCapacity());
					fuel.shrink(1);
				}
				if(IsActive()) smelt_progress++;
			}
			if(IsSmentingComplete()) {
				smelt_progress = 0;
				ItemStack item = inventory.getStackInSlot(ContainerFurnaceAtto.ITEM_INPUT_SLOT_INDEX);
				ItemStack result = GetSmeltingResult(item);
				item.shrink(1);
				ItemStack output = inventory.getStackInSlot(ContainerFurnaceAtto.ITEM_OUTPUT_SLOT_INDEX);
				if(output.isEmpty()) inventory.setStackInSlot(ContainerFurnaceAtto.ITEM_OUTPUT_SLOT_INDEX, result);
				else output.setCount(result.getCount()+output.getCount());
			}				
		}else {
			smelt_progress = 0;
		}
		
		if(innitialActiveState != IsActive())
			AttoBlockFurnace.setState(this.IsActive(), this.world, this.pos);
		
		StateUpdateTick--;
		if(StateUpdateTick <= 0) {
			AttoBlockFurnace.setState(this.IsActive(), this.world, this.pos);
			StateUpdateTick = 20;
		}

		if(CanInfuse()) {			
			ItemStack infusionInput = inventory.getStackInSlot(ContainerFurnaceAtto.INFUSION_INPUT_SLOT_INDEX);
			InfusionRecipe recipe = GetInfusionRecipe(infusionInput);
			if(recipe != null)
				infusion_require = recipe.getInfusionAmmount();
			if(waste_level > 0) {
				waste_level--;
				infusion_progress++;
			}
			if(infusion_progress >= infusion_require) {
				ItemStack output = inventory.getStackInSlot(ContainerFurnaceAtto.INFUSION_OUTPUT_SLOT_INDEX);
				if(output.isEmpty()) {
					inventory.setStackInSlot(ContainerFurnaceAtto.INFUSION_OUTPUT_SLOT_INDEX, recipe.getResult());
				}else {
					output.setCount(output.getCount()+recipe.getResult().getCount());
				}
				infusionInput.shrink(1);
				infusion_progress = 0;
			}
		}else if(CanFillBucket()) {
			waste_level += infusion_progress;
			infusion_progress = 0;
			if(waste_level >= 1000) {
				waste_level -= 1000;
				ItemStack infusionInput = inventory.getStackInSlot(ContainerFurnaceAtto.INFUSION_INPUT_SLOT_INDEX);
				infusionInput.shrink(1);
				ItemStack filledBusket = FluidUtil.getFilledBucket(new FluidStack(FluidInit.FLUID_TAINT, 1000));
				inventory.setStackInSlot(ContainerFurnaceAtto.INFUSION_OUTPUT_SLOT_INDEX, filledBusket.copy());
			}			
		}else if(CanDrainBucket()) {
			ItemStack infusionInput = inventory.getStackInSlot(ContainerFurnaceAtto.INFUSION_INPUT_SLOT_INDEX);
			infusionInput.shrink(1);
			waste_level += 1000;		
		}else {
			if(infusion_progress > 0) {
				infusion_progress--;
				waste_level = Utils.Math.ClampInt(waste_level+1, 0, getWasteCapacity());				
			}else {
				infusion_progress = 0;
			}
		}
		
		/*ItemStack infusionInput = inventory.getStackInSlot(ContainerFurnaceAtto.INFUSION_INPUT_SLOT_INDEX);
		if(infusionInput.getItem() instanceof ItemBucket) {
			AttoCraft.logger.info(infusionInput.getItem());
			UniversalBucket bucket = new UniversalBucket();
			infusionInput.shrink(1);
			ItemStack outputSlot = inventory.getStackInSlot(ContainerFurnaceAtto.INFUSION_OUTPUT_SLOT_INDEX);
			ItemStack filledBusket = FluidUtil.getFilledBucket(new FluidStack(FluidInit.FLUID_TAINT, 1000));
			if(outputSlot.isEmpty())
				inventory.setStackInSlot(ContainerFurnaceAtto.INFUSION_OUTPUT_SLOT_INDEX, filledBusket);
		}*/
		
		sendUpdates();
	}

	public ItemStack GetSmeltingResult(ItemStack stack) {
		return FurnaceRecipes.instance().getSmeltingResult(stack).copy();
	}
	public InfusionRecipe GetInfusionRecipe(ItemStack stack) {
		return InfusionRecipeHandler.GetTaintInfusionResult(stack.getItem());
	}
	public boolean CanSmelt() {
		ItemStack item = inventory.getStackInSlot(ContainerFurnaceAtto.ITEM_INPUT_SLOT_INDEX);
		if(item == null || item.isEmpty()) return false;
		ItemStack result = GetSmeltingResult(item);
		if(result == null || result.isEmpty()) return false;
		ItemStack outputStack = inventory.getStackInSlot(ContainerFurnaceAtto.ITEM_OUTPUT_SLOT_INDEX);
		if(outputStack.isEmpty()) return true;
		if(outputStack.getCount() + result.getCount() > outputStack.getMaxStackSize()) return false;
		return outputStack.isItemEqual(result);
	}
	
	public boolean CanFillBucket() {
		ItemStack item = inventory.getStackInSlot(ContainerFurnaceAtto.INFUSION_INPUT_SLOT_INDEX);
		if(item == null || item.isEmpty() || !(item.getItem() instanceof ItemBucket)) return false;
		ItemStack outputStack = inventory.getStackInSlot(ContainerFurnaceAtto.INFUSION_OUTPUT_SLOT_INDEX);
		return outputStack.isEmpty();
	}	
	public boolean CanDrainBucket() {
		ItemStack item = inventory.getStackInSlot(ContainerFurnaceAtto.INFUSION_INPUT_SLOT_INDEX);
		ItemStack outputStack = inventory.getStackInSlot(ContainerFurnaceAtto.INFUSION_OUTPUT_SLOT_INDEX);
		return item != null && item.getItem() instanceof UniversalBucket && (outputStack.isEmpty() || outputStack.getItem() == Items.BUCKET);
	}
	
	public boolean CanInfuse() {
		ItemStack item = inventory.getStackInSlot(ContainerFurnaceAtto.INFUSION_INPUT_SLOT_INDEX);
		if(item == null || item.isEmpty()) return false;
		InfusionRecipe recipe = GetInfusionRecipe(item);
		if(recipe == null) return false;
		ItemStack outputStack = inventory.getStackInSlot(ContainerFurnaceAtto.INFUSION_OUTPUT_SLOT_INDEX);
		if(outputStack.isEmpty()) return true;
		if(outputStack.getCount() + recipe.getResult().getCount() > outputStack.getMaxStackSize()) return false;
		return outputStack.isItemEqual(recipe.getResult());
	}
	
	public boolean IsSmentingComplete() {
		return smelt_progress >= GetSmeltSpeed();
	}
	
	@Override
	@Nullable
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(pos, 3, this.getUpdateTag());
	}
	
	@Override
	public NBTTagCompound getUpdateTag() {
		return this.writeToNBT(new NBTTagCompound());
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		//super.onDataPacket(net, pkt);
		handleUpdateTag(pkt.getNbtCompound());
	}
	@Override
	public void handleUpdateTag(NBTTagCompound tag) {
		this.readFromNBT(tag);
	}
	
	private void sendUpdates() {
		world.markBlockRangeForRenderUpdate(pos, pos);
		world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
		world.scheduleBlockUpdate(pos,this.getBlockType(), 0,0);
		markDirty();
	}
	
}
