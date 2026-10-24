import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    private final char[][] board = new char[3][3];
    private final char player = 'X';
    private final char computer = 'O';
    private final Scanner sc = new Scanner(System.in);
    private final Random rand = new Random();

    public TicTacToe() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    public void startGame() {
        System.out.println("==============================");
        System.out.println("         TIC TAC TOE");
        System.out.println("==============================");
        System.out.println("You are X, the computer is O.");
        printBoard();

        while (true) {
            // Player move
            System.out.println("Your turn (X)");
            int row, col;
            while (true) {
                row = readNumber("Enter the row (1-3): ") - 1;
                col = readNumber("Enter the column (1-3): ") - 1;
                if (isValid(row, col)) {
                    break;
                }
                System.out.println("Invalid move! That cell is taken or out of range. Try again.");
            }
            board[row][col] = player;
            printBoard();

            if (checkWinner(player)) {
                System.out.println("Congratulations! You won the game.");
                break;
            }
            if (isBoardFull()) {
                System.out.println("Match draw!");
                break;
            }

            // Computer move
            System.out.println("Computer's turn (O)");
            computerMove();
            printBoard();

            if (checkWinner(computer)) {
                System.out.println("Computer won the game.");
                break;
            }
            if (isBoardFull()) {
                System.out.println("Match draw!");
                break;
            }
        }
        sc.close();
    }

    // Reads a whole number safely (no crash on letters)
    private int readNumber(String message) {
        while (true) {
            System.out.print(message);
            String input = sc.next();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    public void printBoard() {
        System.out.println();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Checks if a move is inside the board and the cell is empty
    private boolean isValid(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-';
    }

    // Checks if the given symbol has won
    private boolean checkWinner(char symbol) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) {
                return true;
            }
            if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol) {
                return true;
            }
        }
        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) {
            return true;
        }
        return board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol;
    }

    // Checks whether the board is full
    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    // Computer strategy: win if possible, block the player, otherwise random
    private void computerMove() {
        if (tryMove(computer)) {
            return;
        }
        if (tryMove(player)) {
            return;
        }
        int row, col;
        do {
            row = rand.nextInt(3);
            col = rand.nextInt(3);
        } while (!isValid(row, col));
        board[row][col] = computer;
    }

    // Looks for a cell where 'symbol' would win; if found, the computer plays there
    private boolean tryMove(char symbol) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    board[i][j] = symbol;
                    boolean wins = checkWinner(symbol);
                    board[i][j] = '-';
                    if (wins) {
                        board[i][j] = computer;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.startGame();
    }
}
