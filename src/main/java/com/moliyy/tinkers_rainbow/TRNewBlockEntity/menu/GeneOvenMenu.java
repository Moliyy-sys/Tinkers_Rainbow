package com.moliyy.tinkers_rainbow.TRNewBlockEntity.menu;

import com.moliyy.tinkers_rainbow.TRNewBlockEntity.block.GeneOvenBlockEntity;
import com.moliyy.tinkers_rainbow.init.ModMenuTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;

public class GeneOvenMenu extends AbstractContainerMenu {
    public GeneOvenMenu(int id, Inventory inv) {
        this(id, inv, getBlockEntityFromPlayer(inv.player));
    }

    public GeneOvenMenu(int id, Inventory inv, GeneOvenBlockEntity entity) {
        this(id, inv, entity, entity != null ? entity.getContainerData() : new SimpleContainerData(3));
    }
    private static GeneOvenBlockEntity getBlockEntityFromPlayer(Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            CompoundTag tag = serverPlayer.getMainHandItem().getOrCreateTag();
            if (tag.contains("OvenPos")) {
                // 使用 NbtUtils 读取位置
                BlockPos pos = NbtUtils.readBlockPos(tag.getCompound("OvenPos"));
                return (GeneOvenBlockEntity) player.level().getBlockEntity(pos);
            }
        }
        return null;
    }
    private final GeneOvenBlockEntity blockEntity;
    private final ContainerData data;

    public GeneOvenMenu(int id, Inventory inv, GeneOvenBlockEntity entity, ContainerData data) {
        super(ModMenuTypes.GENE_OVEN_MENU.get(), id);
        this.blockEntity = entity;
        this.data = data;

        // 添加输入槽 (0-3)
        for (int i = 0; i < 4; i++) {
            addSlot(new Slot(entity, i, 38 + i * 18, 17));
        }

        // 添加燃料槽 (4)
        addSlot(new Slot(entity, 4, 56, 53) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return AbstractFurnaceBlockEntity.isFuel(stack);
            }
        });

        // 添加输出槽 (5)
        addSlot(new Slot(entity, 5, 116, 35) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false; // 禁止手动放置物品到输出槽
            }

            @Override
            public void onTake(Player player, ItemStack stack) {
                super.onTake(player, stack);
                // 触发配方完成逻辑
            }
        });

        // 添加玩家背包
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlot(new Slot(inv, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }

        // 添加玩家快捷栏
        for (int col = 0; col < 9; col++) {
            addSlot(new Slot(inv, col, 8 + col * 18, 142));
        }

        // 添加数据同步
        addDataSlots(data);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack originalStack = slot.getItem();
            itemStack = originalStack.copy();

            // 处理从熔炉槽位移到背包
            if (index < 6) {
                if (!this.moveItemStackTo(originalStack, 6, 42, true)) {
                    return ItemStack.EMPTY;
                }
            }
            // 处理从背包到输入槽
            else if (index < 42 && !this.moveItemStackTo(originalStack, 0, 4, false)) {
                return ItemStack.EMPTY;
            }
            // 处理燃料
            else if (AbstractFurnaceBlockEntity.isFuel(originalStack) &&
                    !this.moveItemStackTo(originalStack, 4, 5, false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return blockEntity.stillValid(player);
    }

    public int getProgress() {
        return data.get(0);
    }

    public int getMaxProgress() {
        return data.get(1);
    }

    public boolean isWorking() {
        return data.get(2) == 1;
    }
}