
public class TennisGame1 implements TennisGame {

    private static final String LOVE = "Love";
    private static final String FIFTEEN = "Fifteen";
    private static final String THIRTY = "Thirty";
    private static final String FORTY = "Forty";
    private static final String ALL = "All";
    private static final String SCORE_DELIMITER = "-";
    private static final String DEUCE = "Deuce";
    private static final String ADVANTAGE = "Advantage ";
    private static final String WIN_FOR = "Win for ";
    private int player1Score = 0;
    private int player2Score = 0;
    private final String player1Name;
    private final String player2Name;

    public TennisGame1(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name))
            player1Score++;
        else
            player2Score++;
    }

    public String getScore() {
        if (player1Score == player2Score) {
            return manageScoreWhenEquality();
        }
        if (player1Score >= 4 || player2Score >= 4) {
            return manageScoreWhenAdvantageOrWin();
        }

        return manageSimpleScore();
    }

    private String manageSimpleScore() {
        String score = translateIntScoreToStringScore(player1Score);
        score += SCORE_DELIMITER;
        score += translateIntScoreToStringScore(player2Score);
        return score;
    }

    private String manageScoreWhenAdvantageOrWin() {
        int player1AdvantageValue = player1Score - player2Score;

        if (player1AdvantageValue == 1) return ADVANTAGE + player1Name;
        if (player1AdvantageValue == -1) return ADVANTAGE + player2Name;
        if (player1AdvantageValue >= 2) return WIN_FOR + player1Name;

        return WIN_FOR + player2Name;
    }

    private String translateIntScoreToStringScore(int score) {
        return switch (score) {
            case 0 -> LOVE;
            case 1 -> FIFTEEN;
            case 2 -> THIRTY;
            case 3 -> FORTY;
            default -> throw new IllegalStateException("Unexpected value: " + score);
        };
    }

    private String manageScoreWhenEquality() {
        return switch (player1Score) {
            case 0 -> LOVE + SCORE_DELIMITER + ALL;
            case 1 -> FIFTEEN + SCORE_DELIMITER + ALL;
            case 2 -> THIRTY + SCORE_DELIMITER + ALL;
            default -> DEUCE;
        };
    }
}
