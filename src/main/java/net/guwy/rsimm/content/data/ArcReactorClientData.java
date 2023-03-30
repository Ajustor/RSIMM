package net.guwy.rsimm.content.data;

import net.guwy.rsimm.mechanics.capabilities.player.armor_data.FlyMode;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ArcReactorClientData {
    // You gotta pray these move in sync
    private static List<Integer> itemId = new ArrayList<Integer>();
    private static List<UUID> playerUUID = new ArrayList<UUID>();


    public static void setReactorData(int itemId, UUID uuid){

        // Checks if the uuid is ever assigned and if not assigns it to the end of the list
        if(!ArcReactorClientData.playerUUID.contains(uuid)){
            // Generates new places for the itemId and uuid
            ArcReactorClientData.playerUUID.add(uuid);
            ArcReactorClientData.itemId.add(itemId);

        } else {
            // records the index of the uuid so the item id is assigned in the same index
            int index = ArcReactorClientData.playerUUID.indexOf(uuid);

            // assigns the itemIf to the same index as uuid
            ArcReactorClientData.itemId.set(index, itemId);
        }


    }

    public static Item getReactorItem(UUID uuid){
        if(ArcReactorClientData.playerUUID.contains(uuid)){

            // gets the itemId on the same index as the uuid and assigns it to a item
            int index = ArcReactorClientData.playerUUID.indexOf(uuid);
            Item item = Item.byId(ArcReactorClientData.itemId.get(index));

            return item;
        } else {
            return null;
        }
    }

    public static void removeArcReactorData(UUID uuid){
        if(ArcReactorClientData.playerUUID.contains(uuid)){
            // removes the uuid and index from the list so they won't show up anymore
            // used in the case of arc reactor being removed

            int index = ArcReactorClientData.playerUUID.indexOf(uuid);

            ArcReactorClientData.itemId.remove(index);
            ArcReactorClientData.playerUUID.remove(index);

        }
    }


}
