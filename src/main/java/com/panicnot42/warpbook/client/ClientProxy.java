package com.panicnot42.warpbook.client;

import com.panicnot42.warpbook.Proxy;
import com.panicnot42.warpbook.WarpBookMod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends Proxy {
	@Override
	public void registerRenderers() {
		
		regMesh(WarpBookMod.items.unboundWarpPageItem);
		regMesh(WarpBookMod.items.boundWarpPageItem);
		regMesh(WarpBookMod.items.hyperWarpPageItem);
		regMesh(WarpBookMod.items.deathlyWarpPageItem);
		regMesh(WarpBookMod.items.playerWarpPageItem);
		regMesh(WarpBookMod.items.warpBookItem);
		for(int i = 0; i < 6; i++) {
			regMesh(WarpBookMod.items.legacyPageItem, i);
		}
		regMesh(WarpBookMod.items.warpClusterItem);
		regMesh(WarpBookMod.items.unboundWarpPotionItem);
		regMesh(WarpBookMod.items.boundWarpPotionItem);
		regMesh(WarpBookMod.items.playerWarpPotionItem);
		
		regMesh(Item.getItemFromBlock(WarpBookMod.blocks.bookCloner));
		regMesh(Item.getItemFromBlock(WarpBookMod.blocks.teleporter));
	}
	
	private void regMesh(Item item) {
		regMesh(item, 0);
	}
	
	private void regMesh(Item item, int meta) {
		ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
		mesher.register(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));

		//Register Color Handler for the item.
		if(item instanceof IItemColor) {
			Minecraft.getMinecraft().getItemColors().registerItemColorHandler((IItemColor) item, new Item[] {item});
		}
	}
	
}
