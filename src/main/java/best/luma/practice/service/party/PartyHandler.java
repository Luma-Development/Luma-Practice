package best.luma.practice.service.party;

import com.google.common.collect.ImmutableSet;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PartyHandler {

    static final int INVITE_EXPIRATION_SECONDS = 30;

    private final Set<Party> parties = Collections.newSetFromMap(new ConcurrentHashMap<>());
    private final Map<UUID, Party> playerPartyCache = new ConcurrentHashMap<>();

    public PartyHandler() {
    }

    public Set<Party> getParties() {
        return ImmutableSet.copyOf(parties);
    }

    public boolean hasParty(Player player) {
        return playerPartyCache.containsKey(player.getUniqueId());
    }

    public Party getParty(Player player) {
        return playerPartyCache.get(player.getUniqueId());
    }

    public Party getParty(UUID uuid) {
        return playerPartyCache.get(uuid);
    }

    public Party getOrCreateParty(Player player) {
        Party party = getParty(player);

        if (party == null) {
            party = new Party(player.getUniqueId());
            parties.add(party);
        }

        return party;
    }

    void removeParty(Party party) {
        parties.remove(party);
    }

    public void updatePartyCache(UUID playerUuid, Party party) {
        if (party != null) {
            playerPartyCache.put(playerUuid, party);
        } else {
            playerPartyCache.remove(playerUuid);
        }
    }

}