package com.panicnot42.warpbook;

import com.panicnot42.warpbook.item.LocusWarpPageItem;
import com.panicnot42.warpbook.item.LocusWarpPotionItem;
import com.panicnot42.warpbook.item.DeathlyWarpPageItem;
import com.panicnot42.warpbook.item.HyperWarpPageItem;
import com.panicnot42.warpbook.item.HyperWarpPotionItem;
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
	public HyperWarpPageItem hyperWarpPageItem;
	public LocusWarpPageItem locusWarpPageItem;
	public UnboundWarpPageItem unboundWarpPageItem;
	public DeathlyWarpPageItem deathlyWarpPageItem;
	public LegacyWarpPageItem legacyPageItem;
	public Item warpClusterItem;
	public UnboundWarpPotionItem unboundWarpPotionItem;
	public LocusWarpPotionItem locusWarpPotionItem;
	public PlayerWarpPotionItem playerWarpPotionItem;
	public HyperWarpPotionItem hyperWarpPotionItem;

	public WarpItems() {

		//Misc
		warpBookItem = new WarpBookItem("warpbook");
		warpClusterItem = new WarpItem("warpcluster");

		//Pages
		unboundWarpPageItem = new UnboundWarpPageItem("unboundwarppage");
		locusWarpPageItem = new LocusWarpPageItem("boundwarppage");
		playerWarpPageItem = new PlayerWarpPageItem("playerwarppage");
		hyperWarpPageItem = new HyperWarpPageItem("hyperwarppage");
		deathlyWarpPageItem = new DeathlyWarpPageItem("deathlywarppage");
		legacyPageItem = new LegacyWarpPageItem("warppage");
		
		//Potions
		unboundWarpPotionItem = new UnboundWarpPotionItem();
		locusWarpPotionItem = new LocusWarpPotionItem();
		playerWarpPotionItem = new PlayerWarpPotionItem();
		hyperWarpPotionItem = new HyperWarpPotionItem();
	}
	
	public void register(IForgeRegistry<Item> registry) {

		//Misc
		registry.register(warpBookItem);
		registry.register(warpClusterItem);

		//Pages
		registry.register(unboundWarpPageItem);
		registry.register(locusWarpPageItem);
		registry.register(playerWarpPageItem);
		registry.register(hyperWarpPageItem);
		registry.register(deathlyWarpPageItem);
		registry.register(legacyPageItem);
		
		//Potions
		registry.register(unboundWarpPotionItem);
		registry.register(locusWarpPotionItem);
		registry.register(playerWarpPotionItem);
		registry.register(hyperWarpPotionItem);
		
		ItemBlock itemBlock = new ItemBlock(WarpBookMod.blocks.bookCloner);
		itemBlock.setRegistryName(WarpBookMod.blocks.bookCloner.getRegistryName());
		registry.register(itemBlock);
		
		itemBlock = new ItemBlock(WarpBookMod.blocks.teleporter);
		itemBlock.setRegistryName(WarpBookMod.blocks.teleporter.getRegistryName());
		registry.register(itemBlock);
	}
	
}
