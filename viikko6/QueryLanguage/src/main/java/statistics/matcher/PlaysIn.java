
package statistics.matcher;

import statistics.Player;

public class PlaysIn implements Matcher {
    private String team;

    public PlaysIn(String team) {
        this.team = team;
    }        
    
    @Override
    public boolean test(Player p) {
        return p.getTeam().contains(team);
    }
    
}
