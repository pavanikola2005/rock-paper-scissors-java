import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        final int MIN = 1;
        final int MAX = 200;
        String playAgain = "yes";

        while (playAgain.equals("yes") || playAgain.equals("y")) {
            int secret = random.nextInt(MAX) + 1;
            int tries = 0;
            int guess = -1;

            System.out.println("==============================");
            System.out.println("     NUMBER GUESSING GAME");
            System.out.println("==============================");
            System.out.println("I'm thinking of a number between " + MIN + " and " + MAX + ".");

            while (guess != secret) {
                System.out.print("Enter your guess: ");
                String input = sc.next();

                // Make sure the input is a whole number
                try {
                    guess = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a number.");
                    guess = -1;
                    continue;
                }

                // Make sure the number is in range (invalid guesses are not counted)
                if (guess < MIN || guess > MAX) {
                    System.out.println("Invalid number! Enter a number between " + MIN + " and " + MAX + ".");
                    guess = -1;
                    continue;
                }

                tries++;

                if (guess < secret) {
                    System.out.println("Too low! Try again.");
                } else if (guess > secret) {
                    System.out.println("Too high! Try again.");
                }
            }

            System.out.println("Great! You guessed it correctly.");
            System.out.println("It took you " + tries + " guesses.");

            // Ask to play again, only accept yes/no
            while (true) {
                System.out.print("Would you like to play again? (yes/no): ");
                playAgain = sc.next().toLowerCase();
                if (playAgain.equals("yes") || playAgain.equals("y")
                        || playAgain.equals("no") || playAgain.equals("n")) {
                    break;
                }
                System.out.println("Please type yes or no.");
            }
            System.out.println();
        }

        System.out.println("-----------------Game ended!-----------------------");
        sc.close();
    }
}
