package GameContent.Object;

import CoreGame.GamePanel;
import CoreGame.WidgetComponent.HUD;
import GameContent.Object.MasterObject.InteractInterface;
import GameContent.Object.MasterObject.ObjectPendOnPlayer;
import GameContent.WidgetInstances.NarrativeMessageWD;
import HelpDevGameTool.ImageUtility;

public class PieceofPaper extends ObjectPendOnPlayer implements InteractInterface {
    boolean claim = false;
    public NarrativeMessageWD Dialogue;
    public PieceofPaper(){
        Sprite = ImageUtility.LoadImage("/PieceOfPaper/pieceofpaper.png");
        Dialogue = new NarrativeMessageWD(true,"The real exist did not exist " +
                "\n HAHAHAAA...." );

    }
    @Override
    public void interact(){
        {
            if(Sprite == null) {
                GamePanel.GetInst().player.SetFreeToControl(true);
                Dialogue.RemoveFromHUD();
            }
            if(!claim){
                if(Dialogue.IsOnScreen()){
                    Dialogue.NextContent();
                }
                else
                {
                    HUD.AddWidget(Dialogue);
                    claim = true;
                    Sprite = null;
                    GamePanel.GetInst().player.SetFreeToControl(false);
                }
            }
        }
    }
}
