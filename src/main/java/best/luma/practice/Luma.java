package best.luma.practice;

import best.luma.practice.api.command.CommandManager;
import best.luma.practice.arena.Arena;
import best.luma.practice.arena.listeners.ArenaListener;
import best.luma.practice.arena.listeners.ArenaWandListener;
import best.luma.practice.service.kit.Kit;
import best.luma.practice.service.party.PartyHandler;
import best.luma.practice.service.profile.listener.ProfileListener;
import best.luma.practice.storage.config.type.ConfigFile;
import best.luma.practice.utils.chat.CC;
import best.luma.practice.command.player.PingCommand;
import best.luma.practice.command.staff.TestCommand;
import best.luma.practice.service.profile.Profile;
import best.luma.practice.service.profile.database.ProfileDB;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

import java.util.Arrays;
import java.util.Random;

/**
 * @author ziue
 * Created at 5/30/2022
 * Project: Practice
 */

@Getter @Setter
public final class Luma extends JavaPlugin {

    public static Random RANDOM = new Random();
    @Getter private PartyHandler partyHandler;
    @Getter private MongoDatabase mongoDatabase;
    private ConfigFile config, database, messages, arenas, kits, mainTablist;

    @Override
    public void onEnable() {
        if(!Luma.getInstance().getDescription().getName().contains("Luma")) {
            Bukkit.getConsoleSender().sendMessage(CC.translate("&cYou edited the plugin.yml, please don't do that"));
            Bukkit.getConsoleSender().sendMessage(CC.translate(" &cPlease check your plugin.yml and try again."));
            Bukkit.getConsoleSender().sendMessage(CC.translate("            &cDisabling Luma"));
            Bukkit.getPluginManager().disablePlugin(this);
            Bukkit.shutdown();
            return;
        }

        // Load configs
        loadConfigs();

        // Load database
        setupDatabase();

        // Register listeners, managers and commands
        registerListeners();
        registerManagers();
        registerCommands();

        // Send startup message
        CC.sendStartupMessage();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerCommands() {
        new CommandManager(this);
        new TestCommand();
        new PingCommand();
    }

    public void registerManagers() {
        partyHandler = new PartyHandler();

        Arena.init();
        Kit.init();
        Profile.init();
    }

    public void registerListeners() {
        Arrays.asList(
                new ArenaWandListener(),
                new ArenaListener(),
                new ProfileListener()
        ).forEach(listener -> getServer().getPluginManager().registerEvents(listener, this));
    }

    public void loadConfigs() {
        this.config = new ConfigFile(this, "config");
        this.database = new ConfigFile(this, "connection/database");

        this.messages = new ConfigFile(this, "lang/messages");

        this.arenas = new ConfigFile(this, "cache/arenas");
        this.kits = new ConfigFile(this, "cache/kits");

        this.mainTablist = new ConfigFile(this, "tab/tablist");

        /*
         *  TODO: DO NOT DELETE
         *  this.mainTablist = new ConfigFile(this, "tab/event/event");
         *  this.mainTablist = new ConfigFile(this, "tab/match/teamfight");
         *  this.mainTablist = new ConfigFile(this, "tab/match/ffafight");
         *  this.mainTablist = new ConfigFile(this, "tab/party/partyffafight");
         *  this.mainTablist = new ConfigFile(this, "tab/party/partyteamfight");
         */

    }

    public void setupDatabase() {
        Profile.db = new ProfileDB();

        if(Profile.getDb() instanceof ProfileDB) {
            try {
                if (database.getBoolean("MONGO.AUTHENTICATION.ENABLED")) {
                    mongoDatabase = new MongoClient(new ServerAddress(database.getString("MONGO.HOST"), database.getInteger("MONGO.PORT")), MongoCredential.createCredential(database.getString("MONGO.AUTHENTICATION.USERNAME"), database.getString("MONGO.AUTHENTICATION.DATABASE"), database.getString("MONGO.AUTHENTICATION.PASSWORD").toCharArray()), MongoClientOptions.builder().build()).getDatabase(database.getString("MONGO.DATABASE"));
                } else {
                    mongoDatabase = new MongoClient(database.getString("MONGO.HOST"), database.getInteger("MONGO.PORT")).getDatabase(database.getString("MONGO.DATABASE"));
                }
            } catch (Exception e) {
                Bukkit.getConsoleSender().sendMessage(CC.translate("            &4&lMongo Internal Error"));
                Bukkit.getConsoleSender().sendMessage(CC.translate("        &cMongo is not setup correctly!"));
                Bukkit.getConsoleSender().sendMessage(CC.translate(     "&cPlease check your mongo and try again."));
                Bukkit.getConsoleSender().sendMessage(CC.translate("              &4&lDisabling Luma"));
                Bukkit.getServer().getPluginManager().disablePlugin(this);
                return;
            }
        }
    }

    public static Luma getInstance() {
        return getPlugin(Luma.class);
    }
}
