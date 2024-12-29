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
        if (GamePanel.GetInst().gameState != GameState.Tittle) return;

        // Load font tùy chỉnh
        Font customFont = null;
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/MedievalSharp.ttf"));
        } catch (Exception e) {
            e.printStackTrace();
            customFont = new Font("Arial", Font.BOLD, 64); // Dự phòng
        }

        // Áp dụng font tùy chỉnh
        g2.setFont(customFont.deriveFont(Font.BOLD, 64));

        // Vẽ hình nền
        g2.drawImage(ImageLoader.LoadImage("/Player/main.jpg"), 0, 0, GamePanel.GetInst().getWidth(), GamePanel.GetInst().getHeight(), null);

        // Tiêu đề game
        int x = 16 * GamePanel.scale + 96;
        int y = 32 * GamePanel.scale;
        g2.setColor(Color.BLACK); // Màu đổ bóng
        g2.drawString("ABYSS WALKER", x + 4, y + 4); // Dịch chuyển bóng
        g2.setColor(Color.WHITE); // Màu chính
        g2.drawString("ABYSS WALKER", x, y);

        // Hiển thị các tùy chọn
        g2.setFont(customFont.deriveFont(Font.BOLD, 32)); // Font nhỏ hơn cho các tùy chọn
        x += GamePanel.tileSize;
        String[] options = {"New Game", "Load Game", "Quit"};

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
