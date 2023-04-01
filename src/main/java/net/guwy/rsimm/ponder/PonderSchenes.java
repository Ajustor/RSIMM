package net.guwy.rsimm.ponder;

import com.simibubi.create.foundation.ponder.SceneBuilder;
import com.simibubi.create.foundation.ponder.SceneBuildingUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

public class PonderSchenes {
    public static void arcReactorCharger(SceneBuilder scene, SceneBuildingUtil util) {
        scene.title("arc_reactor_charger", "Using Arc Reactor Charging to Charge Reactors");
        scene.configureBasePlate(0, 0, 3);
        scene.showBasePlate();
        scene.world.showSection(util.select.layersFrom(1), Direction.DOWN);
        BlockPos charger = util.grid.at(1, 1, 1);
        for (int i = 0; i < 3; i++) {
            scene.idle(5);
            scene.world.showSection(util.select.position(1 + i, 1, 2), Direction.DOWN);
        }
        scene.idle(10);
        scene.overlay.showText(50)
                .text("Arc Reactor Charger Can be used to charge Arc Reactors")
                .placeNearTarget()
                .pointAt(util.vector.topOf(charger));
        scene.idle(50);
        scene.rotateCameraY(90);
        scene.idle(20);
        scene.addKeyframe();
        scene.overlay.showText(70)
                .text("It Can accept energy from the bottom")
                .placeNearTarget()
                .pointAt(util.vector.topOf(charger));
        scene.idle(70);
        scene.markAsFinished();
    }

    public static void template(SceneBuilder scene, SceneBuildingUtil util) {
        scene.title("arc_reactor_charger", "This is a template");
        scene.showBasePlate();
        scene.idle(10);
        scene.world.showSection(util.select.layersFrom(1), Direction.DOWN);
    }
}
