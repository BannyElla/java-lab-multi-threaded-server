package common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author ella.zabrodina
 */
public class ClientThread implements Runnable {

    private Socket socket;

    public ClientThread(Socket socket) {
        this.socket = socket;

    }

    @Override
    public void run() {
        try {
            InputStream in = socket.getInputStream();

            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(reader);
            String text = br.readLine();
            System.out.println(Thread.currentThread().getId() + " " + text);
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }

}
