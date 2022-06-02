package best.luma.practice.service.event.events;

import best.luma.practice.service.event.EventType;
import best.luma.practice.service.match.events.MatchBackedEvent;
import com.google.common.collect.ImmutableList;
import org.bukkit.ChatColor;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public final class KothEvent extends MatchBackedEvent {

    public KothEvent() {
        super(EventType.KOTH);
    }

    @Override
    public void startEvent(Set<UUID> queued) {
    }

    @Override
    public List<String> getLiveStatus() {
        return ImmutableList.of(
            ChatColor.YELLOW + "Eddythepro" + ChatColor.WHITE + " capturing",
            ChatColor.YELLOW + "5:15" + ChatColor.WHITE + " remaining"
        );
    }

}