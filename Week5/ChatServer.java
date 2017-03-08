package Week5;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
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

        BufferedReader stdIn =
                new BufferedReader(
                    new InputStreamReader(System.in));
        String inputLine;

        System.out.println("The chat room starts now!");
        int clientNumber = 0;
        int maxClients = numberOfClients;
        while (maxClients > 0) {
            PrintWriter out = printWriters.get(clientNumber);
            BufferedReader in = inputStreamReaders.get(clientNumber);
            inputLine = in.readLine();
        	System.out.println(String.format("Client %d says:", clientNumber) + inputLine);
            if (inputLine.equals("Bye")) {
                out.println(inputLine);
                sockets.get(clientNumber).close();
                out.close();
                in.close();
                maxClients--;
            }
            out.println(stdIn.readLine());
            out.flush();
            clientNumber = (clientNumber + 1) % maxClients;
        }
        serverSocket.close();
        stdIn.close();


    }
}