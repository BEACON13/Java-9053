package PartIV;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class ImagePanel extends JPanel {
    private Image img;
    private int value;
    private static final Random random = new Random();

    // How many times to roll the dice before stopping, keep the same as in RollDice.java
    private static final int ROLLS = 5;

    public ImagePanel(String img) {
        this(new ImageIcon(img).getImage());
    }

    public ImagePanel(Image img) {
        this.img = img;
        this.value = 1;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setLayout(null);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, null);
    }

    public int roll() {
        value = random.nextInt(6) + 1;

        Timer timer = new Timer(100, e -> {
            int randomValue = random.nextInt(6) + 1;
            img = new ImageIcon("die" + randomValue + ".png").getImage();
            repaint();
        });
        timer.setInitialDelay(0);
        timer.setRepeats(true);
        timer.start();

        Timer stopTimer = new Timer(100 * ROLLS, e -> {
            timer.stop();
            img = new ImageIcon("die" + value + ".png").getImage();
            repaint();
        });
        stopTimer.setRepeats(false);
        stopTimer.start();

        return value;
    }

    public int getValue() {
        return this.value;
    }
}
