package io.xp.kata.blackjack;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class GameTest {
    Game game = new Game();
    ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @Test
    void testGame() throws IOException {
        printGame(game.startGame());
        printGame(game.deal());
        printGame(game.deal());
        printGame(game.deal());
    }

    private void printGame(GameResult gameResult) throws JsonProcessingException {
        System.out.println(writer.writeValueAsString(gameResult));
    }

    @Disabled
    @Test
    public void start_game_should_fapai() {
        GameResult gameDto = game.startGame();

        assertEquals(asList("B7"), gameDto.getHost().getCards());
        assertEquals(asList("A7","C7"), gameDto.getPlayer().getCards());
    }

    @Disabled
    @Test
    public void close_deal_should_return_game_result() {
        game.startGame();

        GameResult result = game.closeDeal();

        assertEquals(true, result.getHost().isWinner());
        assertEquals(false, result.getPlayer().isWinner());
        assertEquals(asList("B7","BA"), result.getHost().getCards());
        assertEquals(asList("A7","C7"), result.getPlayer().getCards());
    }

    @Disabled
    @Test
    public void host_should_deal_until_17() {
        game.startGame();
        GameResult result = game.closeDeal();

        assertEquals(asList("B7","B2","B3","B4","B5"), result.getHost().getCards());
        assertEquals(asList("A7","C7"), result.getPlayer().getCards());
    }

    @Disabled
    @Test
    public void host_should_lose_with_bust() {
        game.startGame();
        GameResult result = game.closeDeal();

        assertEquals(asList("B7","B9","BA"), result.getHost().getCards());
        assertTrue(result.getPlayer().isWinner());
    }

}