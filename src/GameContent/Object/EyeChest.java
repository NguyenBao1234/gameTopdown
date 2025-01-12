package GameContent.Object;

import CoreGame.Data.Enums.Collision;
import CoreGame.GamePanel;
import CoreGame.KeyHandlerComponent.KeyHandler;
import CoreGame.SoundComponent.SoundUtility;
import CoreGame.WidgetComponent.HUD;
import GameContent.NPC.NonThreatening.Morph_Idle;
import GameContent.Object.MasterObject.ObjectNeedKeyItem;
import GameContent.WidgetInstances.NarrativeMessageWD;
import HelpDevGameTool.ImageUtility;

import java.awt.event.KeyEvent;
import java.io.IOException;

public class EyeChest extends ObjectNeedKeyItem {
    public NarrativeMessageWD Dialogue;
    public EyeChest(){

        super("/Objects/EyeChest/closedEyeChest.png","/Objects/EyeChest/OpenedEyeChest.png");
        CollisionMode = Collision.Block;
        Dialogue = new NarrativeMessageWD("Chuc ban may man lan sau leu leu");
    }

    @Override
    public void interact(){
        SoundUtility.playSound(1,false,"/Sound/SFX/Object/wood.wav");
        if(!bUnlock) return;

        //super.interact();
        if (Dialogue.IsOnScreen()) {
            Dialogue.NextContent();
            GamePanel.GetInst().getPlayer().SetFreeToControl(true);
            Sprite = ImageUtility.LoadImage("/Objects/EyeChest/closedEyeChest.png");
        } else {
            HUD.AddWidget(Dialogue);
            GamePanel.GetInst().getPlayer().SetFreeToControl(false);
            SoundUtility.playSound(1,false,"/Sound/SFX/Object/open_wood.wav");
            super.interact();
        }
    }
}
