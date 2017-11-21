package com.panicnot42.warpbook.item;

import java.util.List;

import com.panicnot42.warpbook.core.IDeclareWarp;
import com.panicnot42.warpbook.core.WarpColors;
import com.panicnot42.warpbook.util.Waypoint;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BoundWarpPageItem extends WarpPageItem implements IDeclareWarp {
	public BoundWarpPageItem(String name) {
		super(name);
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
	public boolean hasValidData(ItemStack stack) {
		return stack.hasTagCompound() &&
				stack.getTagCompound().hasKey("posX") &&
				stack.getTagCompound().hasKey("posY") &&
				stack.getTagCompound().hasKey("posZ") &&
				stack.getTagCompound().hasKey("dim");
	}
	
	@Override
	public boolean isWarpCloneable(ItemStack stack) {
		return hasValidData(stack);//This at the very least is definitely clonable
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int pageColor() {
		return WarpColors.UNBOUND.getColor();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int symbolColor() {
		return WarpColors.BOUND.getColor();
	}
}
