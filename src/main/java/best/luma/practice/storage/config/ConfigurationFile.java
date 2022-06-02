package best.luma.practice.storage.config;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public abstract class ConfigurationFile {

    public static String FILE_EXTENSION = ".yml";
    private JavaPlugin plugin;
    private String name;

    public ConfigurationFile(JavaPlugin plugin, String name) {
        this.plugin = plugin;
        this.name = name;
    }

    public abstract String getString(String str);
    public abstract String getStringOrDefault(String str, String str2);

    public abstract int getInteger(String str);

    public abstract double getDouble(String str);

    public abstract long getLong(String str);

    public abstract Object get(String str);

    public abstract List<String> getStringList(String str);

    public JavaPlugin getPlugin() {
        return this.plugin;
    }

    public String getName() {
        return this.name;
    }

}
