package com.minegobum.attocraft.utils.handlers;

import com.minegobum.attocraft.AttoCraft;
import com.minegobum.attocraft.init.BlockInit;
import com.minegobum.attocraft.init.FluidInit;
import com.minegobum.attocraft.init.ItemInit;
import com.minegobum.attocraft.utils.interfaces.IHasModel;
import com.minegobum.attocraft.world.gen.AttoCraftWorldGenOres;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class RegistryHandler {

	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
		TileEntityHandler.registerTileEntities();
	}
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));		
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {
		for(Item item: ItemInit.ITEMS) {
			if(item instanceof IHasModel) {
				((IHasModel)item).registerModel();
			}
		}
		for(Block block: BlockInit.BLOCKS) {
			if(block instanceof IHasModel) {
				((IHasModel)block).registerModel();
			}
		}
	}
	
	public static void preInitRegister() {
		FluidInit.RegisterFluids();		
		GameRegistry.registerWorldGenerator(new AttoCraftWorldGenOres(BlockInit.ORE_ATTO_OVERWORLD), 0);
		
		RenderHandler.registerCustomMeshesAndStates();
	}

	public static void initRegister() {
		NetworkRegistry.INSTANCE.registerGuiHandler(AttoCraft.INSTANCE, new GuiHandler());
		
	}

	public static void postInitRegister() {
		AttoEssenceRegistryHandler.FinalizeRegistries();
		RecipeRegisterHandler.RegisterInfusionRecipes();
	}
	
}
