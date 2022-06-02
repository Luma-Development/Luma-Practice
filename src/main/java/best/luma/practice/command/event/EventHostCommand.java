package best.luma.practice.command.event;

import best.luma.practice.api.command.BaseCommand;
import best.luma.practice.api.command.Command;
import best.luma.practice.api.command.CommandArgs;

public class EventHostCommand extends BaseCommand {

    @Command(name = "event host", aliases = {"event start", "host"})
    @Override
    public void onCommand(CommandArgs command) {
        String[] args = command.getArgs();

        if (args.length == 0) {
            //menu
        }
    }
}