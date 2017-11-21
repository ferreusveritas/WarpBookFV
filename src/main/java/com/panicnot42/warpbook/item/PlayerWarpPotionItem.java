package com.panicnot42.warpbook.item;

import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

import com.panicnot42.warpbook.core.WarpColors;
import com.panicnot42.warpbook.util.MathUtils;
import com.panicnot42.warpbook.util.Waypoint;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PlayerWarpPotionItem extends WarpPotionItem {

	public static final String name = "playerwarppotion"; 

	public PlayerWarpPotionItem() {
		this(name);
	}
	
	public PlayerWarpPotionItem(String name) {
		super(name);
	}

	@Override
	public String GetName(World world, ItemStack stack) {
		if(stack.hasTagCompound() && stack.getTagCompound().hasKey("player")) {
			return stack.getTagCompound().getString("player");
		} else {
			return "No Data";
		}
	}
	
	@Override
	public Waypoint GetWaypoint(EntityPlayer player, ItemStack stack) {

		if (!player.world.isRemote) {
			if(hasValidData(stack)) {
				UUID playerID = UUID.fromString(stack.getTagCompound().getString("playeruuid"));
				EntityPlayerMP playerTo = null;
				List<EntityPlayerMP> allPlayers = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayers();
				for (EntityPlayerMP playerS : allPlayers) {
					if (playerS.getUniqueID().equals(playerID)) {
						playerTo = playerS;
					}
				}
				if (player != playerTo && playerTo != null) {
					return new Waypoint("", "",
							MathUtils.round(playerTo.posX, RoundingMode.DOWN),
							MathUtils.round(playerTo.posY, RoundingMode.DOWN),
							MathUtils.round(playerTo.posZ, RoundingMode.DOWN),
							playerTo.dimension);
				}
			}
		}

		return null;
	}
	
	@Override
	public boolean hasValidData(ItemStack stack) {
		return stack.hasTagCompound() && stack.getTagCompound().hasKey("playeruuid");

	}
	
	@Override
	public boolean isWarpCloneable(ItemStack stack) {
		return true;//Allow this to be written to a page.
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		if(hasValidData(stack)) {
			tooltip.add(GetName(world, stack));
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getColor() {
		return WarpColors.PLAYER.getColor();
	}

}
