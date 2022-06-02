package best.luma.practice.command.staff;

import best.luma.practice.api.command.BaseCommand;
import best.luma.practice.api.command.Command;
import best.luma.practice.api.command.CommandArgs;
import best.luma.practice.utils.chat.CC;
import org.bukkit.entity.Player;

public class TestCommand extends BaseCommand {

    @Command(name = "test", permission = "rpractice.staff")
    @Override
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();
        player.sendMessage(CC.translate("&btest command"));
    }
}
