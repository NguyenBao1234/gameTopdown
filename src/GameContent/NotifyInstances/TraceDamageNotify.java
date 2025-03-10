//Copyright POWGameStd
package GameContent.NotifyInstances;

import POWJ.AnimNotifyComponent.AnimNotify;
import POWJ.CollisionComponent.CollisionChecker;
import POWJ.EntityComponent.BaseCharacter;
import POWJ.EntityComponent.BaseObject;
import POWJ.GamePanel;
import GameContent.MainPlayer;
import GameContent.NPC.Enemy.Enemy;


public class TraceDamageNotify extends AnimNotify
{
    private final BaseCharacter Container;
    private float scaleX, scaleY;
    int width, height;

    public TraceDamageNotify(int frameStart, BaseCharacter entityContain, float BoxScaleX, float BoxScaleY)
    {
        super(frameStart, 0);
        Container = entityContain;
        scaleX = BoxScaleX;
        scaleY = BoxScaleY;
    }

    @Override
    public void ReceiveNotifyBegin()
    {
        float damage = (Container instanceof Enemy) ? ((Enemy) Container).getDamage() : ((MainPlayer) Container).getDamageWeapon();

        width = (int)(scaleX * GamePanel.tileSize);
        height = (int)(scaleY * GamePanel.tileSize);
        int worldX = Container.worldX;
        int worldY = Container.worldY;
        int BiasX = width - GamePanel.tileSize;
        int BiasY = height - GamePanel.tileSize;

        boolean bHasSwapEdge = false;
        switch(Container.GetCurrentDirection())
        {
            case up :
                worldY -= (GamePanel.tileSize + BiasY);
                worldX -= BiasX/2;
                break;
            case down:
                worldY += GamePanel.tileSize;
                worldX -= BiasX/2;
                break;
            case left:
                SwapBoxDirect();
                bHasSwapEdge = true;
                worldX -= (GamePanel.tileSize + BiasY);
                worldY -= BiasX/2;
                break;
            case right:
                SwapBoxDirect();
                bHasSwapEdge = true;
                worldX += GamePanel.tileSize;
                worldY -= BiasX/2;
                break;
        }
        for (BaseObject enemy: CollisionChecker.GetOverlappedObjectsInBox(Container,worldX, worldY, width, height))
        {
            Container.ApplyPointDamage( enemy,Container, damage,
                    enemy.getScreenX(), enemy.getScreenY(),
                    Container.worldX,Container.worldY );
        }
        if(bHasSwapEdge) SwapBoxDirect();
    }

    private void SwapBoxDirect()
    {
        width = width + height;
        height = width - height;
        width = width - height;
    }

    public void SetScaleOfTraceBox(int width, int height)
    {
        scaleY = height;
        scaleX = width;
    }

    @Override
    public void ReceiveNotifyEnd() {

    }

    @Override
    public void ReceiveNotifyTick() {
    }
}
