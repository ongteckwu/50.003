package Week5;
import java.io.*;
import java.net.*;
import java.util.Arrays;

public class TicTacToePlayer {
    public static void main(String[] args) throws Exception {
        String hostName = "localhost";
        String hostIP = "localhost";
        //String hostName = "fe80::7517:c1af:b2bb:da73%4";
        int portNumber = 4321;

//        Socket echoSocket = new Socket(hostName, portNumber);
        Socket echoSocket = new Socket();
        echoSocket.setSoTimeout(2000);
        SocketAddress sockaddr = new InetSocketAddress(hostIP, portNumber);
        echoSocket.connect(sockaddr, 100);
        PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
        BufferedReader in =
                new BufferedReader(
                        new InputStreamReader(echoSocket.getInputStream()));
        BufferedReader stdIn =
                new BufferedReader(
                        new InputStreamReader(System.in));

        // get turn
        int turn;
        while (true) {
            try {
                if (in.readLine().equals("your turn")) {
                    turn = 1;
                } else {
                    turn = 0;
                }
                break;
            } catch (SocketTimeoutException e) {

            }
        }
        String userInput;
        String input;
        String answer;
        do {
            if (turn == 1) {
                try {
                    while((input = in.readLine()) != null) {
                        System.out.println(input); // fking hack
                    }
                } catch (SocketTimeoutException e) {

                }
                do {
                    userInput = stdIn.readLine();
                    out.println(userInput);
                    out.flush();
                    answer = in.readLine();
                } while (!answer.equals("0"));

                if (answer.equals("2")) {
                    System.out.println("You have won the game");
                }

                turn = (turn + 1) % 2;
            } else {
                // wait for signal to wake up
                System.out.println("Waiting for opponent's move...");
                try {
                    answer = in.readLine();
                    if (answer.equals("2")) {
                        System.out.println("Opponent has won the game");
                    } else {
                        turn = (turn + 1) % 2;
                    }
                } catch (SocketTimeoutException e){

                }
            }
        } while (true);

//        echoSocket.close();
//        in.close();
//        out.close();
//        stdIn.close();
    }
}
