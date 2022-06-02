package best.luma.practice.service.match.task;

import best.luma.practice.service.match.Match;
import best.luma.practice.service.match.MatchState;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.scheduler.BukkitRunnable;

public class MatchTask extends BukkitRunnable {

    private final Match match;

    private int totalTicked;
    @Getter @Setter private int nextAction;

    public MatchTask(Match match) {
        this.match = match;

        nextAction = 6;
    }

    @Override
    public void run() {
        totalTicked++;
        nextAction--;

        if (match.getMatchState() == MatchState.STARTING_ROUND) {
            if(nextAction == 0) {
                match.setMatchState(MatchState.PLAYING);
                match.sendMessage("match started");
            } else {
                // TODO: add match starting countdown
            }

        } else if (match.getMatchState() == MatchState.ENDING_ROUND) {
            // TODO check nextAction, start new round.
        } else if (match.getMatchState() == MatchState.ENDING_MATCH) {
            // TODO Reset placed blocks in match
        }
    }
}
