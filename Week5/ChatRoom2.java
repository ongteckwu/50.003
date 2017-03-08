package Week5;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ChatRoom2 {
    public static void main(String[] args) throws Exception {
        List<Socket> sockets = new ArrayList<>();
        List<BufferedReader> inputStreamReaders = new ArrayList<>();

        ServerSocket serverSocket = new ServerSocket(4321);
        serverSocket.setSoTimeout(10000);
        String inputLine;

        while (true) {
            try {
                Socket newSocket = serverSocket.accept();
                newSocket.setSoTimeout(100);
                sockets.add(newSocket);
                System.out.println("New client connected.");
                inputStreamReaders.add(new BufferedReader(
                        new InputStreamReader(newSocket.getInputStream())));
            } catch (java.net.SocketTimeoutException e) {
                System.out.println("Start server now");
                break;
            }

        }

//        serverSocket.close();
        while (sockets.size() > 0) {
            for (int i = 0; i < sockets.size(); i++) {
                try {
                    BufferedReader in = inputStreamReaders.get(i);
                    inputLine = in.readLine();
                    if (inputLine.equals("")) {
                        System.out.println("A client has left the chatroom");
                        sockets.get(i).close();
                        in.close();
                        continue;
                    }
                    System.out.println(String.format("Client %d says:", i) + inputLine);
                } catch (java.net.SocketTimeoutException e) {

                } catch (NullPointerException e) {

                }
            }

            // remove all sockets that are closed
            for (int i = 0; i < sockets.size(); i++) {
                if (sockets.get(i).isClosed()) {
                    sockets.remove(i);
                    inputStreamReaders.remove(i);
                }
            }
        }
    }
}