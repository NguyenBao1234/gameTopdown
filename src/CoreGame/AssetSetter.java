package CoreGame;

import Entity.Object.OBJ_Chest;
import Entity.Object.OBJ_Door;
import Entity.Object.OBJ_Key;

public class AssetSetter 
{
    public AssetSetter (){}
    
    public void setObject()
    {
        GamePanel.getInstGamePanel().obj[0] = new OBJ_Key();
        GamePanel.getInstGamePanel().obj[0].worldX = 0 ;//* GamePanel.getInstGamePanel().tileSize;
        GamePanel.getInstGamePanel().obj[0].worldY = 0 ;//* GamePanel.getInstGamePanel().tileSize;

        GamePanel.getInstGamePanel().obj[1] = new OBJ_Door();
        GamePanel.getInstGamePanel().obj[1].worldX = 2 * GamePanel.tileSize;
        GamePanel.getInstGamePanel().obj[1].worldY = 4 * GamePanel.tileSize;

        GamePanel.getInstGamePanel().obj[2] = new OBJ_Chest();
        GamePanel.getInstGamePanel().obj[2].worldX = 4 * GamePanel.tileSize;
        GamePanel.getInstGamePanel().obj[2].worldY = 8 * GamePanel.tileSize;

    }
}
