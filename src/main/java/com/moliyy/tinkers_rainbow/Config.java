package com.moliyy.tinkers_rainbow;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@Mod.EventBusSubscriber(modid = Tinkers_Rainbow.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.BooleanValue LOG_DIRT_BLOCK = BUILDER
            .comment("Whether to log the dirt block on common setup")
            .define("logDirtBlock", true);

    private static final ForgeConfigSpec.IntValue MAGIC_NUMBER = BUILDER
            .comment("A magic number")
            .defineInRange("magicNumber", 42, 0, Integer.MAX_VALUE);

    public static final ForgeConfigSpec.ConfigValue<String> MAGIC_NUMBER_INTRODUCTION = BUILDER
            .comment("What you want the introduction message to be for the magic number")
            .define("magicNumberIntroduction", "The magic number is... ");

<<<<<<< HEAD
    public static final ForgeConfigSpec.IntValue MAX_GLORY_CORE_USES;
    public static final ForgeConfigSpec.IntValue MAX_END_SINGULARITY_USES;
    public static final ForgeConfigSpec.IntValue MAX_COSMIC_NUCLEUS_USES;

=======
>>>>>>> 981271775e69e87b2613d06676af9d23474e788f
    // a list of strings that are treated as resource locations for items
    private static final ForgeConfigSpec.ConfigValue<List<? extends String>> ITEM_STRINGS = BUILDER
            .comment("A list of items to log on common setup.")
            .defineListAllowEmpty(Collections.singletonList("items"), () -> List.of("minecraft:iron_ingot"), Config::validateItemName);

<<<<<<< HEAD
    static ForgeConfigSpec SPEC = BUILDER.build();
=======
    static final ForgeConfigSpec SPEC = BUILDER.build();
>>>>>>> 981271775e69e87b2613d06676af9d23474e788f

    public static boolean logDirtBlock;
    public static int magicNumber;
    public static String magicNumberIntroduction;
    public static Set<Item> items;

    private static boolean validateItemName(final Object obj)
    {
        return obj instanceof final String itemName && ForgeRegistries.ITEMS.containsKey(new ResourceLocation(itemName));
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        logDirtBlock = LOG_DIRT_BLOCK.get();
        magicNumber = MAGIC_NUMBER.get();
        magicNumberIntroduction = MAGIC_NUMBER_INTRODUCTION.get();

        // convert the list of strings into a set of items
        items = ITEM_STRINGS.get().stream()
                .map(itemName -> ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemName)))
                .collect(Collectors.toSet());
    }
<<<<<<< HEAD
    static {
        BUILDER.push("Item Usage Limits");

        MAX_GLORY_CORE_USES = BUILDER
                .comment("Maximum number of Glory Cores a player can use (default: 10)")
                .defineInRange("maxGloryCoreUses", 10, 1, Integer.MAX_VALUE);

        MAX_END_SINGULARITY_USES = BUILDER
                .comment("Maximum number of End Singularities a player can use (default: 10)")
                .defineInRange("maxEndSingularityUses", 10, 1, Integer.MAX_VALUE);

        MAX_COSMIC_NUCLEUS_USES = BUILDER
                .comment("Maximum number of Cosmic Nuclei a player can use (default: 10)")
                .defineInRange("maxCosmicNucleusUses", 10, 1, Integer.MAX_VALUE);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
=======
>>>>>>> 981271775e69e87b2613d06676af9d23474e788f
}
