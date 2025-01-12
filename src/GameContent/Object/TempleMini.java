package GameContent.Object;

import CoreGame.Data.Enums.Collision;
import CoreGame.GamePanel;
import CoreGame.SoundComponent.SoundUtility;
import CoreGame.WidgetComponent.HUD;
import GameContent.Object.MasterObject.InteractInterface;
import GameContent.Object.MasterObject.ObjectPendOnPlayer;
import GameContent.WidgetInstances.NarrativeMessageWD;

import java.io.IOException;

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
        if(Dialogue.IsOnScreen()) {
            Dialogue.NextContent();
            try {
                GamePanel.GetInst().saveLoad.save();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else
        {
            HUD.AddWidget(Dialogue);
            SoundUtility.playSound(1,false,"/Sound/SFX/Object/confirmation-upward.wav");
        }
    }

}
