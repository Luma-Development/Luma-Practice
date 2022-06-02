package best.luma.practice.service.profile.listener;

import best.luma.practice.service.profile.inventory.impl.InventoryItem;
import best.luma.practice.service.profile.Profile;
import best.luma.practice.service.profile.ProfileState;
import best.luma.practice.service.profile.inventory.Inventory;
import best.luma.practice.utils.chat.CC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.*;

public class ProfileListener implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoinEvent(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		Profile profile = Profile.getProfile(player.getUniqueId());

		profile.setName(player.getName());
		profile.setOnline(true);

		profile.setState(ProfileState.LOBBY);
		player.sendMessage("set state to lobby");

		profile.setMatch(null);

		// Give inventory items to player on join
		//Inventory.giveInventoryItems(player);

		event.setJoinMessage(null); // Remove vanilla join message
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onLoginEvent(PlayerLoginEvent event) {
		Player player = event.getPlayer();

		if(event.getResult() == PlayerLoginEvent.Result.ALLOWED) {
			if(Profile.getProfiles().get(player.getUniqueId()) == null) {
				Profile profile = Profile.getProfile(player.getUniqueId());
				try {
					Profile.getProfiles().put(player.getUniqueId(), profile);
				} catch (Exception e) {
					event.disallow(PlayerLoginEvent.Result.KICK_OTHER, CC.translate("&cFailed to load profile."));
					throw new IllegalArgumentException(("Profile could not be created"));
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerQuitEvent(PlayerQuitEvent event) {
		Player player = event.getPlayer();

		event.setQuitMessage(null);

		Profile profile = Profile.getProfile(player.getUniqueId());
		profile.setOnline(false);
	}

	@EventHandler
	public void onInventoryItem(PlayerInteractEvent event) {
		// Check if item isn't null and if the action is equal to RIGHT_CLICK_AIR or RIGHT_CLICK_BLOCK
		if (event.getItem() != null && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
			Player player = event.getPlayer();
			InventoryItem inventoryItem = Inventory.fromItemStack(event.getItem());

			if (inventoryItem != null) { // If item isn't null.
				if (inventoryItem.getCommand() != null) { // If item has a command bound.
					event.setCancelled(true); // Cancel event
					player.chat("/" + inventoryItem.getCommand()); // Execute command
				}
			}
		}
	}

	@EventHandler(ignoreCancelled = true)
	public void onPlayerItemDamageEvent(PlayerItemDamageEvent event) {
		Profile profile = Profile.getProfile(event.getPlayer().getUniqueId());

		if (profile.getState() == ProfileState.LOBBY) {
			event.setCancelled(true); // Disable damage for players in lobby.
		}
	}

	@EventHandler
	public void onEntityDamageEvent(EntityDamageEvent event) {
		if(event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			Profile profile = Profile.getProfile(player.getUniqueId());

			if(profile.getState() == ProfileState.LOBBY || profile.getState() == ProfileState.IN_QUEUE) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onFoodLevelChangeEvent(FoodLevelChangeEvent event) {
		if(event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			Profile profile = Profile.getProfile(player.getUniqueId());

			if(profile.getState() == ProfileState.LOBBY || profile.getState() == ProfileState.IN_QUEUE) {
				event.setCancelled(true);
			}
		}
	}
}