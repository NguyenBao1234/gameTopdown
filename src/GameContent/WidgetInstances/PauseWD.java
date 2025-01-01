package GameContent.WidgetInstances;

import CoreGame.Data.Enums.GameState;
import CoreGame.GamePanel;
import CoreGame.KeyHandlerComponent.KeyHandler;
import CoreGame.WidgetComponent.HUD;
import HelpDevGameTool.ImageLoader;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PauseWD extends OptionalWidget
{
    private Font customFont;
    public PauseWD()
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
        options = new String[]{"Resume", "Main Menu"};
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
        g2.setFont(customFont.deriveFont(Font.BOLD, 64));
        int x = 32 * GamePanel.scale;
        int y = 64 * GamePanel.scale;
        g2.setColor(Color.BLACK); // Màu đổ bóng
        g2.drawString("PAUSE GAME", x + 4, y + 4); // Dịch chuyển bóng
        g2.setColor(Color.WHITE); // Màu chính
        g2.drawString("PAUSE GAME", x, y);

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
            g2.drawString(options[i], x, y + GamePanel.tileSize * (2 + i));
        }

        // Hiệu ứng hover cho dấu ">"
        int iconX = x - GamePanel.tileSize;
        int iconY = y + GamePanel.tileSize * (2 + SelectingRowOption) - GamePanel.tileSize / 2;

        Image selectorIcon = ImageLoader.LoadImage("/Objects/skull.png");
        g2.drawImage(selectorIcon, iconX, iconY, GamePanel.tileSize/2, GamePanel.tileSize/2, null);
    }

    @Override
    protected void SelectOption()
    {
        super.SelectOption();
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
            GamePanel.GetInst().getMainMenuWD().PlayBGMusic();
        }
    }
}
