package com.GenbuProject.KasuteraMod.Systems.CreativeTabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.GenbuProject.KasuteraMod.KasuteraMod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class toKasutera extends CreativeTabs {
	public toKasutera() {
		super(getNextID(), "KasuteraTab");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return Item.getItemFromBlock(KasuteraMod.KasuteraBlock);
	}
}
