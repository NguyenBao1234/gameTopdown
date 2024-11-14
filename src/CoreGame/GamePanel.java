package CoreGame;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable
{
    //Screen Setting property://
    final int originalTileSize = 16;
    final int scale = 3;
    final int tileSize = originalTileSize * scale;

    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize*maxScreenCol;
    final int screenHeight = tileSize*maxScreenRow;

    Thread gameThread;

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground((Color.blue));
        this.setDoubleBuffered(true);
    }

    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run()
    {
        while (gameThread != null)
        {
            update();

            repaint(); // this is the way to call "painComponent"
        }
    }

    public void update()
    {
        //System.out.println("Game loop is running");
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g; //convert 'g' from Graphics type  into Graphics2D to create 'g2'

        g2.setColor(Color.white);
        g2.fillRect(10,10,48,48);
        g2.dispose();
    }
}
