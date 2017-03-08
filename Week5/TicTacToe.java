package Week5;

/**
 * Created by ongteckwu on 1/3/17.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TicTacToe {
    public static void main(String[] args) throws Exception {
        int numberOfClients = 2;
        List<Socket> sockets = new ArrayList<>();
        List<PrintWriter> printWriters = new ArrayList<>();
        List<BufferedReader> inputStreamReaders = new ArrayList<>();

        ServerSocket serverSocket = new ServerSocket(4321);
        for (int i = 0; i < numberOfClients; i++) {
            System.out.println(String.format("(... expecting connection %d th connection...)", i));
            Socket clientSocket = serverSocket.accept();
            sockets.add(clientSocket);
            System.out.println(String.format("(... connection established for client %d ...)", i));
            printWriters.add(new PrintWriter(clientSocket.getOutputStream(), true));
            inputStreamReaders.add(new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream())));
        }

        String inputLine;

        System.out.println("The game starts now!");
        int clientNumber = (Math.random() > 0.5) ? 1 : 0;
        int oppoClientNumber = (clientNumber == 1) ? 0 : 1;
        System.out.printf("Client %d starts first\n", clientNumber);
        PrintWriter out1 = printWriters.get(clientNumber);
        PrintWriter out2 = printWriters.get(oppoClientNumber);
        out1.println("your turn");
        out1.flush();
        out2.println("not your turn");
        out2.flush();
        TicTacToeBoard board = new TicTacToeBoard();

        int currentPlayer = 1;
        while (true) {
            PrintWriter out = printWriters.get(clientNumber);
            PrintWriter outOppo = printWriters.get(oppoClientNumber);
            BufferedReader in = inputStreamReaders.get(clientNumber);
            System.out.println(String.format("Player %d's turn", clientNumber));

            board.displayBoard(System.out);
            board.displayBoard(out);
            out.flush();

            int input;
            while(true) {
                input = in.read() - '0';
                if (1 > input || input > 9) {
                    out.println(0);
                    out.flush();
                    continue;
                }
                // set piece
                int loc = input - 1;
                if (!board.setPiece(loc, currentPlayer)) {
                    out.println(0);
                    out.flush();
                } else {
                    break;
                }
            }

            if (board.isWin(currentPlayer)) {
                System.out.printf("Player %d won!\n", clientNumber);
                out1.println(2);
                out2.println(2);
                out1.flush();
                out2.flush();
                break;
            }

            out.println(1);
            // wake opponent up
            outOppo.println(0);

            // change player
            clientNumber = clientNumber == 1 ? 0 : 1;
            oppoClientNumber = oppoClientNumber == 1 ? 0 : 1;
            currentPlayer = currentPlayer == 1 ? 2 : 1;
        }

        // end game
        for (int i = 0; i < numberOfClients; i++) {
            printWriters.get(i).close();
            inputStreamReaders.get(i).close();
            sockets.get(i).close();

        }
        serverSocket.close();
    }
}

class TicTacToeBoard {

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
    public void displayBoard(PrintStream out) {
        out.println("-------------");
        for (int i = 0; i < board.length; i++) {
            int[] boardRow = board[i];
            out.println("|   |   |   |");
            for (int j = 0; j < boardRow.length; j++) {
                int piece = boardRow[j];
                Integer locNumber = i * 3 + j + 1;
                // display piece
                String dPiece;
                switch(piece) {
                    case 1: dPiece = "O"; break;
                    case 2: dPiece = "X"; break;
                    default: dPiece = locNumber.toString();
                }
                out.print("| " + dPiece + " ");
            }
            out.println("|");
            out.println("|   |   |   |");
            out.println("-------------");
        }
    }

    public void displayBoard(PrintWriter out) {
        out.println("-------------");
        for (int i = 0; i < board.length; i++) {
            int[] boardRow = board[i];
            out.println("|   |   |   |");
            for (int j = 0; j < boardRow.length; j++) {
                int piece = boardRow[j];
                Integer locNumber = i * 3 + j + 1;
                // display piece
                String dPiece;
                switch(piece) {
                    case 1: dPiece = "O"; break;
                    case 2: dPiece = "X"; break;
                    default: dPiece = locNumber.toString();
                }
                out.print("| " + dPiece + " ");
            }
            out.println("|");
            out.println("|   |   |   |");
            out.println("-------------");
        }
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