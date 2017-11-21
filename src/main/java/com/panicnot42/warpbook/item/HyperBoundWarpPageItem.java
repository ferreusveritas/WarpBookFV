package com.panicnot42.warpbook.item;

import java.util.List;

import com.panicnot42.warpbook.WarpWorldStorage;
import com.panicnot42.warpbook.core.WarpColors;
import com.panicnot42.warpbook.util.Waypoint;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class HyperBoundWarpPageItem extends WarpPageItem {

	public HyperBoundWarpPageItem(String name) {
		super(name);
	}
	
	@Override
	public String GetName(World world, ItemStack stack) {
		//WarpWorldStorage storage = WarpWorldStorage.get(world);
		return stack.getTagCompound().getString("hypername");
		//return storage.getWaypoint(stack.getTagCompound().getString("hypername")).name;
	}
	
	@Override
	public Waypoint GetWaypoint(EntityPlayer player, ItemStack stack) {
		WarpWorldStorage storage = WarpWorldStorage.get(player.getEntityWorld());
		return storage.getWaypoint(stack.getTagCompound().getString("hypername"));
	}
		
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flagIn) {
		if (stack.hasTagCompound()) {
			String name = stack.getTagCompound().getString("hypername");
			if(name != null) {
				tooltip.add(name);
				//This will never work since the waypoint information is stored on the server and this is client side only.
				Waypoint wp = WarpWorldStorage.get(world).getWaypoint(name);
				if(wp != null && wp.friendlyName != null) {
					tooltip.add(wp.friendlyName);
				}
			}
		}
	}
	
	@Override
	public boolean hasValidData(ItemStack stack) {
		return stack.getTagCompound().hasKey("hypername");
	}
	
	@Override
	public boolean isWarpCloneable(ItemStack stack) {
		return true;//This is clonable TODO: Make this a configurable option
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int pageColor() {
		return WarpColors.UNBOUND.getColor();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int symbolColor() {
		return WarpColors.HYPER.getColor();
	}
	
}
