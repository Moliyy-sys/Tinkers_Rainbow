package com.moliyy.tinkers_rainbow.datagen;


import com.moliyy.tinkers_rainbow.Tinkers_Rainbow;
import com.moliyy.tinkers_rainbow.init.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
//import net.minecraftforge.client.model.generators.ItemModelBuilder;
//import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Tinkers_Rainbow.MOD_ID, existingFileHelper);
    }
    @Override
    protected void registerModels() {
        simpleItem(ModItems.YELLOW_DUST);
        simpleItem(ModItems.RAINBOW_INGOT);
        simpleItem(ModItems.BLUE_STEEL);
        simpleItem(ModItems.GREEN_JELLY_INGOT);
        simpleItem(ModItems.INDIGO_SCRAP);
        simpleItem(ModItems.ORANGE_GEM);
        simpleItem(ModItems.RED_NUGGET);
        simpleItem(ModItems.VIOLET_CUBE);
        simpleItem(ModItems.RAW_BLUE_STEEL);
        simpleItem(ModItems.BLUE_STEEL_NUGGET);
        simpleItem(ModItems.GENE_METAl);
        simpleItem(ModItems.GENE_METAl_NUGGET);
        simpleItem(ModItems.WAKAMO_INGOT);
        simpleItem(ModItems.GLORIOUS_CORE);
        simpleItem(ModItems.END_SINGULARITY);
        simpleItem(ModItems.COSMIC_NUCLEUS);
    }

    private void simpleItem(RegistryObject<Item> item){
        withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Tinkers_Rainbow.MOD_ID, "item/" + item.getId().getPath()));
    }
    private ItemModelBuilder handheldItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(Tinkers_Rainbow.MOD_ID,"item/"+item.getId().getPath()));
    }

}
