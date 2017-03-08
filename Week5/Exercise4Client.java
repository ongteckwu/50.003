package Week5;
import java.io.*;
import java.net.*;

public class Exercise4Client {
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

        for (int i = 0; i < 1000; i++) {
            out.println("Fuck ya!");
            out.flush();
            System.out.println(in.readLine());
        }
        echoSocket.close();
        in.close();
        out.close();
    }
}
