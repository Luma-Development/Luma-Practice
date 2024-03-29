package best.luma.practice.service.event;

import best.luma.practice.Luma;
import best.luma.practice.service.match.team.MatchGamePlayer;
import lombok.Getter;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public abstract class Event implements Listener {

    @Getter private final EventType type;

    @Getter private UUID host;
    @Getter private int countdown;
    @Getter private Instant started;
    @Getter private boolean active;
    @Getter private boolean restricted;
    @Getter private Set<UUID> queued = new HashSet<>();

    protected Event(EventType type) {
        this.type = type;
    }

    void initialize(UUID host, int countdown, boolean restricted) {
        this.host = host;
        this.countdown = countdown;
        this.restricted = restricted;

        new BukkitRunnable() {

            @Override
            public void run() {
                Event.this.countdown--;

                if (Event.this.countdown == 0) {
                    active = true;
                    started = Instant.now();
                    startEvent(queued);

                    cancel();
                }
            }

        }.runTaskTimer(Luma.getInstance(), 20L, 20L);
    }

    public abstract void startEvent(Set<UUID> queued);
    public abstract Set<MatchGamePlayer> getParticipants();
    public abstract boolean isParticipant(UUID player);
    public abstract List<String> getLiveStatus();

}