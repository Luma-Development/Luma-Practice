package best.luma.practice.service.party.event;

import best.luma.practice.service.party.Party;
import lombok.Getter;
import org.bukkit.event.HandlerList;

public class PartyCreateEvent extends PartyEvent {

    @Getter private static HandlerList handlerList = new HandlerList();

    public PartyCreateEvent(Party party) {
        super(party);
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

}
