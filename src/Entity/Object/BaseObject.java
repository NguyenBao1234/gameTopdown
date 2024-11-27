package Entity.Object;

import CoreGame.GamePanel;
import Entity.Entity;
import java.awt.*;

public class BaseObject extends Entity
{
    public String name;
    public void draw (Graphics2D g2)
    {
        int screenX = worldX - GamePanel.getInstGamePanel().player.worldX + GamePanel.truePlayerScreenX;
        int screenY = worldY - GamePanel.getInstGamePanel().player.worldY + GamePanel.truePlayerScreenY;
        g2.drawImage(sprite, screenX, screenY, 16*GamePanel.scale, 16*GamePanel.scale, null );
    }
}


