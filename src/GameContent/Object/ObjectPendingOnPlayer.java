package GameContent.Object;

import CoreGame.EntityComponent.BaseObject;
import CoreGame.EntityComponent.Entity;
import CoreGame.GamePanel;

import java.awt.*;

public abstract class ObjectPendingOnPlayer extends BaseObject
{
    public ObjectPendingOnPlayer()
    {
        SpriteRenderSizeX = 16*GamePanel.scale;
        SpriteRenderSizeY = 16*GamePanel.scale;
    }
    @Override
    public void Render(Graphics2D g2)
    {
        screenX = worldX - GamePanel.GetInst().player.worldX + GamePanel.truePlayerScreenX;
        screenY = worldY - GamePanel.GetInst().player.worldY + GamePanel.truePlayerScreenY;
        super.Render(g2);
    }

    @Override
    public void Tick(float delta) {

    }

    @Override
    public void OnBeginOverlapped(Entity otherEntity) {

    }

    @Override
    public void OnEndOverlapped(Entity otherEntity) {

    }
}
