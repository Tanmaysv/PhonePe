package models;

import java.util.List;

public class Over {
    private int number;
    private List<Ball> balls;

    public void addBall(Ball ball) {
        this.balls.add(ball);
    }
}
