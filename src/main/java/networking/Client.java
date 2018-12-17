package networking;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", 8080)) {

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(
                    new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            try {
                String outLine;
                String inLine;
                System.out.println("Print a message for server");
                while (!(outLine = consoleReader.readLine()).equals("stop")) {
                    out.println(outLine);
                    inLine = in.readLine();
                    System.out.println(inLine);
                    System.out.println("Print a message for server");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

