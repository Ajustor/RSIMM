package net.guwy.rsimm.utils;


import net.minecraft.data.worldgen.DimensionTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

public class AtmosphericDensity {
    static int SEA_LEVEL = 62, NO_ATMOSPHERE_Y = 2000;

    public static double getAtmosphericDensity(Entity entity){

        // Overworld has a density changing depending on height
        if(entity.getLevel().dimension() == Level.OVERWORLD){
            return Math.min(1.0, Math.max(0.0, 1.0 - (entity.getY() - SEA_LEVEL) / (NO_ATMOSPHERE_Y - SEA_LEVEL)));
        }
        // End is 0.3
        else if (entity.getLevel().dimension() == Level.END){
            return 0.3;
        }
        // Any other dimension is 1
        else {
            return 1;
        }
    }
}
