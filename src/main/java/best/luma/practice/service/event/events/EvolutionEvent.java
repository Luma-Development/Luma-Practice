package best.luma.practice.service.event.events;

import best.luma.practice.service.event.EventType;
import best.luma.practice.service.match.events.MatchBackedEvent;
import com.google.common.collect.ImmutableList;
import org.bukkit.ChatColor;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public final class EvolutionEvent extends MatchBackedEvent {

    public EvolutionEvent() {
        super(EventType.EVOLUTION);
    }

    @Override
    public void startEvent(Set<UUID> queued) {
    }

    @Override
    public List<String> getLiveStatus() {
        return ImmutableList.of(
            ChatColor.WHITE + "Top 3:",
            ChatColor.WHITE + "  5 " + ChatColor.YELLOW + "Topu",
            ChatColor.WHITE + "  5 " + ChatColor.YELLOW + "ziue",
            ChatColor.WHITE + "  4 " + ChatColor.YELLOW + "Cead"
        );
    }

}