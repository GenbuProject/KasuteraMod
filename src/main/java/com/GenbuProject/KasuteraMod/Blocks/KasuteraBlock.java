package com.GenbuProject.KasuteraMod.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.GenbuProject.KasuteraMod.KasuteraMod;
import com.GenbuProject.KasuteraMod.Systems.CreativeTabs.CreativeTab;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class KasuteraBlock extends Block {
	public KasuteraBlock () {
		super(Material.rock);
		setBlockName("KasuteraBlock");
		setCreativeTab(CreativeTab.toKasutera);

		setStepSound(soundTypeStone);
		setHarvestLevel("pickaxe", 1);
		setResistance(2000.0F);
		setLightOpacity(255);
	}

	@SideOnly(Side.CLIENT)
	private IIcon Top, Bottom, Center, Back, Left, Right;

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister Icon) {
		Top = Icon.registerIcon(KasuteraMod.ModID + ":KasuteraBlock_Top");
		Bottom = Icon.registerIcon(KasuteraMod.ModID + ":KasuteraBlock_Bottom");
		Center = Icon.registerIcon(KasuteraMod.ModID + ":KasuteraBlock_Center");
		Back = Icon.registerIcon(KasuteraMod.ModID + ":KasuteraBlock_Back");
		Left = Icon.registerIcon(KasuteraMod.ModID + ":KasuteraBlock_Left");
		Right = Icon.registerIcon(KasuteraMod.ModID + ":KasuteraBlock_Right");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int Direction, int Meta) {
		switch (Meta) {
			case 0:
				switch (Direction) {
					case 0:
						return this.Bottom;

					case 1:
						return this.Top;

					case 2:
						return this.Back;

					case 3:
						return this.Center;

					case 4:
						return this.Left;

					case 5:
						return this.Right;

					default:
						return null;
				}

			case 1:
				switch (Direction) {
					case 0:
						return this.Bottom;

					case 1:
						return this.Top;

					case 2:
						return this.Left;

					case 3:
						return this.Right;

					case 4:
						return this.Center;

					case 5:
						return this.Back;

					default:
						return null;
				}

			case 2:
				switch (Direction) {
					case 0:
						return this.Bottom;

					case 1:
						return this.Top;

					case 2:
						return this.Center;

					case 3:
						return this.Back;

					case 4:
						return this.Right;

					case 5:
						return this.Left;

					default:
						return null;
				}

			case 3:
				switch (Direction) {
					case 0:
						return this.Bottom;

					case 1:
						return this.Top;

					case 2:
						return this.Right;

					case 3:
						return this.Left;

					case 4:
						return this.Back;

					case 5:
						return this.Center;

					default:
						return null;
				}
		}

		return null;
	}

	@Override
	public void onBlockPlacedBy(World World, int X, int Y, int Z, EntityLivingBase Entity, ItemStack Item) {
		int Meta = MathHelper.floor_double((double)(Entity.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
		World.setBlockMetadataWithNotify(X, Y, Z, Meta, 2);
	}

	@Override
	public int damageDropped(int Meta) {
		return Meta;
	}
}