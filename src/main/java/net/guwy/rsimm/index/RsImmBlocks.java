package net.guwy.rsimm.index;

import net.guwy.rsimm.RsImm;
import net.guwy.rsimm.content.blocks.arc_reactor_charger.ArcReactorChargerBlock;
import net.guwy.rsimm.content.blocks.armor_equipping_station.ArmorEquippingStationBlock;
import net.guwy.rsimm.content.blocks.armor_unequipping_station.ArmorUnequippingStationBlock;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class RsImmBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, RsImm.MOD_ID);
    public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RsImm.MOD_ID);



    // Machines
    public static final RegistryObject<Block> ARC_REACTOR_CHARGER = registerBlock("arc_reactor_charger", () ->
            new ArcReactorChargerBlock(BlockBehaviour.Properties.of(Material.METAL).
                    strength(4.5f).explosionResistance(3f).
                    sound(SoundType.METAL).noOcclusion()), RsImmCreativeModeTabs.MAIN);

    public static final RegistryObject<Block> ARMOR_EQUIPPING_STATION = registerBlock("armor_equipping_station", () ->
            new ArmorEquippingStationBlock(BlockBehaviour.Properties.of(Material.METAL).
                    strength(4.5f).explosionResistance(3f).
                    sound(SoundType.METAL).noOcclusion()), RsImmCreativeModeTabs.MAIN);

    public static final RegistryObject<Block> ARMOR_UNEQUIPPING_STATION = registerBlock("armor_unequipping_station", () ->
            new ArmorUnequippingStationBlock(BlockBehaviour.Properties.of(Material.METAL).
                    strength(4.5f).explosionResistance(3f).
                    sound(SoundType.METAL).noOcclusion()), RsImmCreativeModeTabs.MAIN);




    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block,
                                                                     CreativeModeTab tab){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab){
        return RsImmItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }



    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block,
                                                                     CreativeModeTab tab, String tooltipKey){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab, tooltipKey);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab, String tooltipKey){
        return RsImmItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)){
            @Override
            public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
                pTooltip.add(Component.literal(tooltipKey));
            }
        });
    }

    private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> block){
        return BLOCKS.register(name, block);
    }


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        BLOCK_ITEMS.register(eventBus);
    }
}
