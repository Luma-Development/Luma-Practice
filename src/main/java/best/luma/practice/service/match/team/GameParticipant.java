package best.luma.practice.service.match.team;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class GameParticipant<T extends GamePlayer> {

    @Getter private final T leader;

    public GameParticipant(T leader) {
        this.leader = leader;
    }

    public List<T> getPlayers() {
        return Collections.singletonList(leader);
    }

    public void sendMessage(String message) {
        for (GamePlayer gamePlayer : getPlayers()) {
            if (!gamePlayer.isDisconnected()) {
                Player player = gamePlayer.getPlayer();

                if (player != null) {
                    player.sendMessage(message);
                }
            }
        }
    }

}
