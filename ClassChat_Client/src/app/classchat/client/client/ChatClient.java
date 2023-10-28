package app.classchat.client.client;

import app.classchat.client.logger.Logger;
import app.classchat.client.logger.LoggerUsage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {
    private static Socket socket;
    private static PrintWriter out;
    private static BufferedReader in;
    private static BufferedReader stdIn;
    private static final Logger logger = new Logger("ClassChat Client Backend", LoggerUsage.ALL_USAGES);
    public static void launchClient(String server_ip, String username) {
        try {
            socket = new Socket(server_ip, 2910);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            stdIn = new BufferedReader(new InputStreamReader(System.in));

            logger.log("Connecté au Serveur " + server_ip + ":2910");

            out.println(username);
            logger.log("Nom d'Utilisateur utilisé: " + username);

            Thread readThread = new Thread(() -> {
                try {
                    String serverMessage;
                    while ((serverMessage = in.readLine()) != null) {
                        System.out.println(serverMessage);
                        System.out.print(">> ");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            readThread.start();

            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                if (userInput.equals("/exit")) {
                    break;
                }
            }
        } catch (IOException e) {
            logger.error("Erreur lors de la communication avec le Serveur.");
        } finally {
            stopClient();
        }
    }

    public static void stopClient() {
        try {
            if (socket != null) {
                socket.close();
                logger.warn("Client arrêté.");
            }
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
            if (stdIn != null) {
                stdIn.close();
            }
        } catch (IOException e) {
            logger.error("Erreur lors de l'arrêt du Client.\u001B[0m");
            e.printStackTrace();
        }
    }
}


