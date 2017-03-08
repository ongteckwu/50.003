package Week5;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FactorPrimeServerMul {
    public static void main(String[] args) throws Exception {
        List<Socket> sockets = new ArrayList<>();
        List<PrintWriter> printWriters = new ArrayList<>();
        List<BufferedReader> inputStreamReaders = new ArrayList<>();

        ServerSocket serverSocket = new ServerSocket(4321);
        serverSocket.setSoTimeout(10000);

        int numberOfClients = 0;
        while (true) {
            try {
                System.out.println(String.format("(... expecting connection %d th connection...)", numberOfClients));
                Socket clientSocket = serverSocket.accept();
                sockets.add(clientSocket);
                clientSocket.setSoTimeout(1000);
                System.out.println(String.format("(... connection established for client %d ...)", numberOfClients));
                printWriters.add(new PrintWriter(clientSocket.getOutputStream(), true));
                inputStreamReaders.add(new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream())));
            }
            catch (java.net.SocketTimeoutException e) {
                System.out.println("Timed out.");
                break;
            }
            numberOfClients++;
        }

        if (numberOfClients == 0) {
            System.out.println("No clients. Exiting...");
            serverSocket.close();
            System.exit(0);
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