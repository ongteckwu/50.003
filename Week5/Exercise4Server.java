package Week5;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Exercise4Server {
    public static void main(String[] args) throws Exception {
        System.out.println("(... expecting connection from ...)");
        ServerSocket serverSocket = new ServerSocket(4321);
        Socket clientSocket = serverSocket.accept();
        System.out.println("(... connection established for client ...)");
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        String inputLine;

        while (true) {
            inputLine = in.readLine();
            System.out.println("Client: " + inputLine);
            if (inputLine.equals("Bye")) {
                break;
            }
            out.println("Fuck u");
            out.flush();
        }
        out.println(inputLine);
        clientSocket.close();
        out.close();
        in.close();
        serverSocket.close();
    }
}