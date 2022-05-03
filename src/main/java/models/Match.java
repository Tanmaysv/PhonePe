package models;

import exceptions.InvalidInput;

import java.util.List;

public class Match {
    private int name;
    private List<Team> teams;
    private List<Ball> balls;
    private Team currentBattingTeam;
    private Team currentBowlingTeam;
    private int totalPlayersInTeam;

    public void addPlayersToTeam(Team team, List<String> players) throws InvalidInput {
        if(players.size() > totalPlayersInTeam) {
            throw new InvalidInput("Cannot pass more players than allowed");
        }
        players.forEach(player -> {
            try {
                team.addPlayer(new Player(player));
            } catch (InvalidInput e) {
                System.out.println(e.getMessage());
            }
        });
    }

    public void startMatch(Team battingTeam) {

    }

}
