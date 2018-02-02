package com.GenbuProject.KasuteraMod.Mobs.Renders;

import net.minecraft.client.renderer.entity.RenderMinecart;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class KasuteraCarRender extends RenderMinecart {
	public KasuteraCarRender() {
		super();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity Ent) {
		return new ResourceLocation("kasuteramod:textures/mobs/KasuteraCar.png");
	}
}