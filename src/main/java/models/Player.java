package models;

public class Player {
    private String name;
    private int score;
    private int totalFours;
    private int totalSixes;
    private int ballsFaced;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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
            case 6:
                updateTotalSixes();
            default:
                return;
        }
    }

    public void updatePlayerScore(int score) {
        this.score += score;
    }

    public void updateTotalFours() {
        this.totalFours++;
    }

    public void updateTotalSixes() {
        this.totalSixes++;
    }

    public void updateBallsFaced() {
        this.ballsFaced++;
    }

}
