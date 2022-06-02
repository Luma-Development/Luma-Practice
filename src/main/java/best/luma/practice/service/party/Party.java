package best.luma.practice.service.party;

import best.luma.practice.Luma;
import best.luma.practice.service.party.event.PartyCreateEvent;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;

import java.util.Set;
import java.util.UUID;

public class Party {

    @Getter private final UUID id = new UUID(Luma.RANDOM.nextLong(), Luma.RANDOM.nextLong());
    public static int MAX_SIZE = 30;
    @Getter private UUID leader;
    private Set<UUID> members = Sets.newLinkedHashSet();
    @Getter @Setter private PartyAccessState accessState = PartyAccessState.CLOSED;
    @Getter @Setter private String password = null;

    Party(UUID leader) {
        this.leader = Preconditions.checkNotNull(leader, "leader");
        this.members.add(leader);

        Luma.getInstance().getPartyHandler().updatePartyCache(leader, this);
        Bukkit.getPluginManager().callEvent(new PartyCreateEvent(this));
    }

}
