package best.luma.practice.service.kit;

import best.luma.practice.Luma;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Kit {

    private static final List<Kit> kits = new ArrayList<>();

    private final String name;
    @Setter private boolean isEnabled;

    public Kit(String name) {
        this.name = name;
    }

    public static void init() {

        FileConfiguration config = Luma.getInstance().getKits().getConfiguration();

    }

    public void saveKit() {

    }

    public void deleteKit() {

    }

    public static Kit getKit(String name) {
        for (Kit kit : kits) {
            if(kit.getName().equalsIgnoreCase(name)) {
                return kit;
            }
        }
        return null;
    }
}
