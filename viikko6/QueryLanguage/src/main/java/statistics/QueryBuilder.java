package statistics;

import statistics.matcher.*;

/**
 * TODO Class Javadoc comment
 *
 * @author Rsl1122
 */
public class QueryBuilder {

    private Matcher combiningWith;
    private Matcher current;

    public QueryBuilder() {
        combiningWith = new All();
        current = combiningWith;
    }

    public QueryBuilder(Matcher readyStatement) {
        this();
        this.current = new Or(readyStatement, player -> combiningWith.test(player));
    }

    public QueryBuilder playsIn(String team) {
        return combine(new PlaysIn(team));
    }

    private QueryBuilder combine(Matcher matcher) {
        combiningWith = new And(combiningWith, matcher);
        return this;
    }

    public QueryBuilder hasAtLeast(int value, String of) {
        return combine(new HasAtLeast(value, of));
    }

    public QueryBuilder hasFewerThan(int value, String of) {
        return combine(new HasFewerThan(value, of));
    }

    public QueryBuilder or() {
        return new QueryBuilder(combiningWith);
    }

    public Matcher build() {
        return current;
    }

}
