
public class TennisGame1 implements TennisGame {

    private static final String LOVE = "Love";
    private static final String FIFTEEN = "Fifteen";
    private static final String THIRTY = "Thirty";
    private static final String DEUCE = "Deuce";
    private static final String FORTY = "Forty";
    private static final String ALL = "All";
    private static final String ADVANTAGE = "Advantage ";
    private static final String WIN_FOR = "Win for ";
    private static final String SCORE_SEPARATOR = "-";

    private Score player1Score = new Score();
    private Score player2Score = new Score();

    private String player1Name;
    private String player2Name;

    public TennisGame1(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (player1Name.equals(playerName))
            player1Score.addPoint();
        else
            player2Score.addPoint();
    }

    private String getScoreNominal() {
        return player1Score.translate() + SCORE_SEPARATOR + player2Score.translate();
    }

    public String getScore() {
        if (isEquality()) {
            return getScoreWhenEquality();
        }
        if (isAdvantageOrWin()) {
            return getScoreWhenAdvantageOrWin();
        }
        return getScoreNominal();
    }

    private boolean isAdvantageOrWin() {
        return player1Score.get() >= 4 || player2Score.get() >= 4;
    }

    private boolean isEquality() {
        return player1Score.compare(player2Score) == 0;
    }

    private String getScoreWhenAdvantageOrWin() {

        int scoreDifference = getDifferenceScore();
        if (scoreDifference > 0) {
            return advantageOrWinForPlayer(player1Name, scoreDifference);
        }
        return advantageOrWinForPlayer(player2Name, scoreDifference);
    }

    private String advantageOrWinForPlayer(String playerName, Integer advantage) {
        if (Math.abs(advantage) == 1) {
            return ADVANTAGE + playerName;
        } else {
            return WIN_FOR + playerName;
        }
    }

    private int getDifferenceScore() {
        return player1Score.get() - player2Score.get();
    }

    private String getScoreWhenEquality() {
        if (player1Score.get() < 3) {
            return player1Score.translate() + SCORE_SEPARATOR + ALL;
        } else {
            return DEUCE;
        }
    }


    private class Score {

        private Integer current = 0;

        public void addPoint() {
            this.current++;
        }

        public Integer compare(Score score) {
            return this.current.compareTo(score.get());
        }

        public Integer get() {
            return current;
        }

        public String translate() {
            switch (current) {
                case 0:
                    return LOVE;
                case 1:
                    return FIFTEEN;
                case 2:
                    return THIRTY;
                case 3:
                    return FORTY;
                default:
                    throw new IllegalArgumentException("Score interdit");
            }
        }

    }
}
