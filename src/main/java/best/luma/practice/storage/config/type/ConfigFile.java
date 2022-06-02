package best.luma.practice.storage.config.type;

import best.luma.practice.Luma;
import best.luma.practice.storage.config.ConfigurationFile;
import com.google.common.io.Files;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ConfigFile extends ConfigurationFile {

    private final File file;
    private final YamlConfiguration configuration;

    public ConfigFile(final JavaPlugin plugin, final String name, final boolean overwrite) {
        super(plugin, name);

        this.file = new File(plugin.getDataFolder(), name + FILE_EXTENSION);
        plugin.saveResource(name + FILE_EXTENSION, overwrite);
        configuration = new YamlConfiguration();

        try {
            configuration.loadFromString(Files.toString(file, StandardCharsets.UTF_8));
        } catch (InvalidConfigurationException | IOException e) {
            e.printStackTrace();
        }
    }

    public ConfigFile(final JavaPlugin plugin, final String name) {
        this(plugin, name, false);
    }

    @Override
    public String getString(String str) {
        if(this.configuration.contains(str)) {
            return ChatColor.translateAlternateColorCodes('&', this.configuration.getString(str));
        }
        return null;
    }

    @Override
    public String getStringOrDefault(String str, String str2) {
        String toReturn = this.getString(str);
        return (toReturn == null) ? str2 : toReturn;
    }

    @Override
    public int getInteger(String str) {
        if(this.configuration.contains(str)) {
            return this.configuration.getInt(str);
        }
        return 0;
    }

    @Override
    public double getDouble(String str) {
        if(this.configuration.contains(str)) {
            return this.configuration.getDouble(str);
        }
        return 0.0;
    }

    @Override
    public long getLong(String str) {
        if(this.configuration.contains(str)) {
            return this.configuration.getLong(str);
        }
        return 0;
    }

    public boolean getBoolean(String str) {
        return this.configuration.contains(str) && this.configuration.getBoolean(str);
    }

    @Override
    public Object get(String str) {
        if(this.configuration.contains(str)) {
            return this.configuration.get(str);
        }
        return null;
    }

    @Override
    public List<String> getStringList(String str) {
        if(this.configuration.contains(str)) {
            return this.configuration.getStringList(str);
        }
        return null;
    }

    public void save() {
        File folder = Luma.getInstance().getDataFolder();
        try {
            getConfiguration().save(new File(folder, getName() + FILE_EXTENSION));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getFile() {
        return this.file;
    }

    public YamlConfiguration getConfiguration() {
        return this.configuration;
    }
}
