package com.panicnot42.warpbook;

import com.panicnot42.warpbook.item.BoundWarpPageItem;
import com.panicnot42.warpbook.item.DeathlyWarpPageItem;
import com.panicnot42.warpbook.item.HyperBoundWarpPageItem;
import com.panicnot42.warpbook.item.PlayerWarpPageItem;
import com.panicnot42.warpbook.item.PotatoWarpPageItem;
import com.panicnot42.warpbook.item.UnboundWarpPageItem;
import com.panicnot42.warpbook.item.WarpBookItem;
import com.panicnot42.warpbook.item.WarpPageItem;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.registries.IForgeRegistry;

public class WarpItems {
	public WarpBookItem warpBookItem;
	public PlayerWarpPageItem playerWarpPageItem;
	public HyperBoundWarpPageItem hyperWarpPageItem;
	public BoundWarpPageItem boundWarpPageItem;
	public UnboundWarpPageItem unboundWarpPageItem;
	public PotatoWarpPageItem potatoWarpPageItem;
	public DeathlyWarpPageItem deathlyWarpPageItem;
	public WarpPageItem legacyPageItem;
	
	public WarpItems() {
		warpBookItem = new WarpBookItem("warpbook");
		playerWarpPageItem = new PlayerWarpPageItem("playerwarppage");
		hyperWarpPageItem = new HyperBoundWarpPageItem("hyperwarppage");
		boundWarpPageItem = new BoundWarpPageItem("boundwarppage");
		unboundWarpPageItem = new UnboundWarpPageItem("unboundwarppage");
		potatoWarpPageItem = new PotatoWarpPageItem("potatowarppage");
		deathlyWarpPageItem = new DeathlyWarpPageItem("deathlywarppage");
		legacyPageItem = new WarpPageItem("warppage");
	}
	
	public void register(IForgeRegistry<Item> registry) {
		registry.register(warpBookItem);
		registry.register(playerWarpPageItem);
		registry.register(hyperWarpPageItem);
		registry.register(boundWarpPageItem);
		registry.register(unboundWarpPageItem);
		registry.register(potatoWarpPageItem);
		registry.register(deathlyWarpPageItem);
		registry.register(legacyPageItem);
		
		ItemBlock itemBlock = new ItemBlock(WarpBookMod.blocks.bookCloner);
		itemBlock.setRegistryName(WarpBookMod.blocks.bookCloner.getRegistryName());
		registry.register(itemBlock);
		
		itemBlock = new ItemBlock(WarpBookMod.blocks.teleporter);
		itemBlock.setRegistryName(WarpBookMod.blocks.teleporter.getRegistryName());
		registry.register(itemBlock);
	}
	
}
