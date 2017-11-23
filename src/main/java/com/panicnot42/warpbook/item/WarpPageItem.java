package com.panicnot42.warpbook.item;

import com.panicnot42.warpbook.WarpBookMod;
import com.panicnot42.warpbook.core.IDeclareWarp;
import com.panicnot42.warpbook.util.Waypoint;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WarpPageItem extends Item implements IDeclareWarp, IItemColor {

	public static final String unbound = "§4§kUnbound";
	public static final String ttprefix = "§a";
	
	public WarpPageItem(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setMaxStackSize(64);
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 1;
	}
	
	@Override
	public String getName(World world, ItemStack stack) {
		return null;//Potions can't be used in book so this isn't needed.
	}
	
	@Override
	public Waypoint getWaypoint(EntityPlayer player, ItemStack stack) {
		return null;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack itemStack = player.getHeldItem(hand);
		
		WarpBookMod.warpDrive.handleWarp(player, itemStack);

		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStack);
	}
		
	@Override
	public boolean hasValidData(ItemStack stack) {
		return false;
	}
	
	@Override
	public boolean isWarpCloneable(ItemStack stack) {
		return false;
	}
	
	@Override
	public boolean canGoInBook() {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemstack(ItemStack stack, int tintIndex) {
		switch(tintIndex) {
			case 0: return pageColor();
			case 1: return symbolColor();
			default: return 0x00FFFFFF;
		}
	}
	
	@SideOnly(Side.CLIENT)
	public int pageColor() {
		return 0x00FFFFFF;
	}
	
	@SideOnly(Side.CLIENT)
	public int symbolColor() {
		return 0x00000000;
	}
	
}
