package com.GenbuProject.KasuteraMod.Handlers;

import net.minecraftforge.event.terraingen.InitNoiseGensEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;

import com.GenbuProject.KasuteraMod.Structures.KasuteraTower;

import cpw.mods.fml.common.Mod.EventHandler;

public class KasuteraTowerGenerateHandler {
	KasuteraTower KasuteraTower = new KasuteraTower();

	@EventHandler
	public void onInitNoiseGensEvent(InitNoiseGensEvent Event) {

	}

	@EventHandler
	public void onPopulateChankEvent(PopulateChunkEvent.Pre Event) {

	}

	@EventHandler
	public void onPopulateChankEvent(PopulateChunkEvent.Populate Event) {

	}

	@EventHandler
	public void onPopulateChankEvent(PopulateChunkEvent.Post Event) {
		if (Event.world.provider.dimensionId == 0) {
			KasuteraTower.func_151539_a(Event.chunkProvider, Event.world, Event.chunkX, Event.chunkZ, null);
			KasuteraTower.generateStructuresInChunk(Event.world, Event.rand, Event.chunkX, Event.chunkZ);
		}
	}
}