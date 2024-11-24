package CoreGame;

import Entity.BaseCharacter;
import Tile.TileManager;

public class CollisionChecker
{
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
}
