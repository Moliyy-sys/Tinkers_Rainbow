package com.moliyy.tinkers_rainbow.TRNewBlockEntity.block;

import com.moliyy.tinkers_rainbow.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;

public class Gene_Oven_Block extends Block implements EntityBlock {
    public Gene_Oven_Block(Properties pProperties) {
        super(pProperties);
    }
    public static final BooleanProperty WORKING = BooleanProperty.create("working");

    public Gene_Oven_Block() {
        super(Properties.of()
                .strength(3.5f)
                .requiresCorrectToolForDrops()
                .noOcclusion()
                .lightLevel(state -> state.getValue(WORKING) ? 10 : 0));

        this.registerDefaultState(this.defaultBlockState().setValue(WORKING, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(WORKING);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new GeneOvenBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
            Level level, BlockState state, BlockEntityType<T> type
    ) {
        return level.isClientSide ? null :
                createTickerHelper(type, ModBlockEntities.GENE_OVEN.get());
    }

    // 修正后的 ticker 辅助方法
    @Nullable
    protected static <T extends BlockEntity> BlockEntityTicker<T> createTickerHelper(
            BlockEntityType<T> type,
            BlockEntityType<? extends GeneOvenBlockEntity> expectedType
    ) {
        return expectedType == type ? (level, pos, state, blockEntity) -> {
            if (blockEntity instanceof GeneOvenBlockEntity geneOven) {
                geneOven.tick(level, pos, state, geneOven);
            }
        } : null;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos,
                                 Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide) {
            // 将位置存储到玩家手中物品
            ItemStack heldItem = player.getItemInHand(hand);
            heldItem.getOrCreateTag().put("OvenPos", NbtUtils.writeBlockPos(pos));

            // 打开菜单
            if (level.getBlockEntity(pos) instanceof MenuProvider menuProvider) {
                NetworkHooks.openScreen((ServerPlayer) player, menuProvider, buf -> buf.writeBlockPos(pos));
            }
        }
        return InteractionResult.SUCCESS;
    }
}