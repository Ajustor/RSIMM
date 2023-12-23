package net.guwy.rsimm.utils;


import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class EnvironmentalTemp {

    public static double getAreaTemp(Entity entity){
        Level level = entity.getLevel();

        double biomeTemp = ((level.getBiome(entity.getOnPos()).get().getBaseTemperature()) * 31.25) + 273; // 0.8 base biome temperature(plains) = 25 degree celsius
        biomeTemp *= AtmosphericDensity.getAtmosphericDensity(entity); // Biome temp decreases as the height increases

        double tempForBlock = getTempForBlockUnderEntity(entity, level);

        // If there is a block defined: return average temp
        // else return only the biome temp
        return tempForBlock >= 0 ? (biomeTemp + tempForBlock) / 2 : biomeTemp;
    }



    /** @return temp in celsius */
    private static double getTempForBlock(Block block){

        if(block == Blocks.WATER) return 10;
        else if(block == Blocks.ICE) return -10;
        else if(block == Blocks.PACKED_ICE) return -20;
        else if(block == Blocks.BLUE_ICE) return -40;
        else if(block == Blocks.FROSTED_ICE) return -5;
        else if(block == Blocks.POWDER_SNOW) return 0;
        else if(block == Blocks.SNOW) return 0;
        else if(block == Blocks.SNOW_BLOCK) return 0;

        else if(block == Blocks.LAVA) return 1200;
        else if(block == Blocks.MAGMA_BLOCK) return 800;
        else if(block == Blocks.FIRE) return 400;
        else if(block == Blocks.SOUL_FIRE) return 400;
        else if(block == Blocks.CAMPFIRE) return 400;
        else if(block == Blocks.SOUL_CAMPFIRE) return 400;

        // retrun value for undefined blocks
        else return Double.MIN_VALUE;
    }

    /** @return temp in kelvin */
    private static double getTempForBlockUnderEntity(Entity entity, Level level){
        BlockPos pos = entity.getOnPos();
        int i = 0;

        // keep checking under the player until there is a block
        while(level.getBlockState(pos.offset(0, i, 0)).getBlock() == Blocks.AIR){
            i--;
        }

        Block block = level.getBlockState(pos.offset(0, i, 0)).getBlock();
        double temp = getTempForBlock(block);

        if(temp != Double.MIN_VALUE){
            // decrease temp by the distance the player is from the block
            return (temp + 273) / Math.max(1, Math.sqrt(i));
        }
        // return invalid temp if the block is undefined
        else {
            return -1;
        }

    }
}
