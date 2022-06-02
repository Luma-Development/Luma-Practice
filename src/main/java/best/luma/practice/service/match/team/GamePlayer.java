package best.luma.practice.service.match.team;

import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

@Data
public class GamePlayer {

    private UUID uuid;
    private String username;

    private boolean disconnected;
    private boolean isDead;

    public GamePlayer(UUID uuid, String username) {
        this.uuid = uuid;
        this.username = username;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }
}
