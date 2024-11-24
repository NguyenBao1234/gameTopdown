package CoreGame;

import Entity.BaseCharacter;
import Tile.TileManager;

public class CollisionChecker
{
    private TileManager tileManager;
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
        System.out.printf("checking TopRow: %d, BottonRow:%d, LeftCol: %d, RightCol: %d\n",topOverlapTileRow, bottomOverlapTileRow ,leftOverlapTileCol, rightOverlapTileCol);
        if(leftOverlapTileCol < 0||rightOverlapTileCol < 0||topOverlapTileRow <0||bottomOverlapTileRow<0) return;

        tileManager = GamePanel.getInstGamePanel().tileManager;
        int tile1Type, tile2Type;

        tile1Type = tileManager.tileTypeMap[leftOverlapTileCol][topOverlapTileRow];
        tile2Type = tileManager.tileTypeMap[rightOverlapTileCol][topOverlapTileRow];
        character.bOverlapping = tileManager.tiles[tile1Type].collision || tileManager.tiles[tile2Type].collision;
//        switch (character.getCurrentDirection())
//        {
//            case up:
//                if(leftOverlapTileCol >= tileManager.tileTypeMap[0].length
//                        ||rightOverlapTileCol >= tileManager.tileTypeMap[0].length
//                        ||topOverlapTileRow >= tileManager.tileTypeMap.length)
//                {
//                    return;
//                }
//                //noinspection ReassignedVariable
////                topOverlapTileRow = (character.worldY + character.collisionArea.y )/GamePanel.tileSize;
////                if(topOverlapTileRow < 0) return;
//                tile1Type = tileManager.tileTypeMap[leftOverlapTileCol][topOverlapTileRow];
//                tile2Type = tileManager.tileTypeMap[rightOverlapTileCol][topOverlapTileRow];
//                System.out.printf("checking TopRow: %d, BottonRow:%d, LeftCol: %d, RightCol: %d, Tile1 = %d, Tile2 = %d\n",topOverlapTileRow, bottomOverlapTileRow ,leftOverlapTileCol, rightOverlapTileCol,tile1Type,tile2Type);
//                character.bOverlapping = tileManager.tiles[tile1Type].collision || tileManager.tiles[tile2Type].collision;
//                break;
//
//            case down:
//                if(leftOverlapTileCol >= tileManager.tileTypeMap[0].length
//                        ||rightOverlapTileCol >= tileManager.tileTypeMap[0].length
//                        ||bottomOverlapTileRow >= tileManager.tileTypeMap.length)
//                {
//                    return;
//                }
//                //noinspection ReassignedVariable
////                bottomOverlapTileRow = (character.worldY + character.collisionArea.y + 48)/GamePanel.tileSize;
//                tile1Type = tileManager.tileTypeMap[leftOverlapTileCol][bottomOverlapTileRow];
//                tile2Type = tileManager.tileTypeMap[rightOverlapTileCol][bottomOverlapTileRow];
//                System.out.printf("checking TopRow: %d, LeftCol: %d, RightCol: %d, Tile1 = %d, Tile2 = %d\n",topOverlapTileRow, leftOverlapTileCol, rightOverlapTileCol,tile1Type,tile2Type);
//                character.bOverlapping = tileManager.tiles[tile1Type].collision || tileManager.tiles[tile2Type].collision;
//                break;

//            case left:
//                tile1Type = tileManager.tileTypeMap[leftOverlapTileCol][topOverlapTileRow];
//                tile2Type = tileManager.tileTypeMap[leftOverlapTileCol][bottomOverlapTileRow];
//                //System.out.printf("checking TopRow: %d, LeftCol: %d, RightCol: %d, Tile1 = %d, Tile2 = %d\n",topOverlapTileRow, leftOverlapTileCol, rightOverlapTileCol,tile1Type,tile2Type);
//                character.bOverlapping = tileManager.tiles[tile1Type].collision || tileManager.tiles[tile2Type].collision;
//                break;
//
//            case right:
//                tile1Type = tileManager.tileTypeMap[rightOverlapTileCol][topOverlapTileRow];
//                tile2Type = tileManager.tileTypeMap[rightOverlapTileCol][bottomOverlapTileRow];
//                //System.out.printf("checking TopRow: %d, LeftCol: %d, RightCol: %d, Tile1 = %d, Tile2 = %d\n",topOverlapTileRow, leftOverlapTileCol, rightOverlapTileCol,tile1Type,tile2Type);
//                character.bOverlapping = tileManager.tiles[tile1Type].collision || tileManager.tiles[tile2Type].collision;
//                break;
//        }
    }
}
