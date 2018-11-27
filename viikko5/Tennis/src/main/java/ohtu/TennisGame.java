package ohtu;

public class TennisGame {

    private int p1Points = 0;
    private int p2Points = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (player1Name.equals(playerName)) {
            p1Points++;
        } else {
            p2Points++;
        }
    }

    public String getScore() {
        boolean tie = p1Points == p2Points;
        boolean lateGame = p1Points >= 4 || p2Points >= 4;

        if (tie) {
            return tied();
        } else if (lateGame) {
            return lateGame();
        } else {
            return earlyGame();
        }
    }

    private String earlyGame() {
        return getScoreName(p1Points) + "-" + getScoreName(p2Points);
    }

    private String lateGame() {
        int pointDifference = p1Points - p2Points;
        boolean hasAdvantage = Math.abs(pointDifference) == 1;

        String score = hasAdvantage ? "Advantage " : "Win for ";
        score += pointDifference > 0 ? player1Name : player2Name;

        return score;
    }

    private String tied() {
        String scoreName = getScoreName(p1Points);
        if (scoreName == null) {
            return "Deuce";
        } else {
            return scoreName + "-All";
        }
    }

    private String getScoreName(int points) {
        switch (points) {
            case 0:
                return "Love";
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            case 3:
                return "Forty";
            default:
                return null;
        }
    }
}