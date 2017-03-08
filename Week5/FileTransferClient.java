package Week5;
import java.io.*;
import java.net.*;

public class FileTransferClient {
    public static void main(String[] args) throws Exception {
        String hostName = "localhost";
        String hostIP = "localhost";
        //String hostName = "fe80::7517:c1af:b2bb:da73%4";
        int portNumber = 4321;
        String readFileName = "/Users/ongteckwu/50.003/src/Week5/test.txt";
        String writeFileName = "work_" + (int) (Math.random() * 1000) + ".txt";
        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader(readFileName);
            br = new BufferedReader(fr);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File read problem. Probably file does not exist...");
            System.exit(0);
        }

//        Socket echoSocket = new Socket(hostName, portNumber);
        Socket echoSocket = new Socket();
        echoSocket.setSoTimeout(5000);
        SocketAddress sockaddr = new InetSocketAddress(hostIP, portNumber);
        echoSocket.connect(sockaddr, 100);
        PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
        BufferedReader in =
                new BufferedReader(
                        new InputStreamReader(echoSocket.getInputStream()));

        // create file
        String input;
        while (true) {
            try {
                out.println("FILE: " + writeFileName);
                out.flush();
                input = in.readLine();
                System.out.println(input);
                if (input.equals("1")) {
                    System.out.println("File creation done");
                    break;
                }
            } catch (IOException e) {

            }
        }

        boolean messageSent = true;
        String message;
        while (true) {
            message = null;
            if (messageSent) {
                if ((message = br.readLine()) == null) {
                    // EOF
                    out.println("Close socket");
                    out.flush();
                    break;
                }
                messageSent = false;
            }

            try {
                out.println(message);
                out.flush();
                if (in.readLine().equals("1")) {
                    System.out.println(message + " read");
                    messageSent = true;
                }
                else {
                    System.out.println("Something wrong happened.");
                    break;
                }
            } catch (IOException e) {
                System.out.println("Retrying same message...");
            }

        }

        echoSocket.close();
        in.close();
        out.close();
    }
}
