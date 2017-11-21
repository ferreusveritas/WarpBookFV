package com.panicnot42.warpbook.core;

import com.panicnot42.warpbook.util.Waypoint;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IDeclareWarp {
  String GetName(World world, ItemStack stack);
  Waypoint GetWaypoint(EntityPlayer player, ItemStack stack);
  
  /** Does this stack have valid waypoint data? */
  boolean hasValidData(ItemStack stack);
  
  /** Basically can this be copied? Either in the book cloner or via a copy recipe */
  boolean isWarpCloneable(ItemStack stack);

  /** Can this go in a book? */
  boolean canGoInBook();
}
