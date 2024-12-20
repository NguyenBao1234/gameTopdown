package CoreGame;

import GameContent.EventTriggerBox.MapPortal;
import GameContent.NPC.HangingNPC;
import GameContent.NPC.NonThreatening.Morph_Idle;
import GameContent.Object.ChestObj;
import GameContent.Object.DoorObj;
import CoreGame.EntityComponent.BaseObject;
import GameContent.Object.KeyItemObject;

public class WorldManager
{
    public static void SetUpObject()
    {
        GamePanel.GetInst().obj[0][0] = new DoorObj();
        GamePanel.GetInst().obj[0][0].worldX = 2 * GamePanel.tileSize;
        GamePanel.GetInst().obj[0][0].worldY = 4 * GamePanel.tileSize;

        GamePanel.GetInst().obj[0][1] = new KeyItemObject(GamePanel.GetInst().obj[GamePanel.GetInst().currentMapIndex][0]);
        GamePanel.GetInst().obj[0][1].worldX = 0 ;//* GamePanel.getInstGamePanel().tileSize;
        GamePanel.GetInst().obj[0][1].worldY = 0 ;//* GamePanel.getInstGamePanel().tileSize;

        GamePanel.GetInst().obj[0][2] = new ChestObj();
        GamePanel.GetInst().obj[0][2].worldX = 4 * GamePanel.tileSize;
        GamePanel.GetInst().obj[0][2].worldY = 6 * GamePanel.tileSize;

        GamePanel.GetInst().obj[0][3] = new MapPortal(1, 1,5);
        GamePanel.GetInst().obj[0][3].worldX = 10 * GamePanel.tileSize;
        GamePanel.GetInst().obj[0][3].worldY = 2 * GamePanel.tileSize;

        GamePanel.GetInst().obj[1][0] = new MapPortal(0, 9,2);
        GamePanel.GetInst().obj[1][0].worldX = 0 * GamePanel.tileSize;
        GamePanel.GetInst().obj[1][0].worldY = 5 * GamePanel.tileSize;

        GamePanel.GetInst().obj[0][4] = new HangingNPC();
        GamePanel.GetInst().obj[0][4].worldX = 2 * GamePanel.tileSize;
        GamePanel.GetInst().obj[0][4].worldY = 3 * GamePanel.tileSize;

        GamePanel.GetInst().obj[0][5] = new Morph_Idle();
        GamePanel.GetInst().obj[0][5].worldX = 3 * GamePanel.tileSize;
        GamePanel.GetInst().obj[0][5].worldY = 3 * GamePanel.tileSize;


    }
    public static void SimulateObject()
    {
        BaseObject Objects[] = GamePanel.GetInst().obj[GamePanel.GetInst().currentMapIndex];
        for(BaseObject object : Objects)
        {
            if (object == null) continue;
            object.Simulate(GamePanel.GetInst().player);
        }
    }
}
