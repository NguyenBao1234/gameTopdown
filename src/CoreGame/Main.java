package CoreGame;

import javax.swing.*;

public class Main
{
    public static void main (String[] args)
    {
        JFrame window = new JFrame();
        window.setTitle("Game adventure 2D");

        GamePanel gamePanel = GamePanel.getInstGamePanel();
        gamePanel.setupGame();
        gamePanel.startGameThread();
        window.add(gamePanel);
        window.pack();

        window.setVisible(true);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
