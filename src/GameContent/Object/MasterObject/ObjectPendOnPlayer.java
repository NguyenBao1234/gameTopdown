package GameContent.Object.MasterObject;

import CoreGame.EntityComponent.BaseObject;
import CoreGame.EntityComponent.Entity;
import CoreGame.GamePanel;
import HelpDevGameTool.ImageUtility;

import java.awt.*;

public abstract class ObjectPendOnPlayer extends BaseObject
{
    public ObjectPendOnPlayer()
    {
        SpriteRenderSizeX = 16*GamePanel.scale;
        SpriteRenderSizeY = 16*GamePanel.scale;
    }
    public ObjectPendOnPlayer(String DefaultImgPath)
    {
        Sprite = ImageUtility.LoadImage(DefaultImgPath);
        if(Sprite == null) return;
        SpriteRenderSizeX = Sprite.getWidth() * GamePanel.scale;
        SpriteRenderSizeY = Sprite.getHeight() * GamePanel.scale;
    }
    @Override
    public void Render(Graphics2D g2)
    {
        screenX = worldX - GamePanel.GetInst().player.worldX + GamePanel.truePlayerScreenX - (SpriteRenderSizeX - GamePanel.tileSize)/2;
        screenY = worldY - GamePanel.GetInst().player.worldY + GamePanel.truePlayerScreenY - (SpriteRenderSizeY - GamePanel.tileSize)/2;
        super.Render(g2);
    }

    @Override
    public void OnBeginOverlapped(Entity otherEntity) {

    }

    @Override
    public void OnEndOverlapped(Entity otherEntity) {

    }
}
