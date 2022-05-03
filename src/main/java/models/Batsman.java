package models;

public class Batsman extends Player {
    private int score;
    private int totalFours;
    private int totalSixes;
    private int ballsFaced;

    public Batsman(String name) {
        super(name);
    }

    public int getScore() {
        return score;
    }

    public int getTotalFours() {
        return totalFours;
    }

    public int getTotalSixes() {
        return totalSixes;
    }

    public int getBallsFaced() {
        return ballsFaced;
    }

    public void updatePlayerStats(int score) {
        updatePlayerScore(score);
        updateBallsFaced();
        switch (score) {
            case 4:
                updateTotalFours();
                break;
            case 6:
                updateTotalSixes();
                break;
            default:
                return;
        }
    }

    public void updatePlayerScore(int score) {
        this.score += score;
    }

    public void updateTotalFours() {
        this.totalFours = this.totalFours + 1;
    }

    public void updateTotalSixes() {
        this.totalSixes = this.totalSixes + 1;
    }

    public void updateBallsFaced() {
        this.ballsFaced = this.ballsFaced + 1;
    }

}
