package com.panicnot42.warpbook;

import com.panicnot42.warpbook.item.BoundWarpPageItem;
import com.panicnot42.warpbook.item.BoundWarpPotionItem;
import com.panicnot42.warpbook.item.DeathlyWarpPageItem;
import com.panicnot42.warpbook.item.HyperBoundWarpPageItem;
import com.panicnot42.warpbook.item.PlayerWarpPageItem;
import com.panicnot42.warpbook.item.PlayerWarpPotionItem;
import com.panicnot42.warpbook.item.UnboundWarpPageItem;
import com.panicnot42.warpbook.item.UnboundWarpPotionItem;
import com.panicnot42.warpbook.item.WarpBookItem;
import com.panicnot42.warpbook.item.WarpItem;
import com.panicnot42.warpbook.item.LegacyWarpPageItem;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.registries.IForgeRegistry;

public class WarpItems {
	public WarpBookItem warpBookItem;
	public PlayerWarpPageItem playerWarpPageItem;
	public HyperBoundWarpPageItem hyperWarpPageItem;
	public BoundWarpPageItem boundWarpPageItem;
	public UnboundWarpPageItem unboundWarpPageItem;
	public DeathlyWarpPageItem deathlyWarpPageItem;
	public LegacyWarpPageItem legacyPageItem;
	public Item warpClusterItem;
	public UnboundWarpPotionItem unboundWarpPotionItem;
	public BoundWarpPotionItem boundWarpPotionItem;
	public PlayerWarpPotionItem playerWarpPotionItem;

	public WarpItems() {
		warpBookItem = new WarpBookItem("warpbook");
		playerWarpPageItem = new PlayerWarpPageItem("playerwarppage");
		hyperWarpPageItem = new HyperBoundWarpPageItem("hyperwarppage");
		boundWarpPageItem = new BoundWarpPageItem("boundwarppage");
		unboundWarpPageItem = new UnboundWarpPageItem("unboundwarppage");
		deathlyWarpPageItem = new DeathlyWarpPageItem("deathlywarppage");
		legacyPageItem = new LegacyWarpPageItem("warppage");
		warpClusterItem = new WarpItem("warpcluster");
		unboundWarpPotionItem = new UnboundWarpPotionItem();
		boundWarpPotionItem = new BoundWarpPotionItem();
		playerWarpPotionItem = new PlayerWarpPotionItem();
	}
	
	public void register(IForgeRegistry<Item> registry) {
		registry.register(warpBookItem);
		registry.register(playerWarpPageItem);
		registry.register(hyperWarpPageItem);
		registry.register(boundWarpPageItem);
		registry.register(unboundWarpPageItem);
		registry.register(deathlyWarpPageItem);
		registry.register(legacyPageItem);
		registry.register(warpClusterItem);
		registry.register(boundWarpPotionItem);
		registry.register(unboundWarpPotionItem);
		registry.register(playerWarpPotionItem);
		
		ItemBlock itemBlock = new ItemBlock(WarpBookMod.blocks.bookCloner);
		itemBlock.setRegistryName(WarpBookMod.blocks.bookCloner.getRegistryName());
		registry.register(itemBlock);
		
		itemBlock = new ItemBlock(WarpBookMod.blocks.teleporter);
		itemBlock.setRegistryName(WarpBookMod.blocks.teleporter.getRegistryName());
		registry.register(itemBlock);
	}
	
}
