package networking;
import java.io.*;
import java.net.Socket;

public class ClientThread implements Runnable {
    private Socket socket;
    private BufferedReader in;
    PrintWriter out;


    public ClientThread(Socket socket) {
        try {
            this.socket = socket;
            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.out = new PrintWriter(this.socket.getOutputStream(), true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            while (true) {
                String line;
                while (true) {
                    line = in.readLine();
                    out.println("Thanks for " + line);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

