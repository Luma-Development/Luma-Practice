package best.luma.practice.service.profile.inventory;

import best.luma.practice.service.profile.inventory.impl.InventoryItem;
import best.luma.practice.utils.ItemBuilder;
import best.luma.practice.utils.player.PlayerUtil;
import best.luma.practice.service.profile.Profile;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Inventory {

    @Getter
    private static final Map<InventoryItem, InventoryEntry> items = new HashMap<>();

    public static void init() {
        for (InventoryItem inventoryItem : InventoryItem.values()) {
            try {
                // TODO: build inventory item from yml config

                int slot = 9;

                ItemBuilder builder = new ItemBuilder(Material.valueOf(Material.DIAMOND_SWORD.name()));
                builder.durability(0);
                builder.name("name");
                builder.lore("lore");

                InventoryEntry inventoryEntry;
                if (slot > 0) inventoryEntry = new InventoryEntry(builder.build(), slot - 1); // if slot is more then 0, remove 1 from slot.
                else inventoryEntry = new InventoryEntry(builder.build(), -1);

                items.put(inventoryItem, inventoryEntry); // add item to hashmap
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void giveInventoryItems(Player player) {
        Profile profile = Profile.getProfile(player.getUniqueId());

        ItemStack[] itemStacks = new ItemStack[9];
        Arrays.fill(itemStacks, null);

        switch (profile.getState()) {
            case LOBBY: {
                itemStacks[getSlot(InventoryItem.QUEUE_UNRANKED)] = getItem(InventoryItem.QUEUE_UNRANKED);
                itemStacks[getSlot(InventoryItem.QUEUE_RANKED)] = getItem(InventoryItem.QUEUE_RANKED);
            }
            break;
            case IN_QUEUE: {
                itemStacks[getSlot(InventoryItem.LEAVE_QUEUE)] = getItem(InventoryItem.LEAVE_QUEUE);
            }
            break;
            case SPECTATING: {
                itemStacks[getSlot(InventoryItem.STOP_SPECTATING)] = getItem(InventoryItem.STOP_SPECTATING);
            }
            break;
        }

        PlayerUtil.resetPlayer(player);

        for (int i = 0; i < 9; i++) {
            player.getInventory().setItem(i, itemStacks[i]); // Set items in players inventory
        }

        player.updateInventory(); // Update inventory
    }

    public static ItemStack getItem(InventoryItem inventoryItem) {
        return items.get(inventoryItem).getItemStack();
    }

    public static int getSlot(InventoryItem inventoryItem) {
        return items.get(inventoryItem).getSlot();
    }

    public static InventoryItem fromItemStack(ItemStack itemStack) {
        for (Map.Entry<InventoryItem, InventoryEntry> entry : Inventory.getItems().entrySet()) {
            if (entry.getValue() != null && entry.getValue().getItemStack().equals(itemStack)) {
                return entry.getKey();
            }
        }

        return null;
    }


}
