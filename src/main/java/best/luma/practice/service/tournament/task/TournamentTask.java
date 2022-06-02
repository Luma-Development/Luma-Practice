package best.luma.practice.service.tournament.task;

import best.luma.practice.Luma;
import best.luma.practice.service.tournament.Tournament;
import lombok.RequiredArgsConstructor;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author ziue
 * Created at 5/30/2022
 * Project: Practice
 */

@RequiredArgsConstructor
public class TournamentTask extends BukkitRunnable {

    private final Luma plugin;
    private final Tournament tournament;

    @Override
    public void run() {

    }
}
