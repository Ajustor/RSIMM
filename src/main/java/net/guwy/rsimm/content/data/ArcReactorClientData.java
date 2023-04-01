package net.guwy.rsimm.content.data;

import net.guwy.rsimm.content.items.arc_reactors.ArcReactorItem;
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
    private static List<Double> energyPercentage = new ArrayList<Double>();


    public static void setReactorData(int itemId, UUID uuid, double energyPercentage){

        // Checks if the uuid is ever assigned and if not assigns it to the end of the list
        if(!ArcReactorClientData.playerUUID.contains(uuid)){
            // Generates new places for the itemId and uuid
            ArcReactorClientData.playerUUID.add(uuid);
            ArcReactorClientData.itemId.add(itemId);
            ArcReactorClientData.energyPercentage.add(energyPercentage);

        } else {
            // records the index of the uuid so the item id is assigned in the same index
            int index = ArcReactorClientData.playerUUID.indexOf(uuid);

            // assigns the itemId to the same index as uuid
            ArcReactorClientData.itemId.set(index, itemId);
            ArcReactorClientData.energyPercentage.set(index, energyPercentage);
        }


    }

    public static Item getReactorItem(UUID uuid){
        if(ArcReactorClientData.playerUUID.contains(uuid)){

            // gets the itemId on the same index as the uuid and assigns it to a item
            int index = ArcReactorClientData.playerUUID.indexOf(uuid);

            ArcReactorItem arcReactorItem = (ArcReactorItem) Item.byId(ArcReactorClientData.itemId.get(index));
            double percentage = ArcReactorClientData.energyPercentage.get(index);

            Item item;

            //if energy is above 10% return the working item
            if(percentage > 0.10){
                item = Item.byId(ArcReactorClientData.itemId.get(index));
            }

            // if energy is below 10% return the working item based on a chance which changes with the remaining energy
            else if (percentage > 0 && Math.random() <= percentage * 10){
                item = Item.byId(ArcReactorClientData.itemId.get(index));
            }

            // if all of them fails return the depleted item
            else {
                item = arcReactorItem.depletedItem();
            }

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
            ArcReactorClientData.energyPercentage.remove(index);

        }
    }


}
