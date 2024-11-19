package CoreGame;

import javax.swing.*;
import java.util.ArrayList;

public class Main {
    public static void main (String[] args)
    {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Game adventure 2D");
        window.setLocationRelativeTo(null);

        GamePanel gamePanel =new GamePanel();

        gamePanel.setupGame();
        gamePanel.startGameThread();
        window.add(gamePanel);
        window.pack();

        window.setVisible(true);
    }
}
