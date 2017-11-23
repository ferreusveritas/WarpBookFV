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

public class HyperWarpPotionItem extends WarpPotionItem {

	public static final String name = "hyperwarppotion"; 

	public HyperWarpPotionItem() {
		this(name);
	}
	
	public HyperWarpPotionItem(String name) {
		super(name);
	}

	@Override
	public String getName(World world, ItemStack stack) {
		if (hasValidData(stack)) {
			String name = stack.getTagCompound().getString("hypername");
			if(name != null) {
				return name;
			}
		}
		return unbound;
	}
	
	@Override
	public Waypoint getWaypoint(EntityPlayer player, ItemStack stack) {

		if (!player.world.isRemote) {
			if(hasValidData(stack)) {
				WarpWorldStorage storage = WarpWorldStorage.get(player.getEntityWorld());
				return storage.getWaypoint(stack.getTagCompound().getString("hypername"));				
			}
		}

		return null;
	}
	
	@Override
	public boolean hasValidData(ItemStack stack) {
		return stack.hasTagCompound() && stack.getTagCompound().hasKey("hypername");
	}
	
	@Override
	public boolean isWarpCloneable(ItemStack stack) {
		return true;//Allow this to be written to a page.
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ttprefix + getName(world, stack));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getColor() {
		return WarpColors.HYPER.getColor();
	}

}
