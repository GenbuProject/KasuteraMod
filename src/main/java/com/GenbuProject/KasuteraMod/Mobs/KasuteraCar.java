package com.GenbuProject.KasuteraMod.Mobs;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import com.GenbuProject.KasuteraMod.KasuteraMod;

public class KasuteraCar extends EntityMinecart {
	public KasuteraCar(World World) {
		super(World);
		setMinecartName("かすてら。カー");
	}

	public KasuteraCar(World World, double X, double Y, double Z) {
		super(World, X, Y, Z);
		setMinecartName("かすてら。カー");
	}

	@Override
	public int getMinecartType() {
		return 0;
	}

	@Override
	public boolean canBeRidden() {
		return true;
	}

	@Override
	public float getMaxCartSpeedOnRail() {
		return 10.0F;
	}

	@Override
	public boolean interactFirst(EntityPlayer Player) {
		if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.event.entity.minecart.MinecartInteractEvent(this, Player))) {
			return true;
		}

		if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer && this.riddenByEntity != Player) {
			return true;
		} else if (this.riddenByEntity != null && this.riddenByEntity != Player) {
			return false;
		} else {
			if (!this.worldObj.isRemote) {
				Player.mountEntity(this);
			}

			return true;
		}
	}

	public static EntityMinecart createMinecart(World World, double X, double Y, double Z, int Type) {
		return new KasuteraCar(World, X, Y, Z);
	}

	@Override
	public void killMinecart(DamageSource Damage) {
		this.setDead();

		ItemStack Item = new ItemStack(KasuteraMod.KasuteraCarTicket, 1);

		if (getEntityData().hasKey("CustomName")) {
			Item.setStackDisplayName(getEntityData().getString("CustomName"));
		}

		this.entityDropItem(Item, 0.0F);
	}
}