package CoreGame;

import Entity.Object.ChestObj;
import Entity.Object.DoorObj;
import CoreGame.EntityComponent.BaseObject;
import CoreGame.EntityComponent.KeyItemObject;

public class WorldManager
{
    public static void SetUpObject()
    {
        GamePanel.getInstGamePanel().obj[GamePanel.getInstGamePanel().currentMapIndex][0] = new DoorObj();
        GamePanel.getInstGamePanel().obj[GamePanel.getInstGamePanel().currentMapIndex][0].worldX = 2 * GamePanel.tileSize;
        GamePanel.getInstGamePanel().obj[GamePanel.getInstGamePanel().currentMapIndex][0].worldY = 4 * GamePanel.tileSize;

        GamePanel.getInstGamePanel().obj[GamePanel.getInstGamePanel().currentMapIndex][1] = new KeyItemObject(GamePanel.getInstGamePanel().obj[GamePanel.getInstGamePanel().currentMapIndex][0]);
        GamePanel.getInstGamePanel().obj[GamePanel.getInstGamePanel().currentMapIndex][1].worldX = 0 ;//* GamePanel.getInstGamePanel().tileSize;
        GamePanel.getInstGamePanel().obj[GamePanel.getInstGamePanel().currentMapIndex][1].worldY = 0 ;//* GamePanel.getInstGamePanel().tileSize;

        GamePanel.getInstGamePanel().obj[GamePanel.getInstGamePanel().currentMapIndex][2] = new ChestObj();
        GamePanel.getInstGamePanel().obj[GamePanel.getInstGamePanel().currentMapIndex][2].worldX = 4 * GamePanel.tileSize;
        GamePanel.getInstGamePanel().obj[GamePanel.getInstGamePanel().currentMapIndex][2].worldY = 6 * GamePanel.tileSize;

    }
    public static void SimulateObject()
    {
        BaseObject Objects[] = GamePanel.getInstGamePanel().obj[GamePanel.getInstGamePanel().currentMapIndex];
        for(BaseObject object : Objects)
        {
            if (object == null) return;
            object.SimulateOverlapping(GamePanel.getInstGamePanel().player);
        }
    }
}
