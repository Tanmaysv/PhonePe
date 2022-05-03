package service;

import models.Match;
import models.Player;
import models.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ScoreboardServiceTest {
    private ScoreboardService scoreboardService;

    @BeforeEach
    void setup(){
        int numberOfPlayers = 5;
        List<String> players = new ArrayList<>(Arrays.asList("P1", "P2", "P3", "P4", "P5"));

        Team team1 = new Team("Team 1");
        Match match = new Match("T20", team1, 5, 2);

        for(int i = 0; i < numberOfPlayers; i++){
            match.addPlayersToTeam(team1, players.get(i));
        }

        scoreboardService = new ScoreboardService(match);
    }

    @Test
    public void testOnFallOfWicketUpdateBatsman() {
        Player actualOutput = scoreboardService.onFallOfWicketUpdateBatsman();
        assertEquals("P1", actualOutput.getName());

        Player actualOutput2 = scoreboardService.onFallOfWicketUpdateBatsman();
        assertEquals("P2", actualOutput2.getName());
    }

    @Test
    public void testIsNextBatsmanAvailable() {
        boolean actualOutput = scoreboardService.isNextBatsmanAvailable();
        assertTrue(actualOutput);
    }

}
