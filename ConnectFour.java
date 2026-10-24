import java.util.Scanner;

public class ConnectFour {

    static final int ROWS = 6;
    static final int COLS = 7;
    static char[][] board = new char[ROWS][COLS];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String playAgain = "yes";

        System.out.println("==============================");
        System.out.println("         CONNECT FOUR");
        System.out.println("==============================");
        System.out.println("Get 4 discs in a row to win!");

        while (playAgain.equals("yes") || playAgain.equals("y")) {
            playGame(sc);

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
        }

        System.out.println("Thanks for playing!");
        sc.close();
    }

    static void playGame(Scanner sc) {
        initializeBoard();
        char currentPlayer = 'X';

        while (true) {
            printBoard();

            int col = readColumn(sc, currentPlayer);

            if (!dropDisc(col, currentPlayer)) {
                System.out.println("Column is full! Choose another column.");
                continue;
            }

            if (checkWin(currentPlayer)) {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            }

            if (isBoardFull()) {
                printBoard();
                System.out.println("Game draw!");
                break;
            }

            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

    // Reads a valid column number (1-7) without crashing on bad input
    static int readColumn(Scanner sc, char player) {
        while (true) {
            System.out.print("Player " + player + ", enter column (1-" + COLS + "): ");
            String input = sc.next();
            try {
                int col = Integer.parseInt(input) - 1;
                if (col >= 0 && col < COLS) {
                    return col;
                }
                System.out.println("Invalid column! Choose between 1 and " + COLS + ".");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    static void initializeBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = '.';
            }
        }
    }

    static void printBoard() {
        System.out.println();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("1 2 3 4 5 6 7");
        System.out.println();
    }

    static boolean dropDisc(int col, char player) {
        for (int row = ROWS - 1; row >= 0; row--) {
            if (board[row][col] == '.') {
                board[row][col] = player;
                return true;
            }
        }
        return false;
    }

    static boolean isBoardFull() {
        for (int j = 0; j < COLS; j++) {
            if (board[0][j] == '.') {
                return false;
            }
        }
        return true;
    }

    static boolean checkWin(char player) {
        // Horizontal
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j <= COLS - 4; j++) {
                if (board[i][j] == player
                        && board[i][j + 1] == player
                        && board[i][j + 2] == player
                        && board[i][j + 3] == player) {
                    return true;
                }
            }
        }

        // Vertical
        for (int i = 0; i <= ROWS - 4; i++) {
            for (int j = 0; j < COLS; j++) {
                if (board[i][j] == player
                        && board[i + 1][j] == player
                        && board[i + 2][j] == player
                        && board[i + 3][j] == player) {
                    return true;
                }
            }
        }

        // Diagonal (top-left to bottom-right)
        for (int i = 0; i <= ROWS - 4; i++) {
            for (int j = 0; j <= COLS - 4; j++) {
                if (board[i][j] == player
                        && board[i + 1][j + 1] == player
                        && board[i + 2][j + 2] == player
                        && board[i + 3][j + 3] == player) {
                    return true;
                }
            }
        }

        // Diagonal (bottom-left to top-right)
        for (int i = 3; i < ROWS; i++) {
            for (int j = 0; j <= COLS - 4; j++) {
                if (board[i][j] == player
                        && board[i - 1][j + 1] == player
                        && board[i - 2][j + 2] == player
                        && board[i - 3][j + 3] == player) {
                    return true;
                }
            }
        }

        return false;
    }
}
