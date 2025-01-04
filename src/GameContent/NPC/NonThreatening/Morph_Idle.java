package GameContent.NPC.NonThreatening;

import CoreGame.GamePanel;
import CoreGame.WidgetComponent.HUD;
import GameContent.Object.MasterObject.InteractInterface;
import GameContent.Object.MasterObject.ObjectPendOnPlayer;
import GameContent.WidgetInstances.NarrativeMessageWD;
import HelpDevGameTool.ImageUtility;

import java.io.IOException;

public class Morph_Idle extends ObjectPendOnPlayer implements InteractInterface
{
    int interactCount = 0;
    private NarrativeMessageWD DialogueWD = new NarrativeMessageWD
            ("Chao ke lac loi toi nghiep",
                    "Khong co bat ky con nguoi nao ngoai kia dau",
                    "Chuc may man");
    private int speedDefaultPlayer;
    public Morph_Idle()
    {
        flipBook = ImageUtility.makeFlipBook("/Morph/Idle");
        fpsPerImage = 6;
        SpriteRenderSizeX = 17 * GamePanel.scale;
        SpriteRenderSizeY = 28 * GamePanel.scale;

    }

    @Override
    public void interact()
    {
        if(DialogueWD.IsOnScreen())
        {
            DialogueWD.NextContent();
            if(!DialogueWD.IsOnScreen())
            {
                interactCount ++;
                GamePanel.GetInst().player.SetFreeToControl(true);
            }
        }
        else
        {
            if(interactCount != 0) DialogueWD.SetMessages("...","Dung Lam phien ta");
            if(interactCount == 3) DialogueWD.SetMessages("(0_0)","Dien a",
                    "Nguoi co bi dien khong",
                    "Ta mac ke nha nguoi ");
            if(interactCount == 4)  {
                DialogueWD.SetMessages("game save!");
                try {
                    GamePanel.GetInst().saveLoad.save();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if(interactCount > 4) return;
            HUD.AddWidget(DialogueWD);
            GamePanel.GetInst().player.SetFreeToControl(false);
        }
    }
}
