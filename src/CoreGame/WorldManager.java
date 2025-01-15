package CoreGame;

import GameContent.EventTriggerBox.EndGameTrigger;
import GameContent.EventTriggerBox.MapPortal;
import GameContent.EventTriggerBox.TextTrigger;
import GameContent.NPC.Enemy.Orc1;
import GameContent.NPC.HangingNPC;
import GameContent.NPC.NonThreatening.Loop_NPC;
import GameContent.NPC.NonThreatening.Morph_Idle;
import GameContent.Object.*;
import CoreGame.EntityComponent.BaseObject;
import GameContent.Object.MasterObject.BreakableObject;
import GameContent.Object.MasterObject.KeyItemObject;
import GameContent.Object.MasterObject.ObjectPendOnPlayer;

import java.awt.*;

public class WorldManager
{
    public static void SetUpObject()
    {
        GamePanel.GetInst().obj[0][0] = new DoorObj();
        GamePanel.GetInst().obj[0][0].worldX = 16 * GamePanel.tileSize;
        GamePanel.GetInst().obj[0][0].worldY = 19 * GamePanel.tileSize;

        GamePanel.GetInst().obj[0][1] = new KeyItemObject(GamePanel.GetInst().obj[0][0]);
        GamePanel.GetInst().obj[0][1].worldX = 7 * GamePanel.tileSize;
        GamePanel.GetInst().obj[0][1].worldY = 34 * GamePanel.tileSize;

        GamePanel.GetInst().obj[0][2] = new BreakableObject("/Objects/BrokenTree/FallenTree.png",
                "/Objects/BrokenTree/FallenTreeBroken.png",20, 90, "/Sound/SFX/Object/wood_iron.wav", "/Sound/SFX/Object/wood_break.wav");
        GamePanel.GetInst().obj[0][2].worldX = 37 * GamePanel.tileSize;
        GamePanel.GetInst().obj[0][2].worldY = (int) (15.5 * GamePanel.tileSize);

        GamePanel.GetInst().obj[0][3] = new MapPortal(1, 12,5);
        GamePanel.GetInst().obj[0][3].worldX = 42 * GamePanel.tileSize;
        GamePanel.GetInst().obj[0][3].worldY = 16 * GamePanel.tileSize;

        GamePanel.GetInst().obj[1][0] = new MapPortal(0, 40,17);
        GamePanel.GetInst().obj[1][0].worldX = 11 * GamePanel.tileSize;
        GamePanel.GetInst().obj[1][0].worldY = 6 * GamePanel.tileSize;

        GamePanel.GetInst().obj[0][4] = new HangingNPC();
        GamePanel.GetInst().obj[0][4].worldX = 2 * GamePanel.tileSize;
        GamePanel.GetInst().obj[0][4].worldY = 3 * GamePanel.tileSize;

        GamePanel.GetInst().obj[0][5] = new Morph_Idle();
        GamePanel.GetInst().obj[0][5].worldX = 21 * GamePanel.tileSize;
        GamePanel.GetInst().obj[0][5].worldY = 18 * GamePanel.tileSize;

        GamePanel.GetInst().obj[0][6] = new BreakableObject("/Objects/BrokenTree/FallenTree.png",
                "/Objects/BrokenTree/FallenTreeBroken.png",20, "/Sound/SFX/Object/wood_iron.wav", "/Sound/SFX/Object/wood_break.wav");
        GamePanel.GetInst().obj[0][6].worldX = (int)(9.5 * GamePanel.tileSize);
        GamePanel.GetInst().obj[0][6].worldY = 27 * GamePanel.tileSize;

        GamePanel.GetInst().obj[0][7] = new BreakableObject("/Objects/BrokenTree/FallenTree.png",
                "/Objects/BrokenTree/FallenTreeBroken.png",20, "/Sound/SFX/Object/wood_iron.wav", "/Sound/SFX/Object/wood_break.wav");
        GamePanel.GetInst().obj[0][7].worldX = (int)(5.5 * GamePanel.tileSize);
        GamePanel.GetInst().obj[0][7].worldY = 27 * GamePanel.tileSize;

        GamePanel.GetInst().obj[0][8] = new ObjectPendOnPlayer("/AnimatedObjects/Tree/tree1_3",9);
        GamePanel.GetInst().obj[0][8].worldX = (int)(7.8 * GamePanel.tileSize);
        GamePanel.GetInst().obj[0][8].worldY = 8 * GamePanel.tileSize;

        GamePanel.GetInst().obj[0][9] = new ObjectPendOnPlayer("/AnimatedObjects/Tree/tree3_3",9);
        GamePanel.GetInst().obj[0][9].worldX = 42 * GamePanel.tileSize;
        GamePanel.GetInst().obj[0][9].worldY = 23 * GamePanel.tileSize;

        GamePanel.GetInst().obj[0][10] = new ObjectPendOnPlayer("/AnimatedObjects/Temple/TempleCube3",9);
        GamePanel.GetInst().obj[0][10].worldX = 9 * GamePanel.tileSize;
        GamePanel.GetInst().obj[0][10].worldY = 18 * GamePanel.tileSize;
        GamePanel.GetInst().obj[0][10].setCollisionArea(-64*3/2,-64*3/2,64*3,84*3);

        GamePanel.GetInst().obj[0][11] = new Loop_NPC();
        GamePanel.GetInst().obj[0][11].worldX = 6 * GamePanel.tileSize;
        GamePanel.GetInst().obj[0][11].worldY = 19 * GamePanel.tileSize;

        GamePanel.GetInst().obj[0][12] = new FootTrap();
        GamePanel.GetInst().obj[0][12].worldX = 13 * GamePanel.tileSize;
        GamePanel.GetInst().obj[0][12].worldY = 14 * GamePanel.tileSize;

        GamePanel.GetInst().obj[0][13] = new SwampArea();
        GamePanel.GetInst().obj[0][13].worldX = 12 * GamePanel.tileSize;
        GamePanel.GetInst().obj[0][13].worldY = 25 * GamePanel.tileSize;

        GamePanel.GetInst().obj[0][14] = new Orc1(false);
        GamePanel.GetInst().obj[0][14].worldX = 8 * GamePanel.tileSize;
        GamePanel.GetInst().obj[0][14].worldY = 13 * GamePanel.tileSize;

        GamePanel.GetInst().obj[0][15] = new Orc1(true);
        GamePanel.GetInst().obj[0][15].worldX = 9 * GamePanel.tileSize;
        GamePanel.GetInst().obj[0][15].worldY = 24 * GamePanel.tileSize;

        GamePanel.GetInst().obj[0][16] = new Orc1(true);
        GamePanel.GetInst().obj[0][16].worldX = 39 * GamePanel.tileSize;
        GamePanel.GetInst().obj[0][16].worldY = (int)(15.5 * GamePanel.tileSize);

        GamePanel.GetInst().obj[1][2] = new TempleMini();
        GamePanel.GetInst().obj[1][2].worldX = 22 * GamePanel.tileSize;
        GamePanel.GetInst().obj[1][2].worldY = 11 * GamePanel.tileSize;

        GamePanel.GetInst().obj[1][3] = new EyeChest();
        GamePanel.GetInst().obj[1][3].worldX = 42 * GamePanel.tileSize;
        GamePanel.GetInst().obj[1][3].worldY = 8 * GamePanel.tileSize;

        GamePanel.GetInst().obj[1][4] = new KeyItemObject(GamePanel.GetInst().obj[1][3]);
        GamePanel.GetInst().obj[1][4].worldX = 25 * GamePanel.tileSize;
        GamePanel.GetInst().obj[1][4].worldY = 41 * GamePanel.tileSize;

        GamePanel.GetInst().obj[1][5] = new PieceofPaper();
        GamePanel.GetInst().obj[1][5].worldX = 34 * GamePanel.tileSize;
        GamePanel.GetInst().obj[1][5].worldY = 33 * GamePanel.tileSize;

        GamePanel.GetInst().obj[1][6] = new BreakableObject("/Objects/BrokenTree/FallenTree.png",
                "/Objects/BrokenTree/FallenTreeBroken.png",20, "/Sound/SFX/Object/wood_iron.wav", "/Sound/SFX/Object/wood_break.wav");
        GamePanel.GetInst().obj[1][6].worldX = 41 * GamePanel.tileSize;
        GamePanel.GetInst().obj[1][6].worldY = 15 * GamePanel.tileSize;

        GamePanel.GetInst().obj[1][7] = new ObjectPendOnPlayer("/AnimatedObjects/Tree/tree2_3",9);
        GamePanel.GetInst().obj[1][7].worldX = 43 * GamePanel.tileSize;
        GamePanel.GetInst().obj[1][7].worldY = 5 * GamePanel.tileSize;

        GamePanel.GetInst().obj[1][8] = new ObjectPendOnPlayer("/AnimatedObjects/Tree/tree1_3",9);
        GamePanel.GetInst().obj[1][8].worldX = 35 * GamePanel.tileSize;
        GamePanel.GetInst().obj[1][8].worldY = 2 * GamePanel.tileSize;

        GamePanel.GetInst().obj[1][9] = new ObjectPendOnPlayer("/AnimatedObjects/Temple/Temple3Skulls3",9);
        GamePanel.GetInst().obj[1][9].worldX = 30 * GamePanel.tileSize;
        GamePanel.GetInst().obj[1][9].worldY = 4 * GamePanel.tileSize;

        GamePanel.GetInst().obj[1][10] = new ObjectPendOnPlayer("/AnimatedObjects/Tree/tree2_3",9);
        GamePanel.GetInst().obj[1][10].worldX = 21 * GamePanel.tileSize;
        GamePanel.GetInst().obj[1][10].worldY = GamePanel.tileSize;

        GamePanel.GetInst().obj[1][11] = new ObjectPendOnPlayer("/AnimatedObjects/Tree/tree3_3",9);
        GamePanel.GetInst().obj[1][11].worldX = 27 * GamePanel.tileSize;
        GamePanel.GetInst().obj[1][11].worldY = 5* GamePanel.tileSize;

        GamePanel.GetInst().obj[1][12] = new Orc1(false);
        GamePanel.GetInst().obj[1][12].worldX = 22 * GamePanel.tileSize;
        GamePanel.GetInst().obj[1][12].worldY = 6 * GamePanel.tileSize;

        GamePanel.GetInst().obj[1][13] = new Orc1(false);
        GamePanel.GetInst().obj[1][13].worldX = 30 * GamePanel.tileSize;
        GamePanel.GetInst().obj[1][13].worldY = 30 * GamePanel.tileSize;

        GamePanel.GetInst().obj[1][13] = new Orc1(false);
        GamePanel.GetInst().obj[1][13].worldX = 30 * GamePanel.tileSize;
        GamePanel.GetInst().obj[1][13].worldY = 30 * GamePanel.tileSize;

        GamePanel.GetInst().obj[1][14] = new ObjectPendOnPlayer("/Objects/Lich_shadow3.png");
        GamePanel.GetInst().obj[1][14].worldX = 15 * GamePanel.tileSize;
        GamePanel.GetInst().obj[1][14].worldY = 41 * GamePanel.tileSize;
        GamePanel.GetInst().obj[1][14].setCollisionArea(-128,-128,128*3,128*3);

        GamePanel.GetInst().obj[1][15] = new MapPortal(4,4,7, new Color(0xFF73451E, true));
        GamePanel.GetInst().obj[1][15].worldX = 8 * GamePanel.tileSize;
        GamePanel.GetInst().obj[1][15].worldY = 43 * GamePanel.tileSize;

        GamePanel.GetInst().obj[2][0] = new PortalObject(0,42, 14);
        GamePanel.GetInst().obj[2][0].worldX = 21 * GamePanel.tileSize;
        GamePanel.GetInst().obj[2][0].worldY = 16 * GamePanel.tileSize;

        GamePanel.GetInst().obj[2][1] = new TextTrigger("Hang da bi chan");
        GamePanel.GetInst().obj[2][1].worldX = 30 * GamePanel.tileSize;
        GamePanel.GetInst().obj[2][1].worldY = 16 * GamePanel.tileSize;

        GamePanel.GetInst().obj[2][2] = new TextTrigger("Le ra nguoi khong th o day","Bang cach nao co chu");
        GamePanel.GetInst().obj[2][2].worldX = 11 * GamePanel.tileSize;
        GamePanel.GetInst().obj[2][2].worldY = 42 * GamePanel.tileSize;

        GamePanel.GetInst().obj[2][3] = new MapPortal(3, 16,8);
        GamePanel.GetInst().obj[2][3].worldX = 23 * GamePanel.tileSize;
        GamePanel.GetInst().obj[2][3].worldY = 4 * GamePanel.tileSize;

        GamePanel.GetInst().obj[3][0] = new EndGameTrigger("Secret Ending");
        GamePanel.GetInst().obj[3][0].worldX = 42 * GamePanel.tileSize;
        GamePanel.GetInst().obj[3][0].worldY = 40 * GamePanel.tileSize;

        GamePanel.GetInst().obj[4][0] = new EndGameTrigger("Normal Ending");
        GamePanel.GetInst().obj[4][0].worldX = 45 * GamePanel.tileSize;
        GamePanel.GetInst().obj[4][0].worldY = 6 * GamePanel.tileSize;
    }
    public static void SimulateObject()
    {
        BaseObject Objects[] = GamePanel.GetInst().obj[GamePanel.GetInst().currentMapIndex];
        for(BaseObject object : Objects)
        {
            if (object == null) continue;
            object.Simulate(GamePanel.GetInst().getPlayer());
        }
    }
}
