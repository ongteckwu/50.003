package Week5;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FactorPrimeServer {
    public static void main(String[] args) throws Exception {
        int numberOfClients = 3;
        List<Socket> sockets = new ArrayList<>();
        List<PrintWriter> printWriters = new ArrayList<>();
        List<BufferedReader> inputStreamReaders = new ArrayList<>();

        ServerSocket serverSocket = new ServerSocket(4321);
        for (int i = 0; i < numberOfClients; i++) {
            System.out.println(String.format("(... expecting connection %d th connection...)", i));
            Socket clientSocket = serverSocket.accept();
            sockets.add(clientSocket);
            clientSocket.setSoTimeout(1000);
            System.out.println(String.format("(... connection established for client %d ...)", i));
            printWriters.add(new PrintWriter(clientSocket.getOutputStream(), true));
            inputStreamReaders.add(new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream())));
        }

        String inputLine;

        System.out.println("The chat room starts now!");

        for (int i = 0; i < numberOfClients; i++) {
            PrintWriter out = printWriters.get(i);
            out.println("4294967297");
        }

        int clientNumber = 0;
        int maxClients = numberOfClients;
        while (maxClients > 0) {
            try {
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
            } catch (SocketTimeoutException e) {
                System.out.println(String.format("Client %d timeout", clientNumber));
            }
            clientNumber = (clientNumber + 1) % maxClients;
        }
        serverSocket.close();


    }
}