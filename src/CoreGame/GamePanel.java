package CoreGame;

import CoreGame.Enums.GameState;
import CoreGame.KeyHandlerComponent.KeyHandler;
import CoreGame.SoundComponent.SoundManager;
import Entity.Player;
import Tile.TileManager;
import Entity.Object.Master.BaseObject;

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

    public static final int maxScreenCol = 16; // sau them public
    public static final int maxScreenRow = 12; //them public
    public static final int screenWidth = tileSize*maxScreenCol;
    public static final int screenHeight = tileSize*maxScreenRow;
    public static int truePlayerScreenX = screenWidth/2 - tileSize/2;
    public static int truePlayerScreenY = screenHeight/2 - tileSize/2;

    TileManager tileManager = new TileManager();

    Thread gameThread;

    // ENTITY AND OBJECT
    public Player player;
    public BaseObject obj[] = new BaseObject[4];
    public GameState gameState;
    public final int playState = 1;
    public final int pauseState = 2;



    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground((Color.blue));
        this.setDoubleBuffered(true);
        //this.addKeyListener(KeyHandler.getInstKeyHdl());
        addKeyListener(KeyHandler.getInstance());
        this.setFocusable(true);
        this.requestFocus();
        player = new Player();
    }

    public static GamePanel getInstGamePanel()
    {
        if (instance == null) instance = new GamePanel();
        return instance;
    }

    public void setupGame()
    {
        SoundManager.playSound(0.25f,false,"/Sound/SFX/fanfare.wav");
        AssetSetter.SetUpObject();
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
        if (gameState == GameState.Run) player.update(DeltaTime);
        if (gameState == GameState.Pause) return;
    }
    /**this draw function call every frame*/
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g; //convert 'g' from Graphics type  into Graphics2D to create 'g2'
        tileManager.draw(g2); //  add
        for (int i = 0; i < obj.length;i++){
            if (obj[i] != null){
                obj[i].draw(g2);
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
