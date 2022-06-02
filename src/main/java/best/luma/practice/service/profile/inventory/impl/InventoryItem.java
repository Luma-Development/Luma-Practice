package best.luma.practice.service.profile.inventory.impl;

import lombok.Getter;
import lombok.Setter;

import java.util.regex.Pattern;

public enum InventoryItem {

    QUEUE_RANKED(null),
    QUEUE_UNRANKED(null),
    LEAVE_QUEUE(null),
    STOP_SPECTATING(null);

    @Getter private final String command;
    @Getter @Setter
    private Pattern pattern;

    InventoryItem(String command) {
        this.command = command;
    }

}
