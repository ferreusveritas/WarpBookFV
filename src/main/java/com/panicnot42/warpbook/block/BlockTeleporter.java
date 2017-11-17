package com.panicnot42.warpbook.block;

import com.panicnot42.warpbook.WarpBookMod;
import com.panicnot42.warpbook.core.IDeclareWarp;
import com.panicnot42.warpbook.tileentity.TileEntityTeleporter;
import com.panicnot42.warpbook.util.WorldUtils;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockTeleporter extends Block implements ITileEntityProvider {
	public static final PropertyBool ACTIVE = PropertyBool.create("active");
	
	public BlockTeleporter() {
		super(Material.IRON);
		setUnlocalizedName("teleporter");
		setRegistryName("teleporter");
		setCreativeTab(WarpBookMod.tabBook);
		setSoundType(SoundType.STONE);
		setHardness(10.0f);
		setResistance(20.0f);
		setDefaultState(this.blockState.getBaseState().withProperty(ACTIVE, true));
		setHarvestLevel("pickaxe", 2);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityTeleporter();
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {ACTIVE});
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(ACTIVE, meta == 1);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return ((Boolean)state.getValue(ACTIVE)) ? 1 : 0;
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!player.isSneaking()) {
			IBlockState newState = state;
			TileEntityTeleporter teleporter = (TileEntityTeleporter)world.getTileEntity(pos);
			if (state.getValue(ACTIVE)) { /* Right Click Extraction Disabled.  Just break the block instead
				ItemStack stack = teleporter.GetPage();
				stack.setCount(1);
				if (!world.isRemote) {
					WorldUtils.dropItemStackInWorld(world, pos.getX(), pos.getY(), pos.getZ(), stack);
				}
				teleporter.SetPage(ItemStack.EMPTY);
				newState = state.withProperty(ACTIVE, false);
				world.setBlockState(pos, newState);*/
			}
			else if (!player.getHeldItem(hand).isEmpty() && player.getHeldItem(hand).getItem() instanceof IDeclareWarp) {
				if (((IDeclareWarp)player.getHeldItem(hand).getItem()).WarpCloneable()) {
					teleporter.SetPage(player.getHeldItem(hand));
					if (player.getHeldItem(hand).getCount() < 1) {
						player.getHeldItem(hand).shrink(1);
					}
					else {
						player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
					}
					newState = state.withProperty(ACTIVE, true);
					world.setBlockState(pos, newState);
				}
			}
			world.notifyBlockUpdate(pos, state, newState, 3);
			teleporter.markDirty();
		}
		return true;
	}
	
	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		if (entityIn instanceof EntityPlayer) {
			ItemStack page = ((TileEntityTeleporter)worldIn.getTileEntity(pos)).GetPage();
			if (!page.isEmpty())
				WarpBookMod.warpDrive.handleWarp((EntityPlayer)entityIn, page);
		}
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		float f = 0.0625F;
		return new AxisAlignedBB(pos.getX() + f, pos.getY(), pos.getZ() + f, pos.getX() + 1 - f, pos.getY() + f, pos.getZ() + 1 - f);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
		float f = 0.0625F;
		return new AxisAlignedBB(pos.getX() + f, pos.getY(), pos.getZ() + f, pos.getX() + 1 - f, pos.getY() + f, pos.getZ() + 1 - f);
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		if (!world.isRemote) {
			ItemStack stack = ((TileEntityTeleporter)world.getTileEntity(pos)).GetPage();
			stack.setCount(1);
			WorldUtils.dropItemStackInWorld(world, pos.getX(), pos.getY(), pos.getZ(), stack);
		}
	}
	
}
