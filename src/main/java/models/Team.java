package models;

import exceptions.InvalidInput;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Team {
    private final String name;
    private final List<Player> players;
    private final Queue<Player> availablePlayers;
    private Player strikerBatsman;
    private Player nonStrickerBatsman;
    private int teamScore;
    private int totalWickets;

    public Team(String name) {
        this.name = name;
        this.players = new ArrayList<>();
        this.availablePlayers = new LinkedList<>();
        this.strikerBatsman = null;
        this.nonStrickerBatsman = null;
        this.teamScore = 0;
    }

    public String getName() {
        return name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getTeamScore() {
        return teamScore;
    }

    public int getTotalWickets() {
        return totalWickets;
    }

    public void addPlayer(Player player) throws InvalidInput {
        if(players.contains(player)) {
            throw new InvalidInput("This player is already added to the team");
        } else {
            players.add(player);
        }
    }

    public void initializeBatsman() {
        if(availablePlayers.size() >= 2) {
            this.strikerBatsman = availablePlayers.poll();
            this.nonStrickerBatsman = availablePlayers.poll();
        }
    }

    public void updateTeamStats(int score) {
        strikerBatsman.updatePlayerStats(score);
        if(shouldBatsmanBeSwapped(score))
            swapBatsmanPosition();
        updateTeamScore(score);
    }

    public boolean shouldBatsmanBeSwapped(int score) {
        return score % 2 != 0;
    }

    public void swapBatsmanPosition() {
        Player temp = nonStrickerBatsman;
        this.nonStrickerBatsman = strikerBatsman;
        this.strikerBatsman = temp;
    }

    public void updateTeamScore(int score) {
        this.teamScore += score;
    }

    public void updateTotalWickets() {
        this.totalWickets++;
        if(isNextBatsmanAvailable()) {
            updateTeamStats(0);
            onFallOfWicket();
        } else {
            System.out.println("Team is all out");
        }

    }

    public boolean isNextBatsmanAvailable() {
        return availablePlayers.size() > 0;
    }

    public Player onFallOfWicket() {
        Player currentPlayer = availablePlayers.poll();
        this.strikerBatsman = currentPlayer;
        return currentPlayer;
    }

}
