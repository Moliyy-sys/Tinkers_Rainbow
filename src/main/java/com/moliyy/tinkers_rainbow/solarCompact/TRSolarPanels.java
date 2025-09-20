package com.moliyy.tinkers_rainbow.solarCompact;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import org.zeith.hammerlib.compat.base.BaseCompat;
import org.zeith.hammerlib.event.recipe.RegisterRecipesEvent;
import org.zeith.solarflux.SolarFlux;
import org.zeith.solarflux.compat._base.SolarFluxCompat;
import org.zeith.solarflux.panels.SolarPanel;

import org.zeith.solarflux.init.SolarPanelsSF;

import java.util.function.*;
@BaseCompat.LoadCompat(
        modid = "tinkers_rainbow",
        compatType = SolarFluxCompat.class
)
public class TRSolarPanels extends SolarFluxCompat {
    public final ResourceLocation blue_steelRecipe = SolarFlux.id("solar_panels/tinkers_rainbow/blue_steel_planes");
    public final ResourceLocation rainbow_ingotRecipe = SolarFlux.id("solar_panels/tinkers_rainbow/rainbow_ingot_planes");

    public SolarPanel blue_steel_planes, rainbow_ingot_planes;

    @Override
    public void registerSolarPanels(Supplier<SolarPanel.Builder> factory, Function<SolarPanel.Builder, SolarPanel> registrar) {
        blue_steel_planes = registrar.apply(
                factory.get()
                        .name("tinkers_rainbow.blue_steel")
                        .generation(4096)
                        .transfer(32000)
                        .capacity(48000000)
        );

        rainbow_ingot_planes = registrar.apply(
                factory.get()
                        .name("tinkers_rainbow.rainbow_ingot")
                        .generation(777 * 5648)
                        .transfer(777 * 42000)
                        .capacity(124_432_000_000L)
        );
    }

    @Override
    public void registerRecipes(RegisterRecipesEvent e) {
        var blue_steel = ItemTags.create(new ResourceLocation("tinkers_rainbow", "blue_steel"));
        var rainbow_ingot = ItemTags.create(new ResourceLocation("tinkers_rainbow", "rainbow_ingot"));

        e.shaped()
                .id(blue_steelRecipe)
                .result(blue_steel_planes)
                .shape("cic", "ioi", "cic")
                .map('i', blue_steel)
                .map('o', SolarPanelsSF.getGeneratingSolars(SolarPanelsSF.CORE_PANELS[6]))
                .map('c', Items.DIAMOND)
                .registerIf(SolarPanelsSF::isRecipeActive);
    }
}