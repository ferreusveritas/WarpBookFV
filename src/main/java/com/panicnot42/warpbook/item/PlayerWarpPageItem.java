package com.panicnot42.warpbook.item;

import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

import com.panicnot42.warpbook.core.IDeclareWarp;
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

public class PlayerWarpPageItem extends WarpPageItem implements IDeclareWarp {

	public PlayerWarpPageItem(String name) {
		super(name);
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
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flagIn) {
		if (stack.hasTagCompound()) {
			tooltip.add(GetName(world, stack));
		}
	}
	
	@Override
	public boolean hasValidData(ItemStack stack) {
		return stack.getTagCompound().hasKey("playeruuid");
	}
	
	@Override
	public boolean isWarpCloneable(ItemStack stack) {
		return false;//Let's make sure that player warp pages aren't copyable.  For security purposes.
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int pageColor() {
		return WarpColors.UNBOUND.getColor();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int symbolColor() {
		return WarpColors.PLAYER.getColor();
	}
}
