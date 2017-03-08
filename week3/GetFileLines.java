package week3;

import java.io.*;

/**
 * Created by ongteckwu on 12/2/17.
 */

public class GetFileLines {
    public static void main(String[] args) {

//        if (args.length == 0) {
//            System.out.println("Please input filenames as arguments");
//            System.exit(-1);
//        }

        String[] fArgs = {"testFile.txt", "testFile2.txt"};


        for (String fileName: fArgs) {
            String filePath = GetFileLines.class.getResource(fileName).getFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line = null;
                int lineCount = 0;
                while ((line = reader.readLine()) != null) {
                    lineCount++;
                }
                System.out.println(String.format("File %s has %d lines", fileName, lineCount));
            } catch (FileNotFoundException e) {
                System.out.println(String.format("File %s not found", fileName));
            } catch (IOException e) {
                System.out.println("Something went from with IO. Terminating...");
                System.exit(-1);
            }

        }
    }
}
