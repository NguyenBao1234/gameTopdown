package CoreGame;

import GameContent.EventTriggerBox.MapPortal;
import GameContent.Object.ChestObj;
import GameContent.Object.DoorObj;
import CoreGame.EntityComponent.BaseObject;
import GameContent.Object.KeyItemObject;

public class WorldManager
{
    public static void SetUpObject()
    {
        GamePanel.getInstGamePanel().obj[0][0] = new DoorObj();
        GamePanel.getInstGamePanel().obj[0][0].worldX = 2 * GamePanel.tileSize;
        GamePanel.getInstGamePanel().obj[0][0].worldY = 4 * GamePanel.tileSize;

        GamePanel.getInstGamePanel().obj[0][1] = new KeyItemObject(GamePanel.getInstGamePanel().obj[GamePanel.getInstGamePanel().currentMapIndex][0]);
        GamePanel.getInstGamePanel().obj[0][1].worldX = 0 ;//* GamePanel.getInstGamePanel().tileSize;
        GamePanel.getInstGamePanel().obj[0][1].worldY = 0 ;//* GamePanel.getInstGamePanel().tileSize;

        GamePanel.getInstGamePanel().obj[0][2] = new ChestObj();
        GamePanel.getInstGamePanel().obj[0][2].worldX = 4 * GamePanel.tileSize;
        GamePanel.getInstGamePanel().obj[0][2].worldY = 6 * GamePanel.tileSize;

        GamePanel.getInstGamePanel().obj[0][3] = new MapPortal(1, 1,5);
        GamePanel.getInstGamePanel().obj[0][3].worldX = 10 * GamePanel.tileSize;
        GamePanel.getInstGamePanel().obj[0][3].worldY = 2 * GamePanel.tileSize;

        GamePanel.getInstGamePanel().obj[1][0] = new MapPortal(0, 9,2);
        GamePanel.getInstGamePanel().obj[1][0].worldX = 0 * GamePanel.tileSize;
        GamePanel.getInstGamePanel().obj[1][0].worldY = 5 * GamePanel.tileSize;

    }
    public static void SimulateObject()
    {
        BaseObject Objects[] = GamePanel.getInstGamePanel().obj[GamePanel.getInstGamePanel().currentMapIndex];
        for(BaseObject object : Objects)
        {
            if (object == null) continue;
            object.SimulateOverlapping(GamePanel.getInstGamePanel().player);
        }
    }
}
