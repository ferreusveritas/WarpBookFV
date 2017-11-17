package com.panicnot42.warpbook.item;

import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

import com.panicnot42.warpbook.WarpBookMod;
import com.panicnot42.warpbook.core.IDeclareWarp;
import com.panicnot42.warpbook.util.MathUtils;
import com.panicnot42.warpbook.util.Waypoint;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PlayerWarpPageItem extends Item implements IDeclareWarp {
	public PlayerWarpPageItem(String name) {
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
		return stack.getTagCompound().getString("player");
	}
	
	@Override
	public Waypoint GetWaypoint(EntityPlayer player, ItemStack stack) {
		if (player.world.isRemote) {
			return null;
		}
		UUID playerID = UUID.fromString(stack.getTagCompound().getString("playeruuid"));
		EntityPlayerMP playerTo = null;
		List<EntityPlayerMP> allPlayers = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayers();
		for (EntityPlayerMP playerS : allPlayers) {
			if (playerS.getUniqueID().equals(playerID)) {
				playerTo = playerS;
			}
		}
		if (player == playerTo || playerTo == null) {
			return null;
		}
		return new Waypoint("", "",
				MathUtils.round(playerTo.posX, RoundingMode.DOWN),
				MathUtils.round(playerTo.posY, RoundingMode.DOWN),
				MathUtils.round(playerTo.posZ, RoundingMode.DOWN),
				playerTo.dimension);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
	//public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
		ItemStack itemStack = player.getHeldItem(hand);
		
		if (player.isSneaking()) {
			itemStack = new ItemStack(WarpBookMod.items.unboundWarpPageItem, itemStack.getCount());
			itemStack.setTagCompound(new NBTTagCompound());
		}
		else {
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
			tooltip.add(GetName(world, stack));
		}
	}
	
	@Override
	public Boolean ValidData(ItemStack stack) {
		return stack.getTagCompound().hasKey("playeruuid");
	}
	
	@Override
	public Boolean WarpCloneable() {
		return true;
	}
	
}
