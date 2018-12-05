package statistics.matcher;

import statistics.Player;

public class Not implements Matcher {

    private final Matcher matcher;

    public Not(Matcher matcher) {
        this.matcher = matcher;
    }

    @Override
    public boolean test(Player player) {
        return !matcher.test(player);
    }
}
