package com.GenbuProject.KasuteraMod.Structures;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureStart;

public class KasuteraTowerStart extends StructureStart {
	public KasuteraTowerStart(World World, Random Rnd, int X, int Z) {
		super(X, Z);

		components.add(new KasuteraTowerParts1(Rnd, X, Z));
	}
}