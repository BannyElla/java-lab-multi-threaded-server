package server;

import common.ClientThread;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author ella.zabrodina
 */
public class Server {

    public static final int PORT = 1234;

    public Server() {
        System.out.println("Server is started");
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket socket = serverSocket.accept();
                ClientThread ct = new ClientThread(socket);
                Thread thr = new Thread(ct);
                thr.start();
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}
