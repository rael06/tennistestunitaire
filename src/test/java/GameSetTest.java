import org.junit.jupiter.api.Test;
import org.junit.Assert;

public class GameSetTest {

    private Game game;

    private void playerWin40Points(Player player) {
        game.addPointToPlayer(player);
        game.addPointToPlayer(player);
        game.addPointToPlayer(player);
    }

    private void playerWinAGame(Player player) {
        playerWin40Points(player);
        game.addPointToPlayer(player);
    }

    @Test
    public void game_Player1WinFirstPoint_ShouldBe15() {
        // arrange
        game = new Game(new Player("Rael Calitro"), new Player("L'autre"));

        // act
        game.addPointToPlayer(game.getPlayer1());

        // assert
        Assert.assertEquals("15", game.retrievePointOfPlayer(game.getPlayer1()));
    }

    @Test
    public void game_Player1WinSecondPoint_ShouldBe30() {
        // arrange
        game = new Game(new Player("Rael Calitro"), new Player("L'autre"));

        // act
        game.addPointToPlayer(game.getPlayer1());
        game.addPointToPlayer(game.getPlayer1());

        // assert
        Assert.assertEquals("30", game.retrievePointOfPlayer(game.getPlayer1()));
    }

    @Test
    public void game_Player1WinThirdPoint_ShouldBe40() {
        // arrange
        game = new Game(new Player("Rael Calitro"), new Player("L'autre"));

        // act
        game.addPointToPlayer(game.getPlayer1());
        game.addPointToPlayer(game.getPlayer1());
        game.addPointToPlayer(game.getPlayer1());

        // assert
        Assert.assertEquals("40", game.retrievePointOfPlayer(game.getPlayer1()));
    }

    @Test()
    public void game_Player1WinGame_ShouldBe0() {
        // arrange
        game = new Game(new Player("Rael Calitro"), new Player("L'autre"));

        // act
        playerWin40Points(game.getPlayer1());
        playerWin40Points(game.getPlayer2());
        game.addPointToPlayer(game.getPlayer1());

        // assert
        Assert.assertEquals(0, game.retrieveNbOfGameWonByPlayer(game.getPlayer1()));
    }

    @Test
    public void game_Player1WinAdvantagePoint_ShouldBe40A() {
        // arrange
        game = new Game(new Player("Rael Calitro"), new Player("L'autre"));

        // act
        playerWin40Points(game.getPlayer1());
        playerWin40Points(game.getPlayer2());
        game.addPointToPlayer(game.getPlayer1());

        // assert
        Assert.assertEquals("40A", game.retrievePointOfPlayer(game.getPlayer1()));
    }

    @Test()
    public void game_Player1WinGame_ShouldBe2AndPointReset() {
        // arrange
        game = new Game(new Player("Rael Calitro"), new Player("L'autre"));

        // act
        playerWinAGame(game.getPlayer1());
        playerWinAGame(game.getPlayer1());

        // assert
        Assert.assertEquals("0", game.retrievePointOfPlayer(game.getPlayer1()));
        Assert.assertEquals(2, game.retrieveNbOfGameWonByPlayer(game.getPlayer1()));
    }

    @Test()
    public void game_Player2WinGameAndPlayer1WinGame_ShouldBe2And1AndPointReset() {
        // arrange
        game = new Game(new Player("Rael Calitro"), new Player("L'autre"));

        // act
        playerWinAGame(game.getPlayer1());
        playerWinAGame(game.getPlayer2());
        playerWin40Points(game.getPlayer1());
        playerWin40Points(game.getPlayer2());
        game.addPointToPlayer(game.getPlayer2());
        game.addPointToPlayer(game.getPlayer2());

        // assert
        Assert.assertEquals("0", game.retrievePointOfPlayer(game.getPlayer1()));
        Assert.assertEquals("0", game.retrievePointOfPlayer(game.getPlayer2()));
        Assert.assertEquals(1, game.retrieveNbOfGameWonByPlayer(game.getPlayer1()));
        Assert.assertEquals(2, game.retrieveNbOfGameWonByPlayer(game.getPlayer2()));
    }

    @Test()
    public void game_Player1WinGameSet_ShouldBe1() {
        // arrange
        game = new Game(new Player("Rael Calitro"), new Player("L'autre"));

        // act
        playerWinAGame(game.getPlayer1());
        playerWinAGame(game.getPlayer1());
        playerWinAGame(game.getPlayer1());
        playerWinAGame(game.getPlayer1());
        playerWinAGame(game.getPlayer1());
        playerWinAGame(game.getPlayer1());

        // assert
        Assert.assertEquals(1, game.getPlayer1().getNbOfGameSetsWon());
    }

    @Test()
    public void game_Player1WinGameSetWith7Against6_ShouldBe1() {
        // arrange
        game = new Game(new Player("Rael Calitro"), new Player("L'autre"));

        // act
        playerWinAGame(game.getPlayer1());
        playerWinAGame(game.getPlayer1());
        playerWinAGame(game.getPlayer1());
        playerWinAGame(game.getPlayer1());
        playerWinAGame(game.getPlayer1());

        playerWinAGame(game.getPlayer2());
        playerWinAGame(game.getPlayer2());
        playerWinAGame(game.getPlayer2());
        playerWinAGame(game.getPlayer2());
        playerWinAGame(game.getPlayer2());
        playerWinAGame(game.getPlayer2());

        playerWinAGame(game.getPlayer1());
        playerWinAGame(game.getPlayer1());

        // assert
        Assert.assertEquals(1, game.getPlayer1().getNbOfGameSetsWon());
    }
}
