package com.panicnot42.warpbook.crafting;

import com.panicnot42.warpbook.item.BoundWarpPageItem;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.NonNullList;

public class WarpPageShapeless extends ShapelessRecipes {
	ItemStack recipeOutput;
	
	public WarpPageShapeless(ItemStack recipeOutput, NonNullList<Ingredient> ingredients) {
		super("", recipeOutput, ingredients);
		this.recipeOutput = recipeOutput;
	}
	
	@Override
	public ItemStack getCraftingResult(InventoryCrafting inventory) {
		ItemStack output = recipeOutput.copy();
		try {
			for (int i = 0; i < inventory.getSizeInventory(); ++i) {
				if (inventory.getStackInSlot(i) != null && inventory.getStackInSlot(i).getItem() instanceof BoundWarpPageItem) {
					output.setTagCompound(inventory.getStackInSlot(i).getTagCompound());
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return output;
	}
	
}