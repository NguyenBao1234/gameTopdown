package GameContent.WidgetInstances;

import CoreGame.Data.Enums.GameState;
import CoreGame.GamePanel;
import CoreGame.KeyHandlerComponent.KeyHandler;
import CoreGame.WidgetComponent.HUD;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PauseWD extends OptionalWidget
{

    public PauseWD()
    {
        SetMaxOption(2,0);
        SetupInputComponent();
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
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32));
        g2.setColor(Color.WHITE);

        if(GamePanel.GetInst().gameState != GameState.Pause) return;

        int x = 32* GamePanel.scale;
        int y = 64* GamePanel.scale;
        g2.drawString ("PAUSE", x , y);

        g2.drawString("Continue", x, y + GamePanel.tileSize *2);
        g2.drawString("Main Menu", x, y + GamePanel.tileSize *3);

        switch (SelectingRowOption)
        {
            case 0: g2.drawString(">",x- GamePanel.tileSize, y + GamePanel.tileSize*2 ); break;
            case 1:
                g2.drawString(">",x- GamePanel.tileSize, y + GamePanel.tileSize*3 );
                break;
            case 2: g2.drawString(">",x- GamePanel.tileSize, y + GamePanel.tileSize*4 ); break;
        }
    }

    @Override
    public void EnableInput() {

    }

    @Override
    protected void SelectOption()
    {
        if(GamePanel.GetInst().gameState != GameState.Pause) return;
        if (SelectingRowOption == 0)
        {
            GamePanel.GetInst().gameState = GameState.Run;
            HUD.RemoveWidget(this);
        }
        if(SelectingRowOption == 1 )
        {
            HUD.RemoveWidget(this);
            HUD.AddWidget(GamePanel.GetInst().getMainMenuWD());
            GamePanel.GetInst().gameState = GameState.Tittle;
        }
    }
}
