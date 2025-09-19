package com.moliyy.tinkers_rainbow.init;

//import com.moliyy.tinkers_rainbow.Modifiers.modifiers.TeatimeInWartimeAcceleratorEntity;
import com.moliyy.tinkers_rainbow.Tinkers_Rainbow;
import com.moliyy.tinkers_rainbow.entity.RedSpider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Tinkers_Rainbow.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE = DeferredRegister
            .create(ForgeRegistries.ENTITY_TYPES,Tinkers_Rainbow.MOD_ID);

    public static final RegistryObject<EntityType<RedSpider>> REDSPIDER = ENTITY_TYPE.register("redspider",
            ()->EntityType.Builder.of(RedSpider::new, MobCategory.MONSTER)
                    .sized(0.65f,0.55f).fireImmune()
                    .build(new ResourceLocation(Tinkers_Rainbow.MOD_ID,"redspider").toString()));
    @SubscribeEvent
    public static void entitySpawnRestriction(SpawnPlacementRegisterEvent event){
        event.register(REDSPIDER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                RedSpider::camRedSpiderSpawn,SpawnPlacementRegisterEvent.Operation.REPLACE);
    }
    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event){
        event.put(REDSPIDER.get(), RedSpider.registerAttributes().build());
    }

}
