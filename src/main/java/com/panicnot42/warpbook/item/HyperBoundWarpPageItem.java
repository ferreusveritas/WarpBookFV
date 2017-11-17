package com.panicnot42.warpbook.item;

import java.util.List;

import com.panicnot42.warpbook.WarpBookMod;
import com.panicnot42.warpbook.WarpWorldStorage;
import com.panicnot42.warpbook.core.IDeclareWarp;
import com.panicnot42.warpbook.util.Waypoint;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class HyperBoundWarpPageItem extends Item implements IDeclareWarp {
	public HyperBoundWarpPageItem(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setMaxStackSize(16);
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack itemStack) {
		return 1;
	}
	
	@Override
	public String GetName(World world, ItemStack stack) {
		WarpWorldStorage storage = WarpWorldStorage.get(world);
		return storage.getWaypoint(stack.getTagCompound().getString("hypername")).name;
	}
	
	@Override
	public Waypoint GetWaypoint(EntityPlayer player, ItemStack stack) {
		WarpWorldStorage storage = WarpWorldStorage.get(player.getEntityWorld());
		return storage.getWaypoint(stack.getTagCompound().getString("hypername"));
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack itemStack = player.getHeldItem(hand);
		
		if (!player.isSneaking()) {
			WarpBookMod.warpDrive.handleWarp(player, itemStack);
			if (!player.capabilities.isCreativeMode) {
				itemStack.shrink(1);
			}
		}

		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStack);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flagIn) {
		if (stack.hasTagCompound()) {
			String name = stack.getTagCompound().getString("hypername");
			tooltip.add(name);
			tooltip.add(WarpWorldStorage.get(world).getWaypoint(name).friendlyName);
		}
	}
	
	@Override
	public Boolean ValidData(ItemStack stack) {
		return stack.getTagCompound().hasKey("hypername");
	}
	
	@Override
	public Boolean WarpCloneable() {
		return true;
	}
	
}
