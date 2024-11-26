package CoreGame;

import Entity.BaseCharacter;
import Entity.Entity;
import Tile.TileManager;
import Entity.Player;


public class CollisionChecker
{
    public int collisionAreaX, collisionAreaY;
    public CollisionChecker()
    {

    }

    public void RespondToMap(BaseCharacter character)
    {
        int collisionLeftX = character.worldX + character.collisionArea.x;
        int collisionRightX = character.worldX + character.collisionArea.x + character.collisionArea.width;
        int collisionTopY = character.worldY + character.collisionArea.y;
        int collisionBottomY = character.worldY + character.collisionArea.y + character.collisionArea.height;

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
        character.bOverlapping = tileManager.tiles[tile1Type].collision || tileManager.tiles[tile2Type].collision;
    }
    public int RespondToObject(Entity Entity, boolean player)
    {
        int index = 999;
        for( int i= 0; i< GamePanel.getInstGamePanel().obj.length; i++ )
        {
            if (GamePanel.getInstGamePanel().obj[i] != null)
            {
                Player.collisionArea.x= Entity.worldX + Player.collisionArea.x;
                Player.collisionArea.y = Entity.worldY + Player.collisionArea.y;

                GamePanel.getInstGamePanel().obj[i].solidArea.x =  GamePanel.getInstGamePanel().obj[i].worldX + GamePanel.getInstGamePanel().obj[i].solidArea.x;
                GamePanel.getInstGamePanel().obj[i].solidArea.y = GamePanel.getInstGamePanel().obj[i].worldY + GamePanel.getInstGamePanel().obj[i].solidArea.y;

                switch(BaseCharacter.getCurrentDirection())
                {
                    case up :
                        //Player.collisionArea.y -= 4;
                        if (Player.collisionArea.intersects(GamePanel.getInstGamePanel().obj[i].solidArea)) {
                            System.out.println("up");



                            break;
                        }
                    case down :
                        Player.collisionArea.y += 4;
                        if (Player.collisionArea.intersects(GamePanel.getInstGamePanel().obj[i].solidArea)) {
                            System.out.println("down");
                            break;
                        }
                    case left :
                        Player.collisionArea.x -= 4;
                        if (Player.collisionArea.intersects(GamePanel.getInstGamePanel().obj[i].solidArea)) {
                            System.out.println("left");
                            break;
                        }
                    case right :
                        Player.collisionArea.x += 4;
                        if (Player.collisionArea.intersects(GamePanel.getInstGamePanel().obj[i].solidArea)) {
                            System.out.println("right");
                            break;
                        }

                }
                Player.collisionArea.x = Entity.solidAreaDefaultX;
                Player.collisionArea.y = Entity.solidAreaDefaultY;
                GamePanel.getInstGamePanel().obj[i].solidArea.x = GamePanel.getInstGamePanel().obj[i].solidAreaDefaultX;
                GamePanel.getInstGamePanel().obj[i].solidArea.y = GamePanel.getInstGamePanel().obj[i].solidAreaDefaultY;
            }
        }

        return index;
    }
}
