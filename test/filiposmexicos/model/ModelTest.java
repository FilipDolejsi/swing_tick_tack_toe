package filiposmexicos.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ModelTest {

    @Test
    void switchActivePlayer() {
        // GIVEN
        final Model model = new Model();
        final Player origActivePlayer = model.getActivePlayer();

        // WHEN
        model.switchActivePlayer();

        // THEN
        final Player actualActivePlayer = model.getActivePlayer();
        Assertions.assertNotEquals(origActivePlayer, actualActivePlayer, "players must not be the same");
    }

    @Test
    void modelRemembersPlayerMove() {
        // GIVEN
        final Model model = new Model();
        final Player origActivePlayer = model.getActivePlayer();

        // WHEN
        model.playerMove(origActivePlayer, 1, 1);

        // THEN
        final Player actualPlayer = model.getPlayerMoved(1, 1);
        Assertions.assertEquals(origActivePlayer, actualPlayer, "Players should match");
    }

    @Test
    void detectsWinInAColumn() {
        // GIVEN
        final Model model = new Model();
        final Player origActivePlayer = model.getActivePlayer();

        // WHEN
        model.playerMove(origActivePlayer, 0, 0);
        model.playerMove(origActivePlayer, 0, 1);
        model.playerMove(origActivePlayer, 0, 2);

        // THEN
        final boolean isWinner = model.isWinner(origActivePlayer);
        Assertions.assertTrue(isWinner, "He should win");
        final boolean itIsADraw = model.isDraw();
        Assertions.assertFalse(itIsADraw, "it should not be a draw");
    }

    @Test
    void detectsWinInARow() {
        // GIVEN
        final Model model = new Model();
        final Player origActivePlayer = model.getActivePlayer();

        // WHEN
        model.playerMove(origActivePlayer, 0, 2);
        model.playerMove(origActivePlayer, 1, 2);
        model.playerMove(origActivePlayer, 2, 2);

        // THEN
        final boolean isWinner = model.isWinner(origActivePlayer);
        Assertions.assertTrue(isWinner, "He should win");
        final boolean itIsADraw = model.isDraw();
        Assertions.assertFalse(itIsADraw, "it should not be a draw");
    }

    @Test
    void detectsWinInANegativeDiagonal() {
        // GIVEN
        final Model model = new Model();
        final Player origActivePlayer = model.getActivePlayer();

        // WHEN
        model.playerMove(origActivePlayer, 0, 0);
        model.playerMove(origActivePlayer, 1, 1);
        model.playerMove(origActivePlayer, 2, 2);

        // THEN
        final boolean isWinner = model.isWinner(origActivePlayer);
        Assertions.assertTrue(isWinner, "He should win");
        final boolean itIsADraw = model.isDraw();
        Assertions.assertFalse(itIsADraw, "it should not be a draw");
    }

    @Test
    void detectsWinInAPositiveDiagonal() {
        // GIVEN
        final Model model = new Model();
        final Player origActivePlayer = model.getActivePlayer();

        // WHEN
        model.playerMove(origActivePlayer, 0, 2);
        model.playerMove(origActivePlayer, 1, 1);
        model.playerMove(origActivePlayer, 2, 0);

        // THEN
        final boolean isWinner = model.isWinner(origActivePlayer);
        Assertions.assertTrue(isWinner, "He should win");
        final boolean itIsADraw = model.isDraw();
        Assertions.assertFalse(itIsADraw, "it should not be a draw");
    }

    @Test
    void detectsDraw() {
        // GIVEN
        final Model model = new Model();
        final Player playerX = model.getPlayerX();
        final Player playerO = model.getPlayerO();

        // WHEN
        model.playerMove(playerX, 0, 0);
        model.playerMove(playerO, 0, 1);
        model.playerMove(playerX, 0, 2);
        model.playerMove(playerO, 1, 0);
        model.playerMove(playerX, 1, 1);
        model.playerMove(playerX, 1, 2);
        model.playerMove(playerO, 2, 0);
        model.playerMove(playerX, 2, 1);
        model.playerMove(playerO, 2, 2);

        // THEN
        final boolean itIsADraw = model.isDraw();
        Assertions.assertTrue(itIsADraw, "Nobody should win");
    }

}