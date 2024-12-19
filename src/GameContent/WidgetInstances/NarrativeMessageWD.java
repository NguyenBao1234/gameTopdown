package GameContent.WidgetInstances;

import CoreGame.GamePanel;
import CoreGame.WidgetComponent.HUD;
import CoreGame.WidgetComponent.Widget;

import java.awt.*;

public class NarrativeMessageWD extends Widget
{
    private final String[] Messages;
    private int CurrMessage = 0;
    private final int x,y,widthBox, heightBox;

    public NarrativeMessageWD(String... Content)
    {
        x = GamePanel.tileSize;
        y = GamePanel.screenHeight - GamePanel.tileSize*4;
        widthBox = GamePanel.screenWidth - x - 48;
        heightBox = GamePanel.screenHeight/4;
        Messages = Content;
    }

    @Override
    public void Draw(Graphics2D g2)
    {
        g2.setColor(new Color(0, 0, 0, 170));
        g2.fillRoundRect(x, y,widthBox,heightBox, 35, 35);

        g2.setColor(Color.GRAY);
        g2.setStroke(new BasicStroke(4));
        g2.drawRoundRect(x+5, y+5,widthBox -10,heightBox -10, 25, 25);

        g2.setColor(Color.WHITE);
        if(Messages[0] == null) g2.drawString("Need add Messages to display", x + 32,y + 32);
        else g2.drawString(Messages[CurrMessage],x + 32,y + 32 );
    }

    public int getCurrMessage() {
        return CurrMessage;
    }

    public void setCurrMessage(int currMessage) {
        CurrMessage = currMessage;
    }

    public void NextContent()
    {
        CurrMessage ++;
        if (CurrMessage >= Messages.length)
        {
            CurrMessage = 0;
            HUD.RemoveWidget(this);
        }
    }
}
