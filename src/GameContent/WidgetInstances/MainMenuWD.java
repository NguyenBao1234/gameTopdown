package GameContent.WidgetInstances;

import CoreGame.Data.Enums.GameState;
import CoreGame.GamePanel;
import CoreGame.KeyHandlerComponent.KeyHandler;
import CoreGame.WidgetComponent.HUD;
import HelpDevGameTool.ImageLoader;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class MainMenuWD extends OptionalWidget
{
    public MainMenuWD()
    {
        SetupInputComponent();
        SetMaxOption(3,0);
    }

    public void SetupInputComponent()
    {
        KeyHandler ControllerComp = KeyHandler.getInstance();
        ControllerComp.BindAction(KeyEvent.VK_W, true,this::Up);
        ControllerComp.BindAction(KeyEvent.VK_S, true,this::Down);
        ControllerComp.BindAction(KeyEvent.VK_A, true,this::Left);
        ControllerComp.BindAction(KeyEvent.VK_D, true,this::Right);
        ControllerComp.BindAction(KeyEvent.VK_ENTER, true,this::SelectOption);
    }

    @Override
    public void Draw(Graphics2D g2)
    {
        if(GamePanel.GetInst().gameState != GameState.Tittle) return;

        g2.drawImage(ImageLoader.LoadImage("/Player/ArtWork.png"),0,0,256*3,256*3,null);

        g2.setFont( new Font("Arial", Font.BOLD, 64));
        int x = 16* GamePanel.scale;
        int y = 32* GamePanel.scale;
        g2.setColor(Color.WHITE);
        g2.drawString("ABYSS WALKER", x, y);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32));
        x += GamePanel.tileSize;
        g2.drawString("NewGame", x, y + GamePanel.tileSize *2);
        g2.drawString("LoadGame", x, y + GamePanel.tileSize *3);
        g2.drawString("Quit", x, y + GamePanel.tileSize * 4);

        switch (SelectingRowOption)
        {
            case 0: g2.drawString(">",x- GamePanel.tileSize, y + GamePanel.tileSize*2 ); break;
            case 1:
                g2.drawString(">",x- GamePanel.tileSize, y + GamePanel.tileSize*3 );
                break;
            case 2: g2.drawString(">",x- GamePanel.tileSize, y + GamePanel.tileSize*4 ); break;
        }

    }

    protected void SelectOption()
    {
        if(GamePanel.GetInst().gameState != GameState.Tittle) return;
        if (SelectingRowOption == 0)
        {
            GamePanel.GetInst().gameState = GameState.Run;
            HUD.RemoveWidget(this);
        }
        if(SelectingRowOption == 1) {
            try {
                GamePanel.saveLoad.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        if(SelectingRowOption == 2 ){
            System.exit(0);
        }
    }
}
