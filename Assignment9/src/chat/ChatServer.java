package chat;

import java.awt.BorderLayout;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.*;

import encryption.Encryption;

import java.security.*;


import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class ChatServer extends JFrame {

    private static final String RSA = "RSA";
    private Key privateKey;

    private static final int PORT = 9898;

    private JTextArea textArea;


    private final List<ClientHandler> clients = Collections.synchronizedList(new ArrayList<>());

    private final AtomicInteger clientIdCounter = new AtomicInteger(1);

    public ChatServer() {

        super("Chat Server");

        try {
            privateKey = Encryption.readPrivateKey("keypairs/pkcs8_key");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("problem loading private key: " + e.getMessage());
            System.exit(1);
        }

        setUI();

        startServer();
    }

    private void setUI() {
        textArea = new JTextArea(16, 50);
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void startServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            textArea.append(String.format("Chat Server started at %s%n", new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date())));
            while (true) {
                Socket clientSocket = serverSocket.accept();
                int clientId = clientIdCounter.getAndIncrement();

                String connectedMsg = String.format("\nStarting thread for client %d at %s",
                        clientId, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
                textArea.append(connectedMsg);
                textArea.append("\nHostname: " + clientSocket.getInetAddress().getHostName());
                textArea.append("\nIP Address: " + clientSocket.getInetAddress().getHostAddress());

                ClientHandler client = new ClientHandler(clientSocket, clientId, this);
                clients.add(client);
                new Thread(client).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("error starting server: " + e.getMessage());
        }
    }

    public void removeClient(ClientHandler client) {
        textArea.append("\nClient " + client.getClientId() + " has left.");
        clients.remove(client);
    }

    private class ClientHandler implements Runnable {
        private final Socket socket;
        private final int clientId;
        private final ChatServer server;
        private DataInputStream input;
        private DataOutputStream output;
        private Key communicationKey;


        public ClientHandler(Socket socket, int clientId, ChatServer server) {
            this.socket = socket;
            this.clientId = clientId;
            this.server = server;
        }

        public void run() {
            try {
                input = new DataInputStream(socket.getInputStream());
                output = new DataOutputStream(socket.getOutputStream());

                // Perform handshake
                String message = input.readUTF();
                if ("HELLO".equals(message)) {
                    output.writeUTF("CONNECTED");

                    String encryptedSeed = input.readUTF();
                    byte[] aesSeed = Encryption.pkDecrypt(privateKey, Base64.getDecoder().decode(encryptedSeed));
                    communicationKey = Encryption.generateAESKey(aesSeed);
                }

                while (true) {
                    message = input.readUTF();
                    if ("EXIT".equals(message)) {
                        server.removeClient(this);
                        break;
                    }
                    String decryptedMessage = Encryption.decrypt(communicationKey, message);
                    broadcast(decryptedMessage, clientId);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException |
                     BadPaddingException | InvalidKeyException | InvalidAlgorithmParameterException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void broadcast(String message, int senderId) {
            synchronized (clients) {
                for (ClientHandler client : clients) {
                    if (client.getClientId() != senderId) {
                        client.sendMessage(senderId + ": " + message);
                    }
                }
            }
        }

        public void sendMessage(String message) {
            try {
                String encryptedMessage = Encryption.encrypt(communicationKey, message);
                output.writeUTF(encryptedMessage);
            } catch (IOException | NoSuchPaddingException | NoSuchAlgorithmException |
                     InvalidAlgorithmParameterException |
                     InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
                System.err.println("Error sending message to client " + clientId + ": " + e.getMessage());
            }
        }

        public int getClientId() {
            return this.clientId;
        }

    }

    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();
    }


}

