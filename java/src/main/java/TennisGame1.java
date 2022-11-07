
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

    private int player1Score = 0;
    private int player2Score = 0;

    private String player1Name;
    private String player2Name;

    public TennisGame1(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (player1Name.equals(playerName))
            player1Score += 1;
        else
            player2Score += 1;
    }

    public String getScore() {
        if (player1Score == player2Score) {
            return getScoreWhenEquality();
        } else if (player1Score >= 4 || player2Score >= 4) {
            return getScoreWhenAdvantageOrWin();
        } else {
            return getScoreNominal();
        }
    }

    private String getScoreNominal() {
        return translateScore(player1Score) + SCORE_SEPARATOR + translateScore(player2Score);
    }

    private String getScoreWhenAdvantageOrWin() {
        int minusResult = compareScore();
        if (minusResult == 1) {
            return ADVANTAGE + player1Name;
        } else if (minusResult == -1) {
            return ADVANTAGE + player2Name;
        } else if (minusResult >= 2) {
            return WIN_FOR + player1Name;
        } else {
            return WIN_FOR + player2Name;
        }
    }

    private int compareScore() {
        return player1Score - player2Score;
    }

    private String getScoreWhenEquality() {
        if(player1Score < 3) {
            return translateScore(player1Score) + SCORE_SEPARATOR + ALL;
        } else {
            return DEUCE;
        }
    }

    private String translateScore(Integer score){
        switch (score) {
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
