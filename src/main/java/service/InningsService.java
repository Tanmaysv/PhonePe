package service;

import exceptions.InvalidInput;
import models.Match;
import models.Team;

import java.util.Scanner;

public class InningsService {
    private final Match match;
    private final DisplayService displayService;
    private final ScoreboardService scoreboardService;

    public InningsService(Match match) {
        this.match = match;
        this.displayService = new DisplayService();
        this.scoreboardService = new ScoreboardService(match);
    }

    public void startInnings(Team currentBattingTeam) {
        initializeBattingTeam(currentBattingTeam);
        startBatting(match.getTotalOvers());
    }

    public void startSecondInnings(Team currentBattingTeam, int target) {
        initializeBattingTeam(currentBattingTeam);
        match.setCurrentBattingTeam(currentBattingTeam);
        startSecondInningBatting(match.getTotalOvers(), target);
    }

    private void initializeBattingTeam(Team currentBattingTeam) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Batting order for " + currentBattingTeam.getName());
        for(int i = 0; i < match.getTotalPlayersInTeam(); i++) {
            match.addPlayersToTeam(currentBattingTeam, sc.next());
        }
        currentBattingTeam.initializeBatsman();
    }

    private void startBatting(int totalOvers) {
        Scanner sc = new Scanner(System.in);
        for (int i = 1; i <= totalOvers; i++) {
            System.out.println("Over number: " + i);
            int validBall = 0;
            while (validBall != 6) {
                String score = sc.nextLine();
                if(scoreboardService.isBallValid(score)) validBall++;
                try {
                    scoreboardService.updateScore(score);
                } catch (InvalidInput e) {
                    System.out.println(e.getMessage());
                }
                if (isTeamAllOut()) {
                    displayService.displayScoreCard(match.getCurrentBattingTeam());
                    return;
                }
            }

            scoreboardService.swapBatsmanPosition();
            displayService.displayScoreCard(match.getCurrentBattingTeam());
        }
    }

    private void startSecondInningBatting(int totalOvers, int target) {
        Scanner sc = new Scanner(System.in);
        for (int i = 1; i <= totalOvers; i++) {
            System.out.println("Over number: " + i);
            int validBall = 0;
            while (validBall != 6) {
                String score = sc.nextLine();
                if(scoreboardService.isBallValid(score)) validBall++;
                try {
                    scoreboardService.updateScore(score);
                } catch (InvalidInput e) {
                    System.out.println(e.getMessage());
                }
                if (match.getCurrentBattingTeam().getTeamScore() > target) {
                    displayService.displayScoreCard(match.getCurrentBattingTeam());
                    int wicketsRemaining = match.getTotalPlayersInTeam() - match.getCurrentBattingTeam().getTotalWickets();
                    System.out.println("Result: Team 2 won the match by " + wicketsRemaining + " wickets");
                    return;
                }
                if(isTeamAllOut()) {
                    int runsDifference = target - match.getCurrentBattingTeam().getTeamScore();
                    if(runsDifference == 0) {
                        System.out.println("Result: Match tied");
                    } else {
                        System.out.println("Result: Team 1 won the match by " + runsDifference + " runs");
                    }
                    return;
                }
            }
            scoreboardService.swapBatsmanPosition();
            displayService.displayScoreCard(match.getCurrentBattingTeam());
        }
        int runsDifference = target - match.getCurrentBattingTeam().getTeamScore();
        if(runsDifference == 0) {
            System.out.println("Result: Match tied");
        } else {
            System.out.println("Result: Team 1 won the match by " + runsDifference + " runs");
        }

    }

    public boolean isTeamAllOut() {
        return match.getCurrentBattingTeam().getTotalWickets() == match.getTotalPlayersInTeam() - 1;
    }
}
