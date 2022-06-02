package best.luma.practice.service.match.impl;

import best.luma.practice.service.match.Match;
import best.luma.practice.arena.Arena;
import best.luma.practice.service.kit.Kit;
import best.luma.practice.service.match.team.GameParticipant;
import best.luma.practice.service.match.team.MatchGamePlayer;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

public class TeamMatch extends Match {

    private final GameParticipant<MatchGamePlayer> participantA;
    private final GameParticipant<MatchGamePlayer> participantB;
    private @Setter GameParticipant<MatchGamePlayer> winningParticipant;
    private @Setter
    GameParticipant<MatchGamePlayer> losingParticipant;

    public TeamMatch(Kit kit, Arena arena, boolean ranked, GameParticipant<MatchGamePlayer> participantA,
                          GameParticipant<MatchGamePlayer> participantB) {
        super(kit, arena, ranked);

        this.participantA = participantA;
        this.participantB = participantB;
    }

    @Override
    public boolean canEndMatch() {
        return false;
    }

    @Override
    public boolean canStartRound() {
        return false;
    }

    @Override
    public boolean canEndRound() {
        return false;
    }

    @Override
    public List<GameParticipant<MatchGamePlayer>> getParticipants() {
        return Arrays.asList(participantA, participantB);
    }

}
