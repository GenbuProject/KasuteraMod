package com.GenbuProject.KasuteraMod.Items;

import net.minecraft.block.BlockRailBase;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemMinecart;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.GenbuProject.KasuteraMod.KasuteraMod;
import com.GenbuProject.KasuteraMod.Mobs.KasuteraCar;
import com.GenbuProject.KasuteraMod.Systems.CreativeTabs.CreativeTab;

public class KasuteraCarTicket extends ItemMinecart {
	public KasuteraCarTicket() {
		super(0);
		setUnlocalizedName("KasuteraCarTicket");
		setTextureName(KasuteraMod.ModID + ":KasuteraCarTicket");
		setCreativeTab(CreativeTab.toKasutera);

		setMaxStackSize(16);
	}

	@Override
	public boolean onItemUse(ItemStack Item, EntityPlayer Player, World World, int X, int Y, int Z, int Side, float HitX, float HitY, float HitZ) {
		if (BlockRailBase.func_150051_a(World.getBlock(X, Y, Z))) {
			if (!World.isRemote) {
				EntityMinecart KsCar = KasuteraCar.createMinecart(World, (double)((float)X + 0.5F), (double)((float)Y + 0.5F), (double)((float)Z + 0.5F), this.minecartType);

				if (Item.hasDisplayName()) {
					KsCar.setMinecartName(Item.getDisplayName());
				}

				World.spawnEntityInWorld(KsCar);
			}

			--Item.stackSize;
			return true;
		} else {
			return false;
		}
	}
}