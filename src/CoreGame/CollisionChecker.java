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
        int leftOverlapTileCol = (character.worldX + character.collisionArea.x )/GamePanel.tileSize ;
        int rightOverlapTileCol = (character.worldX + character.collisionArea.x + character.collisionArea.width )/GamePanel.tileSize;
        int topOverlapTileRow = (character.worldY + character.collisionArea.y)/GamePanel.tileSize;
        int bottomOverlapTileRow = (character.worldY + character.collisionArea.y + character.collisionArea.height)/GamePanel.tileSize;

        tileManager = GamePanel.getInstGamePanel().tileManager;
        int tile1Type, tile2Type;
        switch (character.getCurrentDirection())
        {
            case up:
                tile1Type = tileManager.tileTypeMap[leftOverlapTileCol][topOverlapTileRow];
                tile2Type = tileManager.tileTypeMap[rightOverlapTileCol][topOverlapTileRow];
                System.out.printf("checking TopRow: %d, LeftCol: %d, RightCol: %d, Tile1 = %d, Tile2 = %d\n",topOverlapTileRow, leftOverlapTileCol, rightOverlapTileCol,tile1Type,tile2Type);
                if(!tileManager.tiles[tile1Type].collision && !tileManager.tiles[tile2Type].collision)
                {
                    character.bOverlapping = false;
                }
                else character.bOverlapping = true;
                break;

            case down:
                tile1Type = tileManager.tileTypeMap[leftOverlapTileCol][bottomOverlapTileRow];
                tile2Type = tileManager.tileTypeMap[rightOverlapTileCol][bottomOverlapTileRow];
                //System.out.printf("checking TopRow: %d, LeftCol: %d, RightCol: %d, Tile1 = %d, Tile2 = %d\n",topOverlapTileRow, leftOverlapTileCol, rightOverlapTileCol,tile1Type,tile2Type);
                if(!tileManager.tiles[tile1Type].collision && !tileManager.tiles[tile2Type].collision)
                {
                    character.bOverlapping = false;
                }
                else character.bOverlapping = true;
                break;

            case left:
                tile1Type = tileManager.tileTypeMap[leftOverlapTileCol][topOverlapTileRow];
                tile2Type = tileManager.tileTypeMap[leftOverlapTileCol][bottomOverlapTileRow];
                //System.out.printf("checking TopRow: %d, LeftCol: %d, RightCol: %d, Tile1 = %d, Tile2 = %d\n",topOverlapTileRow, leftOverlapTileCol, rightOverlapTileCol,tile1Type,tile2Type);
                if(!tileManager.tiles[tile1Type].collision && !tileManager.tiles[tile2Type].collision)
                {
                    character.bOverlapping = false;
                }
                else character.bOverlapping = true;
                break;

            case right:
                tile1Type = tileManager.tileTypeMap[rightOverlapTileCol][topOverlapTileRow];
                tile2Type = tileManager.tileTypeMap[rightOverlapTileCol][bottomOverlapTileRow];
                //System.out.printf("checking TopRow: %d, LeftCol: %d, RightCol: %d, Tile1 = %d, Tile2 = %d\n",topOverlapTileRow, leftOverlapTileCol, rightOverlapTileCol,tile1Type,tile2Type);
                if(!tileManager.tiles[tile1Type].collision && !tileManager.tiles[tile2Type].collision)
                {
                    character.bOverlapping = false;
                }
                else character.bOverlapping = true;
                break;
        }
    }
}
