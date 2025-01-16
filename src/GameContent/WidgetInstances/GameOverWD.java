//Copyright POWGameStd
package GameContent.WidgetInstances;

import POWJ.Data.Enums.GameState;
import POWJ.GamePanel;
import POWJ.GameSaverComponent.SaveUtility;
import POWJ.KeyHandlerComponent.KeyHandler;
import POWJ.WidgetComponent.HUD;
import HelpDevGameTool.ImageUtility;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GameOverWD extends OptionalWidget
{
    private Font customFont;
    public GameOverWD()
    {
        SetMaxOption(2,0);
        SetupInputComponent();
        // Load font tùy chỉnh
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/MedievalSharp.ttf"));
        } catch (Exception e) {
            e.printStackTrace();
            customFont = new Font("Arial", Font.BOLD, 64); // Dự phòng
        }
        options = new String[]{"Replay From Save Point", "Main Menu"};
    }

    public void SetupInputComponent()
    {
        KeyHandler ControllerComp = KeyHandler.getInstance();
        ControllerComp.BindAction(KeyEvent.VK_W, true,this::Up);
        ControllerComp.BindAction(KeyEvent.VK_S, true,this::Down);
        ControllerComp.BindAction(KeyEvent.VK_ENTER, true,this::SelectOption);
    }

    @Override
    public void Draw(Graphics2D g2)
    {
        g2.setFont(customFont.deriveFont(Font.BOLD, 64));
        int x = 64 * GamePanel.scale;
        int y = 45 * GamePanel.scale;
        g2.setColor(Color.BLACK); // Màu đổ bóng
        g2.drawString("GAME OVER", x + 4, y + 4); // Dịch chuyển bóng
        g2.setColor(Color.WHITE); // Màu chính
        g2.drawString("GAME OVER", x, y);

        // Hiển thị các tùy chọn
        g2.setFont(customFont.deriveFont(Font.BOLD, 32)); // Font nhỏ hơn cho các tùy chọn
        x += GamePanel.tileSize;

        for (int i = 0; i < options.length; i++) {
            if (i == SelectingRowOption) {
                g2.setColor(Color.red);
                g2.setFont(customFont.deriveFont(Font.BOLD, 36)); // Font lớn hơn khi hover
            } else {
                g2.setColor(Color.WHITE);
                g2.setFont(customFont.deriveFont(Font.BOLD, 32)); // Font mặc định
            }
            g2.drawString(options[i], 33* 3, y + GamePanel.tileSize * (2 + i));
        }

        // Hiệu ứng hover cho dấu ">"
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
        if (SelectingRowOption == 0)
        {
            if(!SaveUtility.DoesGameSaveExist("GameSave")) return;
            GamePanel.GetInst().gameState = GameState.Run;
        }
        if(SelectingRowOption == 1 )
        {
            HUD.AddWidget(GamePanel.GetInst().getMainMenuWD());
            GamePanel.GetInst().gameState = GameState.Tittle;
            GamePanel.GetInst().getMainMenuWD().PlayBGMusic();
        }
        KeyHandler ControllerComp = KeyHandler.getInstance();
        ControllerComp.UnbindAction(KeyEvent.VK_W, this::Up);
        ControllerComp.UnbindAction(KeyEvent.VK_S, this::Down);
        ControllerComp.UnbindAction(KeyEvent.VK_ENTER, this::SelectOption);
        HUD.RemoveWidget(this);
    }
}