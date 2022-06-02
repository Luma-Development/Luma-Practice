package best.luma.practice.service.match;

import best.luma.practice.Luma;
import best.luma.practice.service.match.events.MatchStartEvent;
import best.luma.practice.arena.Arena;
import best.luma.practice.service.kit.Kit;
import best.luma.practice.service.match.events.MatchEndEvent;
import best.luma.practice.service.match.task.MatchTask;
import best.luma.practice.service.match.team.GameParticipant;
import best.luma.practice.service.match.team.MatchGamePlayer;
import best.luma.practice.service.profile.Profile;
import best.luma.practice.service.profile.ProfileState;
import best.luma.practice.service.profile.inventory.Inventory;
import best.luma.practice.utils.chat.CC;
import best.luma.practice.utils.player.PlayerUtil;
import best.luma.practice.utils.task.TaskUtils;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class Match {

    @Getter protected static List<Match> matches = new ArrayList<>();

    @Setter protected MatchState matchState = MatchState.STARTING_ROUND;

    private GameParticipant winner;

    protected boolean ranked;
    protected Arena arena;
    protected Kit kit;

    protected MatchTask logicTask;
    protected long timeData;

    public Match(Kit kit, Arena arena, boolean ranked) {
        this.kit = kit;
        this.arena = arena;
        this.ranked = ranked;
        matches.add(this);
    }

    public void setupPlayer(Player player) {
        MatchGamePlayer gamePlayer = getGamePlayer(player);
        gamePlayer.setDead(false);

        if(gamePlayer.isDisconnected()) return;

        Profile profile = Profile.getProfile(player.getUniqueId());

        PlayerUtil.resetPlayer(player);
        player.setMaximumNoDamageTicks(20);

        // if player has no custom kit, apply kit, if they do, give book where they can select their kit

    }

    public void startMatch() {
        matchState = MatchState.STARTING_ROUND;

        logicTask = new MatchTask(this);
        logicTask.runTaskTimer(Luma.getInstance(), 0, 20);

        arena.setActive(true);

        // TODO: send arena message

        for(GameParticipant<MatchGamePlayer> gameParticipant : getParticipants()) {
            for(MatchGamePlayer gamePlayer : gameParticipant.getPlayers()) {
               Player player = gamePlayer.getPlayer();

               if(player != null) {
                   Profile profile = Profile.getProfile(player.getUniqueId());
                   profile.setState(ProfileState.FIGHTING);
                   profile.setMatch(this);

                   TaskUtils.run(() -> setupPlayer(player));
               }
            }
        }

        // TODO: Handle player visibility

        timeData = System.currentTimeMillis();
        new MatchStartEvent(this).call();
    }

    public void onDeath(Player player) {
        // Don't continue if the match is already ending
        if (!(matchState == MatchState.STARTING_ROUND || matchState == MatchState.PLAYING)) return;

        MatchGamePlayer deadGamePlayer = getGamePlayer(player);

        // Don't continue if the player is already dead
        if (deadGamePlayer.isDead()) return;

        Profile profile = Profile.getProfile(player.getUniqueId());

        // TODO add inventory snapshots

    }

    public void endMatch() {
        new MatchEndEvent(this).call();

        for(GameParticipant<MatchGamePlayer> gameParticipant : getParticipants()) {
            for(MatchGamePlayer gamePlayer : gameParticipant.getPlayers()) {
               if(!gamePlayer.isDisconnected()) {
                   Player player = gamePlayer.getPlayer();

                   if(player != null) {
                       Profile profile = Profile.getProfile(player.getUniqueId());

                       player.setFireTicks(0);
                       player.updateInventory();

                       profile.setState(ProfileState.LOBBY);
                       profile.setMatch(null);

                       // Visibility shit
                       Inventory.giveInventoryItems(player);
                       // teleport to spawn
                   }
               }
            }
        }

    }

    public abstract boolean canEndMatch();

    public abstract boolean canStartRound();
    public abstract boolean canEndRound();

    public MatchGamePlayer getGamePlayer(Player player) {
        for(GameParticipant<MatchGamePlayer> gameParticipant : getParticipants()) {
            for(MatchGamePlayer gamePlayer : gameParticipant.getPlayers()) {
                if(gamePlayer.getUuid().equals(player.getUniqueId())) {
                    return gamePlayer;
                }
            }
        }
        return null;
    }

    public abstract List<GameParticipant<MatchGamePlayer>> getParticipants();

    public void sendMessage(String message) {
        for(GameParticipant<MatchGamePlayer> gameParticipant : getParticipants()) {
            gameParticipant.sendMessage(CC.translate(message));
        }
    }

}
