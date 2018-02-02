package com.GenbuProject.KasuteraMod.Mobs.Renders;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class KasuteraRender extends RenderLiving {
	public KasuteraRender() {
		super(new ModelBiped(), 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity Ent) {
		return new ResourceLocation("kasuteramod:textures/mobs/Kasutera.png");
	}
}