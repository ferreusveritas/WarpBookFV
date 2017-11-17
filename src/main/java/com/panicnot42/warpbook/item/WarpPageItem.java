package com.panicnot42.warpbook.item;

import java.util.List;

import com.panicnot42.warpbook.WarpBookMod;

import net.minecraft.client.resources.I18n;
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

public class WarpPageItem extends Item {
	//private static final String[] itemMetaNames = new String[] { "unbound", "bound", "hyperbound", "deathly", "potato", "player" };
	
	public WarpPageItem(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setMaxStackSize(16);
		setHasSubtypes(true);
		setMaxDamage(0);
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack itemStack) {
		return 1;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
	//public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
		ItemStack itemStack = player.getHeldItem(hand);
		
		ItemStack newStack;
		switch (itemStack.getItemDamage()) {
			default:
			case 0:
				newStack = new ItemStack(WarpBookMod.items.unboundWarpPageItem, itemStack.getCount());
				break;
			case 1:
				newStack = new ItemStack(WarpBookMod.items.boundWarpPageItem, itemStack.getCount());
				break;
			case 2:
				newStack = new ItemStack(WarpBookMod.items.hyperWarpPageItem, itemStack.getCount());
				break;
			case 3:
				newStack = new ItemStack(WarpBookMod.items.deathlyWarpPageItem, itemStack.getCount());
				break;
			case 4:
				newStack = new ItemStack(WarpBookMod.items.potatoWarpPageItem, itemStack.getCount());
				break;
			case 5:
				newStack = new ItemStack(WarpBookMod.items.playerWarpPageItem, itemStack.getCount());
				break;
		}
		if (itemStack.hasTagCompound()) {
			newStack.setTagCompound(itemStack.getTagCompound());
		}
		
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, newStack);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format("help.legacy"));
	}
	
}
