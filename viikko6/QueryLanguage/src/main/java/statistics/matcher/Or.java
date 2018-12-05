package statistics.matcher;

import statistics.Player;

public class Or implements Matcher {

    private Matcher[] matchers;

    public Or(Matcher... matchers) {
        this.matchers = matchers;
    }

    @Override
    public boolean test(Player p) {
        for (Matcher matcher : matchers) {
            if (matcher.test(p)) {
                return true;
            }
        }

        return false;
    }
}
