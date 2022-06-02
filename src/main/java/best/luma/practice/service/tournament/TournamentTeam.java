package best.luma.practice.service.tournament;

import best.luma.practice.Luma;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Stream;

/**
 * @author ziue
 * Created at 5/30/2022
 * Project: Practice
 */

@Getter
@Setter
public class TournamentTeam {

    private final Luma plugin = Luma.getInstance();

    private final List<UUID> players;
    private final List<UUID> alivePlayers = new ArrayList<>();
    private UUID leader;

    private final Map<UUID, String> playerNames = new HashMap<>();

    public TournamentTeam(UUID leader, List<UUID> players) {
        this.players = new ArrayList<>();
        this.leader = leader;

        for (UUID playerUUID : players) {
            this.playerNames.put(playerUUID, this.plugin.getServer().getPlayer(playerUUID).getName());
        }
    }

    public void broadcast(String message) {
        this.alivePlayers().forEach(player -> player.sendMessage(message));
    }

    public String getPlayerName(UUID playerUUID) {
        return this.playerNames.get(playerUUID);
    }

    public void killPlayer(UUID playerUUID) {
        this.alivePlayers.remove(playerUUID);
    }

    public Stream<Player> alivePlayers() {
        return this.alivePlayers.stream().map(this.plugin.getServer()::getPlayer).filter(Objects::nonNull);
    }

    public Stream<Player> players() {
        return this.players.stream().map(this.plugin.getServer()::getPlayer).filter(Objects::nonNull);
    }

    public String getLeaderName() {
        return this.getPlayerName(this.getLeader());
    }

    public String getNames() {
        StringBuilder names = new StringBuilder();

        for (int i = 0; i < this.getPlayers().size(); ++i) {
            UUID teammateUUID = this.getPlayers().get(i);
            Player teammate = Bukkit.getServer().getPlayer(teammateUUID);
            String name = teammate == null ? this.getPlayerName(teammateUUID) : teammate.getName();
            int players = this.getPlayers().size();
            if (teammate == null) continue;

            names.append(name)
                    .append(players - 1 == i ? "" : (players - 2 == i ? (players > 2 ? "," : "") + " & " : ", "));
        }
        return names.toString();
    }
}
