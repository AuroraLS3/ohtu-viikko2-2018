
package statistics.matcher;

import statistics.Player;

import java.util.function.Predicate;

public interface Matcher extends Predicate<Player> {
    @Deprecated
    default boolean matches(Player p) {
        return test(p);
    }
}
