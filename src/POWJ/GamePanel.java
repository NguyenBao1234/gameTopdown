//Copyright POWGameStd
package POWJ;

import POWJ.Data.Enums.GameState;
import POWJ.KeyHandlerComponent.KeyHandler;
import POWJ.WidgetComponent.HUD;
import POWJ.EffectComponent.PostProcessing;
import GameContent.DataSave.SaveLoad;
import GameContent.MainPlayer;
import POWJ.MapComponent.TileManager;
import POWJ.EntityComponent.BaseObject;
import GameContent.WidgetInstances.MainMenuWD;

import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel implements Runnable
{
    private static GamePanel instance;
    private final Color DefaultBackgroundColor = new Color(0x0F5A00);
    public static final int FPS = 60;
    //Screen Setting property://
    public static final int originalTileSize = 16;
    public static final int scale = 3;
    public static final int tileSize = originalTileSize * scale;

    public static final int maxScreenCol = 16;
    public static final int maxScreenRow = 12;
    public static final int screenWidth = tileSize*maxScreenCol;
    public static final int screenHeight = tileSize*maxScreenRow;
    public static int truePlayerScreenX = screenWidth/2 - tileSize/2;
    public static int truePlayerScreenY = screenHeight/2 - tileSize/2;

    public static final int maxMap = 10;
    public int currentMapIndex = 0;

    Thread gameThread;


    // ENTITY AND OBJECT
    private MainPlayer player;
    public BaseObject obj[][] = new BaseObject[maxMap][25];//[amount of Maps][object each map]
    PostProcessing postProcessing = new PostProcessing();
    public SaveLoad saveLoad = new SaveLoad();

    public GameState gameState;
    private final MainMenuWD mainMenuWD = new MainMenuWD();

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(DefaultBackgroundColor);
        instance = this;

        addKeyListener(KeyHandler.getInstance());
        this.setFocusable(true);
        this.requestFocus();
        new TileManager();
        player = new MainPlayer();

    }

    public static GamePanel GetInst()
    {
        if (instance == null) instance = new GamePanel();
        return instance;
    }

    public void setupGame()
    {
        gameState = GameState.Tittle;
        WorldManager.SetUpObject();
        postProcessing.setup();
        HUD.AddWidget(mainMenuWD);
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
            player.Tick(DeltaTime);
            WorldManager.SimulateObject();
        }
        if (gameState == GameState.Pause) return;
    }
    /**this draw function call every frame*/
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        HUD.Draw(g2);
        if(gameState == GameState.Tittle) HUD.Draw(g2);
        else
        {
            TileManager.DrawTiles(g2);
            for (int i = 0; i < obj[1].length;i++)
            {
                if (obj[currentMapIndex][i] != null){
                    obj[currentMapIndex][i].Render(g2); // add [currentMap] here too
                }
            }
            player.Render(g2);
            postProcessing.Render(g2);
            HUD.Draw(g2); // need call after map for displaying head up
        }
        g2.dispose();
    }

    @Override
    public void run()
    {
        double drawIntervalUnit = 1000000000 / FPS;// khoang thoi gian: Interval = 1s / FPS
        double passedDelta = 0; //supper precise
        long lastTime = System.nanoTime();
        long currentTime;
        int drawCallCount = 0;
        while (gameThread != null)
        {
            currentTime = System.nanoTime();
            passedDelta += (currentTime - lastTime)/drawIntervalUnit;
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
        }
    }

    public MainMenuWD getMainMenuWD() {
        return mainMenuWD;
    }
    public MainPlayer getPlayer(){return player;}
    public void SetBackgroundColor(Color color){this.setBackground(color);}
    public void SetBackgroundColorDefault(){this.setBackground(DefaultBackgroundColor);}

}
