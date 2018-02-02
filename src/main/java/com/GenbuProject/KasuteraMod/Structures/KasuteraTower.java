package com.GenbuProject.KasuteraMod.Structures;

import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;

public class KasuteraTower extends MapGenStructure {
	@Override
	public String func_143025_a() {
		return "KasuteraTower";
	}

	@Override
	protected boolean canSpawnStructureAtCoords(int X, int Z) {
		return (X == 0 && Z == 0);
	}

	@Override
	protected StructureStart getStructureStart(int X, int Z) {
		return new KasuteraTowerStart(this.worldObj, this.rand, X, Z);
	}
}