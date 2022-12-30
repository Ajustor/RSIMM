package net.guwy.rsimm.index;

import net.guwy.rsimm.RsImm;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class ModTags {
    public static class Blocks {
        //public static final TagKey<Block> NATURAL_SOIL = tag("natural_soil");

        private static TagKey<Block> tag(String name){
            return BlockTags.create(new ResourceLocation(RsImm.MOD_ID, name));
        }

        private static TagKey<Block> forgeTags(String name){
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class Items {
        public static final TagKey<Item> IRONMAN_HELMETS = tag("ironman_helmets");
        public static final TagKey<Item> FORCED_NAME_TAG_HELMETS = tag("forced_name_tag_helmets");
        public static final TagKey<Item> IRONMAN_CHESTPLATES = tag("ironman_chestplates");
        public static final TagKey<Item> IRONMAN_LEGGINGS = tag("ironman_leggings");
        public static final TagKey<Item> IRONMAN_BOOTS = tag("ironman_boots");

        public static final TagKey<Item> UNCHARGED_ARC_REACTORS = tag("uncharged_arc_reactors");

        private static TagKey<Item> tag(String name){
            return ItemTags.create(new ResourceLocation(RsImm.MOD_ID, name));
        }

        private static TagKey<Item> forgeTags(String name){
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }

    public static boolean isItemPresentInTag(ItemStack itemStack, TagKey<Item> tagKey){
        return itemStack.is(tagKey);
    }

    public static boolean isBlockPresentInTag(BlockState state, TagKey<Block> tagKey){
        return state.is(tagKey);
    }
}
