package best.luma.practice.service.match.events;

import best.luma.practice.service.match.Match;
import best.luma.practice.utils.event.CustomEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MatchEndEvent extends CustomEvent {

    private Match match;

}
