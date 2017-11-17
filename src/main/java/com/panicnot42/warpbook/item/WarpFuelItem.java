package com.panicnot42.warpbook.item;

import com.panicnot42.warpbook.WarpBookMod;

import net.minecraft.item.Item;

public class WarpFuelItem extends Item {
	public WarpFuelItem(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(WarpBookMod.tabBook);
	}
}
