package com.panicnot42.warpbook.item;

import java.util.List;

import com.panicnot42.warpbook.WarpBookMod;
import com.panicnot42.warpbook.core.IDeclareWarp;
import com.panicnot42.warpbook.util.Waypoint;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BoundWarpPageItem extends Item implements IDeclareWarp {
	public BoundWarpPageItem(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setMaxStackSize(16);
	}

	@Override
	public int getMaxItemUseDuration(ItemStack itemStack) {
		return 1;
	}

	public static void Bind(ItemStack page, int x, int y, int z, int dim) {
		if (!page.hasTagCompound()) {
			page.setTagCompound(new NBTTagCompound());
		}
		page.getTagCompound().setInteger("posX", x);
		page.getTagCompound().setInteger("posY", y);
		page.getTagCompound().setInteger("posZ", z);
		page.getTagCompound().setInteger("dim", dim);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack itemStack = player.getHeldItem(hand);
		
		if (player.isSneaking()) {
			itemStack = new ItemStack(WarpBookMod.items.unboundWarpPageItem, itemStack.getCount());
			itemStack.setTagCompound(new NBTTagCompound());
		}
		else {
			WarpBookMod.warpDrive.handleWarp(player, itemStack);
			if (!player.capabilities.isCreativeMode)
				itemStack.shrink(1);
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStack);
	}
	
	@Override
	public String GetName(World world, ItemStack stack) {
		return stack.getTagCompound().getString("name");
	}
	
	@Override
	public Waypoint GetWaypoint(EntityPlayer player, ItemStack stack) {
		return new Waypoint("", "",
				stack.getTagCompound().getInteger("posX"),
				stack.getTagCompound().getInteger("posY"),
				stack.getTagCompound().getInteger("posZ"),
				stack.getTagCompound().getInteger("dim"));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		try {
			tooltip.add(stack.getTagCompound().getString("name"));
			tooltip.add(I18n.format("warpbook.bindmsg",
					stack.getTagCompound().getInteger("posX"),
					stack.getTagCompound().getInteger("posY"),
					stack.getTagCompound().getInteger("posZ"),
					stack.getTagCompound().getInteger("dim")));
		}
		catch (Exception e) {}
	}
	
	@Override
	public Boolean ValidData(ItemStack item) {
		return item.hasTagCompound() &&
				item.getTagCompound().hasKey("posX") &&
				item.getTagCompound().hasKey("posY") &&
				item.getTagCompound().hasKey("posZ") &&
				item.getTagCompound().hasKey("dim");
	}
	
	@Override
	public Boolean WarpCloneable() {
		return true;
	}
}
