package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        Statistics stats = new Statistics(new PlayerReaderImpl("http://nhlstats-2013-14.herokuapp.com/players.txt"));

        QueryBuilder query = new QueryBuilder();
        printList(stats,
                query.playsIn("PHI")
                        .hasAtLeast(10, "goals")
                        .hasFewerThan(20, "assists")
                        .or()
                        .playsIn("EDM")
                        .hasAtLeast(60, "points")
                        .build()
        );
    }

    private static void printList(Statistics stats, Matcher m) {
        for (Player player : stats.matches(m)) {
            System.out.println(player);
        }
        System.out.println();
    }
}
