package CoreGame.CollisionComponent;

import CoreGame.Enums.Collision;
import CoreGame.GamePanel;
import CoreGame.EntityComponent.Entity;
import CoreGame.EntityComponent.BaseObject;
import CoreGame.MapComponent.TileManager;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class CollisionChecker
{
    private static CollisionChecker Inst;
    public CollisionChecker(){}

    public static void RespondToCollisionBox(Entity entity)
    {
        Rectangle enityCollisionWorld = new Rectangle();
        enityCollisionWorld.x = entity.worldX + entity.getCollisionArea().x;
        enityCollisionWorld.y = entity.worldY + entity.getCollisionArea().y;
        enityCollisionWorld.width = entity.getCollisionArea().width;
        enityCollisionWorld.height = entity.getCollisionArea().height;

        int curMap = GamePanel.getInstGamePanel().currentMapIndex;

        Rectangle recCollision = new Rectangle();
        for( int i= 0; i< GamePanel.getInstGamePanel().obj[curMap].length; i++ )
        {
            if (GamePanel.getInstGamePanel().obj[curMap][i] != null)
            {
                if( GamePanel.getInstGamePanel().obj[curMap][i].getCollisionMode() == Collision.NoCollision ) continue;
                recCollision.x = GamePanel.getInstGamePanel().obj[curMap][i].worldX + GamePanel.getInstGamePanel().obj[curMap][i].getCollisionArea().x;
                recCollision.y = GamePanel.getInstGamePanel().obj[curMap][i].worldY + GamePanel.getInstGamePanel().obj[curMap][i].getCollisionArea().y;
                recCollision.height = GamePanel.getInstGamePanel().obj[curMap][i].getCollisionArea().height;
                recCollision.width = GamePanel.getInstGamePanel().obj[curMap][i].getCollisionArea().width;

                if (enityCollisionWorld.intersects(recCollision))
                {

                }
            }
        }
    }

    public static CollisionChecker getInst()
    {
        if(Inst==null) Inst = new CollisionChecker();
        return Inst;
    }

    public static void RespondToMap(Entity entity)
    {
        int collisionLeftX = entity.worldX + entity.getCollisionArea().x;
        int collisionRightX = entity.worldX + entity.getCollisionArea().x + entity.getCollisionArea().width;
        int collisionTopY = entity.worldY + entity.getCollisionArea().y;
        int collisionBottomY = entity.worldY + entity.getCollisionArea().y + entity.getCollisionArea().height;

        int leftOverlapTileCol = collisionLeftX/ GamePanel.tileSize ;
        int rightOverlapTileCol = collisionRightX/GamePanel.tileSize;
        int topOverlapTileRow = collisionTopY/GamePanel.tileSize;
        int bottomOverlapTileRow = collisionBottomY/GamePanel.tileSize;

        int curMap = GamePanel.getInstGamePanel().currentMapIndex;

        int WidthOfMap = TileManager.GetWidthOfCurrentMap();
        int HeightOfMap = TileManager.GetHeightOfCurrentMap();

        //System.out.printf("checking TopRow: %d, BottonRow:%d, LeftCol: %d, RightCol: %d\n",topOverlapTileRow, bottomOverlapTileRow ,leftOverlapTileCol, rightOverlapTileCol);
        if(leftOverlapTileCol < 0||rightOverlapTileCol < 0||topOverlapTileRow <0||bottomOverlapTileRow<0
        ||leftOverlapTileCol > WidthOfMap||rightOverlapTileCol > WidthOfMap || topOverlapTileRow > HeightOfMap || bottomOverlapTileRow > HeightOfMap)
        {
            return;
            //Co the thong bao vi tri tren world cua player tai day
        }

        TileManager tileManager = GamePanel.getInstGamePanel().tileManager;
        int tile1Type, tile2Type;
        //To check colliding with the map:
        tile1Type = tileManager.tileTypeMap[curMap][topOverlapTileRow][leftOverlapTileCol];
        tile2Type = tileManager.tileTypeMap[curMap][topOverlapTileRow][rightOverlapTileCol];
        //To check colliding with objects in the map:
        boolean bColidingWithObject = false;
        if(entity.getCollisionMode() == Collision.Block)
        {
            Rectangle entityCollisionWorld = new Rectangle();

            entityCollisionWorld.x = entity.worldX + entity.getCollisionArea().x;
            entityCollisionWorld.y = entity.worldY + entity.getCollisionArea().y;
            entityCollisionWorld.width = entity.getCollisionArea().width;
            entityCollisionWorld.height = entity.getCollisionArea().height;

            Rectangle objectCollisionWorld = new Rectangle();
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
        if(tile1Type < 0 || tile2Type < 0){ entity.SetColliding(true);return;}
        entity.SetColliding((tileManager.tiles[tile1Type].collision == Collision.Block )|| tileManager.tiles[tile2Type].collision == Collision.Block || bColidingWithObject);
    }

    /**Get objects overlap with specified entity*/
    public static BaseObject[] getOverlappedObjects(Entity entity)
    {
        Rectangle enityCollisionWorld = new Rectangle();
        enityCollisionWorld.x = entity.worldX + entity.getCollisionArea().x;
        enityCollisionWorld.y = entity.worldY + entity.getCollisionArea().y;
        enityCollisionWorld.width = entity.getCollisionArea().width;
        enityCollisionWorld.height = entity.getCollisionArea().height;

        Rectangle objectCollisionWorld = new Rectangle();

        List <BaseObject> OverlappedObjects = new ArrayList<>();

        boolean bIntersecting = false;
        int curMap = GamePanel.getInstGamePanel().currentMapIndex;
        for( int i= 0; i< GamePanel.getInstGamePanel().obj[curMap].length; i++ )
        {
            if (GamePanel.getInstGamePanel().obj[curMap][i] != null)
            {
                if(GamePanel.getInstGamePanel().obj[curMap][i].getCollisionMode() == Collision.NoCollision ) continue;
                objectCollisionWorld.x = GamePanel.getInstGamePanel().obj[curMap][i].worldX + GamePanel.getInstGamePanel().obj[curMap][i].getCollisionArea().x;
                objectCollisionWorld.y = GamePanel.getInstGamePanel().obj[curMap][i].worldY + GamePanel.getInstGamePanel().obj[curMap][i].getCollisionArea().y;
                objectCollisionWorld.height = GamePanel.getInstGamePanel().obj[curMap][i].getCollisionArea().height;
                objectCollisionWorld.width = GamePanel.getInstGamePanel().obj[curMap][i].getCollisionArea().width;
                if (enityCollisionWorld.intersects(objectCollisionWorld))
                {
                    bIntersecting = true;
                    OverlappedObjects.add(GamePanel.getInstGamePanel().obj[curMap][i]);
                }
            }
        }

        return OverlappedObjects.toArray(BaseObject[]::new);
    }

    public static BaseObject[] getOverlappedObjectsInBox(int worldX, int worldY, int width, int height)
    {
        Rectangle Box = new Rectangle();
        Box.x = worldX;
        Box.y = worldY;
        Box.width = width;
        Box.height = height;

        Rectangle objectCollisionWorld = new Rectangle();

        List <BaseObject> OverlappedObjects = new ArrayList<>();
        int curMap = GamePanel.getInstGamePanel().currentMapIndex;
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
