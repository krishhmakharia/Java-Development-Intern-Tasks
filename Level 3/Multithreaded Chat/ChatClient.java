import java.io.*;
import java.net.*;

public class ChatClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 1234;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println("Connected to chat server!");

            // Thread for receiving messages (no lambda, Java 7 style)
            new Thread(new Runnable() {
                public void run() {
                    try {
                        String message;
                        while ((message = in.readLine()) != null) {
                            System.out.println(message);
                        }
                    } catch (IOException e) {
                        System.out.println("Disconnected from server.");
                    }
                }
            }).start();

            // Main thread for sending messages
            String input;
            while ((input = consoleIn.readLine()) != null) {
                out.println(input);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
