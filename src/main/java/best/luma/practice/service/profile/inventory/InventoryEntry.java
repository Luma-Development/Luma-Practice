package best.luma.practice.service.profile.inventory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

@Getter
@AllArgsConstructor
public class InventoryEntry {

    private ItemStack itemStack;
    private int slot;

}