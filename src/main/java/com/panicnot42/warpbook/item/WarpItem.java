package com.panicnot42.warpbook.item;

import com.panicnot42.warpbook.WarpBookMod;

import net.minecraft.item.Item;

public class WarpItem extends Item {

	public WarpItem(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(WarpBookMod.tabBook);
	}
	
}
