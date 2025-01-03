package CoreGame;

import GameContent.EventTriggerBox.MapPortal;
import GameContent.NPC.Enemy.Monster1;
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
        GamePanel.GetInst().obj[0][0].worldX = 14 * GamePanel.tileSize;
        GamePanel.GetInst().obj[0][0].worldY = 19 * GamePanel.tileSize;

        GamePanel.GetInst().obj[0][1] = new KeyItemObject(GamePanel.GetInst().obj[GamePanel.GetInst().currentMapIndex][0]);
        GamePanel.GetInst().obj[0][1].worldX = 5 * GamePanel.tileSize;
        GamePanel.GetInst().obj[0][1].worldY = 34 * GamePanel.tileSize;

        GamePanel.GetInst().obj[0][2] = new BreakableObject("/Objects/BrokenTree/FallenTree.png",
                "/Objects/BrokenTree/FallenTreeBroken.png",20, 90);
        GamePanel.GetInst().obj[0][2].worldX = 37 * GamePanel.tileSize;
        GamePanel.GetInst().obj[0][2].worldY = (int) (15.5 * GamePanel.tileSize);

        GamePanel.GetInst().obj[0][3] = new MapPortal(1, 12,6);
        GamePanel.GetInst().obj[0][3].worldX = 40 * GamePanel.tileSize;
        GamePanel.GetInst().obj[0][3].worldY = 16 * GamePanel.tileSize;

        GamePanel.GetInst().obj[1][0] = new MapPortal(0, 40,17);
        GamePanel.GetInst().obj[1][0].worldX = 11 * GamePanel.tileSize;
        GamePanel.GetInst().obj[1][0].worldY = 6 * GamePanel.tileSize;

        GamePanel.GetInst().obj[0][4] = new HangingNPC();
        GamePanel.GetInst().obj[0][4].worldX = 2 * GamePanel.tileSize;
        GamePanel.GetInst().obj[0][4].worldY = 3 * GamePanel.tileSize;

        GamePanel.GetInst().obj[0][5] = new Morph_Idle();
        GamePanel.GetInst().obj[0][5].worldX = 21 * GamePanel.tileSize;
        GamePanel.GetInst().obj[0][5].worldY = 15 * GamePanel.tileSize;

        GamePanel.GetInst().obj[0][6] = new BreakableObject("/Objects/BrokenTree/FallenTree.png",
                "/Objects/BrokenTree/FallenTreeBroken.png",20);
        GamePanel.GetInst().obj[0][6].worldX = (int)(7.5 * GamePanel.tileSize);
        GamePanel.GetInst().obj[0][6].worldY = 27 * GamePanel.tileSize;

        GamePanel.GetInst().obj[0][7] = new BreakableObject("/Objects/BrokenTree/FallenTree.png",
                "/Objects/BrokenTree/FallenTreeBroken.png",20);
        GamePanel.GetInst().obj[0][7].worldX = (int)(3.5 * GamePanel.tileSize);
        GamePanel.GetInst().obj[0][7].worldY = 27 * GamePanel.tileSize;

        GamePanel.GetInst().obj[0][8] = new Monster1();
        GamePanel.GetInst().obj[0][8].worldX = 4 * GamePanel.tileSize;
        GamePanel.GetInst().obj[0][8].worldY = 5 * GamePanel.tileSize;

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
