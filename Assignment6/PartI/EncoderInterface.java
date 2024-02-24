import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.*;

public class EncoderInterface extends JFrame {

    JFileChooser jc;

    private JTextArea inputArea;

    private JTextArea outputArea;

    private JComboBox<String> encodeOptions;

    private JButton clearButton;

    public EncoderInterface() {
        setTitle("Encoder");
        setSize(500, 450);
        setLayout(new BorderLayout(0, 10));

        inputArea = new JTextArea(10, 40);
        JScrollPane inputScrollPane = new JScrollPane(inputArea);
        inputScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(inputScrollPane, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        String[] options = {"Numeric", "ROT13"};
        encodeOptions = new JComboBox<>(options);
        panel.add(encodeOptions);

        clearButton = new JButton("Clear");
        panel.add(clearButton);
        panel.setPreferredSize(new Dimension(-1, clearButton.getPreferredSize().height + 10));
        add(panel, BorderLayout.CENTER);

        outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);
        JScrollPane outputScrollPane = new JScrollPane(outputArea);
        outputScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(outputScrollPane, BorderLayout.SOUTH);

        setupListeners();

        setupMenu();
    }

    private void loadFile() {
        // alternately, you can have it return
        // a File object or file path String or whatever you
        // like.
        if (jc == null) jc = new JFileChooser(".");

        int returnValue = jc.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jc.getSelectedFile();
            try {
                String contents = new String(Files.readAllBytes(Paths.get(selectedFile.getAbsolutePath())));
                inputArea.setText(contents);
                encodeInput();
            } catch (IOException e) {
                System.err.println("Unable to read the file: " + e.getMessage());
            }
        }
    }

    private void setupListeners() {
        inputArea.addCaretListener(e -> {
            encodeInput();
        });

        clearButton.addActionListener(e -> {
            inputArea.setText("");
            outputArea.setText("");
        });

        encodeOptions.addActionListener(e -> encodeInput());
    }

    private void setupMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        fileMenu.add(openItem);
        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        openItem.addActionListener(e -> loadFile());
        exitItem.addActionListener(e -> System.exit(0));
    }

    private void encodeInput() {
        String input = inputArea.getText();
        String encoded;
        if (encodeOptions.getSelectedItem().equals("Numeric")) {
            encoded = Encoder.encodeNumeric(input);
        } else {
            encoded = Encoder.encodeROT13(input);
        }
        outputArea.setText(encoded);
    }


    public static void main(String[] args) {
        JFrame frame = new EncoderInterface();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
