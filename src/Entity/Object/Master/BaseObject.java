package Entity.Object.Master;

import CoreGame.GamePanel;
import Entity.Entity;
import java.awt.*;

public class BaseObject extends Entity
{
    protected boolean bVisible = true;
    public BaseObject()
    {
        collisionArea = new Rectangle(0,0,48,48);
    }

    public void draw (Graphics2D g2)
    {
        if(!bVisible || sprite == null) return;
        int screenX = worldX - GamePanel.getInstGamePanel().player.worldX + GamePanel.truePlayerScreenX;
        int screenY = worldY - GamePanel.getInstGamePanel().player.worldY + GamePanel.truePlayerScreenY;
        g2.drawImage(sprite, screenX, screenY, 16*GamePanel.scale, 16*GamePanel.scale, null );
    }
}


