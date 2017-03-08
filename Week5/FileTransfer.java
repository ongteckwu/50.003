package Week5;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileTransfer {
    private static class BuffedSocket {
        Socket socket;
        BufferedReader inputStreamReader;
        PrintWriter outStreamWriter;
        BufferedWriter fileBufferedWriter;
        FileWriter fileWriter;

        public BuffedSocket(Socket socket, BufferedReader inputStreamReader, PrintWriter outStreamWriter) {
            this.socket = socket;
            this.inputStreamReader = inputStreamReader;
            this.outStreamWriter = outStreamWriter;
        }

        public BuffedSocket(Socket socket, BufferedReader inputStreamReader, PrintWriter outStreamWriter, BufferedWriter fileBufferedWriter, FileWriter fileWriter) {
            this.socket = socket;
            this.inputStreamReader = inputStreamReader;
            this.outStreamWriter = outStreamWriter;
            this.fileBufferedWriter = fileBufferedWriter;
            this.fileWriter = fileWriter;
        }

        public Socket getSocket() {
            return socket;
        }

        public void setFileBufferedWriter(BufferedWriter fileBufferedWriter) {
            this.fileBufferedWriter = fileBufferedWriter;
        }

        public void setFileWriter(FileWriter fileWriter) {
            this.fileWriter = fileWriter;
        }

        public BufferedReader getInputStreamReader() {
            return inputStreamReader;
        }

        public PrintWriter getOutStreamWriter() {
            return outStreamWriter;
        }

        public BufferedWriter getFileBufferedWriter() {
            return fileBufferedWriter;
        }

        public FileWriter getFileWriter() {
            return fileWriter;
        }

        public void closeAll() {
            try {
                socket.close();
                if (inputStreamReader != null)
                    inputStreamReader.close();
                if (outStreamWriter != null)
                    outStreamWriter.close();
                if (fileBufferedWriter != null)
                    fileBufferedWriter.close();
                if (fileWriter != null)
                    fileWriter.close();
            } catch (Exception e) {
                closeAll();
            }

        }
    }
    public static void main(String[] args) throws Exception {
        List<BuffedSocket> bSockets = new ArrayList<>();
        ServerSocket serverSocket = new ServerSocket(4321);
        serverSocket.setSoTimeout(100);
        String inputLine;

        while (true) {
            try {
                Socket newSocket = serverSocket.accept();
                newSocket.setSoTimeout(100);
                BufferedReader isr = new BufferedReader(
                        new InputStreamReader(newSocket.getInputStream()));
                PrintWriter osw = new PrintWriter(newSocket.getOutputStream(), true);
                bSockets.add(new BuffedSocket(newSocket, isr, osw));
                System.out.println("New client connected.");

            }
            catch (java.net.SocketTimeoutException e) {

            }

            for (int i = 0; i < bSockets.size(); i++) {
                try {
                    BuffedSocket bs = bSockets.get(i);
                    BufferedReader in = bs.getInputStreamReader();
                    PrintWriter out = bs.getOutStreamWriter();
                    BufferedWriter bw = bs.getFileBufferedWriter();
                    inputLine = in.readLine();
                    if (inputLine.startsWith("FILE:")) {
                        System.out.println("File creation");
                        String[] splitStrings = inputLine.split(" ", 2);
                        String fileName = splitStrings[1];
                        FileWriter fw = new FileWriter(fileName);
                        bs.setFileWriter(fw);
                        bs.setFileBufferedWriter(new BufferedWriter(fw));
                        // file creation success
                        out.println(1);
                        out.flush();
                    }
                    else if (bs.getFileWriter() == null) {
                        out.println(0);
                        out.flush();
                        bs.closeAll();
                    }
                    else if (inputLine.equals("Close socket")) {
                        System.out.println("File read done!");
                        bs.closeAll();
                    }
                    else {
                        System.out.println("Writing line");
                        bw.write(inputLine+"\n");
                        out.println(1);
                        out.flush();
                    }
                } catch (java.net.SocketTimeoutException e) {

                } catch (NullPointerException e) {

                }
            }

            // remove all sockets that are closed
            for (int i = 0; i < bSockets.size(); i++) {
                BuffedSocket bs = bSockets.get(i);
                if (bs.getSocket().isClosed()) {
                    bs.closeAll();
                    bSockets.remove(i);
                }
            }
        }

//        serverSocket.close();

    }
}