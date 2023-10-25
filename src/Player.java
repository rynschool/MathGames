public class Player {

    private String name;
    private int score;
    private int winStreak;

    public Player(String name) {
        this.name = name;
        score = 0;
        winStreak = 0;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public void incrementScore() {
        score++;
    }

    public void reset() {
        score = 0;
    }

    public int getWinStreak() {return winStreak;}

    public void hasWon() {winStreak++;}
    public void hasLost() {winStreak = 0;}
}