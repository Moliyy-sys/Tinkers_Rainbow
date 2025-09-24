package com.moliyy.tinkers_rainbow.TRNewBlockEntity.block;

import com.moliyy.tinkers_rainbow.TRNewBlockEntity.RecipeExecutor;
import com.moliyy.tinkers_rainbow.TRNewBlockEntity.menu.GeneOvenMenu;
import com.moliyy.tinkers_rainbow.TRNewBlockEntity.recipe.GeneOvenRecipe;
import com.moliyy.tinkers_rainbow.init.ModBlockEntities;
import com.moliyy.tinkers_rainbow.init.ModRecipeTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.stream.IntStream;

public class GeneOvenBlockEntity extends BlockEntity implements MenuProvider, WorldlyContainer {
    // 槽位定义
    public static final int INPUT_SLOT_COUNT = 4;
    public static final int FUEL_SLOT = INPUT_SLOT_COUNT;
    public static final int OUTPUT_SLOT = INPUT_SLOT_COUNT + 1;
    public static final int TOTAL_SLOTS = INPUT_SLOT_COUNT + 2;

    // 进度相关
    private static final int MAX_PROGRESS = 200;
    private int progress = 0;
    private boolean isWorking = false;

    // 物品栏
    private final NonNullList<ItemStack> items = NonNullList.withSize(TOTAL_SLOTS, ItemStack.EMPTY);
    private final RecipeExecutor<GeneOvenRecipe> recipeExecutor;

    // 数据同步
    private final ContainerData dataAccess = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> progress;
                case 1 -> MAX_PROGRESS;
                case 2 -> isWorking ? 1 : 0;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> progress = value;
                case 2 -> isWorking = value != 0;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    };

    public GeneOvenBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.recipeExecutor = new RecipeExecutor<>(this::getMatchingRecipe, this::onRecipeComplete, MAX_PROGRESS);
    }

    public GeneOvenBlockEntity(BlockPos pos, BlockState state) {
        this(ModBlockEntities.GENE_OVEN.get(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, GeneOvenBlockEntity blockEntity) {
        if (level.isClientSide()) return;

        boolean changed = false;

        // 检查燃料
        if (blockEntity.items.get(FUEL_SLOT).isEmpty()) {
            // 自动寻找燃料
            for (int i = 0; i < INPUT_SLOT_COUNT; i++) {
                ItemStack stack = blockEntity.items.get(i);
                if (AbstractFurnaceBlockEntity.isFuel(stack)) {
                    blockEntity.items.set(FUEL_SLOT, stack.copy());
                    stack.shrink(1);
                    changed = true;
                    break;
                }
            }
        }

        // 检查是否有匹配的配方
        if (blockEntity.recipeExecutor.hasRecipe() &&
                !blockEntity.items.get(FUEL_SLOT).isEmpty()) {

            blockEntity.isWorking = true;
            blockEntity.progress++;

            // 消耗燃料
            if (blockEntity.progress % 20 == 0) {
                blockEntity.items.get(FUEL_SLOT).shrink(1);
                changed = true;
            }

            if (blockEntity.progress >= blockEntity.recipeExecutor.getMaxProgress()) {
                blockEntity.recipeExecutor.execute();
                blockEntity.resetProgress();
                changed = true;
            }
        } else {
            blockEntity.resetProgress();
            blockEntity.isWorking = false;
        }

        // 更新方块状态
        if (changed || blockEntity.isWorking != state.getValue(Gene_Oven_Block.WORKING)) {
            level.setBlock(pos, state.setValue(Gene_Oven_Block.WORKING, blockEntity.isWorking), 3);
            blockEntity.setChanged();
        }
    }

    @Nullable
    private GeneOvenRecipe getMatchingRecipe() {
        Level level = this.level;
        if (level == null) return null;

        // 创建容器副本用于配方匹配
        SimpleContainer container = new SimpleContainer(INPUT_SLOT_COUNT);
        for (int i = 0; i < INPUT_SLOT_COUNT; i++) {
            container.setItem(i, items.get(i).copy());
        }

        return level.getRecipeManager()
                .getRecipeFor(ModRecipeTypes.GENE_OVEN_TYPE.get(), container, level)
                .orElse(null);
    }

    private void onRecipeComplete(GeneOvenRecipe recipe) {
        // 消耗输入物品
        for (int i = 0; i < INPUT_SLOT_COUNT; i++) {
            items.get(i).shrink(1);
        }

        // 添加输出
        ItemStack result = recipe.getResultItem(level.registryAccess());
        if (items.get(OUTPUT_SLOT).isEmpty()) {
            items.set(OUTPUT_SLOT, result.copy());
        } else {
            items.get(OUTPUT_SLOT).grow(result.getCount());
        }
    }

    public void resetProgress() {
        this.progress = 0;
    }

    public boolean isWorking() {
        return isWorking;
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory playerInv, Player player) {
        return new GeneOvenMenu(id, playerInv, this, dataAccess);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.tinkers_rainbow.gene_oven");
    }

    // 容器实现
    @Override
    public int[] getSlotsForFace(Direction side) {
        // 所有槽位都可从顶部输入
        if (side == Direction.UP) {
            return IntStream.range(0, INPUT_SLOT_COUNT).toArray();
        }
        // 输出槽可从侧面和底部输出
        return new int[]{OUTPUT_SLOT};
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
        return index < INPUT_SLOT_COUNT;
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        return index == OUTPUT_SLOT;
    }

    // 其他容器方法实现
    @Override
    public int getContainerSize() {
        return items.size();
    }

    @Override
    public boolean isEmpty() {
        return items.stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    public ItemStack getItem(int index) {
        return items.get(index);
    }

    @Override
    public ItemStack removeItem(int index, int count) {
        return ContainerHelper.removeItem(items, index, count);
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        return ContainerHelper.takeItem(items, index);
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        items.set(index, stack);
        if (stack.getCount() > getMaxStackSize()) {
            stack.setCount(getMaxStackSize());
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return Container.stillValidBlockEntity(this, player);
    }

    @Override
    public void clearContent() {
        items.clear();
    }

    // 序列化
    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        ContainerHelper.saveAllItems(tag, items);
        tag.putInt("Progress", progress);
        tag.putBoolean("Working", isWorking);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        ContainerHelper.loadAllItems(tag, items);
        progress = tag.getInt("Progress");
        isWorking = tag.getBoolean("Working");
    }
    public ContainerData getContainerData() {
        return dataAccess;
    }
}
