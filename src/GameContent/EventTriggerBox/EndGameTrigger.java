package GameContent.EventTriggerBox;

import CoreGame.Data.Enums.GameState;
import CoreGame.EntityComponent.Entity;
import CoreGame.GamePanel;
import GameContent.MainPlayer;
import GameContent.Object.MasterObject.ObjectPendOnPlayer;
import GameContent.WidgetInstances.EndGameWidget;
import HelpDevGameTool.ImageUtility;

public class EndGameTrigger extends ObjectPendOnPlayer
{
    private final EndGameWidget endGameWidget;
    public EndGameTrigger(String titleEnding)
    {
        endGameWidget = new EndGameWidget(titleEnding);
        Sprite = ImageUtility.LoadImage("/Objects/hint_tile.png");
    }

    @Override
    public void OnBeginOverlapped(Entity otherEntity) {
        super.OnBeginOverlapped(otherEntity);
        if(otherEntity instanceof MainPlayer)
        {
            GamePanel.GetInst().gameState = GameState.Pause;
            endGameWidget.AddToViewport();
        }
    }
}
