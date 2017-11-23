package com.panicnot42.warpbook.item;

import com.panicnot42.warpbook.WarpBookMod;
import com.panicnot42.warpbook.core.WarpColors;
import com.panicnot42.warpbook.util.WarpUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class UnboundWarpPotionItem extends WarpPotionItem {

	public static final String name = "unboundwarppotion";
	
	public UnboundWarpPotionItem() {
		this(name);
	}
	
	public UnboundWarpPotionItem(String name) {
		super(name);
		setCreativeTab(WarpBookMod.tabBook);
	}
	
	@Override
	public ItemStack doBind(World world, EntityPlayer player, ItemStack stack) {
		if (player.isSneaking()) {
			stack = WarpUtils.bindItemStackToPlayer(new ItemStack(WarpBookMod.items.playerWarpPotionItem, stack.getCount()), player);
		}
		else {
			WarpUtils.bindItemStackToLocation(new ItemStack(WarpBookMod.items.locusWarpPotionItem), world, player);
		}
		
		return stack;
	}
	
	@Override
	public boolean isWarpCloneable(ItemStack stack) {
		return false;//Doesn't make sense to clone anything from this
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getColor() {
		return WarpColors.UNBOUND.getColor();
	}

}
