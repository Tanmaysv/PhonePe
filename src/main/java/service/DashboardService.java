package service;

import models.Team;

public class DashboardService {

    public void displayScoreCard(Team team) {
        displayTitle(team);
        displayColumns();
        displayTeamStats(team);
        displayTeamFinalStats(team);
    }

    public void displayTitle(Team team) {
        System.out.println("Scorecard for " + team.getName());
    }

    public void displayColumns() {
        System.out.println("Player Name         Score           4s              6s          Balls");
    }

    public void displayTeamStats(Team team) {
        team.getPlayers().forEach(player -> {
            System.out.println(player.getName() + "         "
                    + player.getScore() + "           "
                    + player.getTotalFours() + "          "
                    + player.getTotalSixes() + "              "
                    + player.getBallsFaced());
        });
    }

    public void displayTeamFinalStats(Team team) {
        System.out.println("Total: " + team.getTeamScore() + " / " + team.getTotalWickets());
    }

    public void displayMatchResult(Team team1, Team team2) {
        int scoreDifference = Math.abs(team1.getTeamScore() - team2.getTeamScore());
        if(team1.getTeamScore() > team2.getTeamScore()) {
            System.out.println("Result: Team1 won the match by " + scoreDifference + " runs");
        } else if(team1.getTeamScore() < team2.getTeamScore()) {
            System.out.println("Result: Team2 won the match by " + scoreDifference + " runs");
        } else {
            System.out.println("Result: Match was a tie");
        }
    }
}
