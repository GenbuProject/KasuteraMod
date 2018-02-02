package com.GenbuProject.KasuteraMod.Items.Tools;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;

import com.GenbuProject.KasuteraMod.KasuteraMod;
import com.GenbuProject.KasuteraMod.Systems.CreativeTabs.CreativeTab;

public class KasuteraSword extends ItemSword {
	public KasuteraSword() {
		super(EnumHelper.addToolMaterial("かすてら。ソード", 2, 500, 2.0F, 6.0F, 10));
		setUnlocalizedName("KasuteraSword");
		setTextureName(KasuteraMod.ModID + ":KasuteraSword");
		setCreativeTab(CreativeTab.toKasutera);

		setMaxStackSize(1);
		setFull3D();
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack Item, World World, EntityPlayer Player, int UseCount) {
		int MaxDuration = this.getMaxItemUseDuration(Item) - UseCount;

		ArrowLooseEvent Event = new ArrowLooseEvent(Player, Item, MaxDuration);
		MinecraftForge.EVENT_BUS.post(Event);

		if (Event.isCanceled()) {
			return;
		}

		MaxDuration = Event.charge;

		boolean Flag = Player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, Item) > 0;

		if (Flag || Player.inventory.hasItem(Items.arrow)) {
			float Duration = (float) MaxDuration / 20.0F;
			Duration = (Duration * Duration + Duration * 2.0F) / 3.0F;

			if ((double) Duration < 0.1D) {
				return;
			}

			if (Duration > 1.0F) {
				Duration = 1.0F;
			}

			final int Arrows = 25;
			EntityArrow[] Arrow = new EntityArrow[Arrows];

			for (int i = 0; i < Arrows; i++) {
				Arrow[i] = new EntityArrow(World, Player, Duration * 5.0F);
			}

			if (Duration == 1.0F) {
				for (int i = 0; i < Arrows; i++) {
					Arrow[i].setIsCritical(true);
				}
			}

			if (EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, Item) > 0) {
				for (int i = 0; i < Arrows; i++) {
					Arrow[i].setDamage(Arrow[i].getDamage() + (double) EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, Item));
				}
			}

			if (EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, Item) > 0) {
				for (int i = 0; i < Arrows; i++) {
					Arrow[i].setKnockbackStrength(EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, Item));
				}
			}

			if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, Item) > 0) {
				for (int i = 0; i < Arrows; i++) {
					Arrow[i].setFire(100);
				}
			}

			Item.damageItem(1, Player);

			World.playSoundAtEntity(Player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + Duration * 0.5F);

			if (Flag) {
				for (int i = 0; i < Arrows; i++) {
					Arrow[i].canBePickedUp = 0;
				}
			} else {
				Player.inventory.consumeInventoryItem(Items.arrow);
			}

			if (!World.isRemote) {
				for (int i = 0; i < Arrows; i++) {
					World.spawnEntityInWorld(Arrow[i]);
				}
			}
		}
	}
}