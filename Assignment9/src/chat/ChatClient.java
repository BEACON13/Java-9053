package chat;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.*;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.*;

import encryption.Encryption;


public class ChatClient extends JFrame {

    private static final String RSA = "RSA";
    private static final String SERVER_PUBLIC_KEY = "MIGeMA0GCSqGSIb3DQEBAQUAA4GMADCBiAKBgGk9wUQ4G9PChyL5SUkCyuHjTNOglEy5h4KEi0xpgjxi/UbIH27NXLXOr94JP1N5pa1BbaVSxlvpuCDF0jF9jlZw5IbBg1OW2R1zUACK+NrUIAYHWtagG7KB/YcyNXHOZ6Icv2lXXd7MbIao3ShrUVXo3u+5BJFCEibd8a/JD/KpAgMBAAE=";
    private PublicKey serverPublicKey;
    private Key communicationKey;

    private Socket socket;

    private DataInputStream fromServer;

    private DataOutputStream toServer;

    private static final int PORT = 9898;

    private JTextArea chatArea;
    private JTextField chatInput;


    public ChatClient() {
        super("Chat Client");
        try {
            serverPublicKey = Encryption.readPublicKey(SERVER_PUBLIC_KEY);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("error getting server public key: " + e.getMessage());
        }
        setUI();
        startClient();
    }

    private void setUI() {
        chatArea = new JTextArea(20, 30);
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        add(scrollPane, BorderLayout.CENTER);

        chatInput = new JTextField();
        chatInput.addActionListener(e -> {
            sendMessage(chatInput.getText());
            chatInput.setText("");
        });
        add(chatInput, BorderLayout.SOUTH);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(event -> exit());
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void startClient() {
        try {
            socket = new Socket("localhost", PORT);
            fromServer = new DataInputStream(socket.getInputStream());
            toServer = new DataOutputStream(socket.getOutputStream());

            handshake();

            new Thread(() -> {
                try {
                    while (true) {
                        String receivedMessage = fromServer.readUTF();
                        String decryptedMessage = Encryption.decrypt(communicationKey, receivedMessage);
                        chatArea.append(decryptedMessage + "\n");
                    }
                } catch (Exception e) {
                    chatArea.append("Disconnected from the server.\n");
                    e.printStackTrace();
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error connecting to the server.",
                    "Connection Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }


    private void handshake() {
        try {
            toServer.writeUTF("HELLO");

            String response = fromServer.readUTF();
            if (!"CONNECTED".equals(response)) {
                throw new IOException("Failed to connect to server");
            }

            byte[] aesSeed = Encryption.generateSeed();
            byte[] encryptedSeed = Encryption.pkEncrypt(serverPublicKey, aesSeed);
            toServer.writeUTF(Base64.getEncoder().encodeToString(encryptedSeed));

            communicationKey = Encryption.generateAESKey(aesSeed);
        } catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                 IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendMessage(String message) {
        try {
            String encryptedMessage = Encryption.encrypt(communicationKey, message);
            toServer.writeUTF(encryptedMessage);
            chatArea.append("Me: " + message + "\n");
        } catch (IOException | NoSuchPaddingException | NoSuchAlgorithmException | InvalidAlgorithmParameterException |
                 InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }

    private void exit() {
        try {
            sendMessage("EXIT");
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }


    public static void main(String[] args) {
        ChatClient chatClient = new ChatClient();
    }
}
