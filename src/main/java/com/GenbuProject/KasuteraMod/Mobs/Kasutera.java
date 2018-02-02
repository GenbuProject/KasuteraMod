package com.GenbuProject.KasuteraMod.Mobs;

import java.util.Random;

import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.GenbuProject.KasuteraMod.KasuteraMod;

public class Kasutera extends EntityZombie {
	public Kasutera(World World) {
		super(World);
		setCustomNameTag("かすてら。");

		tasks.addTask(1, new EntityAIWatchClosest(this, EntityVillager.class, 5F));
		tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1D, true));

		targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));

		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(8);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.375D);
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}

	@Override
	protected String getLivingSound() {
		int Rnd = new Random().nextInt(3);
		return "kasuteramod:Kasutera.Voice" + Rnd;
	}

	@Override
	protected String getHurtSound() {
		return "kasuteramod:Kasutera.OnHurted";
	}

	@Override
	protected String getDeathSound() {
		return "kasuteramod:Kasutera.OnDeath";
	}

	@Override
	protected Item getDropItem() {
		int Rnd = new Random().nextInt(10);

		switch (Rnd) {
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
				return (new ItemStack(KasuteraMod.KasuteraMeet, new Random().nextInt(4))).getItem();

			case 6:
				switch (new Random().nextInt(10)) {
					case 0:
					case 1:
					case 2:
					case 3:
					case 4:
					case 5:
					case 6:
					case 7:
						return null;

					case 8:
					case 9:
						return new ItemStack(KasuteraMod.KasuteraSword, 1).getItem();
				}

				break;

			case 7:
			case 8:
			case 9:
				break;
		}

		return null;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEFINED;
	}

	@Override
	public boolean getCanSpawnHere() {
		return true;
	}
}