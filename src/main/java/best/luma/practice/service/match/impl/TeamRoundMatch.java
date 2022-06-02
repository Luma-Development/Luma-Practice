package best.luma.practice.service.match.impl;

import best.luma.practice.service.match.Match;
import best.luma.practice.arena.Arena;
import best.luma.practice.service.kit.Kit;
import best.luma.practice.service.match.team.GameParticipant;
import best.luma.practice.service.match.team.MatchGamePlayer;

import java.util.List;

public class TeamRoundMatch extends Match {

    public TeamRoundMatch(Kit kit, Arena arena, boolean ranked) {
        super(kit, arena, ranked);
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
        return null;
    }
}
