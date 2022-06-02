package best.luma.practice.arena;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ArenaType {

    BUILDING, // Arenas that has block placement (Cannot be shared with multiple matches)
    SHARED, // Arenas can be played on by multiple people at the same time.
    EVENT // Arenas that are for events ONLY.

}
