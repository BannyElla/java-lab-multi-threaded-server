package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author ella.zabrodina
 */
public class Client {

    public static final int PORT = 1234;
    public static final int SUCCESS = 1;
    public static final int FAIL = 0;
    InetAddress localhost;

    public int connect() {
        try {
            localhost = InetAddress.getLocalHost();
            return SUCCESS;
        } catch (UnknownHostException e) {
            e.printStackTrace(System.err);
            return FAIL;
        }
    }

    public void run() {
        try {
            InputStreamReader reader = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(reader);
            while (true) {
                Socket socket = new Socket(localhost, PORT);
                OutputStream out = socket.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
                String text = br.readLine();
                writer.write(text);

                writer.close();
                socket.close();
                if (text.equals("***")) {
                    break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }

    public static void main(String[] args) {
        Client client1 = new Client();
        if (client1.connect() == Client.SUCCESS) {
            System.out.println("Client1 is started");
            client1.run();
        } else {
            System.out.println("Client1 isn't connected!");
        }
    }
}
