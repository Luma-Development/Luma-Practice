package best.luma.practice.service.party.event;

import com.google.common.base.Preconditions;
import best.luma.practice.service.party.Party;
import lombok.Getter;
import org.bukkit.event.Event;

abstract class PartyEvent extends Event {

    @Getter private Party party;

    PartyEvent(Party party) {
        this.party = Preconditions.checkNotNull(party, "party");
    }

}
