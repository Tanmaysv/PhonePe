package service;

import constants.BallType;
import exceptions.InvalidInput;
import models.Batsman;
import models.Match;
import models.Player;
import models.Team;

public class ScoreboardService {
    private final Match match;

    public ScoreboardService(Match match) {
        this.match = match;
    }

    public void updateScore(String score) throws InvalidInput {
        if (score.equals(BallType.WICKET.getValue())) {
            updateTotalWickets();
        } else if (score.equals(BallType.WIDE.getValue()) || score.equals(BallType.NO_BALL.getValue())) {
            updateTeamScore(1);
        } else {
            if (score.length() == 1 && Integer.parseInt(score) >= 0 && Integer.parseInt(score) <= 6) {
                updateTeamStats(Integer.parseInt(score));
            } else {
                throw new InvalidInput("Score format is not correct");
            }
        }
    }

    public void updateTeamStats(int score) {
        match.getCurrentBattingTeam().getStrikerBatsman().updatePlayerStats(score);
        if(shouldBatsmanBeSwapped(score))
            swapBatsmanPosition();
        updateTeamScore(score);
    }

    public boolean shouldBatsmanBeSwapped(int score) {
        return score % 2 != 0;
    }

    public void swapBatsmanPosition() {
        Team currentBattingTeam = match.getCurrentBattingTeam();
        Batsman temp = currentBattingTeam.getNonStrickerBatsman();
        currentBattingTeam.setNonStrickerBatsman(currentBattingTeam.getStrikerBatsman());
        currentBattingTeam.setStrikerBatsman(temp);
    }

    public void updateTeamScore(int score) {
        Team currentBattingTeam = match.getCurrentBattingTeam();
        currentBattingTeam.setTeamScore(currentBattingTeam.getTeamScore() + score);
    }

    public void updateTotalWickets() {
        Team currentBattingTeam = match.getCurrentBattingTeam();
        currentBattingTeam.setTotalWickets(currentBattingTeam.getTotalWickets() + 1);
        updateTeamStats(0);
        onFallOfWicketUpdateBatsman();
    }

    public boolean isNextBatsmanAvailable() {
        return match.getCurrentBattingTeam().getAvailablePlayers().size() > 0;
    }

    public Player onFallOfWicketUpdateBatsman() {
        Team currentBattingTeam = match.getCurrentBattingTeam();
        Batsman currentPlayer = currentBattingTeam.getAvailablePlayers().poll();
        currentBattingTeam.setStrikerBatsman(currentPlayer);
        return currentPlayer;
    }

    public boolean isBallValid(String score) {
        return !score.equals(BallType.WIDE.getValue()) && !score.equals(BallType.NO_BALL.getValue());
    }


}
