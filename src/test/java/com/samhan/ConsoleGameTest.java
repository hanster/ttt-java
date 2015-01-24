package com.samhan;

import com.samhan.player.Player;
import com.samhan.player.PlayerType;
import com.samhan.ui.Ui;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConsoleGameTest {

    private ConsoleGame consoleGame;
    public int getMoveComputerCall = 0;
    public int getMoveHumanCall = 0;

    @Before
    public void setUp() {
        Ui ui = new StubUi();
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
