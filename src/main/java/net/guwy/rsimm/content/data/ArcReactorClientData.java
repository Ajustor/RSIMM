package net.guwy.rsimm.content.data;

import net.guwy.rsimm.mechanics.capabilities.player.armor_data.FlyMode;
import net.minecraft.world.item.Item;

import java.util.List;
import java.util.UUID;

public class ArcReactorClientData {
    private static List<Integer> itemId;
    private static List<String> playerUUID;


    public static void setReactorData(int itemId, UUID uuid){

        // Checks if the uuid is ever assigned and if not assigns it to the end of the list
        if(!ArcReactorClientData.playerUUID.contains(uuid.toString())){
            ArcReactorClientData.playerUUID.add(uuid.toString());
        }

        // records the index of the uuid so the item id is assigned in the same index
        int index = ArcReactorClientData.playerUUID.indexOf(uuid.toString());

        // assigns the itemIf to the same index as uuid
        ArcReactorClientData.itemId.set(index, itemId);
    }

    public static Item getReactorItem(UUID uuid){

    }


}
