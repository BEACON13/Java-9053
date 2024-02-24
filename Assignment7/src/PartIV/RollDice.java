package PartIV;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RollDice extends JFrame {

    // How many times to roll the dice before stopping, keep the same as in ImagePanel.java
    private static final int ROLLS = 5;

    public RollDice() {
        setTitle("Roll the Dice");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        ImagePanel dice1 = new ImagePanel("die1.png");
        ImagePanel dice2 = new ImagePanel("die1.png");

        JPanel dicePanel = new JPanel();
        dicePanel.add(dice1);
        dicePanel.add(dice2);

        JLabel resultLabel = new JLabel("Result: ");
        resultLabel.setHorizontalAlignment(JLabel.CENTER);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(dicePanel, BorderLayout.NORTH);
        centerPanel.add(resultLabel, BorderLayout.CENTER);

        JButton rollButton = new JButton("Roll Dice");

        rollButton.addActionListener(e -> {
            int total = dice1.roll() + dice2.roll();

            // Wait the dice to stop rolling before showing the result
            Timer resultTimer = new Timer(100 * ROLLS, evt -> resultLabel.setText("Result: " + (total)));
            resultTimer.setRepeats(false);
            resultTimer.start();
        });

        dice1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int rolled = dice1.roll();

                // Wait the dice to stop rolling before showing the result
                Timer resultTimer = new Timer(100 * ROLLS, evt -> resultLabel.setText("Result: " + (rolled + dice2.getValue())));
                resultTimer.setRepeats(false);
                resultTimer.start();
            }
        });

        dice2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int rolled = dice2.roll();

                // Wait the dice to stop rolling before showing the result
                Timer resultTimer = new Timer(100 * ROLLS, evt -> resultLabel.setText("Result: " + (rolled + dice1.getValue())));
                resultTimer.setRepeats(false);
                resultTimer.start();
            }
        });

        setPreferredSize(new Dimension(400, 300));
        add(centerPanel, BorderLayout.CENTER);
        add(rollButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RollDice rollDice = new RollDice();
            rollDice.setVisible(true);
        });
    }
}
