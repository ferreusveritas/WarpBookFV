package com.panicnot42.warpbook;

import com.panicnot42.warpbook.crafting.WarpPageShapeless;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;
import net.minecraftforge.registries.IForgeRegistry;

public class Crafting {
	
	public void register(IForgeRegistry<IRecipe> registry) {
		
		RecipeSorter.register("warpbook:shapeless_page", WarpPageShapeless.class, Category.SHAPELESS, "after:minecraft:shapeless");
		
		PotionType awkward = PotionType.REGISTRY.getObject(new ResourceLocation("awkward"));
		
		//Brewing recipe for unbound warp page
		BrewingRecipeRegistry.addRecipe(
				PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), awkward),
				new ItemStack(WarpBookMod.items.warpClusterItem), //Warp Cluster
				new ItemStack(WarpBookMod.items.unboundWarpPotionItem));
		
		//Recipe for unbound warp book
		GameRegistry.addShapelessRecipe(
			new ResourceLocation(Properties.modid, "unboundWarpPage"),//Name
			null,//Group
			new ItemStack(WarpBookMod.items.unboundWarpPageItem, 1),//Output
			new Ingredient[] {
				Ingredient.fromStacks(new ItemStack(Items.PAPER, 1)),
				Ingredient.fromStacks(new ItemStack(WarpBookMod.items.unboundWarpPotionItem))
			}
		);
		
		//Recipe for warp book
		GameRegistry.addShapelessRecipe(
			new ResourceLocation(Properties.modid, "warpBook"),//Name
			null,//Group
			new ItemStack(WarpBookMod.items.warpBookItem, 1),//Output
			new Ingredient[] {
				Ingredient.fromStacks(new ItemStack(Items.ENDER_PEARL, 1)),
				Ingredient.fromStacks(new ItemStack(Items.BOOK, 1))
			}
		);
		
		//Recipe for deathly warp page
		GameRegistry.addShapedRecipe(
			new ResourceLocation(Properties.modid, "deathlyWarpPage"),//Name
			null,//Group
			new ItemStack(WarpBookMod.items.deathlyWarpPageItem),//Output
			" f ",
			"dud",
			'u', new ItemStack(WarpBookMod.items.unboundWarpPageItem),
			'd', new ItemStack(Items.DIAMOND),
			'f', new ItemStack(Items.FERMENTED_SPIDER_EYE)
		);
		
		//Recipe for warp cluster
		GameRegistry.addShapedRecipe(
			new ResourceLocation(Properties.modid, "warpCluster"),//Name
			null,//Group
			new ItemStack(WarpBookMod.items.warpClusterItem),//Output
			"eee",
			"ere",
			"eee",
			'e', new ItemStack(Items.ENDER_PEARL),
			'r', new ItemStack(Items.REDSTONE)
		);
		
		//Recipe for book cloner
		GameRegistry.addShapedRecipe(
			new ResourceLocation(Properties.modid, "bookCloner"),//Name
			null,//Group
			new ItemStack(WarpBookMod.blocks.bookCloner, 1),//Output
			"sss",
			"owo",
			"ggg",
			's', new ItemStack(Blocks.STONE_SLAB),
			'o', new ItemStack(Blocks.OBSIDIAN),
			'w', new ItemStack(WarpBookMod.items.warpClusterItem),
			'g', new ItemStack(Items.GOLD_INGOT)
		);
		
		//Recipe for teleporter
		GameRegistry.addShapedRecipe(
			new ResourceLocation(Properties.modid, "teleporter"),//Name
			null,//Group
			new ItemStack(WarpBookMod.blocks.teleporter, 1),//Output
			"qpq",
			"ece",
			"owo",
			'q', new ItemStack(Items.QUARTZ),
			'p', new ItemStack(Blocks.STONE_PRESSURE_PLATE),
			'e', new ItemStack(Blocks.END_ROD),
			'c', new ItemStack(WarpBookMod.items.warpClusterItem),
			'o', new ItemStack(Blocks.OBSIDIAN),
			'w', new ItemStack(WarpBookMod.items.unboundWarpPotionItem)
		);
		
		//Recipe to copy bound warp page to an unbound page
		registry.register(
			new WarpPageShapeless(
				new ItemStack(WarpBookMod.items.boundWarpPageItem, 2),//Output
				NonNullList.from(null,
					Ingredient.fromStacks(new ItemStack(WarpBookMod.items.boundWarpPageItem, 1)),
					Ingredient.fromStacks(new ItemStack(WarpBookMod.items.unboundWarpPageItem, 1))
				)
			).setRegistryName(Properties.modid, "boundWarpPageFromCopy")
		);
		
		//Recipe to copy hyper warp page to an unbound page
		registry.register(
			new WarpPageShapeless(
				new ItemStack(WarpBookMod.items.hyperWarpPageItem, 2),//Output
				NonNullList.from(null,
					Ingredient.fromStacks(new ItemStack(WarpBookMod.items.hyperWarpPageItem, 1)),
					Ingredient.fromStacks(new ItemStack(WarpBookMod.items.unboundWarpPageItem, 1))
				)
			).setRegistryName(Properties.modid, "boundWarpPageFromCopy")
		);
		
		//Recipe to apply bound potion to an unbound page
		registry.register(
			new WarpPageShapeless(
				new ItemStack(WarpBookMod.items.boundWarpPageItem),//Output
				NonNullList.from(null,
					Ingredient.fromStacks(new ItemStack(WarpBookMod.items.boundWarpPotionItem)),
					Ingredient.fromStacks(new ItemStack(WarpBookMod.items.unboundWarpPageItem))
				)
			).setRegistryName(Properties.modid, "boundWarpPageFromBoundPotion")
		);
		
		//Recipe to apply bound potion to plain paper
		registry.register(
			new WarpPageShapeless(
				new ItemStack(WarpBookMod.items.boundWarpPageItem),//Output
				NonNullList.from(null,
					Ingredient.fromStacks(new ItemStack(WarpBookMod.items.boundWarpPotionItem)),
					Ingredient.fromStacks(new ItemStack(Items.PAPER))
				)
			).setRegistryName(Properties.modid, "boundWarpPageFromBoundPotionAndPaper")
		);
		
		//Recipe to apply player potion to an unbound page
		registry.register(
			new WarpPageShapeless(
				new ItemStack(WarpBookMod.items.playerWarpPageItem),//Output
				NonNullList.from(null,
					Ingredient.fromStacks(new ItemStack(WarpBookMod.items.playerWarpPotionItem)),
					Ingredient.fromStacks(new ItemStack(WarpBookMod.items.unboundWarpPageItem))
				)
			).setRegistryName(Properties.modid, "playerWarpPageFromPlayerPotion")
		);
		
		//Recipe to apply player potion to plain paper
		registry.register(
			new WarpPageShapeless(
				new ItemStack(WarpBookMod.items.playerWarpPageItem),//Output
				NonNullList.from(null,
					Ingredient.fromStacks(new ItemStack(WarpBookMod.items.playerWarpPotionItem)),
					Ingredient.fromStacks(new ItemStack(Items.PAPER))
				)
			).setRegistryName(Properties.modid, "playerWarpPageFromPlayerPotionAndPaper")
		);
		
	}
	
}
