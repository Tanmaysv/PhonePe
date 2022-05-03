import models.Match;
import models.Team;
import service.InningsService;
import service.MatchService;

import java.util.Scanner;

public class CricketDashboard {
    public static void main(String[] args) {
        Team team1 = new Team("Team 1");
        Team team2 = new Team("Team 2");

        System.out.println("Enter number of players for each team:");
        Scanner sc = new Scanner(System.in);
        int totalPlayersInTeam = sc.nextInt();
        System.out.println("Enter number of overs");
        int totalOvers = sc.nextInt();

        Match match1 = new Match("T20", team1, totalPlayersInTeam, totalOvers);

        MatchService matchService = new MatchService();
        matchService.startMatch(match1.getName());

        InningsService inningsService = new InningsService(match1);
        inningsService.startInnings(team1);
        inningsService.startSecondInnings(team2, match1.getCurrentBattingTeam().getTeamScore());

        matchService.endMatch(match1.getName());

    }
}
