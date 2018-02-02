package com.GenbuProject.KasuteraMod;

import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;

import com.GenbuProject.KasuteraMod.Blocks.KasuteraBlock;
import com.GenbuProject.KasuteraMod.Blocks.KasuteraChest;
import com.GenbuProject.KasuteraMod.Handlers.KasuteraTowerGenerateHandler;
import com.GenbuProject.KasuteraMod.Items.KasuteraCarTicket;
import com.GenbuProject.KasuteraMod.Items.KasuteraEgg;
import com.GenbuProject.KasuteraMod.Items.Foods.KasuteraMeet;
import com.GenbuProject.KasuteraMod.Items.Tools.KasuteraSword;
import com.GenbuProject.KasuteraMod.Mobs.Kasutera;
import com.GenbuProject.KasuteraMod.Mobs.KasuteraCar;
import com.GenbuProject.KasuteraMod.Mobs.Renders.KasuteraCarRender;
import com.GenbuProject.KasuteraMod.Mobs.Renders.KasuteraRender;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkCheckHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = KasuteraMod.ModID, name = KasuteraMod.ModID, version = KasuteraMod.Version)

public class KasuteraMod {
	public final static String ModID = "KasuteraMod";
	public final static String Version = "1.5";

	final BiomeGenBase[] SPAWNLIST = {
		BiomeGenBase.beach,
		BiomeGenBase.birchForest,
		BiomeGenBase.birchForestHills,
		BiomeGenBase.coldBeach,
		BiomeGenBase.coldTaiga,
		BiomeGenBase.coldTaigaHills,
		BiomeGenBase.deepOcean,
		BiomeGenBase.desert,
		BiomeGenBase.desertHills,
		BiomeGenBase.extremeHills,
		BiomeGenBase.extremeHillsEdge,
		BiomeGenBase.extremeHillsPlus,
		BiomeGenBase.forest,
		BiomeGenBase.forestHills,
		BiomeGenBase.frozenOcean,
		BiomeGenBase.frozenRiver,
		BiomeGenBase.hell,
		BiomeGenBase.iceMountains,
		BiomeGenBase.icePlains,
		BiomeGenBase.jungle,
		BiomeGenBase.jungleEdge,
		BiomeGenBase.jungleHills,
		BiomeGenBase.megaTaiga,
		BiomeGenBase.megaTaigaHills,
		BiomeGenBase.mesa,
		BiomeGenBase.mesaPlateau,
		BiomeGenBase.mesaPlateau_F,
		BiomeGenBase.mushroomIsland,
		BiomeGenBase.mushroomIslandShore,
		BiomeGenBase.ocean,
		BiomeGenBase.plains,
		BiomeGenBase.river,
		BiomeGenBase.roofedForest,
		BiomeGenBase.savanna,
		BiomeGenBase.savannaPlateau,
		BiomeGenBase.sky,
		BiomeGenBase.stoneBeach,
		BiomeGenBase.swampland,
		BiomeGenBase.taiga,
		BiomeGenBase.taigaHills
	};

	public static Block KasuteraBlock = new KasuteraBlock();
	public static Block KasuteraChest = new KasuteraChest();

	public static Item KasuteraMeet = new KasuteraMeet();
	public static Item KasuteraEgg = new KasuteraEgg();
	public static Item KasuteraCarTicket = new KasuteraCarTicket();
	public static Item KasuteraSword = new KasuteraSword();

	@EventHandler
	public void Init(FMLInitializationEvent Event) {
		AddItems();
		AddBlocks();
		AddRecipes();
		AddMobs();
		AddHandlers();
	}

	@NetworkCheckHandler
	public boolean netCheckHandler(Map<String, String> Mods, Side Side) {
		return true;
	}

	protected void AddItems() {
		GameRegistry.registerItem(KasuteraMeet, "KasuteraMeet");
		GameRegistry.registerItem(KasuteraEgg, "KasuteraEgg");
		GameRegistry.registerItem(KasuteraCarTicket, "KasuteraCarTicket");
		GameRegistry.registerItem(KasuteraSword, "KasuteraSword");

	}

	protected void AddBlocks() {
		GameRegistry.registerBlock(KasuteraBlock, "KasuteraBlock");
		GameRegistry.registerBlock(KasuteraChest, "KasuteraChest");
	}

	protected void AddRecipes() {
		GameRegistry.addRecipe(new ItemStack(KasuteraCarTicket),
			"   ",
			"MMM",
			"MMM",

			'M', KasuteraMeet
		);

		GameRegistry.addRecipe(new ItemStack(KasuteraSword),
			" M ",
			" M ",
			" S ",

			'M', KasuteraMeet,
			'S', Items.stick
		);

		GameRegistry.addRecipe(new ItemStack(KasuteraBlock),
			"MMM",
			"MMM",
			"MMM",

			'M', KasuteraMeet
		);
	}

	protected void AddMobs() {
		EntityRegistry.registerModEntity(Kasutera.class, "Kasutera", 0, this, 256, 1, true);
		EntityRegistry.addSpawn(Kasutera.class, 15, 2, 8, EnumCreatureType.creature, SPAWNLIST);

		EntityRegistry.registerModEntity(KasuteraCar.class, "KasuteraCar", 1, this, 256, 1, true);

		if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
			AddRenders();
		}
	}

	@SideOnly(Side.CLIENT)
	protected void AddRenders() {
		RenderingRegistry.registerEntityRenderingHandler(Kasutera.class, new KasuteraRender());
		RenderingRegistry.registerEntityRenderingHandler(KasuteraCar.class, new KasuteraCarRender());
	}

	protected void AddHandlers() {
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(new KasuteraTowerGenerateHandler());
	}
}