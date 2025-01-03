package GameContent.Object.MasterObject;

import CoreGame.EntityComponent.BaseObject;
import CoreGame.EntityComponent.Entity;
import CoreGame.GamePanel;
import HelpDevGameTool.ImageUtility;

import java.awt.*;

public class ObjectPendOnPlayer extends BaseObject
{
    public ObjectPendOnPlayer()
    {
        SpriteRenderSizeX = 16*GamePanel.scale;
        SpriteRenderSizeY = 16*GamePanel.scale;
    }
    public ObjectPendOnPlayer(String DefaultImgPath)
    {
        super(DefaultImgPath);
    }
    public ObjectPendOnPlayer(String FlipBookPath, int FPSPerImage)
    {
        super(FlipBookPath,FPSPerImage);
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
