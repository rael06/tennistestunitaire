import java.util.*;

public class Game extends Observable{
    private Player player1;
    private Player player2;
    private List<Player> players = new ArrayList<>();

    public static void main(String[] args) {
        try {
            new Game(new Player("Rael Calitro"), new Player("L'autre")).start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void start() throws InterruptedException {
        while (players.stream().noneMatch(p -> p.getNbOfGameSetsWon() >= 2 && p.getNbOfGameSetsWon() - retrieveOpponent(p).getNbOfGameSetsWon() >= 2 || p.getNbOfGameSetsWon() == 3)) {
            int rand = new Random().nextInt(10);
            Player randomPlayer = players.get(rand % 2);
            addPointToPlayer(randomPlayer);
            Thread.sleep(1000);
            setChanged();
            notifyObservers();
            System.out.println(toString());
        }
    }

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        players.add(player1);
        players.add(player2);
        addObserver(new TennisFrame());
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("\n");
        sj.add(player1.toString());
        sj.add(player2.toString());
        return sj.toString();
    }

    public void addPointToPlayer(Player player) {
        Player opponent = retrieveOpponent(player);
        if (player.isOnAdvantage()) {
            player.increaseNbOfGamesWon();
            opponent.resetPoints();
        } else if (opponent.isScore40() && player.isScore40()) {
            player.toggleAdvantage();
            if (opponent.isOnAdvantage()) {
                opponent.toggleAdvantage();
            }
        } else {
            player.increasePoint();
        }

        if (player.getNbOfGamesWon() == 6 && opponent.getNbOfGamesWon() <= 4 || player.getNbOfGamesWon() == 7) {
            player.increaseNbOfGameSetsWon();
        }

        if (player.isGameSetWon()) {
            resetGameSet();
        }
    }

    private void resetGameSet() {
        players.forEach(Player::resetGameSet);
    }

    private Player retrieveOpponent(Player player) {
        return players.get(Math.abs(players.indexOf(player) - 1));
    }

    public String retrievePointOfPlayer(Player player) {
        return player.retrievePoint();
    }

    public int retrieveNbOfGameWonByPlayer(Player player) {
        return player.getNbOfGamesWon();
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getPlayer1() {
        return player1;
    }
}
