package CoreGame;

import Entity.BaseCharacter;
import Tile.TileManager;

import java.awt.*;


public class CollisionChecker
{
    public CollisionChecker()
    {

    }

    public void RespondToMap(BaseCharacter character)
    {
        int collisionLeftX = character.worldX + character.getCollisionArea().x;
        int collisionRightX = character.worldX + character.getCollisionArea().x + character.getCollisionArea().width;
        int collisionTopY = character.worldY + character.getCollisionArea().y;
        int collisionBottomY = character.worldY + character.getCollisionArea().y + character.getCollisionArea().height;

        int leftOverlapTileCol = collisionLeftX/GamePanel.tileSize ;
        int rightOverlapTileCol = collisionRightX/GamePanel.tileSize;
        int topOverlapTileRow = collisionTopY/GamePanel.tileSize;
        int bottomOverlapTileRow = collisionBottomY/GamePanel.tileSize;
        //System.out.printf("checking TopRow: %d, BottonRow:%d, LeftCol: %d, RightCol: %d\n",topOverlapTileRow, bottomOverlapTileRow ,leftOverlapTileCol, rightOverlapTileCol);
        if(leftOverlapTileCol < 0||rightOverlapTileCol < 0||topOverlapTileRow <0||bottomOverlapTileRow<0) return;

        TileManager tileManager = GamePanel.getInstGamePanel().tileManager;
        int tile1Type, tile2Type;

        tile1Type = tileManager.tileTypeMap[leftOverlapTileCol][topOverlapTileRow];
        tile2Type = tileManager.tileTypeMap[rightOverlapTileCol][topOverlapTileRow];
        character.bColliding = tileManager.tiles[tile1Type].collision || tileManager.tiles[tile2Type].collision;
    }

    public void RespondToObject(BaseCharacter character, boolean player)
    {
        Rectangle characterCollisionWorld = new Rectangle();
        characterCollisionWorld.x = character.worldX + character.getCollisionArea().x;
        characterCollisionWorld.y = character.worldY + character.getCollisionArea().y;
        characterCollisionWorld.width = character.getCollisionArea().width;
        characterCollisionWorld.height = character.getCollisionArea().height;

        Rectangle objectCollisionWorld = new Rectangle();

        for( int i= 0; i< GamePanel.getInstGamePanel().obj.length; i++ )
        {
            if (GamePanel.getInstGamePanel().obj[i] != null)
            {
                objectCollisionWorld.x = GamePanel.getInstGamePanel().obj[i].worldX + GamePanel.getInstGamePanel().obj[i].getCollisionArea().x;
                objectCollisionWorld.y = GamePanel.getInstGamePanel().obj[i].worldY + GamePanel.getInstGamePanel().obj[i].getCollisionArea().y;
                objectCollisionWorld.height = GamePanel.getInstGamePanel().obj[i].getCollisionArea().height;
                objectCollisionWorld.width = GamePanel.getInstGamePanel().obj[i].getCollisionArea().width;

                switch(character.getCurrentDirection())
                {
                    case up :
                        if (characterCollisionWorld.intersects(objectCollisionWorld))
                        {
                            System.out.println("up");
                            break;
                        }
                    case down :
                        if (characterCollisionWorld.intersects(objectCollisionWorld))
                        {
                            System.out.println("down");
                            break;
                        }
                    case left :
                        if (characterCollisionWorld.intersects(objectCollisionWorld))
                        {
                            System.out.println("left");
                            break;
                        }
                    case right :
                        if (characterCollisionWorld.intersects(objectCollisionWorld))
                        {
                            System.out.println("right");
                            break;
                        }
                }
            }
        }

    }
}
