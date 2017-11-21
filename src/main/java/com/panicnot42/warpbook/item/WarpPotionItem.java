package com.panicnot42.warpbook.item;

import com.panicnot42.warpbook.WarpBookMod;
import com.panicnot42.warpbook.core.IDeclareWarp;
import com.panicnot42.warpbook.util.Waypoint;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WarpPotionItem extends Item implements IDeclareWarp, IItemColor {

	public WarpPotionItem(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setMaxStackSize(1);
		setContainerItem(Items.GLASS_BOTTLE);
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return hasValidData(stack) ? EnumAction.DRINK : EnumAction.NONE;
    }
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase player) {
		
		if(player instanceof EntityPlayer) {
			WarpBookMod.warpDrive.handleWarp((EntityPlayer) player, stack);
	        return new ItemStack(Items.GLASS_BOTTLE);
		}
		
        return ItemStack.EMPTY;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        player.setActiveHand(hand);
		ItemStack stack = player.getHeldItem(hand);
		
		if (player instanceof EntityPlayerMP) {
            EntityPlayerMP entityplayermp = (EntityPlayerMP)player;
            CriteriaTriggers.CONSUME_ITEM.trigger(entityplayermp, stack);
            entityplayermp.addStat(StatList.getObjectUseStats(this));
		}
		
		if(!hasValidData(stack)) {
			stack = doBind(world, player, stack);
		}
		
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
	}
	
	public ItemStack doBind(World world, EntityPlayer player, ItemStack stack) {
		return new ItemStack(Items.GLASS_BOTTLE, stack.getCount());
	}
	
    /**
     * How long it takes to use or consume an item
     */
	@Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 32;
    }
	
	@Override
	public boolean canGoInBook() {
		return false;
	}
	
	@Override
	public String GetName(World world, ItemStack stack) {
		return null;
	}
	
	@Override
	public Waypoint GetWaypoint(EntityPlayer player, ItemStack stack) {
		return null;
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
	@SideOnly(Side.CLIENT)
	public int getColorFromItemstack(ItemStack stack, int tintIndex) {
		return tintIndex == 0 ? getColor() : 0x00FFFFFF;
	}

	@SideOnly(Side.CLIENT)
	public int getColor() {
		return 0x00FF00FF;
	}
	
}
