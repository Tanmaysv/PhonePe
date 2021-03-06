package service;

import models.Team;

public class DisplayService {

    public void displayScoreCard(Team team, String overs) {
        displayTitle(team);
        displayColumns();
        displayTeamStats(team);
        displayTeamFinalStats(team, overs);
    }

    public void displayTitle(Team team) {
        System.out.println("Scorecard for " + team.getName());
    }

    public void displayColumns() {
        System.out.println("Player Name         Score           4s              6s          Balls");
    }

    public void displayTeamStats(Team team) {
        team.getPlayers().forEach(player -> {
            System.out.println(player.getName() + "                     "
                    + player.getScore() + "           "
                    + player.getTotalFours() + "          "
                    + player.getTotalSixes() + "              "
                    + player.getBallsFaced());
        });
    }

    public void displayTeamFinalStats(Team team, String overs) {
        System.out.println("Total: " + team.getTeamScore() + " / " + team.getTotalWickets());
        System.out.println("Overs: " + overs);
    }
}
