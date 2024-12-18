package GameContent.NPC;

import CoreGame.EntityComponent.BaseCharacter;
import CoreGame.GamePanel;

import java.awt.*;

public abstract class BaseCharacterPendOnPlayer extends BaseCharacter
{
    @Override
    public void Render(Graphics2D g2)
    {
        screenX = worldX - GamePanel.GetInst().player.worldX + GamePanel.truePlayerScreenX;
        screenY = worldY - GamePanel.GetInst().player.worldY + GamePanel.truePlayerScreenY;
        super.Render(g2);
    }
}
