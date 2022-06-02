package best.luma.practice.command.player;

import best.luma.practice.api.command.BaseCommand;
import best.luma.practice.api.command.Command;
import best.luma.practice.api.command.CommandArgs;
import best.luma.practice.utils.chat.CC;
import org.bukkit.entity.Player;

public class PingCommand extends BaseCommand {

    @Command(name = "ping", aliases = {"ms"})
    @Override
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();
        String[] args = command.getArgs();

        if (args.length == 0) {
            player.sendMessage(CC.translate("&bYour Ping: " + player.spigot().getPing()));
        }
    }
}
