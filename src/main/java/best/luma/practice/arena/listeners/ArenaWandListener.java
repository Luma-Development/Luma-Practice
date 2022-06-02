package best.luma.practice.arena.listeners;

import best.luma.practice.arena.selection.Selection;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ArenaWandListener implements Listener {

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block clicked = event.getClickedBlock();
        ItemStack item = event.getItem();

        if (!(event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            return;
        }

        // Check if you're right or left-clicking a block
        if (item != null && item.equals(Selection.SELECTION_WAND)) {
            int location = 0;

            // Create selection
            Selection selection = Selection.createOrGetSelection(player);

            // Set selection location
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                selection.setPoint2(clicked.getLocation());
                location = 2;
            } else if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
                selection.setPoint1(clicked.getLocation());
                location = 1;
            }

            // Cancel event (To not break block)
            event.setCancelled(true);
            event.setUseItemInHand(Event.Result.DENY);
            event.setUseInteractedBlock(Event.Result.DENY);

            String message = (location == 1 ? "First" : "Second") +
                    " location " + "(" +
                    clicked.getX() + ", " +
                    clicked.getY() + ", " +
                    clicked.getZ() + ")" + " has been set!";

            if (selection.isFullObject()) {
                message += " (" + selection.getCuboid().volume() + " blocks" + ")";
            }
        }
    }

}
