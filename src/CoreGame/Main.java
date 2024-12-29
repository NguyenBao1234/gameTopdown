package CoreGame;

import HelpDevGameTool.ImageLoader;

import javax.swing.*;

public class Main
{
    public static void main (String[] args)
    {
        JFrame window = new JFrame();
        window.setTitle("Abyss Walker");

        GamePanel gamePanel = GamePanel.GetInst();
        gamePanel.setupGame();
        gamePanel.startGameThread();
        
        window.add(gamePanel);
        window.pack();
        window.setIconImage(ImageLoader.LoadImage("/game_icon.png"));
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
