package statistics.matcher;

import statistics.Player;

public class All implements Matcher {

    @Override
    public boolean test(Player player) {
        return true;
    }
}
