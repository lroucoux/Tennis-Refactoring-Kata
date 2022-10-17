
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

    private final String player1Name;
    private final String player2Name;

    public TennisGame2(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public String getScore() {
        if (isEquality()) {
            return getScoreWhenEquality();
        }

        if (isSimpleScore()) {
            return getScoreWhenSimpleScore();
        }

        return getScoreWHenAdvantageOrVictory();
    }

    private boolean isSimpleScore() {
        return player1Point < 4 && player2Point < 4;
    }

    private String getScoreWHenAdvantageOrVictory() {

        int player1AdvantageValue = player1Point - player2Point;
        boolean isPlayer1Win = player1Point >= 4 && player2Point >= 0 && player1AdvantageValue >= 2;
        boolean isPlayer2Win = player2Point >= 4 && player1Point >= 0 && player1AdvantageValue <= -2;
        boolean isAnyPlayerWin = isPlayer1Win || isPlayer2Win;

        if (isAnyPlayerWin) {
            return WIN_FOR + playerWin(isPlayer1Win);
        }

        boolean hasPlayer1Advantage = player1Point > player2Point && player2Point >= 3;

        return ADVANTAGE + playerWithAdvantage(hasPlayer1Advantage);
    }

    private String playerWin(boolean isPlayer1Win) {
        return playerWithAdvantageOrWin(isPlayer1Win);
    }

    private String playerWithAdvantage(boolean hasPlayer1Advantage) {
        return playerWithAdvantageOrWin(hasPlayer1Advantage);
    }


    private String playerWithAdvantageOrWin(boolean isPlayer1WithAdvantageOrWin) {
        if (isPlayer1WithAdvantageOrWin) {
            return player1Name;
        }

        return player2Name;
    }

    private String getScoreWhenSimpleScore() {
        String player1Score = translatePointToScore(player1Point);
        String player2Score = translatePointToScore(player2Point);

        return player1Score + SCORE_DELIMITER + player2Score;
    }

    private String translatePointToScore(int playerPoint) {
        return switch (playerPoint) {
            case 0 -> LOVE;
            case 1 -> FIFTEEN;
            case 2 -> THIRTY;
            case 3 -> FORTY;
            default -> throw new IllegalStateException("Unexpected value: " + playerPoint);
        };
    }

    private String getScoreWhenEquality() {
        if (player1Point >= 3)
            return DEUCE;

        return translatePointToScore(player1Point) + SCORE_DELIMITER + ALL;
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