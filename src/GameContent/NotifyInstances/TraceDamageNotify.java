package GameContent.NotifyInstances;

import CoreGame.AnimNotifyComponent.AnimNotify;
import CoreGame.CollisionComponent.CollisionChecker;
import CoreGame.EntityComponent.BaseCharacter;
import CoreGame.EntityComponent.BaseObject;
import CoreGame.GamePanel;
import GameContent.MainPlayer;
import GameContent.NPC.Enemy.Enemy;

import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class TraceDamageNotify extends AnimNotify
{
    private final BaseCharacter Container;
    private int scaleX, scaleY;
    int width, height;

    public TraceDamageNotify(int frameStart, BaseCharacter entityContain, int BoxScaleX, int BoxScaleY)
    {
        super(frameStart, 2);
        Container = entityContain;
        scaleX = BoxScaleX;
        scaleY = BoxScaleY;
    }

    @Override
    public void ReceiveNotifyBegin()
    {
        float damage = (Container instanceof Enemy) ? ((Enemy) Container).getDamage() : ((MainPlayer) Container).getDamageWeapon();

        int BiasX = scaleX * GamePanel.tileSize - GamePanel.tileSize;
        int BiasY = scaleY * GamePanel.tileSize - GamePanel.tileSize;
        int worldX = Container.worldX;
        int worldY = Container.worldY;
        width = GamePanel.tileSize * scaleX;
        height =  GamePanel.tileSize * scaleY;

        boolean bHasSwapEdge = false;
        switch(Container.GetCurrentDirection())
        {
            case up :
                worldY -= GamePanel.tileSize - BiasY;
                worldX -= BiasX/2;
                break;
            case down:
                worldY += GamePanel.tileSize;
                worldX -= BiasX/2;
                break;
            case left:
                SwapBoxDirect();
                bHasSwapEdge = true;
                worldX -= GamePanel.tileSize - BiasY;
                worldY -= BiasX/2;
                break;
            case right:
                SwapBoxDirect();
                bHasSwapEdge = true;
                worldX += GamePanel.tileSize;
                worldY -= BiasX/2;
                break;
        }
        for (BaseObject enemy: CollisionChecker.GetOverlappedObjectsInBox(worldX, worldY, width, height))
        {
            Container.ApplyPointDamage( enemy,Container, damage,
                    enemy.getScreenX(), enemy.getScreenY(),
                    Container.worldX,Container.worldY );
            System.out.println(enemy);
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
