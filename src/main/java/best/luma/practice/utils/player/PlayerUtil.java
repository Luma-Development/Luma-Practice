package best.luma.practice.utils.player;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class PlayerUtil {

    public static void resetPlayer(Player player) {
        player.setHealth(20.0D);
        player.setSaturation(20.0F);
        player.setFallDistance(0.0F);
        player.setFoodLevel(20);
        player.setMaximumNoDamageTicks(20);
        player.setGameMode(GameMode.SURVIVAL);
    }
}
