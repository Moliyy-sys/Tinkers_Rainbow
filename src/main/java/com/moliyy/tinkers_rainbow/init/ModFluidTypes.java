package com.moliyy.tinkers_rainbow.init;

import com.moliyy.tinkers_rainbow.Tinkers_Rainbow;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.joml.Vector3f;

public class ModFluidTypes {
    public static final ResourceLocation Molten = new ResourceLocation("tconstruct:fluid/molten/still");
    public static final ResourceLocation Flowing_Molten = new ResourceLocation("tconstruct:fluid/molten/flowing");
    public static final ResourceLocation STEW = new ResourceLocation("tconstruct:fluid/food/stew/still");
    public static final ResourceLocation FLOWING_STEW = new ResourceLocation("tconstruct:fluid/food/stew/flowing");

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, Tinkers_Rainbow.MOD_ID);
    private static final FluidType.Properties common = FluidType.Properties.create()
            .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_FILL_LAVA)
            .sound(SoundActions.BUCKET_EMPTY,SoundEvents.BUCKET_EMPTY_LAVA)
            .motionScale(0.0023333333333333335D)
            .canExtinguish(true);
    private static final FluidType.Properties hot = FluidType.Properties.create().density(2000).viscosity(10000).temperature(1000)
            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
            .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
            .motionScale(0.0023333333333333335D)
            .canSwim(false).canDrown(false)
            .pathType(BlockPathTypes.LAVA).adjacentPathType(null);



    public static final RegistryObject<FluidType> molten_blue_steel = FLUID_TYPES.register("molten_blue_steel",
            ()->new TRFluidType(Molten,Flowing_Molten,Molten,
                    0xFF1E90FF,new Vector3f(104f/225f,168f/225f,204f/255f),
                    hot.temperature(1000).lightLevel(15)));
    public static final RegistryObject<FluidType> molten_indigo_scrap = FLUID_TYPES.register("molten_indigo_scrap",
            ()->new TRFluidType(Molten,Flowing_Molten,Molten,
                    0xFF6A5ACD,new Vector3f(104f/225f,168f/225f,204f/255f),
                    hot.temperature(1200).lightLevel(15)));
    public static final RegistryObject<FluidType> molten_orange_gem = FLUID_TYPES.register("molten_orange_gem",
            ()->new TRFluidType(Molten,Flowing_Molten,Molten,
                    0xFFDAA520,new Vector3f(104f/225f,168f/225f,204f/255f),
                    hot.temperature(500).lightLevel(15)));
    public static final RegistryObject<FluidType> molten_violet_cube = FLUID_TYPES.register("molten_violet_cube",
            ()->new TRFluidType(Molten,Flowing_Molten,Molten,
                    0xFFA020F0,new Vector3f(104f/225f,168f/225f,204f/255f),
                    hot.temperature(1800).lightLevel(15)));
    public static final RegistryObject<FluidType> molten_red_nugget = FLUID_TYPES.register("molten_red_nugget",
            ()->new TRFluidType(Molten,Flowing_Molten,Molten,
                    0xFFCD3700,new Vector3f(104f/225f,168f/225f,204f/255f),
                    hot.temperature(1800).lightLevel(15)));

    public static final RegistryObject<FluidType> molten_gene_metal = FLUID_TYPES.register("molten_gene_metal",
            ()->new TRFluidType(Molten,Flowing_Molten,Molten,
                    0xFF5AAD86,new Vector3f(94f/225f,208f/225f,114f/255f),
                    hot.temperature(2800).lightLevel(15)));
//    public static final RegistryObject<FluidType> molten_rainbow_ingot = FLUID_TYPES.register("molten_rainbow_ingot",
//            () -> new TRFluidType(Molten, Flowing_Molten, Molten,
//                    0xFFFFFFFF,
//                    new Vector3f(77f / 225f, 77f / 225f, 77f / 255f),
//                    hot.temperature(7777).lightLevel(7)));
    public static void registers(IEventBus eventBus){
        FLUID_TYPES.register(eventBus);
    }

}
