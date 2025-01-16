package GameContent.Object;

import POWJ.GamePanel;
import POWJ.SoundComponent.SoundUtility;
import POWJ.WidgetComponent.HUD;
import GameContent.Object.MasterObject.InteractInterface;
import GameContent.Object.MasterObject.ObjectPendOnPlayer;
import GameContent.WidgetInstances.NarrativeMessageWD;

public class TempleMini extends ObjectPendOnPlayer implements InteractInterface {
    public NarrativeMessageWD Dialogue;
    public TempleMini(){
        super("/AnimatedObjects/Temple/TempleMini1",5);
        Dialogue = new NarrativeMessageWD(true,"da luu tien trinh!");
        SpriteRenderSizeX = 48 * GamePanel.scale;
        SpriteRenderSizeY = 80 * GamePanel.scale;
    }
    @Override
    public void interact()
    {
        if(Dialogue.IsOnScreen())
        {
            Dialogue.NextContent();
            GamePanel.GetInst().saveLoad.save();
            GamePanel.GetInst().getPlayer().SetFreeToControl(true);
        }
        else
        {
            HUD.AddWidget(Dialogue);
            SoundUtility.playSound(1,false,"/Sound/SFX/Object/confirmation-upward.wav");
            GamePanel.GetInst().getPlayer().SetFreeToControl(false);
        }
    }

}
