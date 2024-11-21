package CoreGame;

import Entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel implements Runnable
{
    private static final int FPS = 60;
    //Screen Setting property://
    final int originalTileSize = 16;
    public final int scale = 3;
    public final int tileSize = originalTileSize * scale;

    public final int maxScreenCol = 16; // sau them public
    public final int maxScreenRow = 12; //them public
    public final int screenWidth = tileSize*maxScreenCol;
    public final int screenHeight = tileSize*maxScreenRow;

    //them code
    TileManager tileM = new TileManager(this);

    Thread gameThread;

    public Player player = new Player(this);

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground((Color.blue));
        this.setDoubleBuffered(true);
        this.addKeyListener(KeyHandler.getInstKeyHdl());
        this.setFocusable(true);
        this.requestFocus();
    }

    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run()
    {
        double drawIntervalUnit = 1000000000 / FPS;// khoang thoi gian: Interval = 1s / FPS
        double passedDelta = 0; //supper precise
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCallCount = 0;
        while (gameThread != null)
        {
            currentTime = System.nanoTime();
            passedDelta += (currentTime - lastTime)/drawIntervalUnit;
            timer += currentTime - lastTime;
            // 1 frame passed://
            if(passedDelta >= 1)
            {
                update(1f/FPS);
                repaint();// this is the way to call "painComponent"
                passedDelta --;
                drawCallCount ++;
                //System.out.println("hellooooooooooooooooo");
            }
            lastTime = currentTime;
            if(timer > 1000000000)
            {
                System.out.println("Fps: "+ drawCallCount);
                timer = 0;
                drawCallCount = 0;
            }
        }
    }

    public void update(float DeltaTime)
    {
        player.update(DeltaTime);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g; //convert 'g' from Graphics type  into Graphics2D to create 'g2'
        tileM.draw(g2); //  add
        player.renderSprite(g2);

        g2.dispose();
    }
}
