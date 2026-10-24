import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        String[] choices = {"Rock", "Paper", "Scissor"};
        int userScore = 0;
        int computerScore = 0;
        int draws = 0;
        String playAgain = "yes";

        while (playAgain.equals("yes") || playAgain.equals("y")) {
            System.out.println("==============================");
            System.out.println("   ROCK PAPER SCISSORS GAME");
            System.out.println("==============================");
            System.out.println("1 - Rock");
            System.out.println("2 - Paper");
            System.out.println("3 - Scissor");
            System.out.print("Please enter a number 1, 2, or 3: ");

            String input = sc.next();

            // Validate input (prevents crash if user types letters)
            if (!input.equals("1") && !input.equals("2") && !input.equals("3")) {
                System.out.println("Invalid choice! Please enter 1, 2, or 3.\n");
                continue;
            }

            int userIndex = Integer.parseInt(input) - 1;
            int computerIndex = rand.nextInt(3);

            String userChoice = choices[userIndex];
            String computerChoice = choices[computerIndex];

            System.out.println("Your choice is " + userChoice + " and the computer's choice is " + computerChoice + ".");

            if (userChoice.equals(computerChoice)) {
                System.out.println("It's a draw.");
                draws++;
            } else if ((userChoice.equals("Rock") && computerChoice.equals("Scissor"))
                    || (userChoice.equals("Paper") && computerChoice.equals("Rock"))
                    || (userChoice.equals("Scissor") && computerChoice.equals("Paper"))) {
                System.out.println("You won this round!");
                userScore++;
            } else {
                System.out.println("You lost this round.");
                computerScore++;
            }

            // Ask to play again, only accept yes/no
            while (true) {
                System.out.print("Play again? (yes/no): ");
                playAgain = sc.next().toLowerCase();
                if (playAgain.equals("yes") || playAgain.equals("y")
                        || playAgain.equals("no") || playAgain.equals("n")) {
                    break;
                }
                System.out.println("Please type yes or no.");
            }
            System.out.println();
        }

        System.out.println("---------------------------------------------------------");
        System.out.println("Final Scores");
        System.out.println("User Score     : " + userScore);
        System.out.println("Computer Score : " + computerScore);
        System.out.println("Draws          : " + draws);

        if (userScore > computerScore) {
            System.out.println("Congratulations! You are the Overall Winner.");
        } else if (computerScore > userScore) {
            System.out.println("Computer is the Overall Winner.");
        } else {
            System.out.println("The match is a draw.");
        }
        System.out.println("=== Game End ===");

        sc.close();
    }
}
