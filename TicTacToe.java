import java.util.Scanner;

public class TicTacToe {
    private char[][] board;
    private char currentPlayer;
    private String player1;
    private String player2;
    private Scanner scanner;

    public TicTacToe(String player1, String player2) {
        board = new char[3][3];
        this.player1 = player1;
        this.player2 = player2;
        currentPlayer = 'X'; // Player X starts
        scanner = new Scanner(System.in);
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    private void printBoard() {
        System.out.println("Current board:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

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

    private boolean checkForWin() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) ||
                    (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)) {
                return true;
            }
        }
        // Check diagonals
        return (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
                (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer);
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public void playGame() {
        boolean playAgain;
        do {
            initializeBoard();
            boolean gameWon = false;

            while (!gameWon && !isBoardFull()) {
                printBoard();
                System.out.println("Player " + (currentPlayer == 'X' ? player1 : player2) + " (" + currentPlayer + "), enter your move (row and column): ");
                int row = scanner.nextInt();
                int col = scanner.nextInt();

                // Validate the move
                if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-') {
                    board[row][col] = currentPlayer;
                    gameWon = checkForWin();

                    if (gameWon) {
                        printBoard();
                        System.out.println("Player " + (currentPlayer == 'X' ? player1 : player2) + " wins!");
                    } else if (isBoardFull()) {
                        printBoard();
                        System.out.println("It's a draw!");
                    } else {
                        switchPlayer();
                    }
                } else {
                    System.out.println("This move is not valid. Try again.");
                }
            }

            System.out.println("Do you want to play again? (yes/no)");
            playAgain = scanner.next().equalsIgnoreCase("yes");

        } while (playAgain);

        scanner.close();
        System.out.println("Thank you for playing!");
    }

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Enter the name of Player 1 (X): ");
        String player1 = inputScanner.nextLine();
        System.out.println("Enter the name of Player 2 (O): ");
        String player2 = inputScanner.nextLine();

        TicTacToe game = new TicTacToe(player1, player2);
        game.playGame();
        inputScanner.close();
    }
}