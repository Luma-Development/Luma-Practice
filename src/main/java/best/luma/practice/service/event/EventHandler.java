package best.luma.practice.service.event;

import lombok.Getter;

import java.util.*;

public class EventHandler {

    @Getter
    private final Set<Event> activeEvents = new HashSet<>();

    public EventHandler() {
    }

    public Event beginEvent(EventType type, UUID host, int countdown, boolean restricted) {
        Event event = type.createInstance();
        event.initialize(host, countdown, restricted);

        activeEvents.add(event);
        return event;
    }

    public Event getNearestEvent() {
        List<Event> events = new ArrayList<>(activeEvents);

        events.removeIf(Event::isActive);
        events.sort(Comparator.comparing(Event::getCountdown));

        return events.isEmpty() ? null : events.get(0);
    }

}