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
    private String SubTitle;
    private boolean bRemoveOnLastWord = true;

    public NarrativeMessageWD(String... Content)
    {
        x = GamePanel.tileSize;
        y = GamePanel.screenHeight - GamePanel.tileSize*4;
        widthBox = GamePanel.screenWidth - x - 48;
        heightBox = GamePanel.screenHeight/4;
        Messages = Content;
    }

    public NarrativeMessageWD(boolean bAutoRemove, String... Content)
    {
        x = GamePanel.tileSize;
        y = GamePanel.screenHeight - GamePanel.tileSize*4;
        widthBox = GamePanel.screenWidth - x - 48;
        heightBox = GamePanel.screenHeight/4;
        Messages = Content;
        bRemoveOnLastWord = bAutoRemove;
    }

    @Override
    public void Draw(Graphics2D g2)
    {
        g2.setFont(g2.getFont().deriveFont(17f));
        g2.setColor(new Color(0, 0, 0, 170));
        g2.fillRoundRect(x, y,widthBox,heightBox, 35, 35);

        g2.setColor(Color.GRAY);
        g2.setStroke(new BasicStroke(4));
        g2.drawRoundRect(x+5, y+5,widthBox -10,heightBox -10, 25, 25);

        g2.setColor(Color.WHITE);
        if(Messages[0] == null) g2.drawString("Need add Messages to display", x + 32,y + 32);
        else g2.drawString(Messages[CurrMessageIndex],x + 32,y + 32 );

        if(SubTitle == null || SubTitle.isBlank()) return;
        g2.setFont(g2.getFont().deriveFont(16f));
        g2.drawString(SubTitle,x +32,y + 32*4 );
    }


    public void SetMessages(String... newContent){Messages = newContent;}
    public void SetMessages(boolean bReset, String... newContent)
    {
        Messages = newContent;
        if(bReset) CurrMessageIndex = 0;
    }

    public void SetMessages(boolean bReset, boolean bAutoRemove, String... newContent)
    {
        Messages = newContent;
        if(bReset) CurrMessageIndex = 0;
        bRemoveOnLastWord = bAutoRemove;
    }

    public String[] GetMessages()
    {
        return Messages;
    }


    public void NextContent()
    {
        CurrMessageIndex++;
        if (CurrMessageIndex >= Messages.length)
        {
            CurrMessageIndex = 0;
            if(bRemoveOnLastWord) RemoveFromHUD();
        }
    }

    public int getCurrMessageIndex() {
        return CurrMessageIndex;
    }

    public void setCurrMessageIndex(int currMessageIndex) {
        CurrMessageIndex = currMessageIndex;
    }

    public void SetSubtitle(String Content){SubTitle = Content;}
}
