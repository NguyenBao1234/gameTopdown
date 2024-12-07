package CoreGame;

import Entity.Object.Instance.ChestObj;
import Entity.Object.Instance.DoorObj;
import Entity.Object.Master.KeyItemObject;

public class AssetSetter
{
    public AssetSetter (){}
    
    public static void SetUpObject()
    {
        GamePanel.getInstGamePanel().obj[0] = new DoorObj();
        GamePanel.getInstGamePanel().obj[0].worldX = 2 * GamePanel.tileSize;
        GamePanel.getInstGamePanel().obj[0].worldY = 4 * GamePanel.tileSize;

        GamePanel.getInstGamePanel().obj[1] = new KeyItemObject(GamePanel.getInstGamePanel().obj[0]);
        GamePanel.getInstGamePanel().obj[1].worldX = 0 ;//* GamePanel.getInstGamePanel().tileSize;
        GamePanel.getInstGamePanel().obj[1].worldY = 0 ;//* GamePanel.getInstGamePanel().tileSize;

        GamePanel.getInstGamePanel().obj[2] = new ChestObj();
        GamePanel.getInstGamePanel().obj[2].worldX = 4 * GamePanel.tileSize;
        GamePanel.getInstGamePanel().obj[2].worldY = 6 * GamePanel.tileSize;

    }
}
