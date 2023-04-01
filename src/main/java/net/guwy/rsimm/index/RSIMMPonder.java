package net.guwy.rsimm.index;

import com.simibubi.create.foundation.ponder.PonderRegistrationHelper;
import com.simibubi.create.foundation.ponder.PonderRegistry;
import com.simibubi.create.foundation.ponder.PonderTag;
import net.guwy.rsimm.RsImm;
import net.guwy.rsimm.ponder.PonderSchenes;
import net.minecraft.resources.ResourceLocation;

public class RSIMMPonder {
    /*
    Code base by mrh0 (Creator of Create Crafts and Additions)
     */

    public static final PonderTag RSIMM = new PonderTag(new ResourceLocation(RsImm.MOD_ID, "rsimm")).item(ModArcReactorItems.MARK_2_ARC_REACTOR.get()
                    , true, false).defaultLang("RSIMM", "ironman mods ponder");

    static final PonderRegistrationHelper HELPER = new PonderRegistrationHelper(RsImm.MOD_ID);

    public static void register(){
        //HELPER.addStoryBoard(CABlocks.ELECTRIC_MOTOR, "electric_motor", PonderScenes::electricMotor, PonderTag.KINETIC_SOURCES, ELECTRIC);

        HELPER.addStoryBoard(ModBlocks.ARC_REACTOR_CHARGER.getId(), "arc_reactor_charger/main", PonderSchenes::arcReactorCharger, RSIMM);


        PonderRegistry.TAGS.forTag(RSIMM)
                .add(ModBlocks.ARC_REACTOR_CHARGER.get());
    }
}
