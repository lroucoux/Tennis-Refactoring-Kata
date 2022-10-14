
public class TennisGame2 implements TennisGame {
    private static final String LOVE = "Love";
    private static final String FIFTEEN = "Fifteen";
    private static final String THIRTY = "Thirty";
    private static final String ALL = "All";
    private static final String SCORE_DELIMITER = "-";
    private static final String DEUCE = "Deuce";
    private static final String FORTY = "Forty";
    private static final String ADVANTAGE = "Advantage ";
    private static final String WIN_FOR = "Win for ";
    private int player1Point = 0;
    private int player2Point = 0;

    private String player1Score = "";
    private String player2Score = "";
    private final String player1Name;
    private final String player2Name;

    public TennisGame2(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public String getScore() {
        String score = "";
        if (isEquality()) {
            return getScoreIfEquality(score);
        }

        if (player1Point > 0 && player2Point == 0) {
            if (player1Point == 1)
                player1Score = FIFTEEN;
            if (player1Point == 2)
                player1Score = THIRTY;
            if (player1Point == 3)
                player1Score = FORTY;

            player2Score = LOVE;
            score = player1Score + SCORE_DELIMITER + player2Score;
        }
        if (player2Point > 0 && player1Point == 0) {
            if (player2Point == 1)
                player2Score = FIFTEEN;
            if (player2Point == 2)
                player2Score = THIRTY;
            if (player2Point == 3)
                player2Score = FORTY;

            player1Score = LOVE;
            score = player1Score + SCORE_DELIMITER + player2Score;
        }

        if (player1Point > player2Point && player1Point < 4) {
            if (player1Point == 2)
                player1Score = THIRTY;
            if (player1Point == 3)
                player1Score = FORTY;
            if (player2Point == 1)
                player2Score = FIFTEEN;
            if (player2Point == 2)
                player2Score = THIRTY;
            score = player1Score + SCORE_DELIMITER + player2Score;
        }
        if (player2Point > player1Point && player2Point < 4) {
            if (player2Point == 2)
                player2Score = THIRTY;
            if (player2Point == 3)
                player2Score = FORTY;
            if (player1Point == 1)
                player1Score = FIFTEEN;
            if (player1Point == 2)
                player1Score = THIRTY;
            score = player1Score + SCORE_DELIMITER + player2Score;
        }

        if (player1Point > player2Point && player2Point >= 3) {
            score = ADVANTAGE + player1Name;
        }

        if (player2Point > player1Point && player1Point >= 3) {
            score = ADVANTAGE + player2Name;
        }

        if (player1Point >= 4 && player2Point >= 0 && (player1Point - player2Point) >= 2) {
            score = WIN_FOR + player1Name;
        }
        if (player2Point >= 4 && player1Point >= 0 && (player2Point - player1Point) >= 2) {
            score = WIN_FOR + player2Name;
        }
        return score;
    }

    private String getScoreIfEquality(String score) {
        if (player1Point == 0)
            score = LOVE;
        if (player1Point == 1)
            score = FIFTEEN;
        if (player1Point == 2)
            score = THIRTY;
        score += SCORE_DELIMITER + ALL;
        if (player1Point >= 3)
            score = DEUCE;

        return score;
    }

    private boolean isEquality() {
        return player1Point == player2Point;
    }

    public void wonPoint(String player) {
        if (player1Name.equals(player))
            player1Point++;
        else
            player2Point++;
    }
}