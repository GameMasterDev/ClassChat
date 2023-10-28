package app.classchat.server.server;

import app.classchat.server.utils.logger.Logger;
import app.classchat.server.utils.logger.LoggerUsage;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {

    private static final Logger logger = new Logger("ClassChat Server Backend", LoggerUsage.ALL_USAGES);
    private static Map<PrintWriter, String> clientMap = new HashMap<>();
    private static ServerSocket serverSocket;
    private static boolean serverRunning = true;
    private static boolean isAuthorized = true;

    public static void launchServer() {
        try {
            serverSocket = new ServerSocket(2910, 0, InetAddress.getByName("0.0.0.0"));
            logger.log("Server Started. Waiting for any connection on port 2910");

            while (serverRunning) {
                Socket clientSocket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String userName = in.readLine();

                new ChatServerThread(clientSocket, in, userName).start();
            }
        } catch (IOException e) {
            logger.error("The server stopped.");
            logger.error("Restart later");
            System.exit(-1);
        }
    }

    public static void stopServer() {
        try {
            serverRunning = false;
            for (PrintWriter writer : clientMap.keySet()) {
                writer.println("The server has stopped.");
            }
            if (serverSocket != null) {
                serverSocket.close();
                logger.warn("Server Stopped");
            }
        } catch (IOException e) {
            logger.error("There was an error while stopping the server");
            e.printStackTrace();
        }
    }

    private static class ChatServerThread extends Thread {
        private Socket socket;
        private BufferedReader reader;
        private String username;

        public ChatServerThread(Socket socket, BufferedReader reader, String username) throws IOException {
            this.socket = socket;
            this.reader = reader;
            this.username = username;
            clientMap.put(new PrintWriter(socket.getOutputStream(), true), username);
            logger.log("-------------------------------------------------");
            logger.log("New Connection with a Client !");
            logger.log("Address: " + socket.getInetAddress().getHostAddress());
            logger.log("Username: " + username);
            logger.log("Authorized: " + isAuthorized);
            logger.log("-------------------------------------------------");
        }

        public void run() {
            try {
                PrintWriter localWriter = new PrintWriter(socket.getOutputStream(), true);
                localWriter.println("Connecté au Chat");

                String message;
                while ((message = reader.readLine()) != null) {
                    if(message.equals("/clients")) {
                        StringBuilder clientList = new StringBuilder("Clients connectés : ");
                        for (String client : clientMap.values()) {
                            clientList.append(client).append(", ");
                        }
                        localWriter.println(clientList.toString());
                    }
                    if(message.equals("/disconnect")) {
                        localWriter.println("Vous vous ëtes déconnecté du Chat");
                        clientMap.remove(localWriter);
                        System.exit(0);
                        break;
                    }
                    broadcast("[Anonymous] >>> " + message);
                    localWriter.println(">> ");
                }
            } catch (IOException e) {
                logger.log("There was an exception");
            } finally {
                try {
                    socket.close();
                    logger.log("Client " + username + " was disconnected");
                } catch (IOException e) {
                    logger.log("Client " + username + " was disconnected.");
                }
            }
        }

    }

    private static void broadcast(String message) {
        for (PrintWriter writer : clientMap.keySet()) {
            writer.println(message);
        }
    }
}

