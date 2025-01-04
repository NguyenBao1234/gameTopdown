package GameContent.WidgetInstances;

import CoreGame.GamePanel;
import CoreGame.WidgetComponent.HUD;
import CoreGame.WidgetComponent.Widget;

import java.awt.*;

public class NarrativeMessageWD extends Widget
{
    private String[] Messages;
    private int CurrMessageIndex = 0;
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
        else g2.drawString(Messages[CurrMessageIndex],x + 32,y + 32 );
    }

    public void SetMessages(String... newContent){Messages = newContent;}
    public void SetMessages(boolean bReset, String... newContent)
    {
        Messages = newContent;
        if(bReset) CurrMessageIndex = 0;
    }


    public int getCurrMessageIndex() {
        return CurrMessageIndex;
    }

    public void setCurrMessageIndex(int currMessageIndex) {
        CurrMessageIndex = currMessageIndex;
    }

    public void NextContent()
    {
        CurrMessageIndex++;
        if (CurrMessageIndex >= Messages.length)
        {
            CurrMessageIndex = 0;
            HUD.RemoveWidget(this);
        }
    }
}
