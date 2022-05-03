package models;

import exceptions.InvalidInput;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Team {
    private final String name;
    private final List<Batsman> players;
    private final Queue<Batsman> availablePlayers;
    private Batsman strikerBatsman;
    private Batsman nonStrickerBatsman;
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

    public List<Batsman> getPlayers() {
        return players;
    }

    public Queue<Batsman> getAvailablePlayers() {
        return availablePlayers;
    }

    public int getTeamScore() {
        return teamScore;
    }

    public int getTotalWickets() {
        return totalWickets;
    }

    public Batsman getStrikerBatsman() {
        return strikerBatsman;
    }

    public Batsman getNonStrickerBatsman() {
        return nonStrickerBatsman;
    }

    public void setStrikerBatsman(Batsman strikerBatsman) {
        this.strikerBatsman = strikerBatsman;
    }

    public void setNonStrickerBatsman(Batsman nonStrickerBatsman) {
        this.nonStrickerBatsman = nonStrickerBatsman;
    }

    public void setTeamScore(int teamScore) {
        this.teamScore = teamScore;
    }

    public void setTotalWickets(int totalWickets) {
        this.totalWickets = totalWickets;
    }

    public void addPlayer(Batsman player) throws InvalidInput {
        if(players.contains(player)) {
            throw new InvalidInput("This player is already added to the team");
        } else {
            players.add(player);
            availablePlayers.add(player);
        }
    }

    public void initializeBatsman() {
        if(availablePlayers.size() >= 2) {
            this.strikerBatsman = availablePlayers.poll();
            this.nonStrickerBatsman = availablePlayers.poll();
        }
    }
}
