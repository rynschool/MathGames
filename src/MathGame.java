import java.util.Scanner;

public class MathGame {

    private Player player1;
    private Player player2;

    private Player player3;
    private Player currentPlayer;
    private Player winner;
    private boolean gameOver;
    private Scanner scanner;
    private boolean lastQuestionWrong;

    // create MathGame object
    public MathGame(Player player1, Player player2, Player player3, Scanner scanner) {
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;
        this.scanner = scanner;
        currentPlayer = null; // will get assigned at start of game
        winner = null; // will get assigned when a Player wins
        lastQuestionWrong = false;
        gameOver = false;
    }

    // ------------ PUBLIC METHODS (to be used by client classes) ------------

    // returns winning Player; will be null if neither Player has won yet
    public Player getWinner() {
        return winner;
    }

    // plays a round of the math game
    public void playRound() {
        chooseStartingPlayer();  // this helper method (shown below) sets currentPlayer to either player1 or player2
        while (!gameOver) {
            printGameState();   // this helper method (shown below) prints the state of the Game
            System.out.println("Current player: " + currentPlayer.getName());
            boolean correct = askQuestion();  // this helper method (shown below) asks a question and returns T or F
            if (correct) {
                System.out.println("Correct!");
                currentPlayer.incrementScore();  // this increments the currentPlayer's score
                swapPlayers();  // this helper method (shown below) sets currentPlayer to the other Player
                lastQuestionWrong = false;
            } else {
                System.out.println("INCORRECT!");
                if (lastQuestionWrong) {
                    gameOver = true;
                    determineWinner();
                } else {
                    swapPlayers();
                    lastQuestionWrong = true;
                }
            }
        }
    }

    // prints the current scores of the two players
    private void printGameState() {
        System.out.println("--------------------------------------");
        System.out.println("Current Scores:");
        System.out.println(player1.getName() + ": " + player1.getScore());
        System.out.println(player2.getName() + ": " + player2.getScore());
        System.out.println(player3.getName() + ": " + player3.getScore());
        System.out.println("--------------------------------------");
    }

    // resets the game back to its starting state
    public void resetGame() {
        player1.reset(); // this method resets the player
        player2.reset();
        player3.reset();
        gameOver = false;
        currentPlayer = null;
        winner = null;
    }

    // ------------ PRIVATE HELPER METHODS (internal use only) ------------

    // randomly chooses one of the Player objects to be the currentPlayer
    private void chooseStartingPlayer() {
        int randNum = (int) (Math.random() * 3) + 1;
        if (randNum == 1) {
            currentPlayer = player1;
        } else if (randNum == 2){
            currentPlayer = player2;
        } else {
            currentPlayer = player3;
        }
    }

    // asks a math question and returns true if the player answered correctly, false if not
    private boolean askQuestion() {
        int operation = (int) (Math.random() * 6) + 1;
        int num1 = (int) (Math.random() * 100) + 1;
        int num2;
        int correctAnswer;
        System.out.println("Type in your answer as an integer (/ is int division)");
        if (operation == 1) {
            num2 = (int) (Math.random() * 100) + 1;
            System.out.print(num1 + " + " + num2 + " = ");
            correctAnswer = num1 + num2;
        } else if (operation == 2) {
            num2 = (int) (Math.random() * 100) + 1;
            System.out.print(num1 + " - " + num2 + " = ");
            correctAnswer = num1 - num2;
        } else if (operation == 3) {
            num2 = (int) (Math.random() * 10) + 1;
            System.out.print(num1 + " * " + num2 + " = ");
            correctAnswer = num1 * num2;
        } else if (operation == 4){  // option == 4
            num2 = (int) (Math.random() * 10) + 1;
            System.out.print(num1 + " / " + num2 + " = ");
            correctAnswer = num1 / num2;
        } else if (operation == 5) {
            num2 = (int) (Math.random() * 3) + 1;
            System.out.print(num1 + " ^ " + num2 + " = ");
            correctAnswer = (int) (Math.pow(num1,num2));
        } else {
            System.out.print("Root of " + num1 + "? (In int form)" + " = ");
            correctAnswer = (int) (Math.sqrt(num1));
        }

        int playerAnswer = scanner.nextInt(); // get player's answer using Scanner
        scanner.nextLine(); // clear text buffer after numeric scanner input

        if (playerAnswer == correctAnswer) {
            return true;
        } else {
            return false;
        }
    }

    // swaps the currentPlayer to the other player
    private void swapPlayers() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else if (currentPlayer == player2){
            currentPlayer = player3;
        } else {
            currentPlayer = player1;
        }
    }

    // sets the winner when the game ends based on the player that missed the question
    private void determineWinner() {
        if (currentPlayer == player1) {
            winner = player2;
            player2.hasWon();
            player1.hasLost();
            player3.hasLost();
        } else if (currentPlayer == player2){
            winner = player3;
            player3.hasWon();
            player1.hasLost();
            player2.hasLost();
        } else {
            winner = player1;
            player1.hasWon();
            player2.hasLost();
            player3.hasLost();
        }
        lastQuestionWrong = false;
    }
}