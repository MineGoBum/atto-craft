package com.minegobum.attocraft;

import org.apache.logging.log4j.Logger;

import com.minegobum.attocraft.proxy.CommonProxy;
import com.minegobum.attocraft.references.References;
import com.minegobum.attocraft.tabs.AttoGeneralTab;
import com.minegobum.attocraft.utils.handlers.RegistryHandler;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = References.MODID, name = References.NAME, version = References.VERSION)
public class AttoCraft {
	
	@Instance
	public static AttoCraft INSTANCE;
	
	public static Logger logger;
	
	public static final CreativeTabs GeneralTab = new AttoGeneralTab("attocraft");
	
	@SidedProxy(clientSide = References.CLIENT, serverSide = References.SERVER)
	public static CommonProxy proxy;
	
	static {
		FluidRegistry.enableUniversalBucket();
	}
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		RegistryHandler.preInitRegister();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		RegistryHandler.initRegister();
		
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		RegistryHandler.postInitRegister();
	
	}
	
}
