package week3;

import java.util.Scanner;

/**
 * Created by ongteckwu on 12/2/17.
 */
public class TicTacToe {

    // used to check win
    private static int[][] lines = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6}
    };

    // 0 represents nothing,
    // 1 represents O,
    // 2 represents X                                }
    private int[][] board = new int[3][3];

    // display board
    public void displayBoard() {
        System.out.println("-------------");
        for (int i = 0; i < board.length; i++) {
            int[] boardRow = board[i];
            System.out.println("|   |   |   |");
            for (int j = 0; j < boardRow.length; j++) {
                int piece = boardRow[j];
                int locNumber = i * 3 + j + 1;
                displayPiece(piece, locNumber);
            }
            System.out.println("|");
            System.out.println("|   |   |   |");
            System.out.println("-------------");
        }
    }

    private void displayPiece(int piece, Integer locNumber) {
        String dPiece;
        switch(piece) {
            case 1: dPiece = "O"; break;
            case 2: dPiece = "X"; break;
            default: dPiece = locNumber.toString();
        }
        System.out.print("| " + dPiece + " ");
    }
    // check win
    public boolean isWin(int piece) {
        boolean hasWon = false;

        for (int[] line : lines) {
            hasWon = checkLine(piece, line);
            if (hasWon) return hasWon;
        }

        return hasWon;
    }

    private boolean checkLine(int piece, int[] line) {
        for (int i : line) {
            int row = i / 3;
            int col = i % 3;
            if (board[row][col] != piece) {
                return false;
            }
        }
        return true;
    }

    // set piece
    public boolean setPiece(int loc, int piece) {
        int row = loc / 3;
        int col = loc % 3;
        int spot = board[row][col];
        if (spot != 0) {
            return false; // spot already filled
        }
        board[row][col] = piece;
        return true;

    }
}

class PlayGame {
    public static void main(String[] args) {
        TicTacToe ttt = new TicTacToe();
        int currentPlayer = 1;
        Scanner scanner = new Scanner(System.in);
        Integer input = null;

        // game loop
        while (true) {
            ttt.displayBoard();
            System.out.println(String.format("Player %d's turn", currentPlayer));

            while (true) {
                System.out.print("Piece number: ");
                input = scanner.nextInt();
                // check input
                if (1 > input || input > 9) {
                    System.out.println("Input range wrong. Try again.");
                    continue;
                }

                // set piece
                int loc = input - 1;
                if (!ttt.setPiece(loc, currentPlayer)) {
                    System.out.println("Location already filled. Try again.");
                } else {
                    break;
                }
            }



            if (ttt.isWin(currentPlayer)) {
                break;
            }
            // change player
            currentPlayer = currentPlayer == 1 ? 2 : 1;
        }

        ttt.displayBoard();
        System.out.println(String.format("Player %d wins!", currentPlayer));

    }
}
