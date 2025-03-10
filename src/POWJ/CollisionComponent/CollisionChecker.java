//Copyright POWGameStd
package POWJ.CollisionComponent;

import POWJ.Data.Enums.Collision;
import POWJ.GamePanel;
import POWJ.EntityComponent.Entity;
import POWJ.EntityComponent.BaseObject;
import POWJ.MapComponent.TileManager;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class CollisionChecker
{
//    private static CollisionChecker Inst;
//    public CollisionChecker(){Inst = this;}
    public static void RespondToCollisionInBox(Entity entity)
    {
        Rectangle enityCollisionWorld = new Rectangle();
        enityCollisionWorld.x = entity.worldX + entity.getCollisionArea().x;
        enityCollisionWorld.y = entity.worldY + entity.getCollisionArea().y;
        enityCollisionWorld.width = entity.getCollisionArea().width;
        enityCollisionWorld.height = entity.getCollisionArea().height;

        int curMap = GamePanel.GetInst().currentMapIndex;

        Rectangle recCollision = new Rectangle();
        for(int i = 0; i< GamePanel.GetInst().obj[curMap].length; i++ )
        {
            if (GamePanel.GetInst().obj[curMap][i] != null)
            {
                if( GamePanel.GetInst().obj[curMap][i].getCollisionMode() == Collision.NoCollision ) continue;
                recCollision.x = GamePanel.GetInst().obj[curMap][i].worldX + GamePanel.GetInst().obj[curMap][i].getCollisionArea().x;
                recCollision.y = GamePanel.GetInst().obj[curMap][i].worldY + GamePanel.GetInst().obj[curMap][i].getCollisionArea().y;
                recCollision.height = GamePanel.GetInst().obj[curMap][i].getCollisionArea().height;
                recCollision.width = GamePanel.GetInst().obj[curMap][i].getCollisionArea().width;

                if (enityCollisionWorld.intersects(recCollision))
                {

                }
            }
        }
    }
//    public static CollisionChecker getInst()
//    {
//        if(Inst==null) Inst = new CollisionChecker();
//        return Inst;
//    }

//    public static void RespondToMap(BaseCharacter character)
//    {
//        //if(TileManager.GetInst() == null) return;
//        int collisionLeftX = character.worldX + character.getCollisionArea().x;
//        int collisionRightX = character.worldX + character.getCollisionArea().x + character.getCollisionArea().width;
//        int collisionTopY = character.worldY + character.getCollisionArea().y;
//        int collisionBottomY = character.worldY + character.getCollisionArea().y + character.getCollisionArea().height;
//
//        int leftOverlapTileCol = collisionLeftX/ GamePanel.tileSize ;
//        int rightOverlapTileCol = collisionRightX/GamePanel.tileSize;
//        int topOverlapTileRow = collisionTopY/GamePanel.tileSize;
//        int bottomOverlapTileRow = collisionBottomY/GamePanel.tileSize;
//
//        int curMap = GamePanel.GetInst().currentMapIndex;
//
//        int WidthOfMap = TileManager.GetWidthOfCurrentMap();
//        int HeightOfMap = TileManager.GetHeightOfCurrentMap();
//
//        //System.out.printf("checking TopRow: %d, BottonRow:%d, LeftCol: %d, RightCol: %d\n",topOverlapTileRow, bottomOverlapTileRow ,leftOverlapTileCol, rightOverlapTileCol);
//        if(leftOverlapTileCol < 0||rightOverlapTileCol < 0||topOverlapTileRow <0||bottomOverlapTileRow<0
//        ||leftOverlapTileCol > WidthOfMap||rightOverlapTileCol > WidthOfMap || topOverlapTileRow > HeightOfMap || bottomOverlapTileRow > HeightOfMap)
//        {
//            //Co the thong bao vi tri tren world cua player tai day
//        }
//
//        int tile1Type, tile2Type;
//        //To check colliding with the map:
//        try {
//            tile1Type = TileManager.tileTypeMap[curMap][topOverlapTileRow][leftOverlapTileCol];
//            tile2Type = TileManager.tileTypeMap[curMap][topOverlapTileRow][rightOverlapTileCol];
//
//        } catch (Exception e) {
//            tile1Type = -1;
//            tile2Type = -1;
//        }
//        //To check colliding with objects in the map:
//        boolean bColidingWithObject = false;
//        if(character.getCollisionMode() == Collision.Block)
//        {
//            Rectangle entityCollisionWorld = new Rectangle();
//            entityCollisionWorld.x = character.worldX + character.getCollisionArea().x;
//            entityCollisionWorld.y = character.worldY + character.getCollisionArea().y;
//            entityCollisionWorld.width = character.getCollisionArea().width;
//            entityCollisionWorld.height = character.getCollisionArea().height;
//
//            Rectangle objectCollisionWorld = new Rectangle();
//            for (int i = 0; i < GamePanel.GetInst().obj[curMap].length; i++)
//            {
//                if (GamePanel.GetInst().obj[curMap][i] != null)
//                {
//                    if(GamePanel.GetInst().obj[curMap][i].getCollisionMode() != Collision.Block
//                            || GamePanel.GetInst().obj[curMap][i] == character) continue;
//                    objectCollisionWorld.x = GamePanel.GetInst().obj[curMap][i].worldX + GamePanel.GetInst().obj[curMap][i].getCollisionArea().x;
//                    objectCollisionWorld.y = GamePanel.GetInst().obj[curMap][i].worldY + GamePanel.GetInst().obj[curMap][i].getCollisionArea().y;
//                    objectCollisionWorld.height = GamePanel.GetInst().obj[curMap][i].getCollisionArea().height;
//                    objectCollisionWorld.width = GamePanel.GetInst().obj[curMap][i].getCollisionArea().width;
//                    if (entityCollisionWorld.intersects(objectCollisionWorld)) bColidingWithObject = true;
//                }
//            }
//        }
//        if(tile1Type < 0 || tile2Type < 0){ character.SetColliding(true);return;}
//        character.SetColliding((TileManager.tiles[tile1Type].collision == Collision.Block )|| TileManager.tiles[tile2Type].collision == Collision.Block || bColidingWithObject);
//    }

    public static boolean IsCollidingWithTileInBox(int x, int y, int width, int height)
    {
        int leftOverlapTileCol = x/ GamePanel.tileSize ;
        int rightOverlapTileCol = (x + width )/GamePanel.tileSize;
        int topOverlapTileRow = y /GamePanel.tileSize;
        int bottomOverlapTileRow = (y + height)/GamePanel.tileSize;

        int curMap = GamePanel.GetInst().currentMapIndex;
        int tile1Type, tile2Type;

        try {
            tile1Type = TileManager.tileTypeMap[curMap][topOverlapTileRow][leftOverlapTileCol];
            tile2Type = TileManager.tileTypeMap[curMap][topOverlapTileRow][rightOverlapTileCol];

        } catch (Exception e) {
            tile1Type = -1;
            tile2Type = -1;
        }
        if(tile1Type < 0 || tile2Type < 0) return true;
        return (TileManager.tiles[tile1Type].collision == Collision.Block ||
                TileManager.tiles[tile2Type].collision == Collision.Block);
    }

    public static boolean IsCollidingWithObjectInBox(BaseObject Owner, int x, int y, int width, int height)
    {
        for( BaseObject object : GetOverlappedObjectsInBox(Owner,x,y,width,height))
        {
            if(object.getCollisionMode() != Collision.Block) continue;
            return true;
        }
        return false;
    }

    /**Get objects overlap with specified entity*/
    public static Entity[] getOverlappedEntities(Entity entity)
    {
        Rectangle enityCollisionWorld = new Rectangle();
        enityCollisionWorld.x = entity.worldX + entity.getCollisionArea().x;
        enityCollisionWorld.y = entity.worldY + entity.getCollisionArea().y;
        enityCollisionWorld.width = entity.getCollisionArea().width;
        enityCollisionWorld.height = entity.getCollisionArea().height;

        Rectangle objectCollisionWorld = new Rectangle();

        List <Entity> OverlappedObjects = new ArrayList<>();

        int curMap = GamePanel.GetInst().currentMapIndex;
        for(int i = 0; i< GamePanel.GetInst().obj[curMap].length; i++ )
        {
            if (GamePanel.GetInst().obj[curMap][i] != null)
            {
                if(GamePanel.GetInst().obj[curMap][i].getCollisionMode() == Collision.NoCollision ) continue;
                objectCollisionWorld.x = GamePanel.GetInst().obj[curMap][i].worldX + GamePanel.GetInst().obj[curMap][i].getCollisionArea().x;
                objectCollisionWorld.y = GamePanel.GetInst().obj[curMap][i].worldY + GamePanel.GetInst().obj[curMap][i].getCollisionArea().y;
                objectCollisionWorld.height = GamePanel.GetInst().obj[curMap][i].getCollisionArea().height;
                objectCollisionWorld.width = GamePanel.GetInst().obj[curMap][i].getCollisionArea().width;
                if (enityCollisionWorld.intersects(objectCollisionWorld))
                {
                    OverlappedObjects.add(GamePanel.GetInst().obj[curMap][i]);
                }
            }
        }

        return OverlappedObjects.toArray(Entity[]::new);
    }

    public static BaseObject[] GetOverlappedObjectsInBox(BaseObject Owner, int worldX, int worldY, int width, int height)
    {
        Rectangle Box = new Rectangle();
        Box.x = worldX;
        Box.y = worldY;
        Box.width = width;
        Box.height = height;

        Rectangle objectCollisionWorld = new Rectangle();

        List <BaseObject> OverlappedObjects = new ArrayList<>();
        int curMap = GamePanel.GetInst().currentMapIndex;

        if(GamePanel.GetInst().getPlayer() != Owner)
        {
            objectCollisionWorld.x = GamePanel.GetInst().getPlayer().worldX + GamePanel.GetInst().getPlayer().getCollisionArea().width;
            objectCollisionWorld.y = GamePanel.GetInst().getPlayer().worldY + GamePanel.GetInst().getPlayer().getCollisionArea().height;
            objectCollisionWorld.width = GamePanel.GetInst().getPlayer().getCollisionArea().width;
            objectCollisionWorld.height = GamePanel.GetInst().getPlayer().getCollisionArea().height;
            if (Box.intersects(objectCollisionWorld)) OverlappedObjects.add(GamePanel.GetInst().getPlayer());
        }
        for(int i = 0; i< GamePanel.GetInst().obj[curMap].length; i++ )
        {
            if (GamePanel.GetInst().obj[curMap][i] != null )
            {
                if(GamePanel.GetInst().obj[curMap][i].getCollisionMode() == Collision.NoCollision ||
                        GamePanel.GetInst().obj[curMap][i] == Owner) continue;
                objectCollisionWorld.x = GamePanel.GetInst().obj[curMap][i].worldX + GamePanel.GetInst().obj[curMap][i].getCollisionArea().x;
                objectCollisionWorld.y = GamePanel.GetInst().obj[curMap][i].worldY + GamePanel.GetInst().obj[curMap][i].getCollisionArea().y;
                objectCollisionWorld.height = GamePanel.GetInst().obj[curMap][i].getCollisionArea().height;
                objectCollisionWorld.width = GamePanel.GetInst().obj[curMap][i].getCollisionArea().width;
                if (Box.intersects(objectCollisionWorld)) OverlappedObjects.add(GamePanel.GetInst().obj[curMap][i]);
            }
        }
        return OverlappedObjects.toArray(BaseObject[]::new);
    }

}
