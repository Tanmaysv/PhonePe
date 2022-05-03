package constants;

public enum BallType {
    NORMAL("Normal"),
    WIDE("Wd"),
    NO_BALL("Nb"),
    WICKET("W");

    private String ballType;

    BallType(String ballType) {
        this.ballType = ballType;
    }

    public String getValue() {
        return ballType;
    }
}
