
public class TennisGame3 implements TennisGame {

    private static final String[] POSSIBLE_SCORE = new String[]{"Love", "Fifteen", "Thirty", "Forty"};
    private static final String DELIMITER = "-";
    private static final String ALL = "All";
    private static final String DEUCE = "Deuce";
    private static final String ADVANTAGE = "Advantage ";
    private static final String WIN_FOR = "Win for ";
    private int player1Point;
    private int player2Point;
    private final String player1Name;
    private final String player2Name;

    public TennisGame3(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public String getScore() {
        if (isSimpleScore()) {
            String score = POSSIBLE_SCORE[player1Point];
            return isEquality() ? score + DELIMITER + ALL : score + DELIMITER + POSSIBLE_SCORE[player2Point];
        }

        if (isEquality())
            return DEUCE;

        String playerWithAdvantageOrWin = player1Point > player2Point ? player1Name : player2Name;
        return isAdvantage() ? ADVANTAGE + playerWithAdvantageOrWin
                : WIN_FOR + playerWithAdvantageOrWin;
    }

    private boolean isAdvantage() {
        int ecartPoint = player1Point - player2Point;
        return ecartPoint * ecartPoint == 1;
    }

    private boolean isEquality() {
        return player1Point == player2Point;
    }

    private boolean isSimpleScore() {
        return player1Point < 4 && player2Point < 4 && (player1Point + player2Point != 6);
    }

    public void wonPoint(String playerName) {
        if (player1Name.equals(playerName))
            this.player1Point += 1;
        else
            this.player2Point += 1;

    }

}
