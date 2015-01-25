package com.samhan.game;

import com.samhan.player.Player;
import com.samhan.player.PlayerType;
import com.samhan.ui.StubUi;
import com.samhan.ui.Ui;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConsoleGameTest {

    private ConsoleGame consoleGame;
    private Ui ui;
    StubUi stubUi;
    public int getMoveComputerCall = 0;
    public int getMoveHumanCall = 0;

    @Before
    public void setUp() {
        ui = new StubUi();
        stubUi = (StubUi) ui;
        Player player1 = new StubHumanPlayer();
        Player player2 = new StubComputerPlayer();
        consoleGame = new ConsoleGame(player1, player2, ui);
    }

    @Test
    public void newConsoleGameStartsWithHumanAsCurrentPlayer(){
        assertEquals(PlayerType.HUMAN, consoleGame.getCurrentPlayer().getType());
    }

    @Test
    public void afterPlayerMakesMoveSwitchCurrentPlayer(){
        consoleGame.attemptNextMove();
        assertEquals(PlayerType.COMPUTER, consoleGame.getCurrentPlayer().getType());
        consoleGame.attemptNextMove();
        assertEquals(PlayerType.HUMAN, consoleGame.getCurrentPlayer().getType());
    }

    @Test
    public void getMoveIsCalledWhenAttemptingMove(){
        consoleGame.attemptNextMove();
        consoleGame.attemptNextMove();

        assertEquals(1 , getMoveComputerCall);
        assertEquals(1 , getMoveHumanCall);
    }

    @Test
    public void drawCallsUiMethods(){
        consoleGame.draw();

        assertEquals(1, stubUi.clearDisplayCallCount);
        assertEquals(1,stubUi.drawBoardCallCount);
        assertEquals(3, stubUi.displayMessageCallCount);
    }

    private void setUpEndGame(String boardPositions) {
        consoleGame = new ConsoleGame(new StubHumanPlayer(), new StubComputerPlayer(), ui, new Board(boardPositions));
        consoleGame.setEndGameMessage();
        consoleGame.draw();
    }

    @Test
    public void setUpNewGameResetsGameState() {
        setUpEndGame("xxxoooxxx");
        assertTrue(consoleGame.hasGameEnded());
        consoleGame.setUpNewGame();
        assertFalse(consoleGame.hasGameEnded());
    }

    @Test
    public void endGameMessageSetWhenXWins() {
        setUpEndGame("xxxxxxxxx");

        assertTrue(consoleGame.hasGameEnded());

        int lastIndexInList = stubUi.displayMessageParamHistory.size() - 1;
        assertEquals("Game Over\n" +
                "\n" +
                "x wins\n" +
                "\n", stubUi.displayMessageParamHistory.get(lastIndexInList));
    }

    @Test
    public void endGameMessageSetWhenOWins(){
        setUpEndGame("ooooooooo");

        int lastIndexInList = stubUi.displayMessageParamHistory.size() - 1;
        assertEquals("Game Over\n" +
                "\n" +
                "o wins\n" +
                "\n", stubUi.displayMessageParamHistory.get(lastIndexInList));
    }

    @Test
    public void endGameMessageSetWhenDrawGame(){
        setUpEndGame("oxooxoxox");

        int lastIndexInList = stubUi.displayMessageParamHistory.size() - 1;
        assertEquals("Game Over\n" +
                "\n" +
                "draw\n" +
                "\n", stubUi.displayMessageParamHistory.get(lastIndexInList));
    }

    @Test
    public void handleExceptionThrownFromMoveInput(){

    }

    public class StubHumanPlayer implements Player {

        @Override
        public int getMove(Board board) {
            getMoveHumanCall++;
            return 0;
        }

        @Override
        public Marker getMarker() {
            return Marker.O;
        }

        @Override
        public PlayerType getType() {
            return PlayerType.HUMAN;
        }
    }

    public class StubComputerPlayer implements Player {

        @Override
        public int getMove(Board board) {
            getMoveComputerCall++;
            return 1;
        }

        @Override
        public Marker getMarker() {
            return Marker.X;
        }

        @Override
        public PlayerType getType() {
            return PlayerType.COMPUTER;
        }
    }
}
