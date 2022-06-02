package best.luma.practice.service.tournament.manager;

import best.luma.practice.api.command.CommandArgs;
import best.luma.practice.Luma;
import best.luma.practice.service.tournament.Tournament;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author ziue
 * Created at 5/30/2022
 * Project: Practice
 */

@Getter
@Setter
@RequiredArgsConstructor
public class TournamentManager {

    private final Luma plugin = Luma.getInstance();

    private final Map<UUID, Tournament> tournaments = new HashMap<>();

    public void createTournament(CommandArgs commandArgs, int teamSize, int size, String kitName) {
        Tournament tournament = new Tournament(UUID.randomUUID(), teamSize, size, kitName);
        this.tournaments.put(tournament.getUniqueId(), tournament);

    }

}
