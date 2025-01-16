package GameContent.Object;

import POWJ.GamePanel;
import POWJ.SoundComponent.SoundUtility;
import POWJ.WidgetComponent.HUD;
import GameContent.Object.MasterObject.InteractInterface;
import GameContent.Object.MasterObject.ObjectPendOnPlayer;
import GameContent.WidgetInstances.NarrativeMessageWD;
import HelpDevGameTool.ImageUtility;

public class PieceofPaper extends ObjectPendOnPlayer implements InteractInterface {
    boolean claim = false;
    public NarrativeMessageWD Dialogue;
    public PieceofPaper(){
        Sprite = ImageUtility.LoadImage("/Objects/pieceofpaper.png");
        Dialogue = new NarrativeMessageWD(true,"The real exist did not exist " +
                "\n HAHAHAAA...." );

    }
    @Override
    public void interact(){
        {

            if(Sprite == null) {
                GamePanel.GetInst().getPlayer().SetFreeToControl(true);
                Dialogue.RemoveFromHUD();
            }
            else SoundUtility.playSound(1,false,"/Sound/SFX/Object/pickup_note_03.wav");
            if(!claim){
                if(Dialogue.IsOnScreen()){
                    Dialogue.NextContent();
                }
                else
                {
                    HUD.AddWidget(Dialogue);
                    claim = true;
                    Sprite = null;
                    GamePanel.GetInst().getPlayer().SetFreeToControl(false);
                }
            }
        }
    }
}
