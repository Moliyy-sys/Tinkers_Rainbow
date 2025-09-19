package com.moliyy.tinkers_rainbow.Loot;

import com.mojang.serialization.Codec;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;
import com.google.common.base.Suppliers;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;


public class WitherDropGloryCoreModifier extends LootModifier {
    public static final Supplier<Codec<WitherDropGloryCoreModifier>> CODEC = Suppliers.memoize(() ->
            RecordCodecBuilder.create(inst -> codecStart(inst).and(
                    ForgeRegistries.ITEMS.getCodec()
                            .fieldOf("item")
                            .forGetter(m -> m.itemStack.getItem())
            ).apply(inst, WitherDropGloryCoreModifier::new))
    );

    private final ItemStack itemStack;

    public WitherDropGloryCoreModifier(LootItemCondition[] conditionsIn, Item item) {
        super(conditionsIn);
        this.itemStack = new ItemStack(item);
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        // 确保只有凋灵掉落
        if (context.getQueriedLootTableId().equals(new ResourceLocation("entities/wither"))) {
            generatedLoot.add(itemStack.copy());
        }
        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
