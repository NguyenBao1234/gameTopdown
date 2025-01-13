package GameContent.WidgetInstances;

import CoreGame.Data.Enums.GameState;
import CoreGame.GamePanel;
import CoreGame.KeyHandlerComponent.KeyHandler;
import CoreGame.WidgetComponent.HUD;
import HelpDevGameTool.ImageUtility;

import java.awt.*;
import java.awt.event.KeyEvent;

public class EndGameWidget extends OptionalWidget
{
    private Font customFont;
    private final String TitleEnding;
    public EndGameWidget(String titleEnding)
    {
        TitleEnding = titleEnding;
        SetMaxOption(1,0);
        SetupInputComponent();
        // Load font tùy chỉnh
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/MedievalSharp.ttf"));
        } catch (Exception e) {
            e.printStackTrace();
            customFont = new Font("Arial", Font.BOLD, 64); // Dự phòng
        }
        options = new String[]{"Main Menu"};
    }

    public void SetupInputComponent()
    {
        KeyHandler ControllerComp = KeyHandler.getInstance();
        ControllerComp.BindAction(KeyEvent.VK_ENTER, true,this::SelectOption);
    }

    @Override
    public void Draw(Graphics2D g2)
    {
        g2.setFont(customFont.deriveFont(Font.BOLD, 64));
        int x = 64 * GamePanel.scale;
        int y = 45 * GamePanel.scale;
        g2.setColor(Color.BLACK);
        g2.drawString(TitleEnding, x + 4, y + 4); // Shadow fx
        g2.setColor(Color.WHITE); // Màu chính
        g2.drawString(TitleEnding, x, y);

        // draw option
        g2.setColor(Color.red);
        g2.setFont(customFont.deriveFont(Font.BOLD, 36)); // Font lớn hơn khi hover
        g2.drawString(options[0], 33* 3, y + GamePanel.tileSize * 2);

        // display hover fx
        int iconX = 33 * 3 - GamePanel.tileSize ;
        int iconY = y + GamePanel.tileSize * (2 + SelectingRowOption) - GamePanel.tileSize / 2;

        Image selectorIcon = ImageUtility.LoadImage("/Objects/skull.png");
        g2.drawImage(selectorIcon, iconX, iconY, GamePanel.tileSize/2, GamePanel.tileSize/2, null);
    }

    @Override
    protected void SelectOption()
    {
        super.SelectOption();
        if(!IsOnScreen()) return;

        HUD.AddWidget(GamePanel.GetInst().getMainMenuWD());
        GamePanel.GetInst().gameState = GameState.Tittle;
        GamePanel.GetInst().getMainMenuWD().PlayBGMusic();

        KeyHandler ControllerComp = KeyHandler.getInstance();
        ControllerComp.UnbindAction(KeyEvent.VK_ENTER, this::SelectOption);
        HUD.RemoveWidget(this);
    }
}
