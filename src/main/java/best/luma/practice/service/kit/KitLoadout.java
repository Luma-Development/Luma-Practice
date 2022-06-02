package best.luma.practice.service.kit;

import lombok.Data;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

/**
 * @author ziue
 * Created at 5/30/2022
 * Project: Practice
 */

@Data
public class KitLoadout {

    private String customName = "Default";
    private ItemStack[] armor;
    private ItemStack[] contents;

    public KitLoadout() {
        this.armor = new ItemStack[4];
        this.contents = new ItemStack[36];
    }

    public KitLoadout(String customName) {
        this.customName = customName;
        this.armor = new ItemStack[4];
        this.contents = new ItemStack[36];
    }

}
