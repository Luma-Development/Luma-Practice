package best.luma.practice.service.profile;

import best.luma.practice.Luma;
import best.luma.practice.service.match.Match;
import best.luma.practice.service.profile.database.DB;
import best.luma.practice.service.profile.database.ProfileDB;
import best.luma.practice.utils.chat.CC;
import best.luma.practice.utils.task.TaskUtils;
import com.mongodb.client.MongoCollection;
import lombok.Getter;
import lombok.Setter;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter @Setter
public class Profile {

    @Getter private static Map<UUID, Profile> profiles = new HashMap<>();
    @Getter public static MongoCollection<Document> collection;
    @Getter public static DB db;

    private Match match;

    private boolean online;

    private ProfileState state;
    private String name;
    private UUID uuid;

    public Profile(UUID uuid) {
        this.uuid = uuid;
        this.state = ProfileState.LOBBY;
        this.name = Bukkit.getOfflinePlayer(this.uuid).getName();
    }

    public static void init() {
        // load profiles from mongodb

        // Players might have joined before the plugin finished loading
        TaskUtils.runLater(() -> {
            if (db instanceof ProfileDB) {
                collection = Luma.getInstance().getMongoDatabase().getCollection("profiles");
                for (Document document : Luma.getInstance().getMongoDatabase().getCollection("profiles").find()) {
                    UUID uuid = UUID.fromString(document.getString("uuid"));
                    Profile profile = new Profile(uuid);

                    try {
                        TaskUtils.runAsync(profile::load);
                    } catch (Exception e) {
                        if (profile.isOnline()) {
                            profile.getPlayer().kickPlayer(CC.translate("&cThe server is loading..."));
                        }
                        throw new IllegalArgumentException("The profile of uuid " + uuid + " could not be loaded.");
                    }

                    profiles.put(uuid, profile);
                }
            }
        }, 40L);
    }

    public static Profile getProfile(UUID uuid) {
        Profile profile = profiles.get(uuid);
        if(profile == null) profile = new Profile(uuid);
        return profile;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    public void load() {
        db.load(this);
    }

    public void save() {
        db.save(this);
    }
}
