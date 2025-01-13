package GameContent.Object;

import CoreGame.Data.Enums.Collision;
import CoreGame.GamePanel;
import CoreGame.SoundComponent.SoundUtility;
import CoreGame.WidgetComponent.HUD;
import GameContent.EventTriggerBox.MapPortal;
import GameContent.Object.MasterObject.ObjectNeedKeyItem;
import GameContent.WidgetInstances.NarrativeMessageWD;
import HelpDevGameTool.ImageUtility;

public class EyeChest extends ObjectNeedKeyItem
{
    private final NarrativeMessageWD Dialogue;
    private int discomfort = 0;
    public EyeChest(){

        super("/Objects/EyeChest/closedEyeChest.png","/Objects/EyeChest/OpenedEyeChest.png");
        CollisionMode = Collision.Block;
        Dialogue = new NarrativeMessageWD("Chuc ban may man lan sau leu leu");
    }

    @Override
    public void interact(){
        SoundUtility.playSound(1,false,"/Sound/SFX/Object/wood.wav");
        if(!bUnlock)
        {
            discomfort++;
            if(discomfort==9)
            {
                Dialogue.SetMessages("Do doc ac","Sao nguoi danh ta nhieu the","Xuong dia nguc di");
                HUD.AddWidget(Dialogue);
                Sprite = ImageUtility.LoadImage(ImgInteractionPath);
            }
            if(discomfort>=9)
            {
                if(Dialogue.IsOnScreen()) Dialogue.NextContent();
                if(!Dialogue.IsOnScreen())
                {
                    discomfort = 7;
                    Sprite = ImageUtility.LoadImage(ImgDefaultPath);
                    MapPortal.Telepot(2,8,25);
                }
            }
            return;
        }

        //super.interact();
        if (Dialogue.IsOnScreen()) {
            Dialogue.NextContent();
            GamePanel.GetInst().getPlayer().SetFreeToControl(true);
            Sprite = ImageUtility.LoadImage(ImgDefaultPath);
        } else {
            Dialogue.SetMessages("Chuc ban may man lan sau leu leu");
            HUD.AddWidget(Dialogue);
            GamePanel.GetInst().getPlayer().SetFreeToControl(false);
            SoundUtility.playSound(1,false,"/Sound/SFX/Object/open_wood.wav");
            super.interact();
        }
    }
}
