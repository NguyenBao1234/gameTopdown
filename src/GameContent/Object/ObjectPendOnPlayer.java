package GameContent.Object;

import CoreGame.EntityComponent.BaseObject;
import CoreGame.EntityComponent.Entity;
import CoreGame.GamePanel;

import java.awt.*;

public abstract class ObjectPendOnPlayer extends BaseObject
{
    public ObjectPendOnPlayer()
    {
        SpriteRenderSizeX = 16*GamePanel.scale;
        SpriteRenderSizeY = 16*GamePanel.scale;
    }
    @Override
    public void Render(Graphics2D g2)
    {
        screenX = worldX - GamePanel.GetInst().player.worldX + GamePanel.truePlayerScreenX - (SpriteRenderSizeX - GamePanel.tileSize);
        screenY = worldY - GamePanel.GetInst().player.worldY + GamePanel.truePlayerScreenY - (SpriteRenderSizeY - GamePanel.tileSize);
        super.Render(g2);
    }

    @Override
    public void OnBeginOverlapped(Entity otherEntity) {

    }

    @Override
    public void OnEndOverlapped(Entity otherEntity) {

    }
}
