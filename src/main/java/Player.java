import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Player {

    private static final List<String> POINTS = new ArrayList<String>() {{
        add("0");
        add("15");
        add("30");
        add("40");
    }};
    private static final String ADVANTAGE = "A";
    private static final int SCORE_0 = 0;
    private static final int SCORE_40 = 3;
    private boolean onAdvantage = false;
    private boolean gameSetWon = false;
    private String fullName;
    private int indexPoint = SCORE_0;
    private int nbOfGamesWon = SCORE_0;
    private int nbOfGameSetsWon = SCORE_0;


    public Player(String fullName) {
        this.fullName = fullName;
    }

    public void increasePoint() {
        indexPoint++;
        if (indexPoint > POINTS.size() - 1) {
            increaseNbOfGamesWon();
        }
    }

    public void resetPoints() {
        indexPoint = SCORE_0;
        onAdvantage = false;
    }

    public String retrievePoint() {
        return onAdvantage ? POINTS.get(indexPoint) + ADVANTAGE : POINTS.get(indexPoint);
    }

    public int getNbOfGamesWon() {
        return nbOfGamesWon;
    }

    public void toggleAdvantage() {
        onAdvantage = !onAdvantage;
    }

    public boolean isScore40() {
        return indexPoint == SCORE_40;
    }

    public boolean isOnAdvantage() {
        return onAdvantage;
    }

    public void increaseNbOfGamesWon() {
        nbOfGamesWon++;
        resetPoints();
    }

    public boolean isGameSetWon() {
        return gameSetWon;
    }

    public void resetGameSet() {
        resetPoints();
        nbOfGamesWon = SCORE_0;
        gameSetWon = false;
    }

    public void increaseNbOfGameSetsWon() {
        nbOfGameSetsWon++;
        gameSetWon = true;
    }

    public int getNbOfGameSetsWon() {
        return nbOfGameSetsWon;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("\n");
        sj.add(fullName);
        sj.add(retrievePoint());
        sj.add(nbOfGamesWon + "");
        sj.add(nbOfGameSetsWon + "");
        return sj.toString();
    }

    public String getFullName() {
        return fullName;
    }
}
