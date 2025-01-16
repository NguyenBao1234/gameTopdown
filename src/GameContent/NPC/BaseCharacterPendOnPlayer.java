//Copyright POWGameStd
package GameContent.NPC;

import POWJ.EntityComponent.BaseCharacter;
import POWJ.GamePanel;

import java.awt.*;

public abstract class BaseCharacterPendOnPlayer extends BaseCharacter
{
    @Override
    public void Render(Graphics2D g2)
    {
        screenX = worldX - GamePanel.GetInst().getPlayer().worldX + GamePanel.truePlayerScreenX - (SpriteRenderSizeX - GamePanel.tileSize)/2;
        screenY = worldY - GamePanel.GetInst().getPlayer().worldY + GamePanel.truePlayerScreenY - (SpriteRenderSizeY - GamePanel.tileSize)/2;
        super.Render(g2);
    }
}
