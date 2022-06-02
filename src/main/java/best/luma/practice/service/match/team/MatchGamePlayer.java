package best.luma.practice.service.match.team;

import lombok.Getter;

import java.util.UUID;

@Getter
public class MatchGamePlayer extends GamePlayer {

    private int elo;

    public MatchGamePlayer(UUID uuid, String username) {
        super(uuid, username);
    }

    public MatchGamePlayer(UUID uuid, String username, int elo) {
        super(uuid, username);

        this.elo = elo;
    }
}
