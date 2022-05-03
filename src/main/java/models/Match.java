package models;

import exceptions.InvalidInput;

public class Match {
    private final String name;
    private Team currentBattingTeam;
    private final int totalPlayersInTeam;
    private final int totalOvers;

    public Match(String name, Team currentBattingTeam, int totalPlayersInTeam, int totalOvers) {
        this.currentBattingTeam = currentBattingTeam;
        this.name = name;
        this.totalPlayersInTeam = totalPlayersInTeam;
        this.totalOvers = totalOvers;
    }

    public Team getCurrentBattingTeam() {
        return currentBattingTeam;
    }

    public String getName() {
        return name;
    }

    public int getTotalPlayersInTeam() {
        return totalPlayersInTeam;
    }

    public int getTotalOvers() {
        return totalOvers;
    }

    public void setCurrentBattingTeam(Team currentBattingTeam) {
        this.currentBattingTeam = currentBattingTeam;
    }

    public void addPlayersToTeam(Team team, String player) {
        try {
            team.addPlayer(new Batsman(player));
        } catch (InvalidInput e) {
            System.out.println(e.getMessage());
        }
    }

}
