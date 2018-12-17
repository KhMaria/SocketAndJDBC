package networking;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {

        Socket clientSocket = null;
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            while (true) {
                clientSocket = serverSocket.accept();
                new Thread(new ClientThread(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
