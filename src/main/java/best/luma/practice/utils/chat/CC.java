package best.luma.practice.utils.chat;

import best.luma.practice.Luma;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class CC {

    public static void sendStartupMessage() {
        Bukkit.getConsoleSender().sendMessage(translate(" "));
        Bukkit.getConsoleSender().sendMessage(translate("     &9&l" + Luma.getInstance().getName()));
        Bukkit.getConsoleSender().sendMessage(translate(""));
        Bukkit.getConsoleSender().sendMessage(translate(" &7| &9Author&7: &f" + Luma.getInstance().getDescription().getAuthors().toString().replace("[", "").replace("]", "")));
        Bukkit.getConsoleSender().sendMessage(translate(" &7| &9Version&7: &f" + Luma.getInstance().getDescription().getVersion()));
        Bukkit.getConsoleSender().sendMessage(translate(" "));
        Bukkit.getConsoleSender().sendMessage(translate(" &7| &9Spigot&7: &f" + Luma.getInstance().getServer().getName()));
        Bukkit.getConsoleSender().sendMessage(translate(" "));
        Bukkit.getConsoleSender().sendMessage(translate(" &9Loaded info"));
        Bukkit.getConsoleSender().sendMessage(translate(" &7| &9Arenas&7: &f" + "arena size here"));
        Bukkit.getConsoleSender().sendMessage(translate(" &7| &9Kits&7: &f" + "kit size here"));
        Bukkit.getConsoleSender().sendMessage(translate(" &7| &9Kits Ranked&7: &f" + "kit ranked size here"));
        Bukkit.getConsoleSender().sendMessage(translate(" &7| &9Clans&7: &f" + "clan size here"));
        Bukkit.getConsoleSender().sendMessage(translate(" "));
    }

    public static String translate(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

}
