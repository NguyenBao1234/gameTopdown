//Copyright POWGameStd
package GameContent.WidgetInstances;

import POWJ.Data.Enums.GameState;
import POWJ.GamePanel;
import POWJ.GameSaverComponent.SaveUtility;
import POWJ.KeyHandlerComponent.KeyHandler;
import POWJ.SoundComponent.Sound;
import POWJ.SoundComponent.SoundUtility;
import POWJ.WidgetComponent.HUD;
import HelpDevGameTool.ImageUtility;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MainMenuWD extends OptionalWidget
{
    private Font customFont = null;
    private Sound BGMusic;
    public MainMenuWD()
    {
        BGMusic = SoundUtility.SpawnSound(1,true,"/Sound/Music/The Otherside.wav");
        SetupInputComponent();
        SetMaxOption(3,0);
        // Load font tùy chỉnh
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/MedievalSharp.ttf"));
        } catch (Exception e) {
            e.printStackTrace();
            customFont = new Font("Arial", Font.BOLD, 64); // Dự phòng
        }
        options = new String[]{"New Game", "Load Game", "Quit"};
    }

    public void SetupInputComponent()
    {
        KeyHandler ControllerComp = KeyHandler.getInstance();
        ControllerComp.BindAction(KeyEvent.VK_W, true,this::Up);
        ControllerComp.BindAction(KeyEvent.VK_S, true,this::Down);
        ControllerComp.BindAction(KeyEvent.VK_UP, true,this::Up);
        ControllerComp.BindAction(KeyEvent.VK_DOWN, true,this::Down);
        ControllerComp.BindAction(KeyEvent.VK_ENTER, true,this::SelectOption);
    }

    @Override
    public void Draw(Graphics2D g2)
    {
        if (GamePanel.GetInst().gameState != GameState.Tittle) return;

        // Áp dụng font tùy chỉnh
        g2.setFont(customFont.deriveFont(Font.BOLD, 64));
       // Vẽ hình nền
        g2.drawImage(ImageUtility.LoadImage("/Player/MainMenu.png"), 0, 0,
                GamePanel.GetInst().getWidth(), GamePanel.GetInst().getHeight(), null);

        int x = 16 * GamePanel.scale + 96;
        int y = 32 * GamePanel.scale;
        g2.setColor(Color.BLACK); // Màu đổ bóng
        g2.drawString("ABYSS WALKER", x + 4, y + 4); // Dịch chuyển bóng
        g2.setColor(Color.WHITE); // Màu chính
        g2.drawString("ABYSS WALKER", x, y);

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

        Image selectorIcon = ImageUtility.LoadImage("/Objects/skull.png");
        g2.drawImage(selectorIcon, iconX, iconY, GamePanel.tileSize/2, GamePanel.tileSize/2, null);

    }



    protected void SelectOption()
    {
        super.SelectOption();
        BGMusic.Stop(true);
        if(GamePanel.GetInst().gameState != GameState.Tittle) return;
        if (SelectingRowOption == 0)
        {
            GamePanel.GetInst().gameState = GameState.Run;
            HUD.RemoveWidget(this);
            GamePanel.GetInst().currentMapIndex = 0;
            GamePanel.GetInst().getPlayer().RestoreState();
            GamePanel.GetInst().SetBackgroundColorDefault();
        }
        if(SelectingRowOption == 1)
        {
            if(!SaveUtility.DoesGameSaveExist("GameSave")) return;
            GamePanel.GetInst().saveLoad.load();
            GamePanel.GetInst().gameState = GameState.Run;
            HUD.RemoveWidget(this);
        }

        if(SelectingRowOption == 2 ){
            System.exit(0);
        }
    }
    public void PlayBGMusic(){BGMusic.Play();}
}
