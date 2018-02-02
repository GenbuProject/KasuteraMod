package com.GenbuProject.KasuteraMod.Items.Foods;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import scala.util.Random;

import com.GenbuProject.KasuteraMod.KasuteraMod;
import com.GenbuProject.KasuteraMod.Systems.CreativeTabs.CreativeTab;

public class KasuteraMeet extends ItemFood {
	public KasuteraMeet() {
		super(2, 4, true);
		setUnlocalizedName("KasuteraMeet");
		setTextureName(KasuteraMod.ModID + ":KasuteraMeet");
		setCreativeTab(CreativeTab.toKasutera);

		setMaxStackSize(64);
	}

	@Override
	public void onFoodEaten(ItemStack Item, World World, EntityPlayer Player) {
		switch (new Random().nextInt(10)) {
			case 0:
			case 1:
			case 2:
				break;

			default:
				Player.addPotionEffect(new PotionEffect(Potion.weakness.id, 30 * 20, 1));
				Player.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 30 * 20, 1));
				Player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 30 * 20, 1));
				break;
		}
	}
}