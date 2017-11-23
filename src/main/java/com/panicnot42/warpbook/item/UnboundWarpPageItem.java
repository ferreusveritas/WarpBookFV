package com.panicnot42.warpbook.item;

import com.panicnot42.warpbook.WarpBookMod;
import com.panicnot42.warpbook.core.WarpColors;
import com.panicnot42.warpbook.util.WarpUtils;
import com.panicnot42.warpbook.util.Waypoint;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class UnboundWarpPageItem extends WarpPageItem {

	public UnboundWarpPageItem(String name) {
		super(name);
		setCreativeTab(WarpBookMod.tabBook);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		
		if (player.isSneaking()) {
			stack = WarpUtils.bindItemStackToPlayer(new ItemStack(WarpBookMod.items.playerWarpPageItem, stack.getCount()), player);
		}
		else {
			WarpUtils.bindItemStackToLocation(new ItemStack(WarpBookMod.items.locusWarpPageItem), world, player);
		}
		
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
	}
	
	public String getName(World world, ItemStack stack) {
		return null;
	}
	
	@Override
	public Waypoint getWaypoint(EntityPlayer player, ItemStack stack) {
		return null;
	}
	
	@Override
	public boolean hasValidData(ItemStack stack) {
		return false;
	}
	
	@Override
	public boolean isWarpCloneable(ItemStack stack) {
		return false;//No information to clone from
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int pageColor() {
		return WarpColors.UNBOUND.getColor();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int symbolColor() {
		return 0x00FFFFFF;
	}
}
