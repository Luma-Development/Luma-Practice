package best.luma.practice.arena;

import best.luma.practice.arena.cuboid.Cuboid;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class Arena extends Cuboid {

    @Getter private final static List<Arena> arenas = new ArrayList<>();

    @Getter protected String name;

    @Getter @Setter protected boolean active;

    @Setter protected Location spawnA;
    @Setter protected Location spawnB;

    public Arena(String name, Location location1, Location location2) {
        super(location1, location2);
        this.name = name;
    }

    public static void init() {

        // load arenas from yml config

    }

    public static Arena getByName(String name) {
        for(Arena arena : arenas) {
            if(arena.getName() != null && arena.getName().equalsIgnoreCase(name)) {
                return arena;
            }
        }
        return null;
    }

    public void delete() {
        arenas.remove(this);
    }

    public int getBuildHeight() {
        int highest = (int) Math.max(spawnA.getY(), spawnB.getY());
        return highest;
    }

    public Location getSpawnA() {
        if (spawnA == null) return null;

        return spawnA.clone();
    }

    public Location getSpawnB() {
        if (spawnB == null) return null;

        return spawnB.clone();
    }
}
