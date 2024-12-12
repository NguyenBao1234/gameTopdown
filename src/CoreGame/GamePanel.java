package CoreGame;

import CoreGame.CollisionComponent.EventHandler;
import CoreGame.Enums.GameState;
import CoreGame.KeyHandlerComponent.KeyHandler;
import CoreGame.SoundComponent.SoundManager;
import Entity.Player;
import CoreGame.MapComponent.TileManager;
import CoreGame.EntityComponent.BaseObject;
import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel implements Runnable
{
    private static GamePanel instance;
    public static final int FPS = 60;
    //Screen Setting property://
    static final int originalTileSize = 16;
    public static final int scale = 3;
    public static final int tileSize = originalTileSize * scale;
    public static final int maxMap = 10;
    public int currentMapIndex = 0;

    public static final int maxScreenCol = 16;
    public static final int maxScreenRow = 12;
    public static final int screenWidth = tileSize*maxScreenCol;
    public static final int screenHeight = tileSize*maxScreenRow;
    public static int truePlayerScreenX = screenWidth/2 - tileSize/2;
    public static int truePlayerScreenY = screenHeight/2 - tileSize/2;

    public static TileManager tileManager = new TileManager();
    public static CoreGame.CollisionComponent.EventHandler EventHandler;

    EventHandler eventHandler; // if this not working then we all dead

    Thread gameThread;
    // ENTITY AND OBJECT
    public Player player;
    public BaseObject obj[][] = new BaseObject[maxMap][5];//[amount of Maps][object each map]
    public GameState gameState;

    public GamePanel()
    {
        setPreferredSize(new Dimension(screenWidth,screenHeight));
        setBackground((Color.blue));
        setDoubleBuffered(true);
        addKeyListener(KeyHandler.getInstance());
        setFocusable(true);
        requestFocus();
        player = new Player();
        eventHandler = new EventHandler();
    }

    public static GamePanel getInstGamePanel()
    {
        if (instance == null) instance = new GamePanel();
        return instance;
    }

    public void setupGame()
    {
        SoundManager.playSound(0.25f,false,"/Sound/SFX/fanfare.wav");
        WorldManager.SetUpObject();
        gameState = GameState.Run;
    }

    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**this function call every frame*/
    public void update(float DeltaTime)
    {
        if (gameState == GameState.Run)
        {
            player.update(DeltaTime);
            eventHandler.checkEvent();
            WorldManager.SimulateObject();
        }
        if (gameState == GameState.Pause) return;
    }
    /**this draw function call every frame*/
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g; //convert 'g' from Graphics type  into Graphics2D to create 'g2'
        tileManager.DrawTiles(g2); //  add
        for (int i = 0; i < obj[1].length;i++)
        {
            if (obj[currentMapIndex][i] != null){
                obj[currentMapIndex][i].draw(g2); // add [currentMap] here too
            }
        }
        player.renderSprite(g2);

        g2.dispose();
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
                //System.out.println("Fps: "+ drawCallCount);
                timer = 0;
                drawCallCount = 0;
            }
        }
    }
}
