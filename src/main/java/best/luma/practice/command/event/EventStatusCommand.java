package best.luma.practice.command.event;

import best.luma.practice.api.command.BaseCommand;
import best.luma.practice.api.command.Command;
import best.luma.practice.api.command.CommandArgs;
import best.luma.practice.utils.chat.CC;
import org.bukkit.Bukkit;

public class EventStatusCommand extends BaseCommand {

    @Command(name = "event announce", aliases = {"event alert"})
    @Override
    public void onCommand(CommandArgs command) {
        String[] args = command.getArgs();

        if (args.length == 0) {
            Bukkit.broadcastMessage(CC.translate("&bEvent Status"));
        }
    }
}