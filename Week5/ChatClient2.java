package Week5;
import java.io.*;
import java.net.*;

public class ChatClient2 {
    public static void main(String[] args) throws Exception {
        String hostName = "localhost";
        String hostIP = "localhost";
        //String hostName = "fe80::7517:c1af:b2bb:da73%4";
        int portNumber = 4321;

//        Socket echoSocket = new Socket(hostName, portNumber);
        Socket echoSocket = new Socket();
        SocketAddress sockaddr = new InetSocketAddress(hostIP, portNumber);
        echoSocket.connect(sockaddr, 100);
        PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
        BufferedReader in =
                new BufferedReader(
                        new InputStreamReader(echoSocket.getInputStream()));
        BufferedReader stdIn =
                new BufferedReader(
                        new InputStreamReader(System.in));
        String userInput;
        do {
            userInput = stdIn.readLine();
            out.println(userInput);
            out.flush();
            System.out.println("Server says: " + in.readLine());
        } while (!userInput.equals("Bye"));

        echoSocket.close();
        in.close();
        out.close();
        stdIn.close();
    }
}
