package com.GenbuProject.KasuteraMod.Items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.GenbuProject.KasuteraMod.KasuteraMod;
import com.GenbuProject.KasuteraMod.Mobs.Kasutera;
import com.GenbuProject.KasuteraMod.Systems.CreativeTabs.CreativeTab;

public class KasuteraEgg extends Item {
	public KasuteraEgg() {
		super();
		setUnlocalizedName("KasuteraEgg");
		setTextureName(KasuteraMod.ModID + ":KasuteraEgg");
		setCreativeTab(CreativeTab.toKasutera);

		setMaxStackSize(16);
	}

	@Override
	public boolean onItemUse(ItemStack Item, EntityPlayer Player, World World, int X, int Y, int Z, int Side, float PosX, float PosY, float PosZ) {
		if (World.isRemote) {
			return true;
		} else {
			Block Ground = World.getBlock(X, Y, Z);
			X += Facing.offsetsXForSide[Side];
			Y += Facing.offsetsYForSide[Side];
			Z += Facing.offsetsZForSide[Side];

			double Height = 0.0D;

			if (Side == 1 && Ground.getRenderType() == 11) {
				Height = 0.5D;
			}

			Entity Ent = Summon(World, (double) X + 0.5D, (double) Y + Height, (double) Z + 0.5D);

			if (Ent != null) {

				if (!Player.capabilities.isCreativeMode) {
					--Item.stackSize;
				}
			}

			return true;
		}
	}

	@Override
	public ItemStack onItemRightClick(ItemStack Item, World World, EntityPlayer Player) {
		if (World.isRemote) {
			return Item;
		} else {
			MovingObjectPosition Pos = this.getMovingObjectPositionFromPlayer(World, Player, true);

			if (Pos == null) {
				return Item;
			} else {
				if (Pos.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
					int X = Pos.blockX;
					int Y = Pos.blockY;
					int Z = Pos.blockZ;

					if (!World.canMineBlock(Player, X, Y, Z)) {
						return Item;
					}

					if (!Player.canPlayerEdit(X, Y, Z, Pos.sideHit, Item)) {
						return Item;
					}

					if (World.getBlock(X, Y, Z) instanceof BlockLiquid) {
						Entity Ent = Summon(World, (double) X, (double) Y, (double) Z);

						if (Ent != null) {
							if (!Player.capabilities.isCreativeMode) {
								--Item.stackSize;
							}
						}
					}
				}

				return Item;
			}
		}
	}

	protected Entity Summon(World World, double X, double Y, double Z) {
		Kasutera Ks = new Kasutera(World);
			Ks.setLocationAndAngles(X, Y, Z, MathHelper.wrapAngleTo180_float(World.rand.nextFloat() * 360.0F), 0.0F);
			Ks.rotationYawHead = Ks.rotationYaw;
			Ks.renderYawOffset = Ks.rotationYaw;
			Ks.onSpawnWithEgg((IEntityLivingData) null);

		World.spawnEntityInWorld(Ks);
		Ks.playLivingSound();

		return Ks;
	}
}