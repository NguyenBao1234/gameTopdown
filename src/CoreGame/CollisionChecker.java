package CoreGame;

import CoreGame.Enums.Collision;
import Entity.Entity;
import Entity.Object.Master.BaseObject;
import Tile.TileManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class CollisionChecker
{
    public CollisionChecker()
    {

    }

    public void RespondToMap(Entity entity)
    {
        int collisionLeftX = entity.worldX + entity.getCollisionArea().x;
        int collisionRightX = entity.worldX + entity.getCollisionArea().x + entity.getCollisionArea().width;
        int collisionTopY = entity.worldY + entity.getCollisionArea().y;
        int collisionBottomY = entity.worldY + entity.getCollisionArea().y + entity.getCollisionArea().height;

        int leftOverlapTileCol = collisionLeftX/GamePanel.tileSize ;
        int rightOverlapTileCol = collisionRightX/GamePanel.tileSize;
        int topOverlapTileRow = collisionTopY/GamePanel.tileSize;
        int bottomOverlapTileRow = collisionBottomY/GamePanel.tileSize;
        //System.out.printf("checking TopRow: %d, BottonRow:%d, LeftCol: %d, RightCol: %d\n",topOverlapTileRow, bottomOverlapTileRow ,leftOverlapTileCol, rightOverlapTileCol);
        if(leftOverlapTileCol < 0||rightOverlapTileCol < 0||topOverlapTileRow <0||bottomOverlapTileRow<0) return;

        TileManager tileManager = GamePanel.getInstGamePanel().tileManager;
        int tile1Type, tile2Type;

        tile1Type = tileManager.tileTypeMap[GamePanel.getInstGamePanel().currentMap][leftOverlapTileCol][topOverlapTileRow];
        tile2Type = tileManager.tileTypeMap[GamePanel.getInstGamePanel().currentMap][rightOverlapTileCol][topOverlapTileRow];
        boolean bColidingWithObject = false;
        if(entity.getCollisionMode() == Collision.Block)
        {
            Rectangle entityCollisionWorld = new Rectangle();

            entityCollisionWorld.x = entity.worldX + entity.getCollisionArea().x;
            entityCollisionWorld.y = entity.worldY + entity.getCollisionArea().y;
            entityCollisionWorld.width = entity.getCollisionArea().width;
            entityCollisionWorld.height = entity.getCollisionArea().height;

            Rectangle objectCollisionWorld = new Rectangle();
            int curMap = GamePanel.getInstGamePanel().currentMap;
            for (int i = 0; i < GamePanel.getInstGamePanel().obj[curMap].length; i++)
            {
                if (GamePanel.getInstGamePanel().obj[curMap][i] != null)
                {
                    if(GamePanel.getInstGamePanel().obj[curMap][i].getCollisionMode() != Collision.Block) continue;
                    objectCollisionWorld.x = GamePanel.getInstGamePanel().obj[curMap][i].worldX + GamePanel.getInstGamePanel().obj[curMap][i].getCollisionArea().x;
                    objectCollisionWorld.y = GamePanel.getInstGamePanel().obj[curMap][i].worldY + GamePanel.getInstGamePanel().obj[curMap][i].getCollisionArea().y;
                    objectCollisionWorld.height = GamePanel.getInstGamePanel().obj[curMap][i].getCollisionArea().height;
                    objectCollisionWorld.width = GamePanel.getInstGamePanel().obj[curMap][i].getCollisionArea().width;
                    if (entityCollisionWorld.intersects(objectCollisionWorld)) bColidingWithObject = true;
                }
            }
        }
        entity.SetColliding(tileManager.tiles[tile1Type].collision || tileManager.tiles[tile2Type].collision || bColidingWithObject);
    }

    /**Get objects overlap with specified entity*/
    public BaseObject[] getOverlappedObjects(Entity entity)
    {
        Rectangle characterCollisionWorld = new Rectangle();
        characterCollisionWorld.x = entity.worldX + entity.getCollisionArea().x;
        characterCollisionWorld.y = entity.worldY + entity.getCollisionArea().y;
        characterCollisionWorld.width = entity.getCollisionArea().width;
        characterCollisionWorld.height = entity.getCollisionArea().height;

        Rectangle objectCollisionWorld = new Rectangle();

        List <BaseObject> OverlappedObjects = new ArrayList<>();

        boolean bIntersecting = false;
        int curMap = GamePanel.getInstGamePanel().currentMap;
        for( int i= 0; i< GamePanel.getInstGamePanel().obj[curMap].length; i++ )
        {
            if (GamePanel.getInstGamePanel().obj[curMap][i] != null)
            {
                if(GamePanel.getInstGamePanel().obj[curMap][i].getCollisionMode() == Collision.NoCollision ) continue;
                objectCollisionWorld.x = GamePanel.getInstGamePanel().obj[curMap][i].worldX + GamePanel.getInstGamePanel().obj[curMap][i].getCollisionArea().x;
                objectCollisionWorld.y = GamePanel.getInstGamePanel().obj[curMap][i].worldY + GamePanel.getInstGamePanel().obj[curMap][i].getCollisionArea().y;
                objectCollisionWorld.height = GamePanel.getInstGamePanel().obj[curMap][i].getCollisionArea().height;
                objectCollisionWorld.width = GamePanel.getInstGamePanel().obj[curMap][i].getCollisionArea().width;
                if (characterCollisionWorld.intersects(objectCollisionWorld))
                {
                    bIntersecting = true;
                    OverlappedObjects.add(GamePanel.getInstGamePanel().obj[curMap][i]);
                }
            }
        }
        entity.setOverlapping(bIntersecting);
        return OverlappedObjects.toArray(BaseObject[]::new);
    }

    public BaseObject[] getOverlappedObjectsInBox(int worldX, int worldY, int width, int height)
    {
        Rectangle Box = new Rectangle();
        Box.x = worldX;
        Box.y = worldY;
        Box.width = width;
        Box.height = height;

        Rectangle objectCollisionWorld = new Rectangle();

        List <BaseObject> OverlappedObjects = new ArrayList<>();
        int curMap = GamePanel.getInstGamePanel().currentMap;
        for( int i= 0; i< GamePanel.getInstGamePanel().obj[curMap].length; i++ )
        {
            if (GamePanel.getInstGamePanel().obj[curMap][i] != null )
            {
                if(GamePanel.getInstGamePanel().obj[curMap][i].getCollisionMode() == Collision.NoCollision ) continue;
                objectCollisionWorld.x = GamePanel.getInstGamePanel().obj[curMap][i].worldX + GamePanel.getInstGamePanel().obj[curMap][i].getCollisionArea().x;
                objectCollisionWorld.y = GamePanel.getInstGamePanel().obj[curMap][i].worldY + GamePanel.getInstGamePanel().obj[curMap][i].getCollisionArea().y;
                objectCollisionWorld.height = GamePanel.getInstGamePanel().obj[curMap][i].getCollisionArea().height;
                objectCollisionWorld.width = GamePanel.getInstGamePanel().obj[curMap][i].getCollisionArea().width;
                if (Box.intersects(objectCollisionWorld)) OverlappedObjects.add(GamePanel.getInstGamePanel().obj[curMap][i]);
            }
        }
        return OverlappedObjects.toArray(BaseObject[]::new);
    }
}
