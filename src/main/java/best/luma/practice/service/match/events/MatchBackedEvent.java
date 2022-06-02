package best.luma.practice.service.match.events;

import best.luma.practice.service.event.Event;
import best.luma.practice.service.event.EventType;
import best.luma.practice.service.match.Match;
import best.luma.practice.service.match.team.MatchGamePlayer;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public abstract class MatchBackedEvent extends Event {

    protected Match match;

    protected MatchBackedEvent(EventType type) {
        super(type);
    }

    @Override
    public Set<MatchGamePlayer> getParticipants() {
        return match.getParticipants().stream()
            .flatMap(t -> t.getPlayers().stream())
            .collect(Collectors.toSet());
    }

    @Override
    public boolean isParticipant(UUID player) {
        return match.getParticipants() != null;
    }

}